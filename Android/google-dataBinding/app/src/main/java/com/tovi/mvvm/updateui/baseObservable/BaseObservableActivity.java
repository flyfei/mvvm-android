package com.tovi.mvvm.updateui.baseObservable;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.tovi.mvvm.R;
import com.tovi.mvvm.databinding.ActivityBaseObservableBinding;

public class BaseObservableActivity extends AppCompatActivity implements View.OnClickListener {

    private Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityBaseObservableBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_base_observable);
        movie = new Movie("魔兽", 0);
        binding.setMovie(movie);

        findViewById(R.id.update_movie).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        movie.setSizeOfWatching(movie.getSizeOfWatching() + 1);
    }
}
