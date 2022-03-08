package com.example.campeones.view.adapter.viewholder;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.campeones.R;
import com.example.campeones.model.entity.Campeon;
import com.example.campeones.view.activity.EditCampeonActivity;

public class CampeonViewHolder extends RecyclerView.ViewHolder{

    public TextView tvName, tvPosicion, tvDificultad, tvSkins, tvUrl;
    public ImageView ivPokemon;
    public Campeon campeon;

    public CampeonViewHolder(@NonNull View itemView) {
        super(itemView);
        tvName = itemView.findViewById(R.id.tvName);
        tvPosicion = itemView.findViewById(R.id.tvType);
        tvDificultad = itemView.findViewById(R.id.tvDificultad);
        tvSkins = itemView.findViewById(R.id.tvSkins);
        tvUrl = itemView.findViewById(R.id.tvUrl);
        ivPokemon = itemView.findViewById(R.id.imgCampeon);
        itemView.setOnClickListener(v -> {
            Log.v("xyzyx", "onclick" + campeon.name);
            Intent intent = new Intent(itemView.getContext(), EditCampeonActivity.class);
            intent.putExtra("pokemon", campeon);
            itemView.getContext().startActivity(intent);
        });
    }
}
