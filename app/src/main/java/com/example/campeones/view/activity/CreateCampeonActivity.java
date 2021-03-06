package com.example.campeones.view.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.campeones.R;
import com.example.campeones.model.entity.Campeon;
import com.example.campeones.model.entity.Posicion;
import com.example.campeones.viewmodel.CampeonViewModel;
import com.example.campeones.viewmodel.PosicionViewModel;

public class CreateCampeonActivity extends AppCompatActivity {

    private EditText etName, etDificultad, etSkins, etUrl;
    private Spinner spPosicion;
    private ImageView ivImage;
    private Campeon campeon;
    private CampeonViewModel campeonViewModel;
    private boolean firstTime = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_campeon);
        init();
    }

    public void init() {
        spPosicion = findViewById(R.id.spAddType);
        etName = findViewById(R.id.etAddName);
        etDificultad = findViewById(R.id.etAddDificultad);
        etSkins = findViewById(R.id.etAddSkins);
        etUrl = findViewById(R.id.etAddUrl);
        ivImage = findViewById(R.id.ivAddImage);

        etUrl.setOnFocusChangeListener((v, hasFocus) -> {
            String url;
            if(!hasFocus) {
                if(!etUrl.getText().toString().isEmpty()) {
                    url = etUrl.getText().toString();
                    Glide.with(this).load(url).into(ivImage);
                }
            }
        });
        getViewModel();
        defineAddListener();
    }

    private void defineAddListener() {
        Button btAdd = findViewById(R.id.btAdd);
        btAdd.setOnClickListener(v -> {
            Campeon campeon = getCampeon();
            if(campeon.isValid()) {
                addCampeon(campeon);
            } else {
                Toast.makeText(this, "Alguno de los campos esta vacio", Toast.LENGTH_LONG).show();
            }
            //1?? validar los datos
            //2?? si est?? bien
            //   3?? insertar
            //   4?? si he insertado bien
            //      5?? si es la primera vez
            //         6?? mostrar el alert
            //      7?? sino
            //         8?? limpiar + toast
            //   9?? sino
            //      10?? toast
            //11?? sino
            //   12?? toast
        });
    }

    private Campeon getCampeon() {
        String name = etName.getText().toString().trim();
        String dificultad = etDificultad.getText().toString().trim();
        String skins = etSkins.getText().toString().trim();
        String url = etUrl.getText().toString().trim();
        Posicion posicion = (Posicion) spPosicion.getSelectedItem();
        Campeon campeon = new Campeon();
        campeon.name = name;
        campeon.dificultad = dificultad;
        campeon.skins = Integer.parseInt(skins);
        campeon.url = url;
        campeon.idposicion = posicion.id;
        return campeon;
    }

    private void addCampeon(Campeon campeon) {
        campeonViewModel.insertCampeon(campeon);
    }

    private void getViewModel() {
        campeonViewModel = new ViewModelProvider(this).get(CampeonViewModel.class);

        /*cvm.getKalos();
        cvm.getKalosResult().observe(this, s -> {
            //aqui me llega la lista de pokemons
        });*/

        /*pvm.getInsertResult().observe(this, aLong -> {
            Log.v("xyzyx", "res: " + aLong);
        });*/
        campeonViewModel.getInsertResults().observe(this, list -> {
            Log.v("xyzyx", "res: " + list);
            if(list.get(0) > 0) {
                if(firstTime) {
                    firstTime = false;
                    alert();
                } else {
                    cleanFields();
                }
            } else {
                Toast.makeText(this, "10 no inserted", Toast.LENGTH_LONG).show();
            }
        });

        PosicionViewModel posicionViewModel = new ViewModelProvider(this).get(PosicionViewModel.class);
        posicionViewModel.getPosiciones().observe(this, types -> {
            Posicion posicion = new Posicion();
            posicion.id = 0;
            posicion.name = getString(R.string.default_posicion);
            types.add(0, posicion);
            ArrayAdapter<Posicion> adapter =
                    new ArrayAdapter<Posicion>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, types);
            spPosicion.setAdapter(adapter);
        });
    }

    private void alert() {
        AlertDialog.Builder builder  = new AlertDialog.Builder(this);
        builder.setTitle("Insertar mas?")
                .setMessage("El campeon se ha insertado correctamente, desea seguir agregando campeones?")
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setPositiveButton( android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        cleanFields();
                    }
                });
        builder.create().show();
    }

    private void cleanFields() {
        etUrl.setText("");
        etDificultad.setText("");
        etSkins.setText("");
        etName.setText("");
        spPosicion.setSelection(0);
        Toast.makeText(this, "8 inserted", Toast.LENGTH_LONG).show();
    }
}