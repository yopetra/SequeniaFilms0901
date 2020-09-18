package com.example.android.sequeniafilms0109.model;

public class Genre {

    private String mName;
    private boolean mSelected;

    public Genre(String name){
        this.mName = name;
        this.mSelected = false;
    }

    public String getName() {
        return mName;
    }

    public void setSelected(boolean isSelected){
        this.mSelected = isSelected;
    }

}
