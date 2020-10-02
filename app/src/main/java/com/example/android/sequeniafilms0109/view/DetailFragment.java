package com.example.android.sequeniafilms0109.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.android.sequeniafilms0109.R;
import com.example.android.sequeniafilms0109.model.Film;
import com.example.android.sequeniafilms0109.model.SelectedFilmHolder;
import com.squareup.picasso.Picasso;


public class DetailFragment extends Fragment {

    private Film mFilm;
    private ImageView mFilmIconImageView;
    private TextView mLocalisedName;
    private TextView mFilmName;
    private TextView mReleaseDate;
    private TextView mRating;
    private TextView mDescription;

    private static final String FILM_ID = "id";
    private int mFilmId;

    public DetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mFilmId = getArguments().getInt(FILM_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);

        mFilm = SelectedFilmHolder.getmFilm();

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
        SelectedFilmHolder.setmFilm(film);
    }


}