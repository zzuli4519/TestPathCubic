package com.example.ming.myapplication;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试贝塞尔曲线绘制
 */
public class MainActivity extends AppCompatActivity {

    final String TAG=MainActivity.class.getSimpleName();
    private View mView;

    private ServiceConnection mServiceConnection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "-----openActivity", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent mIntent=new Intent();
                mIntent.setClass(getApplicationContext(),AboutActivity.class);
                startActivity(mIntent);
            }
        });


        //创建绑定Service
//        bindService(new Intent(this,MyService.class),mServiceConnection,BIND_AUTO_CREATE);


        startService(new Intent(this,MyService.class));
    }


/*    private FllowerAnimation mFllowerAnimation;
    private Button mButton;

    private void initView(){
        mFllowerAnimation= (FllowerAnimation) findViewById(R.id.fllowerAnimationView);
        mButton= (Button) findViewById(R.id.run);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFllowerAnimation.startAnimation();
            }
        });
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
