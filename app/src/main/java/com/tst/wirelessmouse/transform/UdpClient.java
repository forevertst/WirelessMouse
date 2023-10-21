package com.tst.wirelessmouse.transform;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UdpClient {
    static DatagramSocket socket;
    static String message = "1;";

    public static void init() {
        try {
            // 创建一个 DatagramSocket 对象，不需要指定目标主机和端口
            socket = new DatagramSocket();

        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    public static void sendUdpRequest() {
        try {
            // 指定广播地址（通常是 255.255.255.255）
            InetAddress broadcastAddress = InetAddress.getByName("255.255.255.255");

            // 发送的消息内容
            String message = UdpClient.message;

            // 将消息转换为字节数组
            byte[] messageBytes = message.getBytes();

            // 创建 DatagramPacket 对象，包含消息内容和目标地址
            DatagramPacket packet = new DatagramPacket(messageBytes, messageBytes.length, broadcastAddress, 5001);

            // 发送广播消息
            socket.send(packet);

        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    public static void cancel() {
        // 关闭 Socket
        socket.close();
    }
}
