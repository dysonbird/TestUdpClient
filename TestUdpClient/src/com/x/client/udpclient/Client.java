package com.x.client.udpclient;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Client {
	public static DatagramSocket datagramSocket = null; // 连接对象
	
	/** 
     * 接收数据包，该方法会造成线程阻塞 
     * @return 
     * @throws IOException 
     */  
    public static void receive(DatagramPacket packet) throws Exception {  
        try {  
            datagramSocket.receive(packet);  
        } catch (Exception e) {
            throw e;  
        }  
    } 
    
    /** 
     * 发送数据包到指定地点 
     * @param bt 
     * @throws IOException 
     */  
    public static void sendDate(DatagramPacket packet) {
        try {  
            datagramSocket.send(packet);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    } 
    
    /** 
     * 初始化客户端连接 
     * @throws SocketException 
     */  
    public static void init() throws SocketException{  
        try {
            datagramSocket = new DatagramSocket(1234);
            datagramSocket.setSoTimeout(10 * 1000);  
            System.out.println("客户端启动成功");  
        } catch (Exception e) {  
            datagramSocket = null;  
            System.out.println("客户端启动失败");  
            e.printStackTrace();  
        }  
    }  
}
