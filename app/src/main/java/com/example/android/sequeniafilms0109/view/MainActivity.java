package com.example.android.sequeniafilms0109.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.android.sequeniafilms0109.R;
import com.example.android.sequeniafilms0109.presenter.MainActivityPresenter;

public class MainActivity extends AppCompatActivity implements MainActivityPresenter.ViewInterface {

    private TextView mainTextView;
    private MainActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainTextView = findViewById(R.id.tv_main);
        presenter = new MainActivityPresenter(this);

        presenter.printFilmsSizeList();
    }

    @Override
    public void updateText(String textString) {
        mainTextView.setText(textString);
    }
}