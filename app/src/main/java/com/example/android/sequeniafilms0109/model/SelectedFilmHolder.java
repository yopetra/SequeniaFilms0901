package com.example.android.sequeniafilms0109.model;

public class SelectedFilmHolder {

    private static Film mFilm;

    private SelectedFilmHolder(){}

    public static Film getmFilm() {
        return mFilm;
    }

    public static void setmFilm(Film mFilm) {
        SelectedFilmHolder.mFilm = mFilm;
    }
}
