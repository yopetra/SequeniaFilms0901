package com.example.android.sequeniafilms0109.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.sequeniafilms0109.R;
import com.example.android.sequeniafilms0109.model.Film;
import com.example.android.sequeniafilms0109.presenter.MainActivityPresenter;
import com.squareup.picasso.Picasso;

/*
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailFragment extends Fragment {

    private Film mFilm;
    private ImageView mFilmIconImageView;
    private TextView mLocalisedName;
    private TextView mFilmName;
    private TextView mReleaseDate;
    private TextView mRating;
    private TextView mDescription;

//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String FILM_ID = "id";
//    private static final String ARG_PARAM2 = "param2";

//    // TODO: Rename and change types of parameters
    private int mFilmId;
//    private String mParam2;

    public DetailFragment() {
        // Required empty public constructor
    }

    /*
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetailFragment.
     */
//    // TODO: Rename and change types and number of parameters
//    public static DetailFragment newInstance(String param1, String param2) {
//        DetailFragment fragment = new DetailFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mFilmId = getArguments().getInt(FILM_ID);
//            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);

        String filmIcon = mFilm.getImageUrl();
        mFilmIconImageView = rootView.findViewById(R.id.iv_film_detail);
        Picasso.get()
                .load(filmIcon)
                .error(R.drawable.no_image_available_md)
                .resize(200, 245)
                .into(mFilmIconImageView);

        String localizedName = mFilm.getLocalizedName();
        mLocalisedName = rootView.findViewById(R.id.tv_localized_name);
        mLocalisedName.setText(localizedName);

        String name = mFilm.getName();
        mFilmName = rootView.findViewById(R.id.tv_name);
        mFilmName.setText(name);

        int releaseDate = mFilm.getYear();
        mReleaseDate = rootView.findViewById(R.id.tv_release_date);
        mReleaseDate.setText(String.valueOf(releaseDate));

        String rating = mFilm.getRating();
        mRating = rootView.findViewById(R.id.tv_rating);
        mRating.setText(rating);

        String description = mFilm.getDescription();
        mDescription = rootView.findViewById(R.id.tv_description);
        mDescription.setText(description);

        return rootView;
    }

    public void setFilm(Film film){
        mFilm = film;
    }

}