package com.example.android.sequeniafilms0109.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.sequeniafilms0109.R;
import com.example.android.sequeniafilms0109.model.Film;
import com.example.android.sequeniafilms0109.model.FilmsHolder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FilmsListAdapter extends RecyclerView.Adapter<FilmsListAdapter.FilmsListViewHolder> {

    private ArrayList<Film> filmsList = new ArrayList<>();

    private final FilmsAdapterOnclickHandler mClickHandler;

    public interface FilmsAdapterOnclickHandler{
        void onClick(Film film);
    }

    public FilmsListAdapter(FilmsAdapterOnclickHandler clickHandler){
        mClickHandler = clickHandler;
    }

    @NonNull
    @Override
    public FilmsListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.film_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParent = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParent);
        FilmsListViewHolder viewHolder = new FilmsListViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FilmsListViewHolder holder, int position) {
        FilmsHolder filmsHolder = FilmsHolder.getInstance();
        Film film = filmsHolder.getFilmById(position);
        String currentFilmURL = film.getImageUrl();
        if(currentFilmURL != null){
            Picasso.get()
                    .load(currentFilmURL)
                    .resize(200, 245)
                    .into(holder.filmItemImageView);
        }
    }

    @Override
    public int getItemCount() {
        if(filmsList == null){ return 0;}

        return filmsList.size();
    }

    public void setFilmData(ArrayList<Film> films){
        int arrSize = films.size();
        for(int i = 0; i < arrSize; i++){
            filmsList.add(films.get(i));
        }

        notifyDataSetChanged();
    }

    public void clearData(){
        while(filmsList.size() > 0){
            filmsList.remove(0);
        }
    }

    public class FilmsListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView filmItemImageView;

        public FilmsListViewHolder(@NonNull View itemView) {
            super(itemView);
            filmItemImageView = itemView.findViewById(R.id.iv_film_item);
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(view.getContext(), "Clicked " + getAdapterPosition(), Toast.LENGTH_SHORT).show();

        }
    }
}
