package com.example.android.sequeniafilms0109.model;

public class CoupleOfFilms {

    private Film mFilmA, mFilmB;

    public CoupleOfFilms(Film filmA, Film filmB){
        this.mFilmA = filmA;
        this.mFilmB = filmB;
    }

    public Film getmFilmA(){
        return mFilmA;
    }

    public Film getmFilmB(){
        return mFilmB;
    }
}
