package ro.ase.proiectandroidlivescore;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface MarcatoriDao {


    @Insert(onConflict = REPLACE)
    void insert(Marcatori m);

    @Query("SELECT * FROM marcatori")
    List<Marcatori> selectall();

    @Query("DELETE FROM marcatori WHERE matchId = :id AND playerName = :nume")
    void delete(int id, String nume);


    @Query("DELETE FROM marcatori WHERE matchId = :id")
    void deleteById(int id);
}
