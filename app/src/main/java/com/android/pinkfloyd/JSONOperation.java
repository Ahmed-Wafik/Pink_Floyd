package com.android.pinkfloyd;

import android.content.Context;

import com.android.pinkfloyd.model.Album;
import com.android.pinkfloyd.model.Item;
import com.android.pinkfloyd.model.Song;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class JSONOperation {
    private Context context;
    private Gson gson = null;

    public JSONOperation(Context context) {
        this.context = context;
        if (gson == null){
            gson = new Gson();
        }
    }

    private String getStringFromJSON() {

        InputStream inputStream = context.getResources().openRawResource(R.raw.pinkfloyd);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder builder = new StringBuilder();
        String data = "";
        try {
            while ((data = reader.readLine()) != null) {
                builder.append(data);
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return builder.toString();
    }

    public List<Album> getAlbums() {

        String stringFromJSON = getStringFromJSON();
        Item item = gson.fromJson(stringFromJSON, Item.class);
        return item.getAlbumList();
    }

    public List<Song> getSongsFromID(int id) {

        return getAlbums().get(id).getSongList();
    }
}