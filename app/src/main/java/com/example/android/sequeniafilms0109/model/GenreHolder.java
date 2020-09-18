package com.example.android.sequeniafilms0109.model;

import android.os.Build;

import com.example.android.sequeniafilms0109.utils.GenreNameSorter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GenreHolder {

    private static GenreHolder instance = null;
    private ArrayList<Genre> genresList;

    private GenreHolder(){genresList = new ArrayList<>();}

    public static GenreHolder getInstance(){
        if(instance == null){
            instance = new GenreHolder();
        }

        return instance;
    }

    public void extractGenresFrom(ArrayList<Film> films){
        int arrSize = films.size();
        for(int i = 0; i < arrSize; i++){

            List<String> genresInFilm = films.get(i).getGenres();
            int genresFilmSize = genresInFilm.size();

            for(int i2 = 0; i2 < genresFilmSize; i2++){
                String genreNameInFilm = genresInFilm.get(i2);

                int genresInListSize = genresList.size();
                if(genresInListSize == 0){
                    genresList.add(new Genre(genreNameInFilm));
                }

                if(genresInListSize > 0){
                    boolean needAddToList = true;
                    for(int i3 = 0; i3 < genresInListSize; i3++){
                        String genreNameInList = genresList.get(i3).getName();
                        if(genreNameInFilm.matches(genreNameInList)){needAddToList = false;}
                    }

                    if(needAddToList){
                        genresList.add(new Genre(genreNameInFilm));
                    }
                }
            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            genresList.sort(new GenreNameSorter());
        }
    }

    public ArrayList<Genre> getGenresList(){
        return genresList;
    }

    public int getSize(){
        return genresList.size();
    }


    public Genre getById(int genreId) {
        return genresList.get(genreId);
    }

//    public void setSelectedById(int id) {
//        int arrSize = genresList.size();
//        for(int i = 0; i < arrSize; i++){
//            genresList.get(i).setSelected(false);
//        }
//
//        genresList.get(id).setSelected(true);
//    }
}
