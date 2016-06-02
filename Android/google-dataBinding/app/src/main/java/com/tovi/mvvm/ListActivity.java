package com.tovi.mvvm;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.tovi.mvvm.databinding.ActivityListBinding;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        ActivityListBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_list);
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            userList.add(new User("User" + i, i));
        }
        binding.setIndex(1);
        binding.setUserList(userList);
    }
}
