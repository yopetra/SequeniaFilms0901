package com.example.android.sequeniafilms0109.utils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FilmsService {

    private Retrofit retrofit = null;

    public FilmsAPI getAPI(){
        String BASE_URL = "https://s3-eu-west-1.amazonaws.com/sequeniatesttask/";

        if(retrofit == null){
            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit.create(FilmsAPI.class);
    }
}
