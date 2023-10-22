package com.tst.wirelessmouse.activity;

import static com.tst.wirelessmouse.listener.ListenerManager.startListener;
import static com.tst.wirelessmouse.transform.TransformManager.startConnecting;
import static com.tst.wirelessmouse.transform.TransformManager.stopConnectiong;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.tst.wirelessmouse.Global;
import com.tst.wirelessmouse.R;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Global.setMainActivity(this);

        startListener();
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