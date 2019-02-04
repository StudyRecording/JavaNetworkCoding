package io.github.end;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CopyOnWriteArrayList;

public class Serve {

	private static CopyOnWriteArrayList<Channel> all=new CopyOnWriteArrayList<Serve.Channel>();
	public static void main(String[] args) throws IOException {
		System.out.println("--------Serve----------");
		ServerSocket serve=new ServerSocket(9999);
		while(true) {
			Socket socket=serve.accept();
			Channel c=new Channel(socket);
			System.out.println(c.name+" had connected.");
			all.add(c);
			new Thread(c).start();
			

		}
	
		
	}
	
	// 一个客户端代表一个线程
	static class Channel implements Runnable{
		private DataInputStream dis;
		private DataOutputStream dos;
		private Socket socket;
		private String name;
		private boolean isRunning;
		public Channel(Socket socket) {
			this.socket=socket;
			try {
				this.dis=new DataInputStream(socket.getInputStream());
				this.dos=new DataOutputStream(socket.getOutputStream());
				this.name=dis.readUTF();
				this.isRunning=true;
			} catch (IOException e) {
				System.out.println("Error:Serve init fail!");
				release();
				this.isRunning=false;
				
			}
			
		}
		
		// 接收信息
		private String receive() {
			try {
				String str=dis.readUTF();
				return str;
			} catch (IOException e) {
				System.out.println("Error:Serve receiving datas!");
				this.isRunning=false;
				release();
				
			}
			return null;
		}
		
		// 发送信息
		private void send(String msg) {
			try {
				dos.writeUTF(msg);
				dos.flush();
			} catch (IOException e) {
				System.out.println("Error:Serve send datas!");
				this.isRunning=false;
				release();
			}
		}
		
		private void sendOthers(String msg) {
			// 私聊
			if(msg.startsWith("@")) {
				int i=msg.indexOf(":");
				String name=msg.substring(1, i);
				String str=msg.substring(i+1);
				for(Channel others:all) {
					if(others.name.equals(name)) {
						others.send(str);
						break;
					}
				}
			}
			else {// 群聊
				for(Channel others:all) {
					if(others==this) {
						continue;
					}
					others.send(msg);
					
				}
			}

		}
		
		// 释放资源
		private void release() {
			CloseUtil.close(dos,dis,socket);
		}
		
		@Override
		public void run() {
			while(isRunning) {
				String msg=receive();
				if(!msg.equals("")) {
					//send(msg);
					sendOthers(msg);
				}
				
			}
		}
	}

}
