package com.example.android.sequeniafilms0109.utils;

import com.example.android.sequeniafilms0109.view.GenresListFragment;

public class FilmSelectorHolder {

    private FilmSelectorHolder(){}

    private static GenresListFragment.FilmSelector filmSelector;

    public static GenresListFragment.FilmSelector getFilmSelector() {
        return filmSelector;
    }

    public static void setFilmSelector(GenresListFragment.FilmSelector filmSelector) {
        FilmSelectorHolder.filmSelector = filmSelector;
    }
}
