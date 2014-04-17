package com.x.client.udpclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.InetSocketAddress;
import java.net.SocketException;

import tutorial.Basemessage.BaseMessage;

import com.google.protobuf.ByteString;
import com.x.client.message.MsgRecThread;
import com.x.util.ProtocolDefine;

public class UdpClient {
	public static void main(String[] args) throws InterruptedException,
			IOException {
		//初始化客户端
		Client.init();
		
		//启动客户端消息收线程
		MsgRecThread recTh = new MsgRecThread();
		recTh.setName("MsgRecThread");
		recTh.start();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			System.out.println("输入对话");
			String message = br.readLine().trim();
			if (message.toLowerCase().startsWith("stop")) {
				break;
			} else if (message.toLowerCase().startsWith("send")) {
				String[] str = message.split(" ");
				//发送消息
				send(str[1]);
			}
		}
		System.exit(0);
	}
	
	public static void send(String msg){
		BaseMessage.Builder builder = BaseMessage.newBuilder();
		builder.setCommand(ProtocolDefine.FIRST_CONNECT_LOGIN);
		builder.setMsgType(ByteString.copyFrom(new byte[]{1}));
		builder.addIntValue(10086);
		builder.addStrValue(msg);
		
		byte[] buffer = new byte[1024 * 64]; // 缓冲区
		DatagramPacket packet;
		try {
			packet = new DatagramPacket(buffer, buffer.length, new InetSocketAddress("10.6.6.92",2333));
			
			byte[] data = builder.build().toByteArray();
			packet.setData(data);
			
			Client.sendDate(packet);
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}
}
