package io.rerum.accorgol.view.jogador;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import io.rerum.accorgol.R;
import io.rerum.accorgol.model.Jogador;

/**
 * Created by osman on 11/11/2017.
 */

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.JogadorHolder> {

    public static class JogadorHolder extends RecyclerView.ViewHolder{

        CardView cv;
        ImageView fotoJogador;
        TextView nomeJogador;
        TextView idadeJogador;

        JogadorHolder(View itemView) {
            super(itemView);
//            cv = (CardView)itemView.findViewById(R.id.cardView);
//            fotoJogador = (ImageView)itemView.findViewById(R.id.fotoJogadorCard);
//            nomeJogador = (TextView)itemView.findViewById(R.id.nomeJogadorcard);
//            idadeJogador = (TextView)itemView.findViewById(R.id.idadeJogadorCard);
        }
    }

    List<Jogador> jogadores;
    RVAdapter(List<Jogador> jogadores){
        this.jogadores = jogadores;
    }

    @Override
    public JogadorHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.resultadobuscajogador, viewGroup, false);
        JogadorHolder pvh = new JogadorHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(JogadorHolder holder, int position) {
//        holder.fotoJogador.setImageResource(jogadores.get(position).getFotoJogador());
        holder.idadeJogador.setText(jogadores.get(position).getAnoNascimento());
        holder.nomeJogador.setText(jogadores.get(position).getNomeCompleto());
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return jogadores.size();
    }
}
