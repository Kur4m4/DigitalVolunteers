package br.com.kleberxavier.digitalvolunteers.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import br.com.kleberxavier.digitalvolunteers.R;
import br.com.kleberxavier.digitalvolunteers.adapter.ONGAdapter;
import br.com.kleberxavier.digitalvolunteers.dao.ONGDAO;
import br.com.kleberxavier.digitalvolunteers.model.ONG;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Kai on 09/02/2018.
 */

public class NGORecyclerViewFragment extends Fragment {

    private ONGAdapter ongAdapter;
    private ONGDAO ongDAO;

    @BindView(R.id.rvONGs)
    RecyclerView rvONGs;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ngo_recycler_view, container, false);

        ButterKnife.bind(getActivity(), view);

        ongDAO = new ONGDAO(getContext());

        ongAdapter = new ONGAdapter(new ArrayList<ONG>());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvONGs.setLayoutManager(layoutManager);
        rvONGs.setAdapter(ongAdapter);

        CarregaDados();

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle(R.string.nav_ngos);
    }

    private void CarregaDados() {
        ongAdapter.update(ongDAO.getONGs());
    }
}
