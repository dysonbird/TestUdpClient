package com.x.client.udpclient;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Client {
	public static DatagramSocket datagramSocket = null; // ���Ӷ���
	
	/** 
     * �������ݰ����÷���������߳����� 
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
     * �������ݰ���ָ���ص� 
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
     * ��ʼ���ͻ������� 
     * @throws SocketException 
     */  
    public static void init() throws SocketException{  
        try {
            datagramSocket = new DatagramSocket(1234);
            datagramSocket.setSoTimeout(10 * 1000);  
            System.out.println("�ͻ��������ɹ�");  
        } catch (Exception e) {  
            datagramSocket = null;  
            System.out.println("�ͻ�������ʧ��");  
            e.printStackTrace();  
        }  
    }  
}
