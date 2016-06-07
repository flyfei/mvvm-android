package com.tovi.mvvm.updateui.baseObservable;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.tovi.mvvm.BR;

/**
 * @author <a href='mailto:zhaotengfei9@gmail.com'>Tengfei Zhao</a>
 */
public class Movie extends BaseObservable {
    public String name;
    public int sizeOfWatching;


    public Movie(String name, int sizeOfWatching) {
        this.name = name;
        this.sizeOfWatching = sizeOfWatching;
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public int getSizeOfWatching() {
        return sizeOfWatching;
    }

    public void setSizeOfWatching(int sizeOfWatching) {
        this.sizeOfWatching = sizeOfWatching;
        notifyPropertyChanged(BR.sizeOfWatching);
    }
}
