package com.example.javalearningtrainticket;

import java.io.Serializable;

public class Ticket implements Serializable {
    private String user;
    private String mesto;
    private String timeOut;
    private String timeIn;
    private String cost;

    //Конструктор
    public Ticket(String user, String mesto, String timeOut, String timeIn, String cost){
        this.user = user;
        this.mesto = mesto;
        this.timeOut = timeOut;
        this.timeIn = timeIn;
        this.cost = cost;
    }

    //Геттеры и Сеттеры
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getMesto() {
        return mesto;
    }

    public void setMesto(String mesto) {
        this.mesto = mesto;
    }

    public String getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(String timeOut) {
        this.timeOut = timeOut;
    }

    public String getTimeIn() {
        return timeIn;
    }

    public void setTimeIn(String timeIn) {
        this.timeIn = timeIn;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

}
