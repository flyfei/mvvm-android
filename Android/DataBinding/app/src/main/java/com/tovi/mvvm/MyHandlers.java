package com.tovi.mvvm;

import android.util.Log;
import android.view.View;

/**
 * @author <a href='mailto:zhaotengfei9@gmail.com'>Tengfei Zhao</a>
 */
public class MyHandlers {
    public static final String TAG = MyHandlers.class.getSimpleName();

    public void onClick(View view) {
        Log.e(TAG, "onClick");
    }
}
