package com.tovi.mvvm;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.tovi.mvvm.loadimg.LoadImgActivity;
import com.tovi.mvvm.updateui.baseObservable.BaseObservableActivity;
import com.tovi.mvvm.updateui.observableField.ObservableFieldActivity;

/**
 * @author <a href='mailto:zhaotengfei9@gmail.com'>Tengfei Zhao</a>
 */
public class MyHandlers {

    public static final String TAG = MyHandlers.class.getSimpleName();
    public Context context;

    /**
     * Please note that there is a memory leak
     */

    MyHandlers(Context context) {
        if (context != null)
            this.context = context.getApplicationContext();
    }

    public void onClick(View view) {
        Log.e(TAG, "onClick");
    }

    public void toListActivity(View view) {
        if (context != null) {
            Intent intent = new Intent(context, ListActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }

    public void toBaseObservableActivity(View view) {
        if (context != null) {
            Intent intent = new Intent(context, BaseObservableActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }

    public void toObservableFieldActivity(View view) {
        if (context != null) {
            Intent intent = new Intent(context, ObservableFieldActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }

    public void toLoadImgActivity(View view) {
        if (context != null) {
            Intent intent = new Intent(context, LoadImgActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }
}
