package rcjs.com.customviewdemo.widget;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.PathMeasure;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import static android.R.attr.duration;
import static android.R.attr.path;

/**
 * Created by 仁昌居士 on 2017/5/4.
 * Description:提交按钮
 */

public class CommitButton extends View {
    /**
     * 圆角矩形画笔
     */
    private Paint roundRectPaint;

    /**
     * 文字画笔
     */
    private Paint textPaint;

    /**
     * 背景颜色
     */
    private int bg_color = 0xffbc7d53;

    /**
     * 根据view的大小设置成矩形
     */
    private RectF rectf = new RectF();

    /**
     * 文字绘制所在矩形
     */
    private Rect textRect = new Rect();
    /**
     * 两圆圆心之间的距离
     */
    private int two_circle_distance;

    /**
     * view的宽度
     */
    private int width;
    /**
     * view的高度
     */
    private int height;

    /**
     * 圆角半径
     */
    private int circleAngle;

    /**
     * 按钮文字字符串
     */
    private String buttonString = "确认完成";

    /**
     * 路径--用来获取对勾的路径
     */
    private Path tickPath = new Path();

    /**
     * 默认两圆圆心之间的距离=需要移动的距离
     */
    private int default_two_circle_distance;

    /**
     * 取路径的长度
     */
    private PathMeasure pathMeasure;

    /**
     * 动画集
     */
    private AnimatorSet animatorSet = new AnimatorSet();

    /**
     * 矩形到圆角矩形过度的动画
     */
    private ValueAnimator animator_rect_to_angle;
    /**
     * 矩形到正方形过度的动画
     */
    private ValueAnimator animator_rect_to_square;
    /**
     * view上移的动画
     */
    private ObjectAnimator animator_move_to_up;
    /**
     * 绘制对勾（√）的动画
     */
    private ValueAnimator animator_draw_ok;

    /**
     * 是否开始绘制对勾
     */
    private boolean startDrawOk = false;

    /**
     * 对路径处理实现绘制动画效果
     */
    private PathEffect effect;

    /**
     * 对勾（√）画笔
     */
    private Paint okPaint;

    /**
     * view向上移动距离
     */
    private int move_distance = 300;









    public CommitButton(Context context) {
        this(context, null);
    }

    public CommitButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();

    }

    /**
     * 初始化画笔
     */
    private void initPaint() {

        roundRectPaint = new Paint();
        roundRectPaint.setStrokeWidth(4);
        roundRectPaint.setStyle(Paint.Style.FILL);
        roundRectPaint.setAntiAlias(true);
        roundRectPaint.setColor(bg_color);

        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG); //初始化Paint，并且设置消除锯齿
        textPaint.setTextSize(40);
        textPaint.setColor(Color.WHITE);
        textPaint.setTextAlign(Paint.Align.CENTER);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        draw_oval_to_circle(canvas);
        drawText(canvas);
    }

    /**
     * 绘制长方形变成圆形
     *
     * @param canvas 画布
     */
    private void draw_oval_to_circle(Canvas canvas) {

        rectf.left = two_circle_distance;
        rectf.top = 0;
        rectf.right = width - two_circle_distance;
        rectf.bottom = height;

        //画圆角矩形
        canvas.drawRoundRect(rectf, circleAngle, circleAngle, roundRectPaint);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.d("rcjs", "w" + w + "h" + h + "oldw" + oldw + "oldh" + oldh);
        width = w;
        height = h;
        circleAngle = h / 2;
         default_two_circle_distance = (w - h) / 2;

        initOk();
        initAnimation();

    }

    /**
     * 绘制文字
     *
     * @param canvas 画布
     */
    private void drawText(Canvas canvas) {
        textRect.left = 0;
        textRect.top = 0;
        textRect.right = width;
        textRect.bottom = height;
        Paint.FontMetricsInt fontMetrics = textPaint.getFontMetricsInt();
        int baseline = (textRect.bottom + textRect.top - fontMetrics.bottom - fontMetrics.top) / 2;
        //文字绘制到整个布局的中心位置
        canvas.drawText(buttonString, textRect.centerX(), baseline, textPaint);
    }

  /*  @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int height = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);
        int width = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        setMeasuredDimension(width, height);// 强制改View为以最短边为长度的正方形

        Log.d("rcjs", "width" + width + "height" + height);

    }*/

    /**
     * 初始化所有动画
     */
    private void initAnimation() {
        set_rect_to_angle_animation();
        set_rect_to_circle_animation();
        set_move_to_up_animation();
        set_draw_ok_animation();

        animatorSet
                .play(animator_move_to_up)
                .before(animator_draw_ok)
                .after(animator_rect_to_square)
                .after(animator_rect_to_angle);
    }

    /**
     * 设置矩形过度到圆角矩形的动画
     */
    private void set_rect_to_angle_animation() {
        animator_rect_to_angle = ValueAnimator.ofInt(0, height / 2);
        animator_rect_to_angle.setDuration(duration);
        animator_rect_to_angle.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                circleAngle = (int) animation.getAnimatedValue();
                invalidate();
            }
        });
    }


    /**
     * 设置圆角矩形过度到圆的动画
     */
    private void set_rect_to_circle_animation() {
        animator_rect_to_square = ValueAnimator.ofInt(0, default_two_circle_distance);
        animator_rect_to_square.setDuration(duration);
        animator_rect_to_square.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                two_circle_distance = (int) animation.getAnimatedValue();

                int alpha = 255 - (two_circle_distance * 255) / default_two_circle_distance;

                textPaint.setAlpha(alpha);

                invalidate();
            }
        });
    }


    /**
     * 设置view上移的动画
     */
    private void set_move_to_up_animation() {
        final float curTranslationY = this.getTranslationY();
        animator_move_to_up = ObjectAnimator.ofFloat(this, "translationY", curTranslationY, curTranslationY - move_distance);
        animator_move_to_up.setDuration(duration);
        animator_move_to_up.setInterpolator(new AccelerateDecelerateInterpolator());
    }


    /**
     * 绘制对勾的动画
     */
    private void set_draw_ok_animation() {
        animator_draw_ok = ValueAnimator.ofFloat(1, 0);
        animator_draw_ok.setDuration(duration);
        animator_draw_ok.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                startDrawOk = true;
                float value = (Float) animation.getAnimatedValue();

                effect = new DashPathEffect(new float[]{pathMeasure.getLength(), pathMeasure.getLength()}, value * pathMeasure.getLength());
                okPaint.setPathEffect(effect);
                invalidate();
            }
        });
    }


    /**
     * 绘制对勾
     */
    private void initOk() {

        //对勾的路径
        tickPath.moveTo(default_two_circle_distance + height / 8 * 3, height / 2);
        tickPath.lineTo(default_two_circle_distance + height / 2, height / 5 * 3);
        tickPath.lineTo(default_two_circle_distance + height / 3 * 2, height / 5 * 2);

        pathMeasure = new PathMeasure(tickPath, true);
    }
}
