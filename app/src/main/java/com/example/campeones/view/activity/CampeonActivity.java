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
        RecyclerView rvCampeon = findViewById(R.id.rvCampeon);
        rvCampeon.setLayoutManager(new LinearLayoutManager(this));

        CampeonViewModel cvm = new ViewModelProvider(this).get(CampeonViewModel.class);
        CampeonAdapter campeonAdapter = new CampeonAdapter(this);

        rvCampeon.setAdapter(campeonAdapter);

        LiveData<List<CampeonPosicion>> listaCampeonPosicion = cvm.getAllCampeon();
        listaCampeonPosicion.observe(this, campeones -> {
            campeonAdapter.setCampeonList(campeones);
        });

        FloatingActionButton fab = findViewById(R.id.fabAddCampeon);
        fab.setOnClickListener(v -> {
            Intent intent = new Intent(this, CreateCampeonActivity.class);
            startActivity(intent);
        });
    }
}