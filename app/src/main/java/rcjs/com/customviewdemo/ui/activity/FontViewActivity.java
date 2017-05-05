package rcjs.com.customviewdemo.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rcjs.com.customviewdemo.R;
import rcjs.com.customviewdemo.widget.CommitButton;
import rcjs.com.customviewdemo.widget.FontView;

/**
 * Created by 仁昌居士 on 2017/5/3.
 * Description:
 */

public class FontViewActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fontview);
        ButterKnife.bind(this);

    }


}