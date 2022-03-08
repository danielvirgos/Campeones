package com.example.campeones.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.campeones.R;
import com.example.campeones.model.entity.Campeon;
import com.example.campeones.viewmodel.CampeonViewModel;

public class EditCampeonActivity extends AppCompatActivity {

    private EditText etName, etDificultad, etSkins, etUrl;
    private Spinner spPosicion;
    private ImageView ivImage;
    private Campeon campeon;
    private CampeonViewModel campeonViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_campeon);

        init();
    }

    public void init() {
        etName = findViewById(R.id.etEditName);
        etDificultad = findViewById(R.id.etEditDificultad);
        etSkins = findViewById(R.id.etEditSkins);
        etUrl = findViewById(R.id.etEditUrl);
        spPosicion = findViewById(R.id.spEditType);
        ivImage = findViewById(R.id.ivEditImage);

        campeonViewModel = new ViewModelProvider(this).get(CampeonViewModel.class);
        int id = 0;
        campeon = campeonViewModel.get(id);
    }

    public void muestroDatos()  {

    }
}