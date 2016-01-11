package com.example.ming.myapplication;

import android.app.Instrumentation;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.test.InstrumentationTestCase;

/**
 * 测试启动Activity
 * Created by Ming on 2016/1/11.
 */
public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

    public MainActivityTest() {
        super(MainActivity.class);
    }

    public void testLogin(){
        Intent mIntent=new Intent();
        mIntent.setClassName("com.example.ming.myapplication","com.example.ming.myapplication.MainActivity");
        mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Instrumentation mInstrumentation=getInstrumentation();
        mInstrumentation.startActivitySync(mIntent);
    }

}