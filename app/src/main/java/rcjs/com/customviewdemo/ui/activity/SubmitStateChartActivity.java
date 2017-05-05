package rcjs.com.customviewdemo.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.squareup.leakcanary.RefWatcher;

import rcjs.com.customviewdemo.Constans.MyApplication;
import rcjs.com.customviewdemo.R;
import rcjs.com.customviewdemo.widget.SubmitStateChartView;


/**
 * Created by 仁昌居士 on 2017/4/28.
 * Description:
 */

public class SubmitStateChartActivity  extends AppCompatActivity {
    private SubmitStateChartView mSubmitStateChartView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submitchart);
        initView();
    }

    private void initView() {
        RefWatcher refWatcher = MyApplication.getRefWatcher(this);
        refWatcher.watch(this);
        mSubmitStateChartView = (SubmitStateChartView) findViewById(R.id.chart);
        mSubmitStateChartView.setData(2016,12,9,2);
        mSubmitStateChartView.setData(2016,11,9,1);
        mSubmitStateChartView.setData(2016,10,5,10);
        mSubmitStateChartView.setData(2016,8,9,3);
        mSubmitStateChartView.setData(2016,4,20,2);
        mSubmitStateChartView.setData(2016,12,13,3);
        mSubmitStateChartView.setData(2016,12,14,3);
        mSubmitStateChartView.setData(2016,2,15,4);
    }
}