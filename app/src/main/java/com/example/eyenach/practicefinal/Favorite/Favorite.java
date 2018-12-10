package com.example.eyenach.practicefinal.Favorite;

import android.content.ContentValues;

public class Favorite {
    private ContentValues row;

    private String author;
    private String title;
    private String data;

    public Favorite(String author, String title, String data) {
        row = new ContentValues();
        row.put("author", author);
        row.put("title", title);
        row.put("data", data);

        this.author = author;
        this.title = title;
        this.data = data;
    }

    ContentValues getRow(){
        return row;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
