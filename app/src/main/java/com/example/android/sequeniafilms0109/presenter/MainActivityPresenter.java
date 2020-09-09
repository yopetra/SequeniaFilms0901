package com.example.android.sequeniafilms0109.presenter;

import android.util.Log;

import com.example.android.sequeniafilms0109.model.Film;
import com.example.android.sequeniafilms0109.model.Films;
import com.example.android.sequeniafilms0109.utils.FilmsService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityPresenter {

    private ViewInterface viewInterface;
    private FilmsService filmsService;
    private final String LOG_TAG = "MainActivityPresenter";

    public MainActivityPresenter(ViewInterface viewInterface){
        this.viewInterface = viewInterface;

        if(this.filmsService == null){
            this.filmsService = new FilmsService();
        }
    }

    public void printFilmsSizeList(){
        filmsService
                .getAPI()
                .getAllFilms()
                .enqueue(new Callback<Films>() {
                    @Override
                    public void onResponse(Call<Films> call, Response<Films> response) {
                        List<Film> filmsDetails = response.body().getFilms();
                        viewInterface.updateText("Films size = " + filmsDetails.size());
                    }

                    @Override
                    public void onFailure(Call<Films> call, Throwable t) {
                        Log.d(LOG_TAG, "failure = " + t);
                    }
                });
    }

    public interface ViewInterface{
        void updateText(String textString);
    }
}
