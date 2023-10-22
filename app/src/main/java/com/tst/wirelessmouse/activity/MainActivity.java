package com.tst.wirelessmouse.activity;

import static com.tst.wirelessmouse.transform.TransformManager.startConnecting;
import static com.tst.wirelessmouse.transform.TransformManager.stopConnectiong;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;

import com.tst.wirelessmouse.Global;
import com.tst.wirelessmouse.R;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Global.setMainActivity(this);

        // 设置 FLAG_KEEP_SCREEN_ON 标志，使屏幕保持常亮
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    @Override
    protected void onStart() {
        super.onStart();
        startConnecting();
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopConnectiong();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}