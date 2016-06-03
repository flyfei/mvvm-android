package com.tovi.mvvm;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

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
}
