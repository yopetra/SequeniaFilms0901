package com.example.android.sequeniafilms0109.utils;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.sequeniafilms0109.R;
import com.example.android.sequeniafilms0109.model.CoupleOfFilms;
import com.example.android.sequeniafilms0109.model.Devider;
import com.example.android.sequeniafilms0109.model.Film;
import com.example.android.sequeniafilms0109.model.Genre;
import com.example.android.sequeniafilms0109.model.GenreHolder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FilmsListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Object> mainList = new ArrayList<>();
    private final int GENRE_TYPE = 0;
    private final int FILM_TYPE = 1;
    private final int DEVIDER_TYPE = 2;
    private int selectedGenre = -1;

    private static GenresAdapterOnclickHandler mGenresClickHandler;
    private static FilmsAdapterOnClickHandler mFilmsClickHandler;

    public interface GenresAdapterOnclickHandler {
        void onClick(int potition);
    }

    public interface FilmsAdapterOnClickHandler{
        void onClickFilm(int filmId);
    }

    public FilmsListAdapter(GenresAdapterOnclickHandler genresClickHandler, FilmsAdapterOnClickHandler filmsClickHandler){
        mGenresClickHandler = genresClickHandler;
        mFilmsClickHandler = filmsClickHandler;
    }

    public void setSelectedGenre(int id){
        selectedGenre = id;
    }

    public static class GenresTypeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView genreTextView;
        CardView cardView;
        FrameLayout frameLayout;

        public GenresTypeViewHolder(@NonNull View itemView) {
            super(itemView);

            this.genreTextView = itemView.findViewById(R.id.tv_genre_type);
            this.cardView = itemView.findViewById(R.id.card_view);
            this.frameLayout = itemView.findViewById(R.id.fl_genre_container);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int adapterPosition = getAdapterPosition();
            mGenresClickHandler.onClick(adapterPosition);
        }
    }

    public static class FilmsTypeViewHolder extends RecyclerView.ViewHolder implements View.OnTouchListener, View.OnClickListener{

        TextView filmANameTextView;
        TextView filmBNameTextView;
        ImageView filmAImageView;
        ImageView filmBImageView;
        float[] lastTouchDownXY = new float[2];
        int viewWidth;
        int centreOfView;
        int filmId;

        public FilmsTypeViewHolder(@NonNull View itemView) {
            super(itemView);

            this.filmANameTextView = itemView.findViewById(R.id.tv_film_name);
            this.filmBNameTextView = itemView.findViewById(R.id.tv_film_name_right);
            this.filmAImageView = itemView.findViewById(R.id.iv_film_item);
            this.filmBImageView = itemView.findViewById(R.id.iv_film_item_right);

            itemView.setOnTouchListener(this);
            itemView.setOnClickListener(this);
        }

        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if(motionEvent.getActionMasked() == MotionEvent.ACTION_DOWN){
                lastTouchDownXY[0] = motionEvent.getX();
                lastTouchDownXY[1] = motionEvent.getY();
                viewWidth = view.getWidth();
                int qtyOfFilmsInRow = 2;
                centreOfView = viewWidth / qtyOfFilmsInRow;
            }

            GenreHolder genreHolder = GenreHolder.getInstance();
            int genreSize = genreHolder.getSize() + 2; // 2 is two deviders - Genres and Films
            int adapterPosition = getAdapterPosition();
            filmId = (adapterPosition - genreSize) * 2;
            if(lastTouchDownXY[0] > centreOfView){ filmId = filmId + 1; }

            return false;
        }

        @Override
        public void onClick(View view) {
            mFilmsClickHandler.onClickFilm(filmId);

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
            case "Genre":
                Genre genre = (Genre) genreOrFilmItem;

                if(position == selectedGenre){
                    ((GenresTypeViewHolder) holder).frameLayout.setBackgroundColor(Color.parseColor("#85b1d6"));
                }else{
                    ((GenresTypeViewHolder) holder).frameLayout.setBackgroundColor(Color.parseColor("#333333"));
                }
                ((GenresTypeViewHolder) holder).genreTextView.setText( genre.getName() );
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

    public ArrayList<Film> getFilmsFromMainList(){
        int arrSizeCouple = mainList.size();
        ArrayList<Film> films = new ArrayList<>();

        for(int i = 0; i < arrSizeCouple; i++){
            Object item = mainList.get(i);
            if(item.getClass().getSimpleName().matches("CoupleOfFilms")){
                CoupleOfFilms couple = (CoupleOfFilms) item;
                films.add((Film) ((CoupleOfFilms) item).getmFilmA());
                if(((CoupleOfFilms) item).getmFilmB() != null){ films.add(((CoupleOfFilms) item).getmFilmB());}
            }
        }

        return films;
    }
}
