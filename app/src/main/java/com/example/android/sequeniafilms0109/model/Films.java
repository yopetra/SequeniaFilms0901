package com.example.android.sequeniafilms0109.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Films {
    @SerializedName("films")
    @Expose
    private List<Film> films = null;

    public List<Film> getFilms() {
        return films;
    }

//    public void setFilms(List<Film> films) {
//        this.films = films;
//    }
}
