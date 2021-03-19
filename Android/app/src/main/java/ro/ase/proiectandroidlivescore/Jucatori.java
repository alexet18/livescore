package ro.ase.proiectandroidlivescore;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "jucatori")
public class Jucatori {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "matchId")
    private int matchId;

    @ColumnInfo(name = "name")
    private String nume;


    public Jucatori(int id, String nume)
    {
        this.matchId = id;
        this.nume = nume;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }
}
