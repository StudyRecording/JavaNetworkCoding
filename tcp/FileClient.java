package io.github.tcp;

import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class FileClient {

	public static void main(String[] args) throws UnknownHostException, IOException {
		System.out.println("---------Client-------------");
		Socket client=new Socket("localhost",9999);
		InputStream is=new BufferedInputStream(new FileInputStream("src/myGirl.jpg"));
		DataOutputStream dos=new DataOutputStream(client.getOutputStream());
		int len=-1;
		byte[] b=new byte[1024];
		while((len=is.read(b))!=-1) {
			dos.write(b, 0, len);
			dos.flush();
		}
		
		dos.close();
		is.close();
		client.close();
	}

}
