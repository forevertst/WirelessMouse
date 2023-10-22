package com.tst.wirelessmouse.transform;

import static com.tst.wirelessmouse.listener.ListenerManager.startListener;
import static com.tst.wirelessmouse.listener.ListenerManager.stopListener;
import static java.lang.Thread.sleep;

import java.io.IOException;

public class TransformManager {
    static TcpClient tcpClient;
    static UdpClient udpClient;
    static UdpServer udpServer;

    public static void startConnecting() {
        startUdpServer();
        startUdpClient();
    }

    public static void stopConnectiong() {
        stopUdpServer();
        stopUdpClient();
        stopTcpClient();
    }

    public static void startTcpClient(String ip) {
        tcpClient = new TcpClient(ip, 5000);
        Thread thread = new Thread(tcpClient);
        thread.setDaemon(true);
        thread.start();

        startListener();
    }

    public static void tcpClientSend(String message) {
        tcpClient.send(message);
    }

    public static void stopTcpClient() {
        if (tcpClient != null) {
            tcpClient.stop();
            tcpClient = null;
        }
        stopListener();
    }

    public static void startUdpServer() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                udpServer = new UdpServer(5002);
                udpServer.init();
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

    public static void stopUdpServer() {
        udpServer.stop();
    }

    public static void startUdpClient() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                udpClient = new UdpClient("1;", 5001);
                udpClient.init();
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

    public static void stopUdpClient() {

        udpClient.stop();
    }

}
