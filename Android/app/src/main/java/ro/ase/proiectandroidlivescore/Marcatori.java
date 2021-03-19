package ro.ase.proiectandroidlivescore;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity (tableName = "marcatori")
public class Marcatori {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name="matchId")
    private int matchId;

    @ColumnInfo(name="playerName")
    private String playername;




    @Ignore
    public Marcatori(){

    }



    public Marcatori(int matchId, String playername) {
        this.matchId = matchId;
        this.playername = playername;
    }


    @Ignore
    public Marcatori(int id, int matchId, String playername) {
        this.id = id;
        this.matchId = matchId;
        this.playername = playername;
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

    public String getPlayername() {
        return playername;
    }

    public void setPlayername(String playername) {
        this.playername = playername;
    }
}
