package com.goodwiil.goodwillvoice.model;

public class IncomingNumber {

    private String number;
    private String name;

    public IncomingNumber(){}

    public IncomingNumber(String number, String name) {
        this.number = number;
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }




}