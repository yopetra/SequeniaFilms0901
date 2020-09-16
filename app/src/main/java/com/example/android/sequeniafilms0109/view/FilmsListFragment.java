package com.example.android.sequeniafilms0109.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.sequeniafilms0109.R;
import com.example.android.sequeniafilms0109.model.Film;
import com.example.android.sequeniafilms0109.presenter.MainActivityPresenter;
import com.example.android.sequeniafilms0109.utils.FilmsListAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FilmsListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FilmsListFragment extends Fragment implements FilmsListAdapter.FilmsAdapterOnclickHandler, MainActivityPresenter.ViewInterface {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private RecyclerView mFilmsRecyclerView;
    private FilmsListAdapter mAdapter;
    private MainActivityPresenter mPresenter;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FilmsListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FilmsListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FilmsListFragment newInstance(String param1, String param2) {
        FilmsListFragment fragment = new FilmsListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_films_list, container, false);

        mPresenter = new MainActivityPresenter(this);
        mPresenter.fetchFilms();
        ArrayList<Film> filmsList =  mPresenter.getAllFilms();

        mFilmsRecyclerView = rootView.findViewById(R.id.rv_films_grid);

//        GridLayoutManager gridLayoutManager = new GridLayoutManager(rootView.getContext(), 2);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(rootView.getContext(), RecyclerView.VERTICAL, false);
//        mFilmsRecyclerView.setLayoutManager(gridLayoutManager);
        mFilmsRecyclerView.setLayoutManager(linearLayoutManager);
        mFilmsRecyclerView.setHasFixedSize(true);
        mAdapter = new FilmsListAdapter(this);
//        mAdapter.setFilmData(filmsList);
        mFilmsRecyclerView.setAdapter(mAdapter);

        return rootView;
    }

    @Override
    public void onClick(Film film) {
        System.out.println("Film = " + film.getName());
    }

    @Override
    public void updateText(String textString) {

    }

    @Override
    public void applyFilmsData(ArrayList<Object> genresAndFilms) {
        mAdapter.setFilmData(genresAndFilms);
    }
}