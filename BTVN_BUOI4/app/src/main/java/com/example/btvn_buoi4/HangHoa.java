package com.example.btvn_buoi4;

import java.util.List;

public class HangHoa {
    private int id;
    private int avt;
    private String name;
    private String describe;
    private int price;
    private int soLuong;

    public HangHoa() {
    }

    public HangHoa(int id, int avt, String name, String describe, int price, int sl) {
        this.id = id;
        this.avt = avt;
        this.name = name;
        this.describe = describe;
        this.price = price;
        this.soLuong = sl;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public HangHoa getHangHoaById(int id, List<HangHoa> list){
        for(HangHoa a : list)
            if(a.id == id)
                return a;
        return null;
    }
}
