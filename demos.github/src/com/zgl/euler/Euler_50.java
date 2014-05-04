package com.zgl.euler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.zgl.test.AboutNumber;

/**
 * @date 2014.4.20
 * @author yize
 * 方法效率都太低
 */
public class Euler_50 {
	public static void main(String[] args) {
		int max = 10000;
		//new Euler_50().method_1(max);
		new Euler_50().method_2(max);
	}
	
	
	void method_1(int max){
		long start = System.currentTimeMillis();
		AboutNumber aboutNumber = new AboutNumber();
		List<Integer> primes = aboutNumber.findPrimeNum(max);
		int size = primes.size();
		//System.out.println("Prime Number: " + size);
		int[][] arr = new int[size][size];

		for(int i = 0; i < size; i++){
			for(int j = i; j < size; j++){
				if(i == j){
					arr[i][j] = primes.get(j);
				}else{
					arr[i][j] = arr[i][j - 1] + primes.get(j);
				}
			}
		}
		
		int maxLength = 0;
		int maxNum = 0;
		int num;
		while(--size > -1){
			num = primes.get(size);
			for(int i = 0; i < size; i++){
				for(int j = i; j < size; j++){
					if(arr[i][j] == num && (j - i + 1) > maxLength){
						maxLength = j - i + 1;
						maxNum = arr[i][j];
					}
						
				}
			}
			
		}
		
		System.out.println("Method1\n\tPrime: " + maxNum + " Length: " + maxLength);
		System.out.println("\tUse time: " + (System.currentTimeMillis() - start));
	}
	
	void method_2(int max){
		long start = System.currentTimeMillis();
		AboutNumber aboutNumber = new AboutNumber();
		List<Integer> primes = aboutNumber.findPrimeNum(max);
		Map<Integer, Integer> map = new HashMap<>();
		int size = primes.size();
		//System.out.println("Prime Number: " + size);
		int sum = 0;
		int index = -1;
		for(int i = 0; i < size; i++){
			for(int j = i; j < size; j++){
				sum += primes.get(j);
				index = j - i + 1;
				if(primes.contains(sum)){
					if(map.containsKey(sum) && map.get(sum) < index){
						//System.out.print("index:" + index);
						map.put(sum, index);
					}else if(!map.containsKey(sum)){
						//System.out.print("sum" + sum + " index:" + index);
						map.put(sum, index);
					}
					
				}
			}
			
			sum = 0; 
			index = -1;
		}
		System.out.println("-----------------------------");
		
		int prime = -1;
		int longth = -1;
		Set<Entry<Integer, Integer>> entries = map.entrySet();
		for(Entry<Integer, Integer> entry : entries){
			if(entry.getValue() > longth){
				longth = entry.getValue();
				prime = entry.getKey();
			}
		}
		
		System.out.println("Method2\n\tprime:" + prime + " longth:" + longth);
		System.out.println("\tUse time: " + (System.currentTimeMillis() - start));
		
	}
}
