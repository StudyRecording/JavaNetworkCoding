package io.github.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class UDPSend {

	public static void main(String[] args) throws IOException {
		
		System.out.println("Send.....");
		//准备要发送的数据
		String str="我真帅";
		byte[] b=str.getBytes();
		//构建要发生的数据报包
		DatagramPacket pack=new DatagramPacket(b,b.length,new InetSocketAddress("localhost", 8888));
		//创建数据报套接字
		DatagramSocket socket=new DatagramSocket(9999);
		//数据发送
		socket.send(pack);
		//关闭数据报套接字
		socket.close();
		

		
	}

}
