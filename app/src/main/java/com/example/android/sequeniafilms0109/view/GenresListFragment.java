package com.example.android.sequeniafilms0109.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.sequeniafilms0109.R;
import com.example.android.sequeniafilms0109.model.Film;
import com.example.android.sequeniafilms0109.presenter.MainActivityPresenter;
import com.example.android.sequeniafilms0109.utils.FilmSelectorHolder;
import com.example.android.sequeniafilms0109.utils.FilmsListAdapter;

import java.util.ArrayList;


public class GenresListFragment extends Fragment implements FilmsListAdapter.GenresAdapterOnclickHandler, FilmsListAdapter.FilmsAdapterOnClickHandler, MainActivityPresenter.ViewInterface {

    private RecyclerView mFilmsRecyclerView;
    private FilmsListAdapter mAdapter;
    private MainActivityPresenter mPresenter;
    ArrayList<Object> sortedFilms;
    FilmSelector mFilmSelector;

    public GenresListFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("onCreate");

        FilmSelector fl = FilmSelectorHolder.getFilmSelector();
        if(fl != null){
            this.mFilmSelector = FilmSelectorHolder.getFilmSelector();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        System.out.println("onCreateView");
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_films_list, container, false);

        mPresenter = new MainActivityPresenter(this);
        mPresenter.fetchFilms();
        mFilmsRecyclerView = rootView.findViewById(R.id.rv_films_grid);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(rootView.getContext(), RecyclerView.VERTICAL, false);
        mFilmsRecyclerView.setLayoutManager(linearLayoutManager);
        mFilmsRecyclerView.setHasFixedSize(true);
        mAdapter = new FilmsListAdapter(this, this);
        mAdapter.clearData();
        mFilmsRecyclerView.setAdapter(mAdapter);

        return rootView;
    }

    @Override
    public void onClick(int position) {
        mAdapter.setSelectedGenre(position);
        clearSortedFilmsList();
        sortedFilms = mPresenter.getMainListSortedByGenre(position);
        mAdapter.clearData();
        mAdapter.setMainListData(sortedFilms);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        FilmSelectorHolder.setFilmSelector(this.mFilmSelector);
    }

    @Override
    public void applyFilmsData(ArrayList<Object> genresAndFilms) {
        mAdapter.setMainListData(genresAndFilms);
    }

    @Override
    public void onClickFilm(int filmId) {
        ArrayList<Film> films = mAdapter.getFilmsFromMainList();
//        if(sortedFilms != null && sortedFilms.size() > 0){
//            System.out.println("The movies have been sorted");
//        }else{
//            System.out.println("Whole list of movies without sorting");
//        }
//        System.out.println("film id = " + filmId);
        Film film = films.get(filmId);

        mFilmSelector.getFilm(film);
//        System.out.println("film gotten");
    }

    private void clearSortedFilmsList(){
        if(sortedFilms != null){
            while(sortedFilms.size() > 0){
                sortedFilms.remove(0);
            }
        }
    }

    public interface FilmSelector{
        void getFilm(Film film);
    }

    public void setFilmSelector(FilmSelector filmSelector){
        this.mFilmSelector = filmSelector;
    }
}