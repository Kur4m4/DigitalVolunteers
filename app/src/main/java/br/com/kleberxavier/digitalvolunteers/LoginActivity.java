package br.com.kleberxavier.digitalvolunteers;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import br.com.kleberxavier.digitalvolunteers.dao.DefaultUserDAO;
import br.com.kleberxavier.digitalvolunteers.model.DefaultUser;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    private CallbackManager callbackManager;
    private SharedPreferences sharedPreferences;

    @BindView(R.id.tilUsername)
    TextInputLayout tilUsername;

    @BindView(R.id.tilPassword)
    TextInputLayout tilPassword;

    @BindView(R.id.btFacebookLogin)
    LoginButton btFacebookLogin;

    @BindView(R.id.cbManterConectado)
    CheckBox cbManterConectado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedPreferences = getPreferences(MODE_PRIVATE);
        if (sharedPreferences.getBoolean("isConnected", false))
            startRecyclerViewActivity();

        ButterKnife.bind(this);

        if (getIntent() != null) {
            Intent intent = getIntent();
            tilUsername.getEditText().setText(intent.getStringExtra("USUARIO"));
            tilPassword.getEditText().setText(intent.getStringExtra("SENHA"));
        }

        callbackManager = CallbackManager.Factory.create();
        btFacebookLogin.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                startRecyclerViewActivity();
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @OnClick(R.id.btLogar)
    public void logar() {
        DefaultUser defaultUser = new DefaultUserDAO(this).getDefaultUser();
        String usuario = tilUsername.getEditText().getText().toString();
        String senha = tilPassword.getEditText().getText().toString();

        if (usuario.equals(defaultUser.getUsuario()) && senha.equals(defaultUser.getSenha())) {
            if (cbManterConectado.isChecked())
                manterConectado();
            startRecyclerViewActivity();
        } else {
            Toast.makeText(this, R.string.invalid_user, Toast.LENGTH_SHORT).show();
        }
    }

    private void manterConectado() {
        SharedPreferences.Editor e = sharedPreferences.edit();
        e.putBoolean("isConnected", true);
        e.apply();
    }

    public void startRecyclerViewActivity() {
        Intent intent = new Intent(this, NavigationViewActivity.class);
        startActivity(intent);
        finish();
    }
}
