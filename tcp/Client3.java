package io.github.tcp;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client3 {

	public static void main(String[] args) throws UnknownHostException, IOException {
		System.out.println("---------Client-------------");
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		System.out.println("请输入姓名：");
		String name=br.readLine();
		System.out.println("密码：");
		String pwd=br.readLine();
		
		
		Socket socket=new Socket("localhost",9999);
		DataOutputStream dos=new DataOutputStream(socket.getOutputStream());
		String str="name="+name+"&"+"pwd="+pwd;
		dos.writeUTF(str);
		/*******************************客户端接收信息*************************/
		DataInputStream dis=new DataInputStream(socket.getInputStream());
		String str2=dis.readUTF();
		System.out.println(str2);
		
		dos.close();
		socket.close();
	}

}
