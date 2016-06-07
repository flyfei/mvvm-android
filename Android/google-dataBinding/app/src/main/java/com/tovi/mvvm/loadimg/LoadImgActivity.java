package com.tovi.mvvm.loadimg;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.tovi.mvvm.R;
import com.tovi.mvvm.databinding.ActivityLoadImgBinding;

public class LoadImgActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityLoadImgBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_load_img);
        Phone phone = new Phone();
        phone.url = "http://ww3.sinaimg.cn/bmiddle/0065It2Vgw1f4ia9c5ig8j318g0xcard.jpg";
        binding.setPhone(phone);

    }
}
