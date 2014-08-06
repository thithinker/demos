package zgl.test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * ArrayList、LinkedList和Array性能比较
 * @author yize
 * 2014年7月24日
 */
public class TestList_140724 {
	public static void main(String[] args) {
		int len = 10000000;
		List<Integer> arrayList = new ArrayList<>();
		List<Integer> linkedList = new LinkedList<>();
		int[] arr = new int[len];
		Random r = new Random();
		long tt1 = System.currentTimeMillis();
		for(int i = 0; i < len; i++){
			arrayList.add(r.nextInt());
		}
		long tt2 = System.currentTimeMillis();
		
		for(int i = 0; i < len; i++){
			linkedList.add(r.nextInt());
		}
		long tt3 = System.currentTimeMillis();
		
		for(int i = 0; i < len; i++){
			arr[i] = r.nextInt();
		}
		long tt4 = System.currentTimeMillis();
		
		System.out.println("ArrayList Add: " + (tt2 - tt1));
		System.out.println("LinkedList Add: " + (tt3 - tt2));
		System.out.println("Array Add: " + (tt4 - tt3));
		
		
		long t1 = System.currentTimeMillis();
		findMax(arrayList);
		long t2 = System.currentTimeMillis();
		findMax2(arrayList);
		long t3 = System.currentTimeMillis();
		findMax(linkedList);
		long t4 = System.currentTimeMillis();
		findMax2(linkedList);
		long t5 = System.currentTimeMillis();
		findMax(arr);
		long t6 = System.currentTimeMillis();
		findMax2(arr);
		long t7 = System.currentTimeMillis();
		System.out.println(t2 - t1);
		System.out.println(t3 - t2);
		System.out.println(t4 - t3);
		System.out.println(t5 - t4);
		System.out.println(t6 - t5);
		System.out.println(t7 - t6);
		
	}

	public static int findMax(List<Integer> list){
		int max = list.get(0);
		for(int i = 0; i < list.size(); i++){
			if(max < list.get(i))
				max = list.get(i);
		}
		return max;
	}
	
	public static int findMax2(List<Integer> list){
		int max = list.get(0);
		for(int n : list){
			if(max < n)
				max = n;
		}
		return max;
	}
	
	public static int findMax(int[] arr){
		int max = arr[0];
		for(int i = 0; i < arr.length; i++){
			if(max < arr[i])
				max = arr[i];
		}
		return max;
	}
	
	public static int findMax2(int[] arr){
		int max = arr[0];
		for(int n : arr){
			if(max < n)
				max = n;
		}
		return max;
	}
}
