package com.goodwiil.goodwillvoice.model;


public class User {

    private String year;
    private String gender;
    private String career;
    private String city;

    public User() { }

    public User(String year, String gender, String career, String city) {
        this.year = year;
        this.gender = gender;
        this.career = career;
        this.city = city;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
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
