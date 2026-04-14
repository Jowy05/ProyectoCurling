package com.urijoel.curling.dto;

public class StatsDTO {

    private long played;
    private long wins;
    private long losses;
    private double winRate;

    public StatsDTO() {
    }

    public StatsDTO(long played, long wins, long losses, double winRate) {
        this.played = played;
        this.wins = wins;
        this.losses = losses;
        this.winRate = winRate;
    }

    public long getPlayed() {
        return played;
    }

    public void setPlayed(long played) {
        this.played = played;
    }

    public long getWins() {
        return wins;
    }

    public void setWins(long wins) {
        this.wins = wins;
    }

    public long getLosses() {
        return losses;
    }

    public void setLosses(long losses) {
        this.losses = losses;
    }

    public double getWinRate() {
        return winRate;
    }

    public void setWinRate(double winRate) {
        this.winRate = winRate;
    }
}
