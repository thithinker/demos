package demos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Test01 {
	public static void main(String[] args) {
		File file = new File("d:tmp/tmp.txt");
		String line;
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			BufferedWriter bw = null;
			while((line = br.readLine()) != null){
				if(line.contains("系列") && line.length() < 50){
					if(bw != null){
						bw.flush();
						bw.close();
					}
					bw = new BufferedWriter(new FileWriter(new File("D:/tmp/" + line + ".txt")));
				}
				if(bw != null)
					bw.write(line + "\n");
			}
			bw.flush();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
