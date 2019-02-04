package io.github.udp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

public class Send implements Runnable{
	private DatagramSocket socket;
	private BufferedReader br;
	private DatagramPacket pack;
	private String toIp;
	private int toPort;
	/**
	 * 构建发送段
	 * @param thisPort 发送数据的端口
	 * @param toIp 发送数据到达的目的ip
	 * @param toPort 发送数据到达目的端口
	 */
	public Send(int thisPort,String toIp,int toPort) {
		try {
			socket=new DatagramSocket(thisPort);
		} catch (SocketException e) {
			e.printStackTrace();
		}
		br = new BufferedReader(new InputStreamReader(System.in));
		this.toIp=toIp;
		this.toPort=toPort;
	}
	
	@Override
	public void run() {
		while (true) {
			// 准备要发送的数据
			String str;
			try {
				str = br.readLine();
				byte[] b = str.getBytes();
				// 构建要发生的数据报包
				pack = new DatagramPacket(b,0, b.length, 
						new InetSocketAddress(toIp, toPort));
				// 数据发送
				socket.send(pack);
				if(str.equals("bye")) {
						break;
					}
			} catch (IOException e) {
				e.printStackTrace();
			}
	
		}
		//关闭数据报套接字
		socket.close();
		
	}

}
