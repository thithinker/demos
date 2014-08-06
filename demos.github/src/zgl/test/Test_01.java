package zgl.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import zgl.util.Utils;

public class Test_01 {
	public static void main(String[] args) {
		/*List<Double> list1 = new ArrayList<>();
		list1.add(12D);
		list1.add(13D);
		list1.add(14D);
		list1.add(15D);
		Double[] d = new Double[list1.size()];
		list1.toArray(d);
		Utils.printArr(d);
		
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("D:/out.txt", true));
			bw.write("Data: " + Arrays.deepToString(d) + "\n");
			bw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		
		
		Test_01 t = new Test_01();
		List<Integer> list = new ArrayList<>();;
		for(int i = 0; i < 21; i++){
			list.add(new Random().nextInt(100));
		}
		//Utils.printList(list);
		int[][] arr = t.listToArray(list);
		//Utils.printArr(arr);
		
		int[][] arr1 = new int[][]{
				{3, 15},
				{4, 19},
				{6, 23},
				{3, 16},
				{5, 16},
				{7, 21},
		};
		
		int[][] arr2 = new int[][]{
				{2, 11},
				{4, 22},
				{4, 16},
				{3, 18},
				{5, 17},
				
		};
		int[][] arr3 = new int[][]{
				{2, 67},
				{4, 19},
				{3, 19},
		};
		
		int[][] arr4 = new int[][]{
				{12, 18},
		};
		
		int[][] arr5 = new int[][]{
				{14, 23},
				{14, 24},
				{15, 22},
				{15, 23},
				{16, 22},
				
		};
		int[][] arr6 = new int[][]{
				{9, 18},
				{9, 19},
				{11, 17},
		};
		//System.out.println(Arrays.deepToString(d));
		//System.out.println(Arrays.deepToString(arr1));
		int[][] arr7 = new int[][]{
				{9, 26},
				{10, 25},
				
				
		};
		int[][] r = t.intersection(0.4f, arr7);
		Utils.printArr(r);
	}
	
	
	public int[][] listToArray(List<Integer> list){
		int[][] arr = new int[list.size() / 2][2];
		for(int i = 0; i < list.size() / 2; i++){
			arr[i][0] = list.get(2 * i);
			arr[i][1] = list.get(2 * i + 1);
		}
		return arr;
	}
	
	
	public int[][] intersection(float cover, int[][]... arrs){	
		int left = arrs[0][0][0];
		int right = arrs[0][0][1];
		int leftMin = left;
		int rightMax = right;
		int record = 0;
		for(int[][] t : arrs){
			for(int i = 0; i < t.length; i++){
				if(t[i][0] > left)		//左侧最大值
					left = t[i][0];
				if(t[i][1] < right)		//右侧最小值
					right = t[i][1];
				
				if(t[i][0] < leftMin)		//左侧最小值
					leftMin = t[i][0];
				if(t[i][1] > rightMax)		//右侧最大值
					rightMax = t[i][1];
				
				record++;
			}
		}
		
		int[] leftTmp = new int[left - leftMin];
		int[] rightTmp = new int[rightMax - right];
		for(int[][] t : arrs){
			for(int i = 0; i < t.length; i++){
				if(t[i][0] < left)
					leftTmp[t[i][0] - leftMin]++;
				if(t[i][1] > right)
					rightTmp[rightMax - t[i][1]]++;
			}
		}
		
		for(int i = leftTmp.length - 1; i > 0; i--){
			leftTmp[i - 1] += leftTmp[i];
		}
		
		for(int i = rightTmp.length - 1; i > 0; i--){
			rightTmp[i - 1] += rightTmp[i];
		}
		
		//System.out.println("record: " + record );
		
		int m = 0; 
		for(int i = 0; i < leftTmp.length - 1; i++){
			if(((float)leftTmp[i] / (float)record > cover) && leftTmp[i] > leftTmp[i + 1]){
				//System.out.println((float)leftTmp[i] / (float)record);
				m++;
			}else{
				break;
			}
		}
		left -= m;
		
		m = 0;
		for(int i = 0; i < rightTmp.length - 1; i++){
			if((float)rightTmp[i] / record > cover && rightTmp[i] > rightTmp[i + 1]){
				m++;
			}else{
				break;
			}
		}
		right += m;
		
		//Utils.printArr(leftTmp);
		//Utils.printArr(rightTmp);
		return new int[][]{{left, right}};
	}
}
