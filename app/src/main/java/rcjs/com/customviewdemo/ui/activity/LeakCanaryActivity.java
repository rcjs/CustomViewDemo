package rcjs.com.customviewdemo.ui.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.squareup.leakcanary.RefWatcher;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rcjs.com.customviewdemo.Constans.MyApplication;
import rcjs.com.customviewdemo.R;

/**
 * Created by 仁昌居士 on 2017/4/28.
 * Description:
 */

public class LeakCanaryActivity extends AppCompatActivity {
    @BindView(R.id.button)
    Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leakcanary);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.button})
    public void onClick(View view) {
        RefWatcher refWatcher = MyApplication.getRefWatcher(this);
        refWatcher.watch(this);
        startAsyncTask();
    }


    public void startAsyncTask(){
        new AsyncTask<Void,Void,Void>(){
            @Override
            protected Void doInBackground(Void... params) {
                Log.v("家俊","doInBackground");
                SystemClock.sleep(10000);
                return null;
            }
        }.execute();
    }}