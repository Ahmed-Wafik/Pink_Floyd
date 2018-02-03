package com.android.pinkfloyd.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Ahmed Wafik Mohamed on 1/30/2018.
 */

public class Album implements Parcelable{

    @SerializedName("id")
    private int id;

    @SerializedName("album_name")
    private String album_name;

    @SerializedName("poster_path")
    private String poster_path;

    @SerializedName("release_date")
    private int release_date;

    @SerializedName("album_songs")
    private List<Song> songList;


    public Album(int id, String album_name, String poster_path, int release_date, List<Song> songList) {
        this.id = id;
        this.album_name = album_name;
        this.poster_path = poster_path;
        this.release_date = release_date;
        this.songList = songList;
    }

    protected Album(Parcel in) {
        id = in.readInt();
        album_name = in.readString();
        poster_path = in.readString();
        release_date = in.readInt();
        songList = in.createTypedArrayList(Song.CREATOR);
    }

    public static final Creator<Album> CREATOR = new Creator<Album>() {
        @Override
        public Album createFromParcel(Parcel in) {
            return new Album(in);
        }

        @Override
        public Album[] newArray(int size) {
            return new Album[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAlbum_name() {
        return album_name;
    }

    public void setAlbum_name(String album_name) {
        this.album_name = album_name;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public int getRelease_date() {
        return release_date;
    }

    public void setRelease_date(int release_date) {
        this.release_date = release_date;
    }

    public List<Song> getSongList() {
        return songList;
    }

    public void setSongList(List<Song> songList) {
        this.songList = songList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(album_name);
        parcel.writeString(poster_path);
        parcel.writeInt(release_date);
        parcel.writeTypedList(songList);
    }
}
