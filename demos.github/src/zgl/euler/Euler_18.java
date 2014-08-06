package zgl.euler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import org.apache.commons.lang3.SerializationUtils;

import zgl.util.Utils;

/**
 * @author yize
 * 2014年6月8日
 */
public class Euler_18 {
	static int[][] data;
	static int row = 0;
	public static int[][] getData(){
		try {
			RandomAccessFile in = new RandomAccessFile(new File("./euler/euler18.txt"), "r");
			
			String line = null;
			while((line = in.readLine()) != null){
				row++;
			}
			in.seek(0);
			data = new int[row][row];
			String[] ss;
			while((line =in.readLine()) != null){
				ss = line.split(" ");
				for(int i = 0; i < ss.length; i++){
					data[ss.length - 1][i] = Integer.valueOf(ss[i]);
				}
			}
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}
	
	public static int calc(int[][] d){
		for(int i = d.length - 2; i > -1; i--){
			for(int j = i; j > -1; j--){
				d[i][j] = d[i][j] + Math.max(d[i + 1][j], d[i + 1][j+1]);
			}
		}
		//Utils.printArr(d);
		findData(d);
		return d[0][0];
	}
	
	public static void findData(int[][] d){
		int row = 0; 
		int col = 0;
		for(int i = 0; i < d.length; i++){
			if(i == 0){
				row = 0;
				col = 0;
				System.out.println("ROW[" + row + ", " + col + "]" + data[row][col]);
			}else{
				if(d[row + 1][col] > d[row + 1][col + 1]){
					row++;
					System.out.println("ROW[" + row + ", " + col + "]" + data[row][col]);
				}else{
					row++;
					col++;
					System.out.println("ROW[" + row + ", " + col + "]" + data[row][col]);
				}
			}
			
		}
	}
	
	public static void main(String[] args) {
		int[][] data = getData();
		int[][] tmp = SerializationUtils.clone(data);
		System.out.println(calc(tmp));;
		
		Utils.printArr(data);
	}
}
