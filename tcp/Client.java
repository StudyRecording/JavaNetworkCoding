package io.github.tcp;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

	public static void main(String[] args) throws UnknownHostException, IOException {
		System.out.println("---------Client-------------");
		// 实现客户端套接字
		Socket client=new Socket("localhost",9999);
		//  创建此套接字的输出流。 
		DataOutputStream dos=new DataOutputStream(client.getOutputStream());
		//输出数据
		String str="好爱好";
		dos.writeUTF(str);
		// 释放资源
		dos.close();
		client.close();
	}

}
