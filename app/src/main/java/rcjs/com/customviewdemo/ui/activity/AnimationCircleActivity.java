package rcjs.com.customviewdemo.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rcjs.com.customviewdemo.R;
import rcjs.com.customviewdemo.widget.AnimationCircleView;

/**
 * Created by 仁昌居士 on 2017/5/3.
 * Description:
 */

public class AnimationCircleActivity extends AppCompatActivity {

    @BindView(R.id.circle)
    AnimationCircleView mAnimationCircleView;
    @BindView(R.id.start)
    Button start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animationcircle);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.start)
    public void onClick(View view) {
          /*
         * 开线程
         */
        new Thread(mAnimationCircleView).start();
    }

}
