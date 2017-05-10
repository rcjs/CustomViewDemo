package rcjs.com.customviewdemo.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import rcjs.com.customviewdemo.R;

/**
 * Created by 仁昌居士 on 2017/5/10.
 * Description:
 */

public class DimenssionActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dimenssion);
        ButterKnife.bind(this);

    }


}