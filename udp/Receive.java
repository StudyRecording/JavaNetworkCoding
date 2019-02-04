package io.github.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Receive implements Runnable{
	private DatagramSocket socket;
	private DatagramPacket pack;
	/**
	 * 构建接收段
	 * @param port 本机接收数据的端口
	 */
	public Receive(int port) {
		try {
			socket=new DatagramSocket(port);
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		while (true) {
			// 准备要接收数据的字节
			byte[] buf = new byte[1024];
			// 构建接收的容器
			pack = new DatagramPacket(buf,0, buf.length);
			// 接收数据
			try {
				socket.receive(pack);
				// 将字节数据转换为字符串
				byte[] b = pack.getData();
				int len = pack.getLength();
				String str = new String(b, 0, len);			
				System.out.println(str);
				if (str.equals("bye")) {
					break;
				}	
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		//关闭套接字接口
		socket.close();
	}

}
