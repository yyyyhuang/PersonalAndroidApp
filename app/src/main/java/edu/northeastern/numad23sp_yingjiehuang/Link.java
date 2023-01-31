package edu.northeastern.numad23sp_yingjiehuang;

public class Link {
    private String name;
    private String url;
    private int id;

    public Link(String name, String url, int id) {
        this.name = name;
        this.url = url;
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public String getUrl() {
        return this.url;
    }

    public int getId() { return this.id; }
}
