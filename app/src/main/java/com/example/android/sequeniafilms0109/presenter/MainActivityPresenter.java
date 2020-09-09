package com.example.android.sequeniafilms0109.presenter;

public class MainActivityPresenter {

    private ViewInterface viewInterface;

    public MainActivityPresenter(ViewInterface viewInterface){
        this.viewInterface = viewInterface;
    }

    public void updateTextInMain(String textString){
        viewInterface.updateText(textString);
    }

    public interface ViewInterface{
        void updateText(String textString);
    }
}
