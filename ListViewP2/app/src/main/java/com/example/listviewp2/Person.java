package com.example.listviewp2;

public class Person {
    private int avt;
    private String name;
    private String phone;
    private String describe;

    public Person(int avt, String name, String phone, String describe) {
        this.avt = avt;
        this.name = name;
        this.phone = phone;
        this.describe = describe;
    }

    public int getAvt() {
        return avt;
    }

    public void setAvt(int avt) {
        this.avt = avt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

}
