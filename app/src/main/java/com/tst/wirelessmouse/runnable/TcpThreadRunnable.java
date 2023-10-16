package com.tst.wirelessmouse.runnable;

import android.content.Context;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class TcpThreadRunnable implements Runnable {
    Context context;
    String ipAddress;
    int port;
    Socket socket;
    OutputStream outputStream;
    boolean sendFlag;
    String message;

    boolean stopFlag =false;

    public TcpThreadRunnable(String ip, int p, Context con) {
        ipAddress = ip;
        port = p;
        context = con;
        sendFlag = false;
    }

    public void init() {
        try {
            socket = new Socket(ipAddress, port);
            outputStream = socket.getOutputStream();
            int a =3;
            int b =a;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void send(String m) {
        sendFlag = true;
        message = m;
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
            // 创建一个Socket连接到服务器的IP和端口
        this.init();
        while (true) {
            if(stopFlag){
                this.cancel();
                break;
            }
            if (sendFlag) {
                try {
                    outputStream.write(message.getBytes(StandardCharsets.UTF_8));
                    outputStream.flush();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                sendFlag = false;
            }
        }
    }
}
