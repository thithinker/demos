package zgl.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class Utils {
	public static Integer[] getIntArr(int length){
		return getIntArr(length, Integer.MAX_VALUE);
	}
	
	public static Integer[] getIntArr(int length, int max){
		Integer[] result = new Integer[length];
		Random r = new Random();
		for(int i = 0; i < length; i++){
			result[i] = r.nextInt() % max;
		}
		/*System.out.println("******************生成数组******************");
		Utils.printArr(result);
		System.out.println("*******************************************");*/
		return result;
	}
	
	public static void printArr(int[] arr){
		System.out.println("******************ARRAY******************");
		for(int i = 0; i < arr.length; i++){
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}
	
	public static void printArr(Integer[] arr){
		System.out.println("******************ARRAY******************");
		for(int i = 0; i < arr.length; i++){
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}
	
	public static void printArr(double[] arr){
		System.out.println("******************ARRAY******************");
		for(int i = 0; i < arr.length; i++){
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}
	public static void printArr(Double[] arr){
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
	
	public static void printArr(Integer[][] arr){
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
	
	public static void printList(String str, @SuppressWarnings("rawtypes") List list){
		System.out.println("******************LIST" + str + "******************");
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
	
	public static void writeArr(Integer[] a, String path) {
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path)));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < a.length; i++){
			sb.append(a[i] + ",");
		}
		sb.setCharAt(sb.length() - 1, ' ');
		try {
			bw.write(sb.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
