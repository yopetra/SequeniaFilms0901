package com.example.android.sequeniafilms0109.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Film {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("localized_name")
    @Expose
    private String localizedName;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("year")
    @Expose
    private int year;
    @SerializedName("rating")
    @Expose
    private String rating;
    @SerializedName("image_url")
    @Expose
    private Object imageUrl;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("genres")
    @Expose
    private List<String> genres = null;

    public Film(int id, String localizedName, String name, int year, String rating, String imageUrl, String description, ArrayList<String> genres) {
        this.id = id;
        this.localizedName = localizedName;
        this.name = name;
        this.year = year;
        this.rating = rating;
        this.imageUrl = imageUrl;
        this.description = description;
        this.genres = genres;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocalizedName() {
        return localizedName;
    }

    public void setLocalizedName(String localizedName) {
        this.localizedName = localizedName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public Object getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(Object imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }
}
