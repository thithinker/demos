package com.zgl.util;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Utils {
	public static void printArr(int[] arr){
		System.out.println("******************ARRAY******************");
		for(int i = 0; i < arr.length; i++){
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}
	
	public static void printArr(int[][] arr){
		System.out.println("******************ARRAY[][]******************");
		for(int i = 0; i < arr.length; i++){
			for(int j = 0; j < arr[i].length; j++){
				System.out.printf("%4d",arr[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public static void printList(@SuppressWarnings("rawtypes") List list){
		System.out.println("******************LIST******************");
		for(int i = 0; i < list.size(); i++){
			System.out.print(list.get(i) + " ");
		}
		System.out.println();
	}
	
	public static void printSet(Set<Integer> set){
		System.out.println("******************SET******************");
		Iterator<Integer> iterator = set.iterator();
		while(iterator.hasNext()){
			System.out.print(iterator.next() + " ");
		}
		System.out.println();
	}
	
	public static void printMap(Map<Integer, Integer> map){
		System.out.println("******************MAP******************");
		Set<Integer> keys = map.keySet();
		for(Integer i : keys ){
			System.out.print(i + ":" + map.get(i) + "|");
		}
		System.out.println();
	}
}
