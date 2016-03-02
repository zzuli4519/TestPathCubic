package com.example.ming.myapplication.view;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.ming.myapplication.R;

public class ActvityTestToolBar extends AppCompatActivity {

    private Toolbar mToolbar;
    private AppBarLayout mAppBarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actvity_test_tool_bar);
        mToolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mAppBarLayout= (AppBarLayout) findViewById(R.id.appBar);

        // AppBar的监听
        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                int maxScroll = appBarLayout.getTotalScrollRange();
                float percentage = (float) Math.abs(verticalOffset) / (float) maxScroll;
                mToolbar.setBackgroundColor(Color.argb((int) (percentage * 255), 19, 121, 214));
            }
        });


    }

}
