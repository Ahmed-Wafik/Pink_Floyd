package com.android.pinkfloyd;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.pinkfloyd.model.*;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    private static final String TAG = ListActivity.class.getSimpleName();
    private ListView listView;
    private List<Song> songsFromID;
    private int id;
    private List<String> list = new ArrayList<>();
    private JSONOperation operation;

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "On Start");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences.edit();


        listView = findViewById(R.id.listview);
        operation = new JSONOperation(this);

        if (getIntent().getExtras() != null) {
            id = getIntent().getExtras().getInt("data");
            edit.putInt("id", id);
            edit.apply();
        } else {
            id = preferences.getInt("id", 0);
        }
        songsFromID = operation.getSongsFromID(id);
        for (Song song : songsFromID) {
            list.add(song.getName());
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int postion, long l) {
                Intent intent = new Intent(ListActivity.this, DetailActivity.class);

                Bundle bundle = new Bundle();
                String name = songsFromID.get(postion).getName();
                String lyrics = songsFromID.get(postion).getLyrics();

                bundle.putString("name", name);
                bundle.putString("lyrics", lyrics);

                intent.putExtra("song", bundle);
                startActivity(intent);

            }
        });
    }
}
