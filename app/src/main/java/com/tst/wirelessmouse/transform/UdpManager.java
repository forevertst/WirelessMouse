package com.tst.wirelessmouse.transform;

import static java.lang.Thread.sleep;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.tst.wirelessmouse.Global;
import com.tst.wirelessmouse.R;

public class UdpManager {
    static boolean udpClientStopFlag = false;

    public static void startUdpServer() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                UdpServer.init();
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

    public static void stopUdpServer() {
        UdpServer.stop();
    }

    public static void startUdpClient() {
        udpClientStopFlag = false;
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                UdpClient.init();
                while (!udpClientStopFlag) {
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    UdpClient.sendUdpRequest();
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

    public static void stopUdpClient() {
        udpClientStopFlag = true;
        UdpClient.cancel();
    }

}
