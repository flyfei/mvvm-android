package com.tovi.mvvm.updateui.observableField;

import android.databinding.ObservableField;

/**
 * @author <a href='mailto:zhaotengfei9@gmail.com'>Tengfei Zhao</a>
 */
public class Car {
    public final ObservableField<String> name = new ObservableField<>();
    public final ObservableField<Integer> number = new ObservableField<>();
}
