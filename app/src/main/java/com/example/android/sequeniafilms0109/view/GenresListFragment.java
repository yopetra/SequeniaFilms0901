package com.example.android.sequeniafilms0109.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.sequeniafilms0109.R;
import com.example.android.sequeniafilms0109.presenter.MainActivityPresenter;
import com.example.android.sequeniafilms0109.utils.FilmsListAdapter;

import java.util.ArrayList;


// * A simple {@link Fragment} subclass.
// * Use the {@link FilmsListFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
public class GenresListFragment extends Fragment implements FilmsListAdapter.GenresAdapterOnclickHandler, FilmsListAdapter.FilmsAdapterOnClickHandler, MainActivityPresenter.ViewInterface {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
    private RecyclerView mFilmsRecyclerView;
    private FilmsListAdapter mAdapter;
    private MainActivityPresenter mPresenter;
    ArrayList<Object> sortedFilms;

    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;

    public GenresListFragment() {
        // Required empty public constructor
    }

//    **
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment FilmsListFragment.
//     */
    // TODO: Rename and change types and number of parameters
//    public static FilmsListFragment newInstance(String param1, String param2) {
//        FilmsListFragment fragment = new FilmsListFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
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
    public void applyFilmsData(ArrayList<Object> genresAndFilms) {
        mAdapter.setMainListData(genresAndFilms);
    }

    @Override
    public void onClickFilm(int filmId) {
        if(sortedFilms != null && sortedFilms.size() > 0){
            System.out.println("The movies have been sorted");
        }else{
            System.out.println("Whole list of movies without sorting");
        }
        System.out.println("film id = " + filmId);
    }

    private void clearSortedFilmsList(){
        if(sortedFilms != null){
            while(sortedFilms.size() > 0){
                sortedFilms.remove(0);
            }
        }
    }
}