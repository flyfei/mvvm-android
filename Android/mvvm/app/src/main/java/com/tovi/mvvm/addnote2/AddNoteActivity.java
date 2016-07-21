package com.tovi.mvvm.addnote2;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.tovi.mvvm.R;
import com.tovi.mvvm.data.source.NoteRepository;
import com.tovi.mvvm.databinding.ActivityAddNote2Binding;

public class AddNoteActivity extends AppCompatActivity implements AddNoteContract.ViewActivity {

    private ActivityAddNote2Binding binding;
    private AddNoteViewModel mModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mModel = new AddNoteViewModel((AddNoteContract.ViewActivity) this);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_note2);

        binding.setNoteModel(mModel);
        binding.setPresenter(new AddNotePresenter(mModel, NoteRepository.getInstance(getApplicationContext())));
    }

    @Override
    public Activity getActivity() {
        return this;
    }
}
