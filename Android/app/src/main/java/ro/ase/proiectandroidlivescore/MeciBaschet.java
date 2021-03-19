package ro.ase.proiectandroidlivescore;

import java.util.ArrayList;

public class MeciBaschet extends Meci{

    private String time;
    private int id,cod_img1,cod_img2,scor1,scor2;
    ArrayList<String> playersTeam1,playersTeam2;


    public MeciBaschet(int id,String team1, String team2, String type, String league, String date, String time, int cod_img1, int cod_img2) {
        super(team1, team2, type, league, date);
        this.time = time;
        this.cod_img1 = cod_img1;
        this.cod_img2 = cod_img2;
        this.id = id;

        playersTeam1 = new ArrayList<String>();
        playersTeam2 = new ArrayList<String>();
    }

    public MeciBaschet(int id,String team1, String team2, String type, String league, String date, String time, int cod_img1, int cod_img2,int scor1,int scor2) {
        super(team1, team2, type, league, date);
        this.time = time;
        this.cod_img1 = cod_img1;
        this.cod_img2 = cod_img2;
        this.id = id;
        this.scor1 = scor1;
        this.scor2 = scor2;

        playersTeam1 = new ArrayList<String>();
        playersTeam2 = new ArrayList<String>();
    }

    public MeciBaschet()
    {
        super();
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getCod_img1() {
        return cod_img1;
    }

    public void setCod_img1(int cod_img1) {
        this.cod_img1 = cod_img1;
    }

    public int getCod_img2() {
        return cod_img2;
    }

    public void setCod_img2(int cod_img2) {
        this.cod_img2 = cod_img2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<String> getPlayersTeam1() {
        return playersTeam1;
    }

    public void setPlayersTeam1(ArrayList<String> playersTeam1) {
        this.playersTeam1 = playersTeam1;
    }

    public ArrayList<String> getPlayersTeam2() {
        return playersTeam2;
    }

    public void setPlayersTeam2(ArrayList<String> playersTeam2) {
        this.playersTeam2 = playersTeam2;
    }

    public int getScor1() {
        return scor1;
    }

    public void setScor1(int scor1) {
        this.scor1 = scor1;
    }

    public int getScor2() {
        return scor2;
    }

    public void setScor2(int scor2) {
        this.scor2 = scor2;
    }

    public void addPlayers(int team, String player)
    {
        if(team==1)
        {
            playersTeam1.add(player);
        }
        else
        {
            playersTeam2.add(player);
        }
    }


}
