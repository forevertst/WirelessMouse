package com.tst.wirelessmouse.activity;

import static com.tst.wirelessmouse.listener.ListenerManager.startListener;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.tst.wirelessmouse.Global;
import com.tst.wirelessmouse.R;
import com.tst.wirelessmouse.listener.TouchListener;
import com.tst.wirelessmouse.transform.TcpManager;
import com.tst.wirelessmouse.transform.UdpManager;
import com.tst.wirelessmouse.transform.UdpServer;

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
//        TcpManager.startTcpClient();

        UdpManager.startUdpClient();
        UdpManager.startUdpServer();
    }

    @Override
    protected void onStop() {
        super.onStop();
//        Toast.makeText(this, "onStop", Toast.LENGTH_SHORT).show();
        TcpManager.stopTcpClient();
        UdpManager.stopUdpClient();
        UdpManager.stopUdpServer();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}