package com.tovi.mvvm.addnote2;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.tovi.mvvm.BR;

import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author <a href='mailto:zhaotengfei9@gmail.com'>Tengfei Zhao</a>
 */

/**
 * Data binding; events handling.
 */
public class AddNoteViewModel extends BaseObservable implements AddNoteContract.View {

    private Context mApplicationContext;

    private AddNoteContract.ViewActivity mViewActivity;

    private String title = "title";

    private String content = "content";

    public AddNoteViewModel(@NonNull Context applicationContext) {
        this.mApplicationContext = checkNotNull(applicationContext);
    }

    public AddNoteViewModel(@NonNull AddNoteContract.ViewActivity viewActivity) {
        this.mViewActivity = checkNotNull(viewActivity);
        this.mApplicationContext = checkNotNull(viewActivity.getActivity()).getApplicationContext();
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
    public void toast(String msg) {
        Toast.makeText(mApplicationContext, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void openSecondActivity(Map map) {

    }

    @Override
    public void openDialog() {
        showDialog();
    }
    
    private void showDialog() {
        checkNotNull(this.mViewActivity);
        checkNotNull(this.mViewActivity.getActivity());
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(this.mViewActivity.getActivity());
        builder.setTitle("Material Design Dialog");
        builder.setMessage("这是 android.support.v7.app.AlertDialog 中的样式");
        builder.setNegativeButton("取消", null);
        builder.setPositiveButton("确定", null);
        builder.show();
    }
}
