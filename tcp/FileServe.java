package io.github.tcp;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class FileServe {

	public static void main(String[] args) throws IOException {
		System.out.println("--------Serve----------");
		ServerSocket serve=new ServerSocket(9999);
		Socket socket=serve.accept();
		DataInputStream dis=new DataInputStream(socket.getInputStream());
		OutputStream os=new BufferedOutputStream(new FileOutputStream(new File("copy.jpg")));
		
		int len=-1;
		byte[] b=new byte[1024];
		while((len=dis.read(b))!=-1) {
			os.write(b, 0, len);
			os.flush();
		}
		
		os.close();
		dis.close();
		socket.close();
		serve.close();
		
		
	}

}
