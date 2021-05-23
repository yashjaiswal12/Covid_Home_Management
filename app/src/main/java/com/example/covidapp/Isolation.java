package com.example.covidapp;

public class Isolation {
    String temp,bp,oxy,dayq,radio;

    public Isolation(){

    }

    public Isolation(String oxy, String bp, String temp, String dayq,String radio) {
        this.temp = temp;
        this.bp = bp;
        this.oxy = oxy;
        this.dayq = dayq;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getRadio() {
        return radio;
    }

    public void setRadio(String radio) {
        this.temp = radio;
    }

    public String getBp() {
        return bp;
    }

    public void setBp(String bp) {
        this.bp = bp;
    }

    public String getOxy() {
        return oxy;
    }

    public void setOxy(String oxy) {
        this.oxy = oxy;
    }

    public String getDayq() {
        return dayq;
    }

    public void setDayq(String dayq) {
        this.dayq = dayq;
    }
}
