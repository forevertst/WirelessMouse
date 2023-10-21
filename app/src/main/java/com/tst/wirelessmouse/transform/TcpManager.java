package com.tst.wirelessmouse.transform;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.tst.wirelessmouse.Global;
import com.tst.wirelessmouse.R;

public class TcpManager {

    static TcpClient tcpThreadRunnable;

    public static void startTcpClient(String ip) {
        tcpThreadRunnable = new TcpClient(ip, 5000);
        Thread thread = new Thread(tcpThreadRunnable);
        thread.setDaemon(true);
        thread.start();

        //        Activity currentActivity = Global.getMainActivity();
//        Button test_sendButton = currentActivity.findViewById(R.id.test_sendBtn);
//        test_sendButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String text = ((EditText) currentActivity.findViewById(R.id.editTextView)).getText().toString();
//                tcpThreadRunnable.send(text);
//            }
//        });
    }

    public static void tcpClientSend(String message) {
        tcpThreadRunnable.send(message);
    }

    public static void stopTcpClient() {
        if (tcpThreadRunnable != null) {
            tcpThreadRunnable.stop();
        }
    }
}
