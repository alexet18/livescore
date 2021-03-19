package ro.ase.proiectandroidlivescore;

import java.io.Serializable;

public class User implements Serializable {
    String username;
    String password;
    String email;
    String uid;
    String team;

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.team = "N/A";
    }



    public User()
    {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }
}
