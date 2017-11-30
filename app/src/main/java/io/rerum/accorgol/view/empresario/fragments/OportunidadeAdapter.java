package io.rerum.accorgol.view.empresario.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import io.rerum.accorgol.R;
import io.rerum.accorgol.controller.FirebaseHelper;
import io.rerum.accorgol.model.Conquista;
import io.rerum.accorgol.model.Oportunidade;
import io.rerum.accorgol.view.empresario.EditOportunidade;
import io.rerum.accorgol.view.jogador.fragments.AddConquistaFragment;
import io.rerum.accorgol.view.jogador.fragments.CustomAdapter;

/**
 * Created by osman on 30/11/2017.
 */

public class OportunidadeAdapter extends RecyclerView.Adapter<OportunidadeAdapter.MyViewHolder> {

    private ArrayList<Oportunidade> dataSet;
    private Context ctx;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView alturaMin;
        TextView anoNascimento;
        TextView cidde;
        TextView estado;
        TextView peDominante;
        TextView posicao;
        ImageButton remove;
        ImageButton edit;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.alturaMin = (TextView) itemView.findViewById(R.id.alturaOportunidade);
            this.anoNascimento = (TextView) itemView.findViewById(R.id.anoNascimentoOportunidade);
            this.cidde = (TextView) itemView.findViewById(R.id.cidadeOportunidade);
            this.posicao = (TextView) itemView.findViewById(R.id.posicaOportunidade);
            this.estado = (TextView) itemView.findViewById(R.id.estadoOportunidade);
            this.peDominante = (TextView) itemView.findViewById(R.id.peDominanteOportunidade);
            this.remove = (ImageButton) itemView.findViewById(R.id.excluirOportunidade);
            this.edit = (ImageButton) itemView.findViewById(R.id.atualizarOportunidade);
        }
    }

    public OportunidadeAdapter(ArrayList<Oportunidade> data) {
        this.dataSet = data;
    }

    @Override
    public OportunidadeAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cards_oportunidades_fragment, parent, false);
        this.ctx = view.getContext();
        //view.setOnClickListener(MainActivity.myOnClickListener);

        OportunidadeAdapter.MyViewHolder myViewHolder = new OportunidadeAdapter.MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final OportunidadeAdapter.MyViewHolder holder, final int listPosition) {

        TextView alturaMin = holder.alturaMin;
        TextView anoNascimento = holder.anoNascimento;
        TextView cidde = holder.cidde;
        TextView estado = holder.estado;
        TextView peDominante = holder.peDominante;
        TextView posicao = holder.posicao;
        ImageButton remove = holder.remove;
        ImageButton edit = holder.edit;

        alturaMin.setText(dataSet.get(listPosition).getAlturaMin());
        anoNascimento.setText(dataSet.get(listPosition).getAnoNascimento());
        cidde.setText(dataSet.get(listPosition).getCidade());
        estado.setText(dataSet.get(listPosition).getEstado());
        peDominante.setText(dataSet.get(listPosition).getPeDominante());
        posicao.setText(dataSet.get(listPosition).getPosicao());
        final String id = dataSet.get(listPosition).getIdOportunidade();

        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                final DatabaseReference myRef = database.getReference("/Empresarios/"+new FirebaseHelper().recuperar(view.getContext(), String.valueOf(R.string.id_Usuario)) + "/Oportunidades/"+id);
                //removo pelo id da oportunidade
                Log.e("USUARIOOO VER O ID = ", String.valueOf(myRef));
                myRef.setValue(null);
                new FirebaseHelper().armazenar(ctx, "", String.valueOf(R.string.tem_oportunidade));

            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ctx, EditOportunidade.class);
                intent.putExtra("idOportunidade", id);
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
