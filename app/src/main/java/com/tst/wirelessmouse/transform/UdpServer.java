package com.tst.wirelessmouse.transform;

import android.widget.Toast;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UdpServer {
    static DatagramSocket socket;
    static boolean stopFlag = false;

    public static void stop() {
        stopFlag = true;
    }

    public static void init() {
        try {
            // 创建 DatagramSocket 绑定到指定端口
            stopFlag = false;
            int port = 5002;
            socket = new DatagramSocket(port);

            while (true) {
                if (stopFlag) {
                    socket.close();
                    break;
                }
                // 创建用于接收数据的 DatagramPacket
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

                // 接收数据包
                socket.receive(receivePacket);

                // 解析接收到的数据
                String receivedMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
                String senderAddress = receivePacket.getAddress().getHostAddress();
                int senderPort = receivePacket.getPort();

                System.out.println("Received from " + senderAddress + ":" + senderPort + " - " + receivedMessage);
                if (receivedMessage.equals("2;")) {
                    TcpManager.startTcpClient(senderAddress);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
