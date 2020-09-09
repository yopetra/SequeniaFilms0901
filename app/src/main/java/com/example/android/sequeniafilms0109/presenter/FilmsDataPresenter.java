package com.example.android.sequeniafilms0109.presenter;

import com.example.android.sequeniafilms0109.model.Film;

import java.util.List;

public interface FilmsDataPresenter {

    void filmsReady(List<Film> films);
}
