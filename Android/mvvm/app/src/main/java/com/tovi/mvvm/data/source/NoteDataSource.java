package com.tovi.mvvm.data.source;

import com.tovi.mvvm.data.Note;

/**
 * @author <a href='mailto:zhaotengfei9@gmail.com'>Tengfei Zhao</a>
 */
public interface NoteDataSource {
    interface GetTaskCallback {
        void onNoteLoaded(Note note);

        void onNoData();
    }

    void saveNote(Note note);
}
