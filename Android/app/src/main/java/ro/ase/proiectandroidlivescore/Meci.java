package ro.ase.proiectandroidlivescore;

public class Meci {
    String team1,team2,type,league,date;


    public Meci(String team1, String team2, String type, String league, String date) {

        this.team1 = team1;
        this.team2 = team2;
        this.type = type;
        this.league = league;
        this.date = date;

    }


    public Meci(){

    }

    public String getTeam1() {
        return team1;
    }

    public String getTeam2() {
        return team2;
    }

    public String getType() {
        return type;
    }

    public String getLeague() {
        return league;
    }

    public String getDate() {
        return date;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setLeague(String league) {
        this.league = league;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
