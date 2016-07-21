package com.tovi.mvvm.data.source;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.tovi.mvvm.data.Note;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author <a href='mailto:zhaotengfei9@gmail.com'>Tengfei Zhao</a>
 */
public class NoteRepository implements NoteDataSource {
    private static NoteRepository source;

    private Note note;
    private final Context mContext;

    public NoteRepository(Context context) {
        this.mContext = context;
    }

    public static NoteRepository getInstance(Context context) {
        if (null == source) {
            source = new NoteRepository(context);
        }
        return source;
    }

    public void getNote(@NonNull NoteDataSource.GetTaskCallback getTaskCallback) {
        checkNotNull(getTaskCallback);

        if (isEmpty()) {
            getTaskCallback.onNoData();
        }
        getTaskCallback.onNoteLoaded(note);
    }

    public void saveNote(@NonNull Note note) {
        checkNotNull(note);
        this.note = note;
    }

    private boolean isEmpty() {
        if (null == note) {
            return true;
        }
        if (TextUtils.isEmpty(note.getTitle()) && TextUtils.isEmpty(note.getContent())) {
            return true;
        }
        return false;
    }
}
