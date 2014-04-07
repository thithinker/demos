package com.zgl.euler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * @date 2014.4.5
 * @author yize
 *
 */
public class Euler_13 {
	public static void main(String[] args) {
		List<BigInteger> list = new ArrayList<>();
		try {
			FileReader fr = new FileReader(new File("./euler/euler13.txt"));
			BufferedReader br = new BufferedReader(fr);
			String line;
			while((line = br.readLine()) != null){
				list.add(new BigInteger(line));
			}
			
			
			Collections.sort(list);
			for(BigInteger bb : list){
				System.out.println("-----------" + bb);
			}
			BigInteger bi = BigInteger.ZERO;
			for(int i = 0; i < 100; i++){
				bi = bi.add(list.get(i));
			}
			System.out.println(bi);
			
			br.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
		
		}
		
	}
	
	
}
