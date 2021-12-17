package com.example.campeones.model.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.campeones.model.entity.Campeon;
import com.example.campeones.model.entity.CampeonPosicion;
import com.example.campeones.model.entity.Posicion;

import java.util.List;

@Dao
public interface CampeonDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    List<Long> insertCampeon(Campeon... campeones);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Long insert(Campeon campeon);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    List<Long> insertPosicion(Posicion... posicion);

    @Update
    int updatePokemon(Campeon... campeones);

    @Update
    int updatePosicion(Posicion... posicion);

    @Delete
    int deleteCampeones(Campeon... campeones);

    @Delete
    int deletePosicion(Posicion... posicion);

    @Query("delete from campeonposicion")
    int deleteAllPosiciones();

    @Query("delete from campeon")
    int deleteAllCampeon();

    @Query("select * from campeon order by name asc")
    LiveData<List<Campeon>> getCampeones();

    @Query("select p.*, pt.id type_id, pt.name type_name from campeon p join campeonposicion pt on p.idposicion = pt.id order by name asc")
    LiveData<List<CampeonPosicion>> getAllCampeon();

    @Query("select * from campeonposicion order by name asc")
    LiveData<List<Posicion>> getPosiciones();

    @Query("select * from campeon where id = :id")
    LiveData<Campeon> getCampeon(long id);

    @Query("select * from campeonposicion where id = :id")
    LiveData<Posicion> getPosicion(long id);

    @Query("select id from campeonposicion where name = :name")
    long getIdPosicion(String name);

    @Query("select * from campeonposicion where name = :name")
    Posicion getPosicion(String name);
}
