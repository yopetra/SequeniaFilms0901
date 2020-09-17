package com.example.android.sequeniafilms0109.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GenreHolder {

    private static GenreHolder instance = null;
    private ArrayList<String> genresList;

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
                    genresList.add(genreNameInFilm);
                }

                if(genresInListSize > 0){
                    boolean needAddToList = true;
                    for(int i3 = 0; i3 < genresInListSize; i3++){
                        String genreNameInList = genresList.get(i3);
                        if(genreNameInFilm.matches(genreNameInList)){needAddToList = false;}
                    }

                    if(needAddToList){
                        genresList.add(genreNameInFilm);
                    }
                }
            }
        }

        Collections.sort(genresList);
    }

    public ArrayList<String> getGenresList(){
        return genresList;
    }

    public int getSize(){
        return genresList.size();
    }
}
