package com.zgl.net;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class URLTest {
	public static void main(String[] args) {
		String str = "http://192.168.1.117:8088/13_14.ppt";
		try {
			URL url = new URL(str);
			InputStream is = url.openStream();
			OutputStream os = new FileOutputStream(new File("/home/yize/1.ppt"));
			byte[] buf = new byte[1024 * 10];
			int len;
			while((len = is.read(buf)) != -1){
				os.write(buf, 0, len);
			}
			os.close();
			is.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
