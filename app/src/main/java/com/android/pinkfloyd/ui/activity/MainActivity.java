package com.android.pinkfloyd.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.pinkfloyd.R;
import com.android.pinkfloyd.RecyclerViewClickListener;
import com.android.pinkfloyd.ui.fragment.ListFragment;


public class MainActivity extends AppCompatActivity implements RecyclerViewClickListener {

    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (findViewById(R.id.container_view) != null) {
            mTwoPane = true;

            RecyclerView recyclerView = findViewById(R.id.recycleview);
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

            if (savedInstanceState == null) {

                ListFragment listFragment = new ListFragment();
                listFragment.setId(0);
                getSupportFragmentManager().beginTransaction().add(R.id.container_view, listFragment).commit();

            }


        } else {
            mTwoPane = false;
        }

    }

    @Override
    public void onClick(int id) {
        if (mTwoPane) {
            ListFragment listFragment = new ListFragment();
            listFragment.setId(id);
            getSupportFragmentManager().beginTransaction().replace(R.id.container_view, listFragment).commit();

        } else {
            Intent intent = new Intent(this, ListActivity.class);

            intent.putExtra("data", id);
            startActivity(intent);
        }
    }

}
