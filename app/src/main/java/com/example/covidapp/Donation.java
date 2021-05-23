package com.example.covidapp;

public class Donation {
    String fname,lname,address,bloodgrp,number,mailid;

    public Donation() {
    }

    public Donation(String fname, String lname, String address, String bloodgrp, String number, String mailid) {
        this.fname = fname;
        this.lname = lname;
        this.address = address;
        this.bloodgrp = bloodgrp;
        this.number = number;
        this.mailid = mailid;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBloodgrp() {
        return bloodgrp;
    }

    public void setBloodgrp(String bloodgrp) {
        this.bloodgrp = bloodgrp;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getMailid() {
        return mailid;
    }

    public void setMailid(String mailid) {
        this.mailid = mailid;
    }
}
