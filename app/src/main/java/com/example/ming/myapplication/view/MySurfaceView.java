package com.example.ming.myapplication.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.os.Build;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 绘制自定义surfaceView
 * Created by Ming on 2016/1/7.
 */
public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback{

    private Paint mPaint_Q;
    private int paint_Q_color;
    private SurfaceHolder mSurfaceHolder;
    private Path mPathQure;
    private MyThread myThread;

    private List<Point> mListData;

    private int position;

    public MySurfaceView(Context context) {
        this(context,null);
    }

    public MySurfaceView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    @TargetApi(Build.VERSION_CODES.M)
    public MySurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        WindowManager mWindowManager= (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        width = mWindowManager.getDefaultDisplay().getWidth();
        height = (int) (mWindowManager.getDefaultDisplay().getHeight() * 3 / 2f);
        initSurfaceView();
    }

    private int width;
    private int height;


    private int quadCount=10;

    private void getDataTest(){
        if(mListData==null){
            mListData=new ArrayList<Point>();
        }
        Random random = new Random();
        Point mPoint=new Point(100,100);
        for (int i = 0; i < quadCount; i++) {
            if (i == 0) {
                mListData.add(mPoint);
            } else {
                Point tmp = new Point(0, 0);
                if (random.nextInt(100) % 2 == 0) {
                    tmp.x = mPoint.x + random.nextInt(70);
                } else {
                    tmp.x = mPoint.x - random.nextInt(70);
                }
                tmp.y = (int) (height / (float) quadCount * i);
                mListData.add(tmp);
            }
        }
    }

    private void initSurfaceView(){
        mSurfaceHolder=this.getHolder();
        mSurfaceHolder.addCallback(this);
        initPaint();
        getDataTest();
    }

    private void initPaint(){
        mPaint_Q=new Paint();
        mPaint_Q.setAntiAlias(true);
        mPaint_Q.setStyle(Paint.Style.STROKE);
        mPaint_Q.setStrokeWidth(2);
        paint_Q_color=getResources().getColor(android.R.color.white);
        mPaint_Q.setColor(paint_Q_color);
    }

    /**
     * SurfaceView 初始创建
     * @param surfaceHolder SurfaceHolder
     */
    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        myThread=new MyThread();
        myThread.isRunning=true;
        myThread.start();
    }

    /**
     * surface创建发生变化
     * @param surfaceHolder
     * @param i
     * @param i1
     * @param i2
     */
    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    /**
     * 创建surface 销毁
     * @param surfaceHolder
     */
    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        myThread.isRunning=false;
        try {
            myThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    /**
     * 执行绘制县城
     */
    class MyThread extends Thread{

        private boolean isRunning;

        @Override
        public void run() {
            Canvas mCanvas = null;
//            while(isRunning){
//                synchronized (mSurfaceHolder){
//                    try {
//                        mCanvas=mSurfaceHolder.lockCanvas(null);
//                        drawToSurfaceView(mCanvas);
//                        Thread.sleep(20);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }finally {
//                        if(mCanvas!=null){
//                            mSurfaceHolder.unlockCanvasAndPost(mCanvas);
//                        }
//                    }
//                }
//            }

            mCanvas=mSurfaceHolder.lockCanvas(null);
            drawToSurfaceView(mCanvas);
            if(mCanvas!=null){
                mSurfaceHolder.unlockCanvasAndPost(mCanvas);
            }
        }
    }

    private void drawToSurfaceView(Canvas mCanvas){
        if(mCanvas==null){
            return;
        }
        if(mPathQure==null){
            mPathQure=new Path();
        }else{
            mPathQure.reset();
        }
        /**
         * 先移动到绘制目标区域
         */
        mPathQure.moveTo(mListData.get(0).x, mListData.get(0).y);
        for (int i=0; i < mListData.size()-2; i ++){
            mPathQure.quadTo(mListData.get(i + 1).x, mListData.get(i + 1).y, mListData.get(i + 2)
                    .x, mListData.get(i + 2).y);
        }
        mCanvas.drawPath(mPathQure, mPaint_Q);
    }

}
