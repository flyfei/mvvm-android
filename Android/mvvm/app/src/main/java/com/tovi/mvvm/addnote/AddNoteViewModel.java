package com.tovi.mvvm.addnote;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.annotation.NonNull;

import com.tovi.mvvm.BR;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author <a href='mailto:zhaotengfei9@gmail.com'>Tengfei Zhao</a>
 */

/**
 * Data binding; events handling.
 */
public class AddNoteViewModel extends BaseObservable implements AddNoteContract.Presenter {

    private AddNoteContract.View mView;

    private String title = "title";

    private String content = "content";

    public AddNoteViewModel(@NonNull AddNoteContract.View view) {
        this.mView = checkNotNull(view);
    }

    @Bindable
    public String getTitle() {
        return "title";
    }

    @Bindable
    public String getContent() {
        return "content";
    }


    public void setTitle(String title) {
        this.title = title;
        notifyPropertyChanged(BR.title);
    }

    public void setContent(String content) {
        this.content = content;
        notifyPropertyChanged(BR.content);
    }

    @Override
    public void handlerSubmit() {
        this.mView.toast("Hello world");
    }

    @Override
    public void onActivityResume() {

    }

    @Override
    public void onActivityPause() {

    }
}
