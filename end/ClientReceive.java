package io.github.end;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientReceive implements Runnable{
	private Socket socket;
	private DataInputStream dis;
	private boolean isRunning;
	public ClientReceive(Socket socket) {
		this.socket=socket;
		try {
			this.dis=new DataInputStream(socket.getInputStream());
			this.isRunning=true;
		} catch (IOException e) {
			this.isRunning=false;
		}
	}

	
		
	@Override
	public void run() {
		while(isRunning) {
			try {
				String msg=dis.readUTF();
				
				
					System.out.println(msg);
				

			} catch (IOException e) {
				e.printStackTrace();
				isRunning=false;
				CloseUtil.close(dis,socket);
			}
			
		}
	}
	
	
}
