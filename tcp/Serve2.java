package io.github.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Serve2 {

	public static void main(String[] args) throws IOException {
		System.out.println("--------Serve----------");
		ServerSocket serve=new ServerSocket(9999);
		Socket socket=serve.accept();
		DataInputStream dis=new DataInputStream(socket.getInputStream());
		String str=dis.readUTF();
		String[] datas=str.split("&");
		String name="";
		String pwd="";
		for(String info:datas) {
			String[] values=info.split("=");
			if(values[0].equals("name")) {
				System.out.println("用户名为："+values[1]);
				name=values[1];
			}
			else if(values[0].equals("pwd")) {
				System.out.println("密码为："+values[1]);
				pwd=values[1];
			}
		}
		
		/********************* 服务端向客户端传输数据**********************/
		DataOutputStream dos=new DataOutputStream(socket.getOutputStream());
		if(name.equals("hu")&&pwd.equals("123")) {
			dos.writeUTF("登录成功");
		}
		else {
			dos.writeUTF("登录失败");
		}
		
		dis.close();
		socket.close();
		serve.close();
		
		
	}

}
