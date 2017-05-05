package rcjs.com.customviewdemo.widget;

import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import rcjs.com.customviewdemo.utils.UIHelper;

/**
 * Created by 仁昌居士 on 2017/5/4.
 * Description:阴影属性的相关的view
 */

public class MaskFilterView extends View {
    private static final int RECT_SIZE = 800;
    private Paint mPaint;// 画笔
    private Context mContext;// 上下文环境引用

    private int left, top, right, bottom;//

    public MaskFilterView(Context context) {
        this(context, null);
    }

    public MaskFilterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;

        // 初始化画笔
        initPaint();
        setLayerType(LAYER_TYPE_SOFTWARE,null);
        // 初始化资源
        initRes(context);
    }

    /**
     * 初始化画笔
     */
    private void initPaint() {
        // 实例化画笔
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(0xFF603811);

        // 设置画笔遮罩滤镜
        mPaint.setMaskFilter(new BlurMaskFilter(50, BlurMaskFilter.Blur.INNER));
    }

    /**
     * 初始化资源
     */
    private void initRes(Context context) {
        /*
         * 计算位图绘制时左上角的坐标使其位于屏幕中心
         */
        left = UIHelper.getWindowWidthHeight(context)[0] / 2 - RECT_SIZE / 2;
        top = UIHelper.getWindowWidthHeight(context)[1] / 2 - RECT_SIZE / 2;
        right = UIHelper.getWindowWidthHeight(context)[0] / 2 + RECT_SIZE / 2;
        bottom = UIHelper.getWindowWidthHeight(context)[1] / 2 + RECT_SIZE / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.GRAY);

        // 画一个矩形
        canvas.drawRect(left, top, right, bottom, mPaint);
    }
}
