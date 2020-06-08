package com.goodwiil.goodwillvoice.model;

public class User {

    private int year;
    private char gender;
    private String career;
    private String city;

    public User(int year, char gender, String career, String city) {
        this.year = year;
        this.gender = gender;
        this.career = career;
        this.city = city;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }




}
