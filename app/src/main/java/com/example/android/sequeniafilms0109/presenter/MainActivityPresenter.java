package com.example.android.sequeniafilms0109.presenter;

import android.util.Log;

import com.example.android.sequeniafilms0109.model.Devider;
import com.example.android.sequeniafilms0109.model.Film;
import com.example.android.sequeniafilms0109.model.Films;
import com.example.android.sequeniafilms0109.model.FilmsHolder;
import com.example.android.sequeniafilms0109.model.Genre;
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
    ArrayList<Object> genresAndFilmsList = new ArrayList<>();

    public MainActivityPresenter(ViewInterface viewInterface){
        this.viewInterface = viewInterface;

        if(this.filmsService == null){
            this.filmsService = new FilmsService();
        }
    }

    public void fetchFilms(){
        filmsHolder.clearAllData();
        clearData();
        filmsService
                .getAPI()
                .getAllFilms()
                .enqueue(new Callback<Films>() {
                    @Override
                    public void onResponse(Call<Films> call, Response<Films> response) {
                        List<Film> filmsDetails = response.body().getFilms();

                        int arrSize = filmsDetails.size();
                        for(int i = 0; i < arrSize; i++){
                            filmsHolder.addFilm(filmsDetails.get(i));
                        }

                        filmsHolder.sorting();
                        ArrayList<Film> films = filmsHolder.getAllFilms();
                        genreHolder.extractGenresFrom(films);

                        ArrayList<Genre> genres = genreHolder.getGenresList();

                        genresAndFilmsList.add(new Devider("Жанры"));
                        addGenresToList(genres);
                        genresAndFilmsList.add(new Devider("Фильмы"));
                        addFilmsToList(films);
                        viewInterface.applyFilmsData(genresAndFilmsList);
                    }

                    @Override
                    public void onFailure(Call<Films> call, Throwable t) {
                        Log.d(LOG_TAG, "failure = " + t);
                    }
                });
    }

    private void addFilmsToList(ArrayList<Film> films) {
        int size = films.size();

        for(int i = 0; i < size; i++){
            Film filmItem = films.get(i);
            genresAndFilmsList.add(filmItem);
        }
    }

    private void addGenresToList(ArrayList<Genre> genres) {
        int size = genres.size();

        for(int i = 0; i < size; i++){
            String genreName = genres.get(i).getName();
            genresAndFilmsList.add(new Genre(genreName));
        }
    }

    public ArrayList<Film> getAllFilms(){
        return filmsHolder.getAllFilms();
    }

    public ArrayList<Object> getMainListSortedByGenre(int position) {
        String selectedGenreName = genreHolder.getById(position-1).getName();
        int arrSize = filmsHolder.getSize();
        ArrayList<Object> sortedList = new ArrayList<>();

        sortedList.add(new Devider("Жанры"));
        ArrayList<Genre> genres = genreHolder.getGenresList();
        for(int igen = 0; igen < genres.size(); igen++){
            sortedList.add(genres.get(igen));
        }

        sortedList.add(new Devider("Фильмы"));

        for(int i = 0; i < arrSize; i++){
            Film film = filmsHolder.getFilmById(i);
            ArrayList<String> genresOfFilm = (ArrayList<String>) film.getGenres();
            int genSize = genresOfFilm.size();

            for(int i2 = 0; i2 < genSize; i2++){
                if(selectedGenreName.matches(genresOfFilm.get(i2))){
                    sortedList.add(film);
                    break;
                }
            }
        }
        return sortedList;
    }

    public interface ViewInterface{
        void applyFilmsData(ArrayList<Object> genresAndFilms);
    }

    private void clearData(){
        while(genresAndFilmsList.size() > 0){
            genresAndFilmsList.remove(0);
        }
    }
}
