package com.tovi.mvvm.addnote;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.tovi.mvvm.R;
import com.tovi.mvvm.databinding.ActivityAddNoteBinding;

import java.util.Map;

public class AddNoteActivity extends AppCompatActivity implements AddNoteContract.View {

    private ActivityAddNoteBinding binding;
    private AddNoteViewModel mModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mModel = new AddNoteViewModel(this);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_note);
        binding.setNoteModel(mModel);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mModel.onActivityResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mModel.onActivityPause();
    }

    @Override
    public void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void openSecondActivity(Map map) {

    }
}
