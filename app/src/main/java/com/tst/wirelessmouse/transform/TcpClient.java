package com.tst.wirelessmouse.transform;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.tst.wirelessmouse.Global;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class TcpClient implements Runnable {
    String ipAddress;
    int port;
    Socket socket;
    OutputStream outputStream;

    List<String> sendList;

    boolean stopFlag = false;

    public TcpClient(String ip, int p) {
        ipAddress = ip;
        port = p;
        sendList = new ArrayList<>();
    }

    public void initTcpServer() {
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
        initTcpServer();
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
                    Log.d("tcp错误", "tcpClient错误");
                    this.cancel();
                    e.printStackTrace();
                }
            }
        }
    }
}
