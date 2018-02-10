package br.com.kleberxavier.digitalvolunteers.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.kleberxavier.digitalvolunteers.R;
import br.com.kleberxavier.digitalvolunteers.model.ONG;

/**
 * Created by Kai on 09/02/2018.
 */

public class ONGAdapter extends RecyclerView.Adapter<ONGAdapter.ONGViewHolder> {

    private List<ONG> ongs;

    public ONGAdapter(List<ONG> ongs) {
        this.ongs = ongs;
    }

    @Override
    public ONGViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View meuLayout = inflater.inflate(R.layout.ong_row, parent, false);

        return new ONGViewHolder(meuLayout);
    }

    @Override
    public void onBindViewHolder(ONGViewHolder holder, int position) {
        holder.tvNome.setText(ongs.get(position).getNome());
        holder.tvEndereco.setText(ongs.get(position).getEndereco());
        holder.tvTelefone.setText(ongs.get(position).getTelefone());
        holder.tvEmail.setText(ongs.get(position).getEmail());
    }

    @Override
    public int getItemCount() {
        return ongs.size();
    }

    public class ONGViewHolder extends RecyclerView.ViewHolder {

        public TextView tvNome;
        public TextView tvEndereco;
        public TextView tvTelefone;
        public TextView tvEmail;

        public ONGViewHolder(View itemView) {
            super(itemView);

            tvNome = (TextView) itemView.findViewById(R.id.tvNome);
            tvEndereco = (TextView) itemView.findViewById(R.id.tvEndereco);
            tvTelefone = (TextView) itemView.findViewById(R.id.tvTelefone);
            tvEmail = (TextView) itemView.findViewById(R.id.tvEmail);
        }
    }

    public void update(List<ONG> ongs) {
        this.ongs = ongs;
        notifyDataSetChanged();
    }
}
