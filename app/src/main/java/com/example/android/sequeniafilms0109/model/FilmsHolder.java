package com.example.android.sequeniafilms0109.model;

import android.os.Build;

import com.example.android.sequeniafilms0109.utils.FilmNameSorter;

import java.util.ArrayList;
import java.util.Collections;

public class FilmsHolder {

    private static FilmsHolder instance = null;
    private ArrayList<Film> filmsList;

    private FilmsHolder(){
        filmsList = new ArrayList<>();
    }

    public static FilmsHolder getInstance(){
        if(instance == null){
            instance = new FilmsHolder();
        }

        return instance;
    }

    public void addFilm(Film film){
        filmsList.add(film);
    }

    public Film getFilmById(int id){
        return filmsList.get(id);
    }

    public ArrayList<Film> getAllFilms(){
        return filmsList;
    }

    public int getSize(){
        return filmsList.size();
    }

    public void sorting(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            filmsList.sort(new FilmNameSorter());
        }
    }

    public void clearAllData() {
        while(filmsList.size() > 0){
            filmsList.remove(0);
        }
    }
}
