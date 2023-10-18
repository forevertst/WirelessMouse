package com.tst.wirelessmouse.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tst.wirelessmouse.Global;
import com.tst.wirelessmouse.R;
import com.tst.wirelessmouse.listener.TouchListener;
import com.tst.wirelessmouse.transform.TcpManager;
import com.tst.wirelessmouse.transform.TcpThreadRunnable;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Global.setMainActivity(this);

        View rootView = getWindow().getDecorView();
        rootView.setOnTouchListener(new TouchListener());

    }

    @Override
    protected void onStart() {
        super.onStart();


        Toast.makeText(this, "onStart", Toast.LENGTH_SHORT).show();
        TcpManager.startTcpClient();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "onStop", Toast.LENGTH_SHORT).show();
        TcpManager.stopTcpClient();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}