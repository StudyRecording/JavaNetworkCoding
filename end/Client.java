package io.github.end;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

	public static void main(String[] args) throws UnknownHostException, IOException {
		System.out.println("---------Client-------------");
		
		Socket socket=new Socket("localhost",9999);
		new Thread(new ClientSend(socket)).start();
		new Thread(new ClientReceive(socket)).start();
		
		
		
		//socket.close();
	}

}
