package io.github.end;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.concurrent.CopyOnWriteArrayList;

public class ClientSend implements Runnable{
	
	private DataOutputStream dos;
	private BufferedReader br;
	private Socket socket;
	private boolean isRunning;
	
	public ClientSend(Socket socket) {
		this.socket=socket;
		this.br=new BufferedReader(new InputStreamReader(System.in));
		System.out.println("输入你的名字：");
		
		try {
			this.dos=new DataOutputStream(socket.getOutputStream());
			dos.writeUTF(br.readLine());
			dos.flush();
			this.isRunning=true;
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error:Client send init fail!");
			this.isRunning=false;
		}
		
	}

	private String readConsole() {
		try {
			String msg=br.readLine();
			return msg;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
	

	
	@Override
	public void run() {
		while(isRunning) {
			try {
				String msg=readConsole();
				dos.writeUTF(msg);
				dos.flush();
			
			} catch (IOException e) {
				e.printStackTrace();
				isRunning=false;
				CloseUtil.close(br,dos,socket);
			}
		}
	}

}
