package io.github.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPRecive2 {

	public static void main(String[] args) throws IOException {
		System.out.println("Receive.......");
		//指定接收套接字的端口
		DatagramSocket socket=new DatagramSocket(8888);

		while (true) {
			// 准备要接收数据的字节
			byte[] buf = new byte[1024];
			// 构建接收的容器
			DatagramPacket pack = new DatagramPacket(buf,0, buf.length);
			// 接收数据
			socket.receive(pack);
			// 将字节数据转换为字符串
			byte[] b = pack.getData();

			int len = pack.getLength();
			String str = new String(b, 0, len);
			String str2 = new String(b);
			
			System.out.println(str);
			//System.out.println(str+"--->"+str.length());
			System.out.println(len+"---"+str2.length());
			if (str.equals("bye")) {
				break;
			}

		}

		//关闭套接字接口
		socket.close();
	}

}
