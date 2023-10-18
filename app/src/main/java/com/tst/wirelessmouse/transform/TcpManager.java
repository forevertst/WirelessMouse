package com.tst.wirelessmouse.transform;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.tst.wirelessmouse.Global;
import com.tst.wirelessmouse.MyApplication;
import com.tst.wirelessmouse.R;
import com.tst.wirelessmouse.activity.MainActivity;

public class TcpManager {

    static TcpThreadRunnable tcpThreadRunnable;

    public static void startTcpClient() {
        Activity currentActivity = Global.getMainActivity();
        Button test_sendButton = currentActivity.findViewById(R.id.test_sendBtn);
        tcpThreadRunnable = new TcpThreadRunnable("192.168.2.11", 5000);
        Thread thread = new Thread(tcpThreadRunnable);
        thread.start();
        test_sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = ((EditText) currentActivity.findViewById(R.id.editTextView)).getText().toString();
                tcpThreadRunnable.send(text);
            }
        });
    }

    public static void tcpClientSend(String message) {
        tcpThreadRunnable.send(message);
    }

    public static void stopTcpClient() {
        tcpThreadRunnable.stop();
    }
}
