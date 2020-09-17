package com.example.android.sequeniafilms0109.utils;

import com.example.android.sequeniafilms0109.model.Genre;

import java.util.Comparator;

public class GenreNameSorter implements Comparator<Genre> {
    @Override
    public int compare(Genre genre1, Genre genre2) {
        return genre1.getName().compareToIgnoreCase(genre2.getName());
    }
}
