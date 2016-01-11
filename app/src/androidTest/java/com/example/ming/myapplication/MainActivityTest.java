package com.example.ming.myapplication;

import android.app.Instrumentation;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.test.InstrumentationTestCase;

/**
 * Created by Ming on 2016/1/11.
 */
public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

    public MainActivityTest(Class<MainActivity> activityClass) {
        super("com.example.ming.myapplication", activityClass);
    }

    public void testLogin(){
        Intent mIntent=new Intent();
        mIntent.setClassName("com.example.ming.myapplication","com.example.ming.myapplication.MainActivity");
        Instrumentation mInstrumentation=getInstrumentation();
        mInstrumentation.startActivitySync(mIntent);
    }

}