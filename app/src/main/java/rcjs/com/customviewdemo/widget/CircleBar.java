package rcjs.com.customviewdemo.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * Created by 仁昌居士 on 2017/5/4.
 * Description:圆形渐变色进度条
 */

public class CircleBar extends View {
    private Paint circlePaint;
    private int radius;// 圆环半径
    private Paint arcPaint;
    private int mLeft;
    private int mRight;
    private int mTop;
    private int mBottom;
    private int cirlceStrokeWidth = 50;
    private int arcStrokeWidth = 30;
    private int cirlceRadius;
    private int arcRadius;
    private int mMin;

    public CircleBar(Context context) {
        this(context, null);
    }

    public CircleBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

    }

    private void initView() {
        Log.d("rcjs_width","width"+mMin);
        cirlceRadius = mMin / 2 - cirlceStrokeWidth/2;

        mLeft = cirlceStrokeWidth/2 ;
        mRight = mMin -cirlceStrokeWidth / 2;
        mTop = cirlceStrokeWidth/2 ;
        mBottom =  mMin - cirlceStrokeWidth / 2;
        initPaint();
    }


    /**
     * 初始化画笔
     */
    private void initPaint() {
        // 实例化画笔并打开抗锯齿
        circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    /*
     * 设置画笔样式为描边，圆环嘛……当然不能填充不然就么意思了
     *
     * 画笔样式分三种：
     * 1.Paint.Style.STROKE：描边
     * 2.Paint.Style.FILL_AND_STROKE：描边并填充
     * 3.Paint.Style.FILL：填充
     */
        circlePaint.setStyle(Paint.Style.STROKE);

        // 设置画笔颜色为浅灰色
        circlePaint.setColor(Color.BLUE);

    /*
     * 设置描边的粗细，单位：像素px
     * 注意：当setStrokeWidth(0)的时候描边宽度并不为0而是只占一个像素
     */
        circlePaint.setStrokeWidth(cirlceStrokeWidth);

        arcPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        arcPaint.setStrokeWidth(arcStrokeWidth);//设置线宽
        arcPaint.setColor(Color.RED);//设置颜色
        arcPaint.setStyle(Paint.Style.FILL);//默认设置画笔为填充模式
        arcPaint.setStrokeCap(Paint.Cap.ROUND);

        arcPaint.setStyle(Paint.Style.STROKE);//设置画笔为线条模式

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int height = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);
        int width = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        // 获取View最短边的长度
        mMin = Math.min(width, height);
        setMeasuredDimension(mMin, mMin);// 强制改View为以最短边为长度的正方形

        initView();
        /**
         * 这四个参数分别代表的意思是：left   top   right   bottom  左 上 右 下
         * left ： 矩形左边的X坐标
         * top:    矩形顶部的Y坐标
         * right :  矩形右边的X坐标
         * bottom： 矩形底部的Y坐标
         * 其实就是矩形的左上角和右下角的坐标值
         */

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //绘制椭圆

        canvas.drawCircle(mMin / 2, mMin / 2, cirlceRadius, circlePaint);

        RectF rectF = new RectF(mLeft, mTop, mRight, mBottom);
        // 绘制圆环
        canvas.drawArc(rectF, 0, 270, false, arcPaint);
       // canvas.drawRect(rectF, arcPaint);
    }


    /**
     * 设置渐变色
     *
     * @param shaderColor
     */
    public void setShaderColor(int[] shaderColor) {
        Shader newShader = new SweepGradient(0, 0, shaderColor, null);
        arcPaint.setShader(newShader);
    }



}
