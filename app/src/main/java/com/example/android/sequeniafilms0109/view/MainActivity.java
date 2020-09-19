package com.example.android.sequeniafilms0109.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.android.sequeniafilms0109.R;
import com.example.android.sequeniafilms0109.utils.NetworkUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(NetworkUtils.isInternetAvailable()){
            if(savedInstanceState == null){
                GenresListFragment filmsListFragment = new GenresListFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .add(R.id.fl_frame_container, filmsListFragment)
                        .commit();
            }
        }else{
            TextView noInternetTextView = findViewById(R.id.tv_no_internet);
            noInternetTextView.setVisibility(View.VISIBLE);
        }

//
    }
}