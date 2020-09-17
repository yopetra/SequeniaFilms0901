package com.example.android.sequeniafilms0109.utils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.sequeniafilms0109.R;
import com.example.android.sequeniafilms0109.model.CoupleOfFilms;
import com.example.android.sequeniafilms0109.model.Film;
import com.example.android.sequeniafilms0109.model.GenreHolder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

//public class FilmsListAdapter extends RecyclerView.Adapter<FilmsListAdapter.FilmsListViewHolder> {
public class FilmsListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

//    private ArrayList<Film> filmsList;
    private ArrayList<Object> genresAndFilmsList = new ArrayList<>();
    private final int GENRE_TYPE = 0;
    private final int FILM_TYPE = 1;
    private final int DEVIDER_TYPE = 2;

    private final FilmsAdapterOnclickHandler mClickHandler;

    public interface FilmsAdapterOnclickHandler{
        void onClick(Film film);
    }

    public FilmsListAdapter(FilmsAdapterOnclickHandler clickHandler){
        mClickHandler = clickHandler;
    }


    public static class GenresTypeViewHolder extends RecyclerView.ViewHolder{

        TextView genreTextView;
        CardView cardView;

        public GenresTypeViewHolder(@NonNull View itemView) {
            super(itemView);

            this.genreTextView = itemView.findViewById(R.id.tv_genre_type);
            this.cardView = itemView.findViewById(R.id.card_view);
        }
    }

    public static class FilmsTypeViewHolder extends RecyclerView.ViewHolder{

        TextView filmNameTextView;
        ImageView filmAImageView;
        ImageView filmBImageView;

        public FilmsTypeViewHolder(@NonNull View itemView) {
            super(itemView);

            this.filmNameTextView = itemView.findViewById(R.id.tv_film_name);
            this.filmAImageView = itemView.findViewById(R.id.iv_film_item);
            this.filmBImageView = itemView.findViewById(R.id.iv_film_item_right);
        }
    }

//    @NonNull
//    @Override
//    public FilmsListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
//        Context context = viewGroup.getContext();
//        int layoutIdForListItem = R.layout.film_item;
//        LayoutInflater inflater = LayoutInflater.from(context);
//        boolean shouldAttachToParent = false;
//
//        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParent);
//        FilmsListViewHolder viewHolder = new FilmsListViewHolder(view);
//
//        return viewHolder;
//    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        switch (viewType){
            case GENRE_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.genre_type, parent, false);
                return new GenresTypeViewHolder(view);
            case FILM_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.film_item, parent, false);
                return new FilmsTypeViewHolder(view);
        }

        return null;
    }

    @Override
    public int getItemViewType(int position){
        GenreHolder genreHolder = GenreHolder.getInstance();
        int genresSize = genreHolder.getSize();

        if(position < genresSize){return GENRE_TYPE;}
        if(position >= genresSize){return FILM_TYPE;}

        return -1;
    }

