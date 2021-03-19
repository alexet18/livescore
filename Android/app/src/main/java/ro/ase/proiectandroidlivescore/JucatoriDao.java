package ro.ase.proiectandroidlivescore;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface JucatoriDao {

    @Insert(onConflict = REPLACE)
    void insert(Jucatori j);


    @Query("SELECT * FROM jucatori")
    List<Jucatori> selectall();

    @Query("SELECT * FROM jucatori WHERE matchId = :id")
    List<Jucatori> select(int id);

    @Query("DELETE FROM jucatori WHERE matchId = :id")
    void delete(int id);
}
