package com.siliconaroma.modipl.Data;

/**
 * Created by sheikb on 3/30/2017.
 */

public class MatchData {

    public  int _ID           = -1;

    public int get_ID() {
        return _ID;
    }

    public void set_ID(int _ID) {
        this._ID = _ID;
    }

    public String getDATE() {
        return DATE;
    }

    public void setDATE(String DATE) {
        this.DATE = DATE;
    }

    public String getTIME() {
        return TIME;
    }

    public void setTIME(String TIME) {
        this.TIME = TIME;
    }

    public String getTEAM1() {
        return TEAM1;
    }

    public void setTEAM1(String TEAM1) {
        this.TEAM1 = TEAM1;
    }

    public String getTEAM2() {
        return TEAM2;
    }

    public void setTEAM2(String TEAM2) {
        this.TEAM2 = TEAM2;
    }

    public String getVENUE() {
        return VENUE;
    }

    public void setVENUE(String VENUE) {
        this.VENUE = VENUE;
    }

    public String getWINNING_TEAM() {
        return WINNING_TEAM;
    }

    public void setWINNING_TEAM(String WINNING_TEAM) {
        this.WINNING_TEAM = WINNING_TEAM;
    }

    public String DATE          = "date";
    public String TIME      = "time";
    public String TEAM1      = "team_1";
    public String TEAM2   = "team_2";
    public String VENUE  = "venue";
    public String WINNING_TEAM = "winning_team";


    public MatchData(int _ID, String DATE, String TIME, String TEAM1, String TEAM2, String VENUE, String WINNING_TEAM) {
        this._ID = _ID;
        this.DATE = DATE;
        this.TIME = TIME;
        this.TEAM1 = TEAM1;
        this.TEAM2 = TEAM2;
        this.VENUE = VENUE;
        this.WINNING_TEAM = WINNING_TEAM;
    }
}
