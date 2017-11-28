package io.rerum.accorgol.view.jogador.fragments;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import io.rerum.accorgol.R;
import io.rerum.accorgol.model.Conquista;
import io.rerum.accorgol.view.MainActivity;

/**
 * Created by osman on 27/11/2017.
 */

public class CustomAdapter  extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private ArrayList<Conquista> dataSet;
    private Context ctx;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName;
        TextView textViewVersion;
        TextView ano;
        ImageView imageViewIcon;
        BootstrapButton bootstrapButton;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.textViewName = (TextView) itemView.findViewById(R.id.textViewName);
            this.textViewVersion = (TextView) itemView.findViewById(R.id.textViewVersion);
            this.ano = (TextView) itemView.findViewById(R.id.textViewAno);
            this.imageViewIcon = (ImageView) itemView.findViewById(R.id.imageView);
            this.bootstrapButton = (BootstrapButton) itemView.findViewById(R.id.addMaisConquista);
        }
    }

    public CustomAdapter(ArrayList<Conquista> data) {
        this.dataSet = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cards_conquistas_fragment, parent, false);
        this.ctx = view.getContext();
        //view.setOnClickListener(MainActivity.myOnClickListener);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        TextView textViewName = holder.textViewName;
        TextView textViewVersion = holder.textViewVersion;
        TextView ano = holder.ano;
        ImageView imageView = holder.imageViewIcon;

        textViewName.setText(dataSet.get(listPosition).getNomeClube());
        textViewVersion.setText(dataSet.get(listPosition).getNomeConquista());
        ano.setText(dataSet.get(listPosition).getAno());
        Picasso.with(ctx).load(dataSet.get(listPosition).getURIFoto()).into(imageView);

        holder.bootstrapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                android.app.FragmentManager manager = ((Activity) ctx).getFragmentManager();
                AddConquistaFragment addConquistaFragment = new AddConquistaFragment();
                manager.beginTransaction().replace(R.id.contentContainer, addConquistaFragment, addConquistaFragment.getTag()).commit();
            }
        });
//        imageView.setImageURI(Uri.parse(dataSet.get(listPosition).getURIFoto()));
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
