package ro.ase.proiectandroidlivescore;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "pronosticuri")
public class Pronosticuri implements Serializable {

@PrimaryKey(autoGenerate = true)
    private int id;

@ColumnInfo(name = "matchId")
        int matchId;

@ColumnInfo(name = "sport")
    String sport;

@ColumnInfo(name = "team1")
    String team1;

@ColumnInfo(name = "team2")
    String team2;

@ColumnInfo(name = "winner")
    int winner;

@ColumnInfo(name = "verdict")
int verdict;


@Ignore



public Pronosticuri(int id,String sport, String team1, String team2, int winner)
{
    this.matchId = id;
    this.sport = sport;
    this.team1 = team1;
    this.team2 = team2;
    this.winner = winner;
    this.verdict = -1;
}

public Pronosticuri()
{}


    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public String getTeam1() {
        return team1;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    public int getWinner() {
        return winner;
    }

    public void setWinner(int winner) {
        this.winner = winner;
    }

    public int getVerdict() {
        return verdict;
    }

    public void setVerdict(int verdict) {
        this.verdict = verdict;
    }
}
