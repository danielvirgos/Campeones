package com.example.campeones.model.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.campeones.model.entity.Campeon;
import com.example.campeones.model.entity.Posicion;

@Database(entities = {Campeon.class, Posicion.class}, version = 1, exportSchema = false)
public abstract class CampeonDataBase extends RoomDatabase {

    public abstract CampeonDao getDao();

    private static volatile CampeonDataBase INSTANCE;

    /* versión simplificada */
    public static CampeonDataBase getDatabase(Context context) {
        if(INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), CampeonDataBase.class, "campeon").build();
        }
        return INSTANCE;
    }

}
