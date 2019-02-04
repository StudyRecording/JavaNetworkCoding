package io.github.tcp;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Serve {

	public static void main(String[] args) throws IOException {
		System.out.println("--------Serve----------");
		// 实现了服务器套接字
		ServerSocket serve=new ServerSocket(9999);
		// 侦听要连接到此套接字并接受它。（阻塞式——即没有客户端通信请求，程序不向下运行） 
		Socket socket=serve.accept();
		// 创建此套接字的输入流
		DataInputStream dis=new DataInputStream(socket.getInputStream());
		// 读取数据
		String str=dis.readUTF();
		// 显示数据信息
		System.out.println(str);
		// 释放资源
		dis.close();
		socket.close();
		serve.close();
		
		
	}

}
