package com.tovi.mvvm.updateui.observableField;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.tovi.mvvm.R;
import com.tovi.mvvm.databinding.ActivityObservableFieldBinding;

public class ObservableFieldActivity extends AppCompatActivity implements View.OnClickListener {

    private Car car;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityObservableFieldBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_observable_field);
        car = new Car();
        car.name.set("Tesla");
        car.number.set(0);
        binding.setCar(car);

        findViewById(R.id.update_tesla).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        car.number.set(car.number.get() + 1);
    }
}
