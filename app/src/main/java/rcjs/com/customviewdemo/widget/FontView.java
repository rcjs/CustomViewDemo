package rcjs.com.customviewdemo.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by 仁昌居士 on 2017/5/3.
 * Description:字体 Paint属性相关view
 */

public class FontView extends View {
    private static final String mContent = "rcjs仁昌居士ξτβбпшㄎㄊěǔぬも┰┠№＠↓";

    private Paint textPaint, centerLinePaint, baseLinePaint;// 文本的画笔和中心线的画笔

    private int baseX, baseY;// Baseline绘制的XY坐标

    public FontView(Context context) {
        this(context, null);
    }

    public FontView(Context context, AttributeSet attrs) {
        super(context, attrs);

        // 初始化画笔
        initPaint();
    }

    /**
     * 初始化画笔
     */
    private void initPaint() {
        // 实例化画笔
        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setTextSize(70);
        textPaint.setColor(Color.BLACK);
        textPaint.setTextScaleX(0.5F);

        centerLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        centerLinePaint.setStyle(Paint.Style.STROKE);
        centerLinePaint.setStrokeWidth(5);
        centerLinePaint.setColor(Color.RED);


        baseLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        baseLinePaint.setStyle(Paint.Style.STROKE);
        baseLinePaint.setStrokeWidth(5);
        baseLinePaint.setColor(Color.BLUE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 计算Baseline绘制的起点X轴坐标
        baseX = (int) (canvas.getWidth() / 2 - textPaint.measureText(mContent) / 2);

        // 计算Baseline绘制的Y坐标
        baseY = (int) ((canvas.getHeight() / 2) - ((textPaint.descent() + textPaint.ascent()) / 2));

        textPaint.setTextAlign(Paint.Align.LEFT);
        canvas.drawText(mContent, canvas.getWidth() / 2, baseY, textPaint);
        textPaint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(mContent, canvas.getWidth() / 2, baseY + 100, textPaint);
        textPaint.setTextAlign(Paint.Align.RIGHT);
        canvas.drawText(mContent, canvas.getWidth() / 2, baseY + 200, textPaint);
        //  canvas.drawText(mContent, baseX, baseY, textPaint);

        // 为了便于理解我们在画布中心处绘制一条中线
        //    canvas.drawLine(0, baseY, canvas.getWidth(), baseY, baseLinePaint);

        // 为了便于理解我们在画布中心处绘制一条中线
        //    canvas.drawLine(0, canvas.getHeight() / 2, canvas.getWidth(), canvas.getHeight() / 2, centerLinePaint);
    }
}