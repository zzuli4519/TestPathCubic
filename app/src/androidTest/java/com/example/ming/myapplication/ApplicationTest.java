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
 * 单元测试说明
 * Android测试环境主要特征为：
 * 1、可以访问Android系统对象
 * 2、Instrumentation框架可以控制以及测试程序
 * 3、Android系统常用对象的模拟版本
 *
 * Junit TestCase类，不能使用instrumentation框架，但是这些类可以访问系统对象（context）可以访问资源，文件，数据库等</br>
 * 其中包括 ApplicationTestCase、providerTestCase2、ServiceTestCase
 *
 * Instrumentation TestCase类
 * 其中包括{@link android.test.ActivityTestCase ActivityTestCase}
 * {@link android.test.SyncBaseInstrumentation SyncBaseInstrumentation}
 * {@link android.test.SingleLaunchActivityTestCase SingleLaunchActivityTestCase}
 * {@link android.test.ActivityUnitTestCase ActivityUnitTestCase}
 *
 * 不同于其他的测试类，这个测试类不能注入模拟的Intent
 * {@link android.test.ActivityInstrumentationTestCase2 ActivityInstrumentationTestCase2}
 *
 * 其中还有集成Junit的Assert类，其中包括了MoreAsserts和ViewAsserts;
 * MoreAsserts包含有更多的断言方法。 如 assertContainsRegex(String, String)测试正则表达式
 * ViewAsserts类包含关于Android View的有用断言方法 。如assertHasScreenCoordinates(View, View, int, int)，
 * 可以测试View在可视区域的特定X、Y位置。这些Assert简化了UI中几何图形和对齐方式的测试。
 * 详细文档请参考：
 * <a>http://www.cnblogs.com/carbs/archive/2013/01/12/2857596.html<a/>
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