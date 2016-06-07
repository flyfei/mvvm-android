package com.tovi.mvvm.loadimg;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * @author <a href='mailto:zhaotengfei9@gmail.com'>Tengfei Zhao</a>
 */
@SuppressWarnings("unused")
public class BindingAdapters {

    @BindingAdapter({"imageUrl", "error"})
    public static void loadImage(ImageView imageView, String imageUrl, String error) {
        Picasso.with(imageView.getContext()).load(imageUrl).into(imageView);
    }
}
