package com.example.android.sequeniafilms0109.utils;

import com.example.android.sequeniafilms0109.model.Film;

import java.util.Comparator;

public class FilmNameSorter implements Comparator<Film> {
    @Override
    public int compare(Film film1, Film film2) {
        return film1.getLocalizedName().compareToIgnoreCase(film2.getLocalizedName());
    }
}
