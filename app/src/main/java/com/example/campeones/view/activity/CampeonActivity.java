package com.example.campeones.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.campeones.R;
import com.example.campeones.model.entity.CampeonPosicion;
import com.example.campeones.view.adapter.CampeonAdapter;
import com.example.campeones.viewmodel.CampeonViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class CampeonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campeon);
        init();
    }

    public void init() {
        //lista de pokemon
        RecyclerView rvPokemon = findViewById(R.id.rvCampeon);
        rvPokemon.setLayoutManager(new LinearLayoutManager(this));

        CampeonViewModel pvm = new ViewModelProvider(this).get(CampeonViewModel.class);
        CampeonAdapter campeonAdapter = new CampeonAdapter(this);

        rvPokemon.setAdapter(campeonAdapter);

        //LiveData<List<Pokemon>> listaPokemon = pvm.getPokemons();
        LiveData<List<CampeonPosicion>> listaCampeonPosicion = pvm.getAllCampeon();
        listaCampeonPosicion.observe(this, campeones -> {
            campeonAdapter.setCampeonList(campeones);
        });

        FloatingActionButton fab = findViewById(R.id.fabAddCampeon);
        fab.setOnClickListener(v -> {
            Intent intent = new Intent(this, CreateCampeonActivity.class);
            startActivity(intent);
        });
        /*listaPokemon.observe(this, pokemons -> {
            pokemonAdapter.setPokemonList(pokemons);
        });*/
        //RecyclerView rv = findViewById(R.id.tvUrl);
        /*
        TypeViewModel tvm = new ViewModelProvider(this).get(TypeViewModel.class);
        CommonViewModel cvm = new ViewModelProvider(this).get(CommonViewModel.class);
        Type type = new Type();
        type.name = "Aguas";
        Pokemon pokemon = new Pokemon();
        pokemon.height = 2.2;
        pokemon.weight = 2;
        pokemon.name = "Squirtle";
        pokemon.url = "https://assets.pokemon.com/assets/cms2/img/pokedex/full/007.png";
        pvm.insertPokemon(pokemon, type); //hebra*/
    }
}