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
import com.example.android.sequeniafilms0109.model.Devider;
import com.example.android.sequeniafilms0109.model.Film;
import com.example.android.sequeniafilms0109.model.GenreHolder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FilmsListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Object> mainList = new ArrayList<>();
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

        TextView filmANameTextView;
        TextView filmBNameTextView;
        ImageView filmAImageView;
        ImageView filmBImageView;

        public FilmsTypeViewHolder(@NonNull View itemView) {
            super(itemView);

            this.filmANameTextView = itemView.findViewById(R.id.tv_film_name);
            this.filmBNameTextView = itemView.findViewById(R.id.tv_film_name_right);
            this.filmAImageView = itemView.findViewById(R.id.iv_film_item);
            this.filmBImageView = itemView.findViewById(R.id.iv_film_item_right);
        }
    }

    public static class DeviderTypeViewHolder extends RecyclerView.ViewHolder{

        TextView deviderNameTextView;

        public DeviderTypeViewHolder(@NonNull View itemView) {
            super(itemView);

            this.deviderNameTextView = itemView.findViewById(R.id.tv_devider);
        }
    }

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
            case DEVIDER_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.devider_type, parent, false);
                return new DeviderTypeViewHolder(view);
        }

        return null;
    }

    @Override
    public int getItemViewType(int position){
        GenreHolder genreHolder = GenreHolder.getInstance();
        int genresSize = genreHolder.getSize();

        if(position == 0 || position == (genresSize+1)){return DEVIDER_TYPE;}
        if(position < (genresSize+1) && position != 0){return GENRE_TYPE;}
        if(position > (genresSize+1) ){return FILM_TYPE;}

        return -1;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position){
        Object genreOrFilmItem = mainList.get(position);
        String className = genreOrFilmItem.getClass().getSimpleName();

        switch (className){
            case "String":
                ((GenresTypeViewHolder) holder).genreTextView.setText( (String) genreOrFilmItem );
                break;
            case "CoupleOfFilms":
                CoupleOfFilms coupleOfFilms = (CoupleOfFilms) genreOrFilmItem;
                Film filmA = coupleOfFilms.getmFilmA();
                Film filmB = coupleOfFilms.getmFilmB();

                if(filmA != null){
                    String filmAPicture = filmA.getImageUrl();
                    String filmAName = filmA.getLocalizedName();

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
                    ((FilmsTypeViewHolder) holder).filmANameTextView.setText(filmAName);
                }

                if(filmB != null){
                    String filmBPicture = null;
                    String filmBName = filmB.getLocalizedName();

                    if(coupleOfFilms.getmFilmB() != null){
                        filmBPicture = filmB.getImageUrl();
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
                    ((FilmsTypeViewHolder) holder).filmBNameTextView.setText(filmBName);
                }else{
                    // if the film B not available in the couple object
                    ((FilmsTypeViewHolder) holder).filmBImageView.setVisibility(View.GONE);
                    ((FilmsTypeViewHolder) holder).filmBNameTextView.setVisibility(View.GONE);
                }


                break;
            case "Devider":
                Devider devider = (Devider) genreOrFilmItem;
                String name = devider.getmName();
                ((DeviderTypeViewHolder) holder).deviderNameTextView.setText(name);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + className);
        }
    }

    @Override
    public int getItemCount() {
        if(mainList == null){return 0;}

        int size = mainList.size();
        return size;
    }

    public void setMainListData(ArrayList<Object> genresAndFilms){
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
                mainList.add(coupleOfFilms);
                i++;
            }else{
                mainList.add(genresAndFilms.get(i));
            }
        }

        notifyDataSetChanged();
    }

    public void clearData(){

        while(mainList.size() > 0){
            mainList.remove(0);
        }
    }
}
