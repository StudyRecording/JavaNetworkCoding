package io.github.loc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;



public class SpiderTest {

	public static void main(String[] args) throws IOException {
		File file=new File("test.html");
		URL url=new URL("https://www.dianping.com");
		HttpURLConnection conn=(HttpURLConnection)url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("User-Agent" , 
				"Mozilla/5.0 (X11; Linux x86_64…) Gecko/20100101 Firefox/65.0");
		
		BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
		BufferedWriter bw=new BufferedWriter(new FileWriter(file));
		
		String str;
		while(null!=(str=br.readLine())) {
			bw.write(str+"\n");
			
			bw.flush();
		}
		
		bw.close();
		br.close();
	}

}
