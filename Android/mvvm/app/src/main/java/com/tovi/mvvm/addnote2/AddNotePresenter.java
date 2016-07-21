package com.tovi.mvvm.addnote2;

import android.support.annotation.NonNull;

import com.tovi.mvvm.data.source.NoteRepository;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author <a href='mailto:zhaotengfei9@gmail.com'>Tengfei Zhao</a>
 */
public class AddNotePresenter implements AddNoteContract.Presenter {

    private final AddNoteContract.View mView;
    private final NoteRepository mNoteRepository;

    public AddNotePresenter(@NonNull AddNoteContract.View view, @NonNull NoteRepository noteRepository) {
        this.mView = checkNotNull(view);
        this.mNoteRepository = noteRepository;
    }

    @Override
    public void handlerSubmit() {
        this.mView.toast("Hello handler Submit");
    }

}
