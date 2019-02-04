package io.github.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPRecive {

	public static void main(String[] args) throws IOException {
		System.out.println("Receive.......");
		//准备要接收数据的字节
		byte[] buf=new byte[1024*60];
		//构建接收的容器
		DatagramPacket pack=new DatagramPacket(buf,0,buf.length);
		//指定接收套接字的端口
		DatagramSocket socket=new DatagramSocket(8888);
		//接收数据
		socket.receive(pack);
		//将字节数据转换为字符串
		byte[] b=pack.getData();
		System.out.println(new String(b));
		//关闭套接字接口
		socket.close();
	}

}
