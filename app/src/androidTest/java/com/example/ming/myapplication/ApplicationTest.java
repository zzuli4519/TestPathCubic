package com.example.ming.myapplication;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.util.Log;
import android.widget.Toast;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }

    /**
     * 测试执行Test Android测试工程类
     */
    public void testFindAll(){
        Log.e("tag----test","error");
    }


}