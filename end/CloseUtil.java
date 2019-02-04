package io.github.end;

import java.io.Closeable;
import java.io.IOException;

public  class CloseUtil{
	
	public static void close(Closeable...closeables) {
		for(Closeable target:closeables) {
			try {
				if(target!=null) {
					target.close();
				}
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		}
	}

}
