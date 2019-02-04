package io.github.udp;

public class People2 {

	public static void main(String[] args) {
		System.out.println("people 2 进行交流");
		new Thread(new Send(6666,"localhost",7777)).start();
		new Thread(new Receive(9999)).start();
	}

}
