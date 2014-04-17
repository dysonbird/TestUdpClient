package com.x.client.message;

import java.net.DatagramPacket;

import tutorial.Basemessage.BaseMessage;

import com.x.client.udpclient.Client;

public class MsgRecThread extends Thread {

	public void run() {
		while (true) {
			try {
				byte[] buffer = new byte[1024 * 64]; // 缓冲区
				DatagramPacket packet = new DatagramPacket(buffer,buffer.length);
				Client.receive(packet);
				
				byte[] data = new byte[packet.getLength()];  
	            System.arraycopy(packet.getData(), 0, data, 0, packet.getLength());  
				
				// 获得事件实例
	            BaseMessage bm = (BaseMessage) BaseMessage.parseFrom(data);
	            System.out.println("消息接收: " + bm.getStrValue(0));
			} catch (Exception e) {
//				e.printStackTrace();
			}
			// Thread.sleep(1 * 1000);
		}
	}
}
