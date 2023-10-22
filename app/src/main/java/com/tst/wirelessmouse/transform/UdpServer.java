package com.tst.wirelessmouse.transform;

import static com.tst.wirelessmouse.transform.TransformManager.startTcpClient;

import android.widget.Toast;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UdpServer {
    int port;
    DatagramSocket socket;
    boolean stopFlag;

    UdpServer(int port) {
        this.port = port;
        stopFlag = false;
    }

    public void stop() {
        stopFlag = true;
    }

    public void init() {
        try {
            // 创建 DatagramSocket 绑定到指定端口
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
                    startTcpClient(senderAddress);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
