package io.rerum.accorgol.view.empresario;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import io.rerum.accorgol.R;
import io.rerum.accorgol.controller.FirebaseHelper;
import io.rerum.accorgol.model.Conquista;
import io.rerum.accorgol.model.Jogador;
import io.rerum.accorgol.view.VisualizarPerfilJogador;
import io.rerum.accorgol.view.jogador.fragments.AddConquistaFragment;

/**
 * Created by osman on 29/11/2017.
 */

public class BuscarTalentosAdapter extends RecyclerView.Adapter<BuscarTalentosAdapter.MyViewHolder> {

    private ArrayList<Jogador> dataSet;
    private Context ctx;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView nomeJogador;
        TextView ano;
        TextView posicao;
        TextView pe;
        ImageView foto;
        private CardView cardView;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.nomeJogador = (TextView) itemView.findViewById(R.id.cardNome);
            this.posicao = (TextView) itemView.findViewById(R.id.cardPosicao);
            this.ano = (TextView) itemView.findViewById(R.id.cardAno);
            this.foto = (ImageView) itemView.findViewById(R.id.cardFotoPerfil);
            this.pe = (TextView) itemView.findViewById(R.id.cardPeDominante);
            this.cardView = (CardView) itemView.findViewById(R.id.card_perfil_resultado);

        }
    }

    public BuscarTalentosAdapter(ArrayList<Jogador> data) {
        this.dataSet = data;
    }

    @Override
    public BuscarTalentosAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_buscar, parent, false);
        this.ctx = view.getContext();
        //view.setOnClickListener(MainActivity.myOnClickListener);
        BuscarTalentosAdapter.MyViewHolder myViewHolder = new BuscarTalentosAdapter.MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final BuscarTalentosAdapter.MyViewHolder holder, final int listPosition) {

        final TextView nome = holder.nomeJogador;
        TextView posicao = holder.posicao;
        TextView ano = holder.ano;
        TextView pe = holder.pe;
        ImageView imageView = holder.foto;
        CardView card = holder.cardView;

        nome.setText(dataSet.get(listPosition).getNomeCompleto());
        posicao.setText(dataSet.get(listPosition).getPosicao());
        ano.setText(dataSet.get(listPosition).getAnoNascimento());
        pe.setText(dataSet.get(listPosition).getPeDominante());
        Picasso.with(ctx).load(dataSet.get(listPosition).getFotoJogador()).into(imageView);
        final String id = dataSet.get(listPosition).getIdJogador();

        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ctx, "Fmz meu!!! "+id, Toast.LENGTH_SHORT).show();
                new FirebaseHelper().armazenar(view.getContext(), id, String.valueOf(R.string.id_jogador_que_sera_visualizado));
                Intent intent = new Intent(ctx, VisualizarPerfilJogador.class);
                ctx.startActivity(intent);
            }
        });
//        imageView.setImageURI(Uri.parse(dataSet.get(listPosition).getURIFoto()));
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}