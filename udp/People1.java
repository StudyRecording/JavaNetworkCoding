package io.github.udp;

public class People1 {

	public static void main(String[] args) {
		System.out.println("people 1 进行交流");
		new Thread(new Send(8888, "localhost", 9999)).start();
		new Thread(new Receive(7777)).start();
	}

}
