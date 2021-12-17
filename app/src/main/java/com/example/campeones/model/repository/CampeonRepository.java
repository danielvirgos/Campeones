package com.example.campeones.model.repository;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.campeones.model.entity.Campeon;
import com.example.campeones.model.entity.CampeonPosicion;
import com.example.campeones.model.entity.Posicion;
import com.example.campeones.model.room.CampeonDao;
import com.example.campeones.model.room.CampeonDataBase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

public class CampeonRepository {

    private static final String INIT = "init";

    private HashMap<String, String> campeonMap;
    private CampeonDao dao;
    private LiveData<List<CampeonPosicion>> allCampeon;
    private LiveData<List<Campeon>> liveCampeones;
    private LiveData<List<Posicion>> livePosiciones;
    private LiveData<Campeon> liveCampeon;
    private LiveData<Posicion> livePosicion;
    private MutableLiveData<Long> liveInsertResult;
    private MutableLiveData<List<Long>> liveInsertResults;
    //private MutableLiveData<String> liveGetKalosResult;
    private SharedPreferences preferences;
    //private CampeonList CampeonList;

    public CampeonRepository(Context context) {
        CampeonDataBase db = CampeonDataBase.getDatabase(context);
        //campeonList = new CampeonList();
        campeonMap = new HashMap<>();
        dao = db.getDao();
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        liveInsertResult = new MutableLiveData<>();
        liveInsertResults = new MutableLiveData<>();
        //liveGetKalosResult = new MutableLiveData<>();
        if(!getInit()) {
            posicionesPreload();
            setInit();
        }
    }

    public void insertCampeon(Campeon campeon, Posicion posicion) {
        Runnable r = () -> {
            campeon.idposicion = insertPosicionGetId(posicion);
            if(campeon.idposicion > 0) {
                dao.insertCampeon(campeon);
            }
        };
        new Thread(r).start();
    }

    public MutableLiveData<Long> getInsertResult() {
        return liveInsertResult;
    }

    /*public MutableLiveData<String> getKalosResult() {
        return liveGetKalosResult;
    }*/

    public MutableLiveData<List<Long>> getInsertResults() {
        return liveInsertResults;
    }

    private long insertPosicionGetId(Posicion posicion) {
        List<Long> ids = dao.insertPosicion(posicion);
        if(ids.get(0) < 1) {
            return dao.getIdPosicion(posicion.name);
        } else {
            return ids.get(0);
        }
    }

    public void insertCampeon(Campeon... campeones) {
        Runnable r = () -> {
            List<Long> resultList = dao.insertCampeon(campeones);
            liveInsertResult.postValue(resultList.get(0));
            liveInsertResults.postValue(resultList);
        };
        new Thread(r).start();
    }

    public void insertPosicion(Posicion... posiciones) {
        Runnable r = () -> {
            dao.insertPosicion(posiciones);
        };
        new Thread(r).start();
    }

    public void updateCampeon(Campeon... campeones) {
        Runnable r = () -> {
            dao.updatePokemon(campeones);
        };
        new Thread(r).start();
    }

    public void updatePosicion(Posicion... posiciones) {
        Runnable r = () -> {
            dao.updatePosicion(posiciones);
        };
        new Thread(r).start();
    }

    public void deleteCampeones(Campeon... campeones) {
        Runnable r = () -> {
            dao.deleteCampeones(campeones);
        };
        new Thread(r).start();
    }

    public void deletePosicion(Posicion... posiciones) {
        Runnable r = () -> {
            dao.deletePosicion(posiciones);
        };
        new Thread(r).start();
    }

    public LiveData<List<Campeon>> getCampeones() {
        if(liveCampeones == null) {
            liveCampeones = dao.getCampeones();
        }
        return liveCampeones;
    }

    public LiveData<List<Posicion>> getPosiciones() {
        if(livePosiciones == null) {
            livePosiciones = dao.getPosiciones();
        }
        return livePosiciones;
    }

    public LiveData<Campeon> getCampeon(long id) {
        if(liveCampeon == null) {
            liveCampeon = dao.getCampeon(id);
        }
        return liveCampeon;
    }

    public LiveData<Posicion> getPosicion(long id) {
        if(livePosicion == null) {
            livePosicion = dao.getPosicion(id);
        }
        return livePosicion;
    }

    public LiveData<List<CampeonPosicion>> getAllCampeon() {
        if(allCampeon == null) {
            allCampeon = dao.getAllCampeon();
        }
        return allCampeon;
    }

    public void posicionesPreload() {
        String[] posicionNames = new String[] {"topline", "jungla", "midlane", "adc", "support"};
        Posicion[] posiciones = new Posicion[posicionNames.length];
        Posicion posicion;
        int cont = 0;
        for (String s: posicionNames) {
            posicion = new Posicion();
            posicion.name = s;
            posiciones[cont] = posicion;
            cont++;
        }
        insertPosicion(posiciones);
    }

    public void setInit() {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(INIT, true);
        editor.commit();
    }

    public boolean getInit() {
        return preferences.getBoolean(INIT, false);
    }

    /*public void getKalos() {
        Runnable r = () -> {
            String result = pokemonList.getKalos("https://www.pokemon.com/es/api/pokedex/kalos");
            populateHashMap(result);
            liveGetKalosResult.postValue(result); // -> observer
            Log.v("xyzyx", result);
        };
        new Thread(r).start();
    }

    public String getUrl(String pokemonName) {
        String url = campeonMap.get(pokemonName.toLowerCase());
        if(url == null) {
            url = "https://www.latercera.com/resizer/CBmGvvFEACkiaL4Diatt7wyUqlM=/900x600/smart/arc-anglerfish-arc2-prod-copesa.s3.amazonaws.com/public/LUOOHUM2OVEEXG7ZTRSNI6XWLY.png";
        }
        return url;
    }

    private void populateHashMap(String string) {
        String name, url;
        try {
            JSONArray jsonArray = new JSONArray(string);
            JSONObject jsonObject;
            for (int i = 0, size = jsonArray.length(); i < size; i++) {
                jsonObject = jsonArray.getJSONObject(i);
                name = jsonObject.getString("name").toLowerCase();
                url = jsonObject.getString("ThumbnailImage");
                campeonMap.put(name, url);
            }
        } catch (JSONException e) {
        }
    }*/
}
