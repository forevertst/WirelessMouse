package com.tst.wirelessmouse.transform;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.tst.wirelessmouse.Global;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class TcpThreadRunnable implements Runnable {
    String ipAddress;
    int port;
    Socket socket;
    OutputStream outputStream;

    List<String> sendList;

    boolean stopFlag = false;

    public TcpThreadRunnable(String ip, int p) {
        ipAddress = ip;
        port = p;
        sendList = new ArrayList<>();
    }

    public void init() {
        try {
            socket = new Socket(ipAddress, port);
            outputStream = socket.getOutputStream();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void send(String message) {
        sendList.add(message);
    }

    public void cancel() {
        try {
            this.outputStream.close();
            this.socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void stop() {
        stopFlag = true;
    }

    @Override
    public void run() {
        init();
        while (true) {
            if (stopFlag) {
                this.cancel();
                break;
            }
            if (sendList.size() > 0) {
                try {
                    String data = sendList.get(0) + ";";
                    outputStream.write(data.getBytes(StandardCharsets.UTF_8));
                    outputStream.flush();
                    sendList.remove(0);
                } catch (Exception e) {
//                    throw new RuntimeException(e);
                    Handler mainHandler = new Handler(Looper.getMainLooper());
                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(Global.getMainActivity(), "outputStream错误", Toast.LENGTH_SHORT).show();
                        }
                    });
                    e.printStackTrace();
                }
            }
        }
    }
}
