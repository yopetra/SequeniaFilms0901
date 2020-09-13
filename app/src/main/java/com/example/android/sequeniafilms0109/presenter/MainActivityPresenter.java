package com.example.android.sequeniafilms0109.presenter;

import android.util.Log;

import com.example.android.sequeniafilms0109.model.Film;
import com.example.android.sequeniafilms0109.model.Films;
import com.example.android.sequeniafilms0109.model.FilmsHolder;
import com.example.android.sequeniafilms0109.model.GenreHolder;
import com.example.android.sequeniafilms0109.utils.FilmsService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityPresenter {

    private ViewInterface viewInterface;
    private FilmsService filmsService;
    private final String LOG_TAG = "MainActivityPresenter";
    private FilmsHolder filmsHolder = FilmsHolder.getInstance();
    private GenreHolder genreHolder = GenreHolder.getInstance();

    public MainActivityPresenter(ViewInterface viewInterface){
        this.viewInterface = viewInterface;

        if(this.filmsService == null){
            this.filmsService = new FilmsService();
        }
    }

    public void fetchFilms(){
        filmsService
                .getAPI()
                .getAllFilms()
                .enqueue(new Callback<Films>() {
                    @Override
                    public void onResponse(Call<Films> call, Response<Films> response) {
                        List<Film> filmsDetails = response.body().getFilms();

//                        FilmsHolder filmsHolder = FilmsHolder.getInstance();
                        int arrSize = filmsDetails.size();
                        for(int i = 0; i < arrSize; i++){
                            filmsHolder.addFilm(filmsDetails.get(i));
                        }

                        genreHolder.extractGenresFrom(filmsHolder.getAllFilms());
                        viewInterface.applyFilmsData(filmsHolder.getAllFilms());
//                        viewInterface.updateText("Films size = " + filmsDetails.size());
                    }

                    @Override
                    public void onFailure(Call<Films> call, Throwable t) {
                        Log.d(LOG_TAG, "failure = " + t);
                    }
                });
    }

    public ArrayList<Film> getAllFilms(){
        return filmsHolder.getAllFilms();
    }

    public interface ViewInterface{
        void updateText(String textString);
        void applyFilmsData(ArrayList<Film> films);
    }
}
