package com.example.ming.myapplication;

import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewAnimationUtils;

import static android.R.anim.accelerate_decelerate_interpolator;

public class AboutActivity extends AppCompatActivity {

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        /**
         * android 4.1 之后默认添加的切换Activity进入以及退出的动画。过渡动画设置
         */
        this.startActivity(new Intent(),
                ActivityOptions.makeCustomAnimation(this,android.R.anim.accelerate_decelerate_interpolator,android.R.anim.accelerate_decelerate_interpolator).toBundle());

        /**
         * 之前默认版本实现的切换Activity跳转实现动画方式。
         */
        overridePendingTransition(android.R.anim.accelerate_decelerate_interpolator,android.R.anim.accelerate_decelerate_interpolator);

        /**
         * 创建原型荡漾效果。仅限在Android5.0 实现。
         */
//        ViewAnimationUtils.createCircularReveal(this,);
    }
}
