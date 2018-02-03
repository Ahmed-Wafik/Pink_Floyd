package com.android.pinkfloyd.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Ahmed Wafik Mohamed on 1/30/2018.
 */

public class Item {

    @SerializedName("total_results")
    private int total_results;

    @SerializedName("results")
    private List<Album> albumList;

    public Item(int total_results, List<Album> albumList) {
        this.total_results = total_results;
        this.albumList = albumList;
    }

    public int getTotal_results() {
        return total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }

    public List<Album> getAlbumList() {
        return albumList;
    }

    public void setAlbumList(List<Album> albumList) {
        this.albumList = albumList;
    }
}