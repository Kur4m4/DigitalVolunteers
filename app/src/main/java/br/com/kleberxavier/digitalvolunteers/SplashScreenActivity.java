package br.com.kleberxavier.digitalvolunteers;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import br.com.kleberxavier.digitalvolunteers.api.APIUtils;
import br.com.kleberxavier.digitalvolunteers.api.DefaultUserAPI;
import br.com.kleberxavier.digitalvolunteers.dao.DefaultUserDAO;
import br.com.kleberxavier.digitalvolunteers.model.DefaultUser;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashScreenActivity extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGTH = 3500;

    private DefaultUserAPI defaultUserAPI;
    private DefaultUserDAO defaultUserDAO;

    @BindView(R.id.ivLogoSplash)
    ImageView ivLogoSplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        ButterKnife.bind(this);

        defaultUserDAO = new DefaultUserDAO(this);

        carregar();
    }

    private void carregar() {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.animacao_splash);
        anim.reset();

        if (ivLogoSplash != null) {
            ivLogoSplash.clearAnimation();
            ivLogoSplash.startAnimation(anim);
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                defaultUserAPI = APIUtils.getDefaultUserDataAPIService();

                defaultUserAPI.getDefaultUser().enqueue(new Callback<DefaultUser>() {
                    @Override
                    public void onResponse(Call<DefaultUser> call, Response<DefaultUser> response) {
                        Intent intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);

                        DefaultUser defaultUser = response.body();
                        intent.putExtra("USUARIO", defaultUser.getUsuario());
                        intent.putExtra("SENHA", defaultUser.getSenha());

                        if (!defaultUserDAO.defaultUserExists(defaultUser)) {
                            defaultUserDAO.add(defaultUser);
                        }

                        startActivity(intent);
                        SplashScreenActivity.this.finish();
                    }

                    @Override
                    public void onFailure(Call<DefaultUser> call, Throwable t) {

                    }
                });
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
