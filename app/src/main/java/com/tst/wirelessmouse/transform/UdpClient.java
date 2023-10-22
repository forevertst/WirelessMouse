package com.tst.wirelessmouse.transform;

import static java.lang.Thread.sleep;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UdpClient {
    DatagramSocket socket;
    String message;
    byte[] messageBytes;
    int broadcastPort;

    InetAddress broadcastAddress;
    DatagramPacket packet;

    boolean stopFlag;

    UdpClient(String message, int broadcastPort) {
        stopFlag = false;
        this.message = message;
        this.broadcastPort = broadcastPort;
        try {
            // 创建一个 DatagramSocket 对象，不需要指定目标主机和端口
            socket = new DatagramSocket();
            // 指定广播地址（通常是 255.255.255.255）
            broadcastAddress = InetAddress.getByName("255.255.255.255");
            // 将消息转换为字节数组
            messageBytes = this.message.getBytes();
            // 创建 DatagramPacket 对象，包含消息内容和目标地址
            packet = new DatagramPacket(messageBytes, messageBytes.length, broadcastAddress, this.broadcastPort);
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    public void init() {
        while (true) {
            if (stopFlag) {
                socket.close();
                break;
            }
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            this.sendUdpRequest();
        }
    }

    public void sendUdpRequest() {
        try {
            // 发送广播消息
            socket.send(packet);
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        // 关闭 Socket
        stopFlag = true;
    }
}
