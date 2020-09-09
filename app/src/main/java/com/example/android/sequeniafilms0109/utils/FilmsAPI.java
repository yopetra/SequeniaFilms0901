package com.example.android.sequeniafilms0109.utils;

import com.example.android.sequeniafilms0109.model.Films;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FilmsAPI {

    @GET("films.json")
    Call<Films> getAllFilms();
}
