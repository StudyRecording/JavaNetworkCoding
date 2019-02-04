package io.github.udp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class UDPSend2 {

	public static void main(String[] args) throws IOException {
		
		System.out.println("Send.....");
		//创建数据报套接字
		DatagramSocket socket=new DatagramSocket(9999);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			// 准备要发送的数据
			String str = br.readLine();
			byte[] b = str.getBytes();
			// 构建要发生的数据报包
			DatagramPacket pack = new DatagramPacket(b,0, b.length, 
					new InetSocketAddress("localhost", 8888));

			// 数据发送
			socket.send(pack);
			if(str.equals("bye")) {
				break;
			}
		}
		
		
		//关闭数据报套接字
		socket.close();
		

		
	}

}
