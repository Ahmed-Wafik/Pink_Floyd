package com.android.pinkfloyd;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ArrowKeyMovementMethod;
import android.widget.TextView;

import com.android.pinkfloyd.model.Song;

public class DetailActivity extends AppCompatActivity {

    private TextView song_nameTV, lyricsTV;
    private Bundle bundle;
    private String name, lyrics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        song_nameTV = findViewById(R.id.song_name);
        lyricsTV = findViewById(R.id.lyrics);

        if (getIntent().getExtras() != null) {
            bundle= getIntent().getExtras().getBundle("song");

            if (bundle != null) {
                name = bundle.getString("name");
                lyrics = bundle.getString("lyrics");
            }
        }
        song_nameTV.setText(name);
        lyricsTV.setText(lyrics);
//        lyricsTV.setMovementMethod(new ArrowKeyMovementMethod());

    }
}
