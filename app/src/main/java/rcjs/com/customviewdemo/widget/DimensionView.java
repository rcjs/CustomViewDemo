package rcjs.com.customviewdemo.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;

import rcjs.com.customviewdemo.R;


/**
 * Created by 仁昌居士 on 2017/5/10.
 * Description:
 */

public class DimensionView extends AppCompatTextView {


    public DimensionView(Context context) {
        this(context, null);
    }

    public DimensionView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DimensionView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint(context, attrs);
    }

    /**
     * 1.1 初始化画笔
     */
    private void initPaint(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.dimenssionview);

        float a1 = array.getDimension(R.styleable.dimenssionview_info_dp, 0);
        int a2 = array.getDimensionPixelOffset(R.styleable.dimenssionview_info_dp, 0);
        int a3 = array.getDimensionPixelSize(R.styleable.dimenssionview_info_dp, 0);
        int a4 = array.getLayoutDimension(R.styleable.dimenssionview_info_dp, 0);
        int a5 = array.getLayoutDimension(R.styleable.dimenssionview_info_dp, "rcjs");

        float b1 = array.getDimension(R.styleable.dimenssionview_info_px, 0);
        int b2 = array.getDimensionPixelOffset(R.styleable.dimenssionview_info_px, 0);
        int b3 = array.getDimensionPixelSize(R.styleable.dimenssionview_info_px, 0);
        int b4 = array.getLayoutDimension(R.styleable.dimenssionview_info_px, 0);
        int b5 = array.getLayoutDimension(R.styleable.dimenssionview_info_px, "rcjs");

        float c1 = array.getDimension(R.styleable.dimenssionview_info_sp, 0);
        int c2 = array.getDimensionPixelOffset(R.styleable.dimenssionview_info_sp, 0);
        int c3 = array.getDimensionPixelSize(R.styleable.dimenssionview_info_sp, 0);
        int c4 = array.getLayoutDimension(R.styleable.dimenssionview_info_sp, 0);
        int c5 = array.getLayoutDimension(R.styleable.dimenssionview_info_sp, "rcjs");

        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        Log.d("郑毅","屏幕密度"+displayMetrics.density);
        Log.d("郑毅","屏幕密度densityDpi"+displayMetrics.densityDpi);
        Log.d("郑毅", "a=16dp , b=16px,c=16sp");
        Log.d("郑毅", "getDimension= " + a1 + ", getDimension=" + b1 + ",getDimension=" + c1);
        Log.d("郑毅", "getDimensionPixelOffset= " + a2 + ", getDimensionPixelOffset=" + b2 + ",getDimensionPixelOffset=" + c2);
        Log.d("郑毅", "getDimensionPixelSize= " + a3 + ", getDimensionPixelSize=" + b3 + ",getDimensionPixelSize=" + c3);
        Log.d("郑毅", "getLayoutDimension int= " + a4 + ", getLayoutDimension int=" + b4 + ",getLayoutDimension int=" + c4);
        Log.d("郑毅", "getLayoutDimension name= " + a5 + ", getLayoutDimension name=" + b5 + ",getLayoutDimension name=" + c5);


        // 记得一定要回收
        array.recycle();
    }


}
