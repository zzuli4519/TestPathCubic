package com.example.ming.myapplication;

import android.app.Application;
import android.app.Instrumentation;
import android.content.Intent;
import android.test.ApplicationTestCase;
import android.util.Log;

/**
 *
 * Android单元测试
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {

    final String TAG="ApplicationTest";
    private Instrumentation mInstrumentation;
    private MainActivity mainActivity;


    public ApplicationTest() {
        super(Application.class);
    }

    /**
     * 测试执行Test Android测试工程类
     */
    public void testFindAll(){
        mInstrumentation=new Instrumentation();
        Intent mIntent=new Intent();
        mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mIntent.setClassName("com.example.ming.myapplication","com.example.ming.myapplication.AboutActivity");
        mainActivity= (MainActivity) mInstrumentation.startActivitySync(mIntent);
    }

    /**
     * 执行测试之前准备工作
     * @throws Exception
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        Log.i(TAG, "onsetUp");
    }

    /**
     * 测试用例销毁
     * @throws Exception
     */
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        Log.i(TAG, "tearDown");
    }
}