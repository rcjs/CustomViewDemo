package rcjs.com.customviewdemo.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by 仁昌居士 on 2017/5/3.
 * Description:多文字换行view
 */

public class StaticLayoutView extends View {
    private static final String TEXT = "该配合你演出的我演视而不见，在逼一个最爱你的人即兴表演，什么时候我们开始收起了底线，顺应时代的改变看那些拙劣的表演";
    private TextPaint mTextPaint;// 文本的画笔
    private StaticLayout mStaticLayout;// 文本布局

    public StaticLayoutView(Context context) {
        this(context, null);
    }

    public StaticLayoutView(Context context, AttributeSet attrs) {
        super(context, attrs);

        // 初始化画笔
        initPaint();
    }

    /**
     * 初始化画笔
     */
    private void initPaint() {
        // 实例化画笔
        mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextSize(50);
        mTextPaint.setColor(Color.BLACK);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mStaticLayout = new StaticLayout(TEXT, mTextPaint, canvas.getWidth(), Layout.Alignment.ALIGN_NORMAL, 1.0F, 0.0F, false);
        mStaticLayout.draw(canvas);
        canvas.restore();
    }

}