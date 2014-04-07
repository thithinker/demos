package com.zgl.test;

import java.util.Random;

public class Main {
	//输出一维数组
	static void printArr(int[] arr){
		for(int i = 0; i < arr.length; i++){
			System.out.print(arr[i] + " ");
			if(i !=  0 && i % 100 == 0)
				System.out.println();
		}
		System.out.println("\n---------------------------------------------------------");
	}
	//产生一维数组
	static int[] randomArr(int max, int total){
		int[] rs = new int[total];
		Random random = new Random();
		for(int i = 0; i < total; i++){
			rs[i] = random.nextInt(max);
		}
		return rs;
	}
	//测试一维数组是否有序
	static boolean check(int[] arr){
		for(int i = arr.length - 1; i > 0; i--){
			if(arr[i] < arr[i - 1])
				return false;
		}
		return true;
	}
	
	public static void main(String[] args) {
		int a[]={49,38,65,97,76,13,27,49,78,34,12,64,5,4,62,99,98,54,56,17,18,23,34,15,35,25,53,51};
		SortAlgorithm sort = new SortAlgorithm();
		int[] arr = randomArr(100, 5);
		printArr(arr);
		int[] rs = sort.insertSort(arr);
		printArr(rs);
		System.out.println(check(rs));
		
		printArr(sort.quickSort(a));
	}
}
