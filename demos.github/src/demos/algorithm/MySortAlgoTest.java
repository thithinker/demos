package demos.algorithm;

import zgl.util.Utils;
import junit.framework.TestCase;

public class MySortAlgoTest extends TestCase{
	public static void main(String[] args) {
		/*Integer[] arr1 = Utils.getIntArr(20, 100);
		MySortAlgo.insertSort(arr1);
		Utils.printArr(arr1);
		//Utils.printArr(arr1);
		
		Integer[] arr2 = Utils.getIntArr(20, 100);
		MySortAlgo.shellSort(arr2);
		Utils.printArr(arr2);
		
		Integer[] arr3 = Utils.getIntArr(20, 100);
		MySortAlgo.mergeSort(arr3);
		Utils.printArr(arr3);*/
		int length = 1000000;
		int max = 100000000;
		Integer[] arr1 = Utils.getIntArr(length, max);
		Integer[] arr2 = Utils.getIntArr(length, max);
		Integer[] arr3 = Utils.getIntArr(length, max);
		Integer[] arr4 = Utils.getIntArr(length, max);
		Utils.writeArr(arr4, "D:/arr_init.txt");
		
		
		long t1 = System.currentTimeMillis();
		MySortAlgo.insertSort(arr1);
		long t2 = System.currentTimeMillis();
		MySortAlgo.shellSort(arr2);
		long t3 = System.currentTimeMillis();
		MySortAlgo.mergeSort(arr3);
		long t4 = System.currentTimeMillis();
		MySortAlgo.quickSort(arr4);
		long t5 = System.currentTimeMillis();
		
		
		System.out.println("insertSort: " + (t2 - t1));
		System.out.println("shellSort   " + (t3 - t2));
		System.out.println("mergeSort   " + (t4 - t3));
		System.out.println("quickSort   " + (t5 - t4));
		Utils.writeArr(arr4, "D:/arr_sorted.txt");
		
		
	}
}
