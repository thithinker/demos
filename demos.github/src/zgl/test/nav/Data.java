package zgl.test.nav;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Data {
	public static void main(String[] args) {
		String path = "./wifi/data.txt";
		String key1 = "bb";
		String key2 = "B1-202east";
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			String line = null;
			String[] tmp;
			while ((line = br.readLine()) != null) {
				tmp = line.split(",");
				//System.out.println(tmp[2] + " " + tmp[3] + " " + tmp[4]);
				if(tmp[2].equals(key1) && tmp[3].equals(key2)){
					System.out.println(tmp[4]);
				}
			}
			br.close();
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
	}
}
