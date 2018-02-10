package br.com.kleberxavier.digitalvolunteers.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import br.com.kleberxavier.digitalvolunteers.R;
import br.com.kleberxavier.digitalvolunteers.dao.ONGDAO;
import br.com.kleberxavier.digitalvolunteers.model.ONG;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Kai on 09/02/2018.
 */

public class RegisterNGOFragment extends Fragment {

    private ONGDAO ongDAO;

    @BindView(R.id.tilNome)
    TextInputLayout tilNome;

    @BindView(R.id.tilEndereco)
    TextInputLayout tilEndereco;

    @BindView(R.id.tilTelefone)
    TextInputLayout tilTelefone;

    @BindView(R.id.tilEmail)
    TextInputLayout tilEmail;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ongDAO = new ONGDAO(getContext());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register_ngo, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle(R.string.nav_register_ngo);
    }

    @OnClick(R.id.btCadastrar)
    public void cadastrarONG() {
        String nome = tilNome.getEditText().getText().toString();
        String endereco = tilEndereco.getEditText().getText().toString();
        String telefone = tilEndereco.getEditText().getText().toString();
        String email = tilEmail.getEditText().getText().toString();

        if (nome.isEmpty())
            Toast.makeText(getContext(), R.string.invalid_ong, Toast.LENGTH_SHORT);
        else {
            ONG ong = new ONG();
            ong.setNome(nome);
            ong.setEndereco(endereco);
            ong.setTelefone(telefone);
            ong.setEmail(email);
            ongDAO.addONG(ong);
        }
    }
}
