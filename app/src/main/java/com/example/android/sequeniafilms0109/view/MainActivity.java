package com.example.android.sequeniafilms0109.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.android.sequeniafilms0109.R;
import com.example.android.sequeniafilms0109.model.Film;
import com.example.android.sequeniafilms0109.utils.NetworkUtils;

public class MainActivity extends AppCompatActivity implements GenresListFragment.FilmSelector {

    private final int FILMS_LIST = 1;
    private final int FILM_DETAIL = 2;
    private int fragmentId = FILMS_LIST;
    private Film selectedFilm;
    private String LOG_TAG = getClass().getSimpleName();
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();

        if(NetworkUtils.isInternetAvailable()){
            if(savedInstanceState == null){

                switch (fragmentId){
                    case FILMS_LIST:
                        showListFragment();
                        break;
                    case FILM_DETAIL:
                        showDetailFragment();
                        break;
                }
            }
        }else{
            TextView noInternetTextView = findViewById(R.id.tv_no_internet);
            noInternetTextView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void getFilm(Film film) {
        selectedFilm = film;
        fragmentId = FILM_DETAIL;

        showDetailFragment();
    }


    @Override
    public void onBackPressed() {
        if(fragmentId == FILMS_LIST){
            super.onBackPressed();
        }

        fragmentId = FILMS_LIST;
        showListFragment();
    }

    private void showDetailFragment(){
        DetailFragment detailFragment = new DetailFragment();
        detailFragment.setFilm(selectedFilm);

        fragmentManager.beginTransaction()
                .replace(R.id.fl_frame_container, detailFragment)
                .commit();
    }

    private void showListFragment(){
        GenresListFragment filmsListFragment = new GenresListFragment();
        filmsListFragment.setFilmSelector(this);

        fragmentManager.beginTransaction()
                .replace(R.id.fl_frame_container, filmsListFragment)
                .commit();
    }
}