package com.example.campeones.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.campeones.R;
import com.example.campeones.model.entity.Campeon;
import com.example.campeones.model.entity.CampeonPosicion;
import com.example.campeones.model.entity.Posicion;
import com.example.campeones.view.adapter.viewholder.CampeonViewHolder;

import java.util.List;

public class CampeonAdapter extends RecyclerView.Adapter<CampeonViewHolder>{
    private List<CampeonPosicion> campeonList;
    private Context context;

    public CampeonAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public CampeonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_campeon, parent, false);
        /*view.setOnClickListener(v -> {
            Log.v("xyzyx", "onclick create ");
            Pokemon p = (Pokemon) view.getTag();
            Log.v("xyzyx", p.name);
        });*/
        return new CampeonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CampeonViewHolder holder, int position) {
        CampeonPosicion campeonPosicion = campeonList.get(position);
        Campeon campeon = campeonPosicion.campeon;
        holder.campeon = campeon;
        Posicion posicion = campeonPosicion.posicion;
        holder.tvUrl.setText(campeon.url);
        holder.tvDificultad.setText(campeon.dificultad + " ");
        holder.tvSkins.setText(campeon.skins + " ");
        holder.tvPosicion.setText(posicion.name + " (" + posicion.id + ")");
        holder.tvName.setText(campeon.name);
        //Picasso.get().load(pokemon.url).into(holder.ivPokemon);
        Glide.with(context).load(campeon.url).into(holder.ivPokemon);
    }

    @Override
    public int getItemCount() {
        if(campeonList == null) {
            return 0;
        }
        return campeonList.size();
    }

    public void setCampeonList(List<CampeonPosicion> campeonList) {
        /*if(this.pokemonList == null) {
            this.pokemonList = pokemonList;
        }*/
        this.campeonList = campeonList;
        notifyDataSetChanged();
    }
}
