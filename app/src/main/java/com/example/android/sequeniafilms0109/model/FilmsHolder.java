package com.example.android.sequeniafilms0109.model;

import java.util.ArrayList;

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
}
