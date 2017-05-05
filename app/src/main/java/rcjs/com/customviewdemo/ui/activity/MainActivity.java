package rcjs.com.customviewdemo.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rcjs.com.customviewdemo.R;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.slid_menu, R.id.submit_state_chart,R.id.leak_canary,R.id.animation_circle,R.id.font_view,R.id.commit_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.slid_menu:
                openIntent(SlideMenuActivity.class);
                break;
            case R.id.submit_state_chart:
                openIntent(SubmitStateChartActivity.class);
                break;
            case R.id.leak_canary:
                openIntent(LeakCanaryActivity.class);
                break;
            case R.id.animation_circle:
                openIntent(AnimationCircleActivity.class);
                break;
            case R.id.font_view:
                openIntent(FontViewActivity.class);
                break;
            case R.id.commit_btn:
                openIntent(CommitButtonActivity.class);
            default:
                break;
        }
    }


    public void openIntent(Class<?> activityClass) {
        Intent intent = new Intent(this, activityClass);
        startActivity(intent);
    }
}