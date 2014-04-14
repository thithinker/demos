package com.zgl.util;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Utils {
	public static void printArr(int[] arr){
		System.out.println("******************ARRAY******************");
		for(int i = 0; i < arr.length; i++){
			System.out.print(arr[i] + " ");
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
}
