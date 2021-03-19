package ro.ase.proiectandroidlivescore;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface PronosticuriDao {

@Insert(onConflict = REPLACE)
    void insert(Pronosticuri pronosticuri);

@Query("SELECT * from pronosticuri")
List<Pronosticuri> selectall();


@Query("UPDATE pronosticuri SET verdict = :rezultat WHERE matchid = :ID ")
    void setRezultat(int rezultat,int ID);

@Query("DELETE from pronosticuri WHERE matchId = :id")
    void deletePronostic(int id);

}