//    @Override
//    public void onBindViewHolder(@NonNull FilmsListViewHolder holder, int position) {
////        FilmsHolder filmsHolder = FilmsHolder.getInstance();
//        Object genreOrFilmItem = genresAndFilmsList.get(position);
//        String className = genreOrFilmItem.getClass().getSimpleName();
//        System.out.println("Name of class = " + className);
//
//        if(className.matches("String")){
//            System.out.println("Genre on screen = " + genreOrFilmItem);
//            String genre = (String) genreOrFilmItem;
//            holder.genreTextView.setVisibility(View.VISIBLE);
//            holder.filmItemImageView.setVisibility(View.INVISIBLE);
//            holder.genreTextView.setText(genre);
//        }
//        if(className.matches("Film")){
//            holder.genreTextView.setVisibility(View.INVISIBLE);
//            holder.filmItemImageView.setVisibility(View.VISIBLE);
////            Film film = filmsHolder.getFilmById(position);
//            Film film = (Film) genreOrFilmItem;
//            String currentFilmURL = film.getImageUrl();
//            if(currentFilmURL != null){
//                Picasso.get()
//                        .load(currentFilmURL)
//                        .resize(200, 245)
//                        .into(holder.filmItemImageView);
//            }
//        }
//
//    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position){
        Object genreOrFilmItem = genresAndFilmsList.get(position);
        String className = genreOrFilmItem.getClass().getSimpleName();

        switch (className){
            case "String":
                ((GenresTypeViewHolder) holder).genreTextView.setText( (String) genreOrFilmItem );
                break;
            case "CoupleOfFilms":
                CoupleOfFilms coupleOfFilms = (CoupleOfFilms) genreOrFilmItem;
//                Film film = (Film) genreOrFilmItem;
//                String filmPicture = film.getImageUrl();
                String filmAPicture = coupleOfFilms.getmFilmA().getImageUrl();
                String filmBPicture = null;
                if(coupleOfFilms.getmFilmB() != null){
                    filmBPicture = coupleOfFilms.getmFilmB().getImageUrl();
                }
//                if(filmPicture != null){
//                    Picasso.get()
//                            .load(filmPicture)
//                            .resize(200, 245)
//                            .into( ((FilmsTypeViewHolder)holder).filmImageView );
//                }

                if(filmAPicture != null){
                    Picasso.get()
                            .load(filmAPicture)
                            .error(R.drawable.no_image_available_md)
                            .resize(200, 245)
                            .into(((FilmsTypeViewHolder) holder).filmAImageView);
                }else{
                    Picasso.get()
                            .load(R.drawable.no_image_available_md)
                            .into(((FilmsTypeViewHolder) holder).filmAImageView);
                }

                if(filmBPicture != null){
                    Picasso.get()
                            .load(filmBPicture)
                            .error(R.drawable.no_image_available_md)
                            .resize(200, 245)
                            .into(((FilmsTypeViewHolder) holder).filmBImageView);
                }else{
                    Picasso.get()
                            .load(R.drawable.no_image_available_md)
                            .into(((FilmsTypeViewHolder) holder).filmBImageView);
                }
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + className);
        }
    }

    @Override
    public int getItemCount() {
//        if(filmsList == null){ return 0;}
        if(genresAndFilmsList == null){return 0;}

//        return filmsList.size();
        int size = genresAndFilmsList.size();
        return size;
    }

    public void setFilmData(ArrayList<Object> genresAndFilms){
        int arrSize = genresAndFilms.size();
        for(int i = 0; i < arrSize; i++){
            String className = genresAndFilms.get(i).getClass().getSimpleName();

            // Create two films object
            if(className.matches("Film")){
                Film filmA = (Film) genresAndFilms.get(i);
                Film filmB = null;
                if((i+1) < arrSize ){
                    filmB = (Film) genresAndFilms.get(i+1);
                }

                CoupleOfFilms coupleOfFilms = new CoupleOfFilms(filmA, filmB);
                genresAndFilmsList.add(coupleOfFilms);
                i++;
            }else{
                genresAndFilmsList.add(genresAndFilms.get(i));
            }
//            filmsList.add(films.get(i));

        }

        notifyDataSetChanged();
    }

    public void clearData(){
//        while(filmsList.size() > 0){
//            filmsList.remove(0);
//        }

        while(genresAndFilmsList.size() > 0){
            genresAndFilmsList.remove(0);
        }
    }

//    public class FilmsListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
//
//        ImageView filmItemImageView;
//        TextView genreTextView;
//
//        public FilmsListViewHolder(@NonNull View itemView) {
//            super(itemView);
//            filmItemImageView = itemView.findViewById(R.id.iv_film_item);
//            genreTextView = itemView.findViewById(R.id.tv_genre);
//        }
//
//        @Override
//        public void onClick(View view) {
//            Toast.makeText(view.getContext(), "Clicked " + getAdapterPosition(), Toast.LENGTH_SHORT).show();
//
//        }
//    }
}
