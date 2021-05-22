package com.example.tablayout_demo;

public class City {
//            "Type": 1,
//            "SolrID": "/tien-giang",
//            "ID": 1,
//            "Title": "Tiền Giang",
    private int type;
    private String solrID;
    private int id;
    private String title;

    public City(int type, String solrID, int id, String title) {
        this.type = type;
        this.solrID = solrID;
        this.id = id;
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getSolrID() {
        return solrID;
    }

    public void setSolrID(String solrID) {
        this.solrID = solrID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "city{" +
                "type=" + type +
                ", solrID='" + solrID + '\'' +
                ", id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
