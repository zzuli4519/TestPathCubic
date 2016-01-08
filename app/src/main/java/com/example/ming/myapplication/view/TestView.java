package com.example.ming.myapplication.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 绘制自定义surfaceView
 * Created by Ming on 2016/1/7.
 */
public class TestView extends View{

    private Paint mPaint_Q;

    private int paint_Q_color;

    private Path mPathQure;

    private List<CPoint> mListData;

    public TestView(Context context) {
        this(context,null);
    }

    public TestView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TestView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        WindowManager mWindowManager= (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        width = mWindowManager.getDefaultDisplay().getWidth();
        height = (int) (mWindowManager.getDefaultDisplay().getHeight() * 3 / 2f);
        initViewConfig();
    }

    private int width;

    private int height;

    private int quadCount=10;

    private void initViewConfig(){
        initPaint();
        int max = (int) (width * 3 / 4f);
        int min = (int) (width / 4f);
        Random random = new Random();
        int s = (random.nextInt(max) % (max - min + 1)) + min;
        CPoint CPoint = new CPoint(s, 0);
        builderPath(CPoint);
    }

    private void initPaint(){
        mPaint_Q=new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint_Q.setStyle(Paint.Style.STROKE);
        mPaint_Q.setStrokeWidth(2);
        paint_Q_color=getResources().getColor(android.R.color.black);
        mPaint_Q.setColor(paint_Q_color);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        doDrawToView(canvas);
    }

    /**
     * 绘制线偏移位置
     */
    private int range = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 70, getResources().getDisplayMetrics());

    private List<CPoint> builderPath(CPoint point) {
        mListData = new ArrayList<CPoint>();
        Random random = new Random();
        for (int i = 0; i < quadCount; i++) {
            if (i == 0) {
                mListData.add(point);
            } else {
                CPoint tmp = new CPoint(0, 0);
                if (random.nextInt(100) % 2 == 0) {
                    tmp.x = point.x + random.nextInt(range);
                } else {
                    tmp.x = point.x - random.nextInt(range);
                }
                tmp.y = (int) (height / (float) quadCount * i);
                mListData.add(tmp);
            }
        }
        return mListData;
    }

    private void doDrawToView(Canvas mCanvas){
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
        float intensity=0.2f;
        if (mListData.size() > 1) {
            for (int j = 0; j < mListData.size(); j++) {

                CPoint point = mListData.get(j);

                mCanvas.drawCircle(point.x,point.y,2,mPaint_Q);

                if (j == 0) {
                    CPoint next = mListData.get(j + 1);
                    point.dx = ((next.x - point.x) * intensity);
                    point.dy = ((next.y - point.y) * intensity);
                } else if (j == mListData.size() - 1) {
                    CPoint prev = mListData.get(j - 1);
                    point.dx = ((point.x - prev.x) * intensity);
                    point.dy = ((point.y - prev.y) * intensity);
                } else {
                    CPoint next = mListData.get(j + 1);
                    CPoint prev = mListData.get(j - 1);
                    point.dx = ((next.x - prev.x) * intensity);
                    point.dy = ((next.y - prev.y) * intensity);
                }

                // create the cubic-spline path
                if (j == 0) {
                    mPathQure.moveTo(point.x, point.y);
                } else {
                    CPoint prev = mListData.get(j - 1);
                    mPathQure.cubicTo(prev.x + prev.dx, (prev.y + prev.dy),
                            point.x - point.dx, (point.y - point.dy),
                            point.x, point.y);
                }
            }

        }
        mCanvas.drawPath(mPathQure, mPaint_Q);
    }


    private class CPoint {

        public float x = 0f;
        public float y = 0f;

        public float dx = 0f;

        public float dy = 0f;

        public CPoint(float x, float y) {
            this.x = x;
            this.y = y;
        }
    }

}
