package com.example.campeones.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.campeones.model.entity.Posicion;
import com.example.campeones.model.repository.CampeonRepository;

import java.util.List;

public class PosicionViewModel extends AndroidViewModel {

    private CampeonRepository repository;

    public PosicionViewModel(@NonNull Application application) {
        super(application);
        repository = new CampeonRepository(application);
    }

    public void insertPosicion(Posicion... posiciones) {
        repository.insertPosicion(posiciones);
    }

    public void updatePosicion(Posicion... posiciones) {
        repository.updatePosicion(posiciones);
    }

    public void deletePosicion(Posicion... posiciones) {
        repository.deletePosicion(posiciones);
    }

    public LiveData<List<Posicion>> getPosiciones() {
        return repository.getPosiciones();
    }

    public LiveData<Posicion> getPosicion(long id) {
        return repository.getPosicion(id);
    }
}
