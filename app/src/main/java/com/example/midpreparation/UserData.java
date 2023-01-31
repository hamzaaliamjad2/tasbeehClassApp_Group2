package com.example.midpreparation;

import java.time.LocalDate;

public class UserData {
    private String date;
    private int countDarood;
    private int countKalma;
    private int countAstaghfar;

    public UserData(String date,int countDarood, int countKalma, int countAstaghfar) {
        this.date=date;
        this.countDarood = countDarood;
        this.countKalma = countKalma;
        this.countAstaghfar = countAstaghfar;
    }

    public String getDate() {
        return date.toString();
    }

    public void setDate(LocalDate date) {
        this.date = date.toString();
    }


    public int getCountDarood() {
        return countDarood;
    }

    public void setCountDarood(int countDarood) {
        this.countDarood = countDarood;
    }

    public int getCountKalma() {
        return countKalma;
    }

    public void setCountKalma(int countKalma) {
        this.countKalma = countKalma;
    }

    public int getCountAstaghfar() {
        return countAstaghfar;
    }

    public void setCountAstaghfar(int countAstaghfar) {
        this.countAstaghfar = countAstaghfar;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "date=" + date +
                ", countDarood=" + countDarood +
                ", countKalma=" + countKalma +
                ", countAstaghfar=" + countAstaghfar +
                '}';
    }

}