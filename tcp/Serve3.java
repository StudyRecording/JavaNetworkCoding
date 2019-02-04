package io.github.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Serve3 {
	
	static class Channl implements Runnable{
		private Socket socket;
		private DataInputStream dis;
		private DataOutputStream dos;
		private String[] datas;
		private String name;
		private String pwd;
		public Channl(Socket socket) {
			this.socket=socket;
			//this.serve=serve;
			
			try {
				this.dis=new DataInputStream(socket.getInputStream());
				this.dos=new DataOutputStream(socket.getOutputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		
		@Override
		public void run() {
			
			String str;
			try {
				str = dis.readUTF();
				datas=str.split("&");
			} catch (IOException e) {
				e.printStackTrace();
			}

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
			
			
			if(name.equals("hu")&&pwd.equals("123")) {
				try {
					dos.writeUTF("登录成功");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else {
				try {
					dos.writeUTF("登录失败");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			try {
				dis.close();
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

	public static void main(String[] args) throws IOException {
		System.out.println("--------Serve----------");
		ServerSocket serve=new ServerSocket(9999);
		while(true) {
			Socket socket=serve.accept();
			System.out.println("一个客户端建立链接");
			new Thread(new Channl(socket)).start();
		}

	}

}
