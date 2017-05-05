package rcjs.com.customviewdemo.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rcjs.com.customviewdemo.R;
import rcjs.com.customviewdemo.widget.AnimationButton;

/**
 * Created by 仁昌居士 on 2017/5/5.
 * Description:带动画效果的按钮
 */

public class CommitButtonActivity extends AppCompatActivity {

    @BindView(R.id.commit_btn)
    AnimationButton animationButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commitbutton);
        ButterKnife.bind(this);
        animationButton.setAnimationButtonListener(new AnimationButton.AnimationButtonListener() {
            @Override
            public void onClickListener() {
                animationButton.start();
            }

            @Override
            public void animationFinish() {
                Toast.makeText(CommitButtonActivity.this,"动画执行完毕",Toast.LENGTH_SHORT).show();
//                finish();
            }
        });
    }




}