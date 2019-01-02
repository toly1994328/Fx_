package com.toly1994.fx.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Picture;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.toly1994.fx.analyze.HelpDraw;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * 作者：张风捷特烈<br/>
 * 时间：2019/1/1 0001:8:38<br/>
 * 邮箱：1981462002@qq.com<br/>
 * 说明：参数方程
 */
public class MathParmView extends View {
    private static final String TAG = "MathPolarView";
    private Point mCoo = new Point(500, 700);//坐标系
    private Picture mCooPicture;//坐标系canvas元件
    private Picture mGridPicture;//网格canvas元件
    private Paint mHelpPint;//辅助画笔

    private Paint mPaint;//主画笔
    private Path mPath;//主路径

    private TreeSet<Float> Df = new TreeSet<>();//定义域
    private Map<Float, Float> funMap = new HashMap<>();//映射表
    private Paint mTextPaint;//文字画笔

    public MathParmView(Context context) {
        this(context, null);
    }

    public MathParmView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();//初始化
    }

    private void init() {
        //初始化主画笔
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLUE);
        mPaint.setStrokeWidth(4);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        //初始化主路径
        mPath = new Path();

        //文字路径
        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextSize(30);

        //初始化辅助
        mHelpPint = HelpDraw.getHelpPint(Color.RED);
        mCooPicture = HelpDraw.getCoo(getContext(), mCoo);
        mGridPicture = HelpDraw.getGrid(getContext());

        initDf();
        map();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        HelpDraw.draw(canvas, mGridPicture, mCooPicture);
        canvas.save();
        canvas.translate(mCoo.x, mCoo.y);
        canvas.scale(1, -1);//y轴向上
        //根据映射表绘制点
        drawMap(canvas, funMap);
        canvas.restore();

        canvas.save();
        canvas.translate(mCoo.x, mCoo.y);
        drawHelp(canvas);
        canvas.restore();
    }

    private void drawHelp(Canvas canvas) {
//        canvas.drawText("ρ= 100*(1-4*sinθ),[" + Df.first() + "," + Df.last() + "]", Df.last(), -y(Df.last()+50), mTextPaint);
    }

    /**
     * 初始化定义域
     */
    private void initDf() {
        for (float i = 0; i <= 360 ; i++) {
            Df.add(i);//初始化定义域
        }
    }

    /**
     * 绘制映射表:
     *
     * @param canvas 画笔
     * @param map    点集映射表
     */
    private void drawMap(Canvas canvas, Map<Float, Float> map) {
        map.forEach((k, v) -> {
            canvas.drawPoint(k, v, mPaint);
        });
    }

    /**
     * 对应法则:y=a√(cos2θ)sinθ
     *
     * @param thta 原像(自变量)
     * @return y像(因变量)
     */
    private float y(Float thta) {
        float y = (float) (200 * Math.sqrt(Math.cos(2*thta))*Math.sin(thta));
        return y;
    }

    /**
     * 对应法则:x=a√(cos2θ )cosθ
     *
     * @param thta 原像(自变量)
     * @return x像(因变量)
     */
    private float x(Float thta) {
        float x = (float) (200 * Math.sqrt(Math.cos(2*thta))*Math.cos(thta));
        return x;
    }

    /**
     * 遍历定义域,将原像x和像f(x)加入映射表
     */
    private void map() {
        Df.forEach(x -> {
            float thta = (float) (Math.PI / 180 * x);


            funMap.put(x(thta), y(thta));
        });
        //添加所有点
    }

}