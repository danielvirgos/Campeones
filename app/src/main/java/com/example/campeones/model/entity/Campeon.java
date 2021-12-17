package com.example.campeones.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;


@Entity(tableName = "campeon",
        indices = {@Index(value = "name", unique = true)},
        foreignKeys = {@ForeignKey(entity = Posicion.class, parentColumns = "id", childColumns = "idposicion", onDelete = ForeignKey.CASCADE)})
public class Campeon implements Parcelable {

    //id autonumérico
    @PrimaryKey(autoGenerate = true)
    public long id;

    @NonNull
    @ColumnInfo(name = "name")
    public String name;

    @NonNull
    @ColumnInfo(name = "idposicion")
    public long idposicion;

    @ColumnInfo(name = "dificultad")
    public String dificultad;

    @ColumnInfo(name = "skins")
    public int skins;

    @ColumnInfo(name = "url")
    public String url;

    public Campeon() {
    }

    public Campeon(Parcel in) {
        id = in.readLong();
        name = in.readString();
        idposicion = in.readLong();
        dificultad = in.readString();
        skins = in.readInt();
        url = in.readString();
    }

    public static final Creator<Campeon> CREATOR = new Creator<Campeon>() {
        @Override
        public Campeon createFromParcel(Parcel in) {
            return new Campeon(in);
        }

        @Override
        public Campeon[] newArray(int size) {
            return new Campeon[size];
        }
    };

    public boolean isValid() {
        return !(name.isEmpty() || dificultad.isEmpty()  || skins <= 0 || url.isEmpty() || idposicion <= 0);
        //shortcut
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(name);
        dest.writeLong(idposicion);
        dest.writeString(dificultad);
        dest.writeInt(skins);
        dest.writeString(url);
    }
}
