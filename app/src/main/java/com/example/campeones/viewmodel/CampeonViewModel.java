package com.example.campeones.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.campeones.model.entity.Campeon;
import com.example.campeones.model.entity.CampeonPosicion;
import com.example.campeones.model.entity.Posicion;
import com.example.campeones.model.repository.CampeonRepository;

import java.util.List;

public class CampeonViewModel extends AndroidViewModel {

    private CampeonRepository repository;

    public CampeonViewModel(@NonNull Application application) {
        super(application);
        repository = new CampeonRepository(application);
    }

    public void insertCampeon(Campeon... campeones) {
        repository.insertCampeon(campeones);
    }

    public void updateCampeon(Campeon... campeones) {
        repository.updateCampeon(campeones);
    }

    public void deletePokemons(Campeon... campeones) {
        repository.deleteCampeones(campeones);
    }

    public LiveData<List<Campeon>> getCampeones() {
        return repository.getCampeones();
    }

    public LiveData<Campeon> getCampeon(long id) {
        return repository.getCampeon(id);
    }

    public void insertPokemon(Campeon campeon, Posicion posicion) {
        repository.insertCampeon(campeon, posicion);
    }

    public LiveData<List<CampeonPosicion>> getAllCampeon() {
        return repository.getAllCampeon();
    }

    public MutableLiveData<Long> getInsertResult() {
        return repository.getInsertResult();
    }

    public MutableLiveData<List<Long>> getInsertResults() {
        return repository.getInsertResults();
    }

    /*public void getKalos() {
        repository.getKalos();
    }

    public MutableLiveData<String> getKalosResult() {
        return repository.getKalosResult();
    }

    public String getUrl(String pokemonName) {
        return repository.getUrl(pokemonName);
    }*/
}
