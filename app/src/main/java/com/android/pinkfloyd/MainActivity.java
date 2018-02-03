package com.android.pinkfloyd;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.android.pinkfloyd.adapter.AlbumsAdapter;
import com.android.pinkfloyd.model.Album;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    String data = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycleview);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        recyclerView.setHasFixedSize(true);
        new FetchJSONData().execute();

    }

    private class FetchJSONData extends AsyncTask<Void, Void, List<Album>> {
        private JSONOperation operation;
        AlbumsAdapter albumsAdapter;


        @Override
        protected List<Album> doInBackground(Void... voids) {

            operation = new JSONOperation(MainActivity.this);
            return operation.getAlbums();
        }

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected void onPostExecute(List<Album> albumList) {
            albumsAdapter = new AlbumsAdapter(MainActivity.this, albumList);
            recyclerView.setAdapter(albumsAdapter);
        }
    }

}
