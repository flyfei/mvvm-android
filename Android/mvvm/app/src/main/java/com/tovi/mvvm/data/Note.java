package com.tovi.mvvm.data;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.google.common.base.Objects;

import java.util.UUID;

/**
 * @author <a href='mailto:zhaotengfei9@gmail.com'>Tengfei Zhao</a>
 */
public class Note {
    private String id;
    private String title;
    private String content;
    private boolean isCompleted;

    public Note(String mTitle, @Nullable String mContent) {
        this.id = UUID.randomUUID().toString();
        this.title = mTitle;
        this.content = mContent;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public boolean isEmpty() {
        return TextUtils.isEmpty(title) || TextUtils.isEmpty(content);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note = (Note) o;
        return Objects.equal(id, note.id) && Objects.equal(title, note.title) && Objects.equal(content, note.content);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, title, content);
    }

    @Override
    public String toString() {
        return "Note`s title " + title;
    }
}
