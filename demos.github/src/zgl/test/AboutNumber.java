package zgl.test;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * @date 2014.4.4
 * @author yize
 * 
 *
 */
public class AboutNumber {
	/**
	 * 在有序数组查找两个和等于指定值的数
	 * @param nums 有序数组
	 * @param sum 和
	 * @return 包含所找到两个数的数组, 或者是两个最小的整数值
	 */
	public int[] findPairInSortedArray(int[] nums, int sum){
		int[] tmp = new int[nums.length];
		int[] result = {Integer.MIN_VALUE, Integer.MIN_VALUE};
		for(int i = 0; i < nums.length; i++){
			tmp[i] = sum - nums[i];
		}
		
		int m = 0;
		int n = nums.length - 1;
		while(m < nums.length && n >= 0){
			if(nums[m] == tmp[n]){
				result[0] = nums[m];
				result[1] = sum - nums[m];
				return result;
			}
			while(m < nums.length && nums[m] < tmp[n])
				m++;
			while(n >= 0 && tmp[n] < nums[m]){
				n--;
			}
		}
		return result;
	}
	
	public int[] findPair(int[] nums, int sum){
		int[] result = {Integer.MIN_VALUE, Integer.MIN_VALUE};
		BitSet bitSet = new BitSet();
		for(int i = 0; i < nums.length; i++){
			bitSet.set(nums[i]);
		}
		for(int i = 0; i < bitSet.size(); i++){		//ERROR 此处遍历范围不是nums.length
			if(bitSet.get(i) && bitSet.get(sum - i)){
				result[0] = i;
				result[1] = sum - i;
				return result;
			}
		}
		return result;
	}

	
	/**
	 * 从1～n个数中找出所有其和等于sum的数的组合
	 * @param sum 和值
	 * @param n 最大值
	 */
	Stack<Integer> stack = new Stack<>();
	public void findFactor(int sum, int n){
		//结束条件
		if(n <= 0 || sum <= 0)
			return;
		//输出
		if(sum == n){
			Iterator<Integer> iterator = stack.iterator();
			while(iterator.hasNext()){
				System.out.print(iterator.next() + " + ");
			}
			System.out.println(n);
		}
		stack.push(n);
		findFactor(sum - n, n - 1);
		stack.pop();
		findFactor(sum, n - 1);
	}
	
	/**
	 * 筛选法求指定范围内的素数（可将伴随数组改成BitSet）
	 * @date 2014.4.7
	 * @param max 最大范围
	 * @return 包含指定范围内素数的列表
	 */
	public List<Integer> findPrimeNum(int max){
		List<Integer> list = new ArrayList<>();		//返回列表
		int[] tmp = new int[max + 1];				//伴随数组
		for(int i = 2; i <= max; i++){				//初始认为每个数均为素数
			tmp[i] = 1;
		}
		
		int j;
		for(int i = 2; i + i <= max; i++){
			j = i + i;
			while(j <= max){						//只要为某一数的整数倍，即不是素数
				tmp[j] = 0;
				j += i;
			}
		}
		
		for(int i = 2; i <= max; i++){
			if(tmp[i] == 1){
				list.add(i);
			}
		}
		
		return list;
	}
	
	/**
	 * 寻找给定数组中某几个连续数和的最大值（非最优算法，最优算法时间复杂度O(n),如下）
	 * @date 2014.4.7
	 * @param arr 输入数组
	 * @return 子数组的最大值
	 */
	public int maxOfSubArr(int[] arr){
		int res = Integer.MIN_VALUE;
		int[][] tmp = new int[arr.length][arr.length];	//辅助数组，tmp[i][j]表示数组arr中从arr[i]到arr[j]的连续数的和（包括两端）
		for(int i = 0; i < arr.length; i++){
			for(int j = i; j < arr.length; j++){
				if(i == j){
					tmp[i][j] = arr[i];
				}else{
					tmp[i][j] = tmp[i][j - 1] + arr[j];
				}
				if(tmp[i][j] > res)
					res = tmp[i][j];
			}
		}
		return res;
	}
	
	/**
	 * 寻找给定数组中某几个连续数和的最大值
	 * @date 2014.4.7
	 * @param arr 输入数组
	 * @return 子数组的最大值
	 */
	public int maxOfSubArr_2(int[] arr){
		int res = arr[0];
		int tmp = 0;
		
		for(int i = 0; i < arr.length; i++){
			if(tmp < 0){
				tmp = arr[i];
			}else{
				tmp += arr[i];
			}
			if(res < tmp){
				res = tmp;
			}
		}
		return res;
	}
	/**扩展：
	 * 1、如果数组是二维数组,同样要你求最大子数组的和列?
	 * 2、如果是要你求子数组的最大乘积列?
	 * 3、如果同时要求输出子段的开始和结束列? 
	 */
	public static void main(String[] args) {
		AboutNumber aNumber  = new AboutNumber();
		/*List<Integer> list = aNumber.findPrimeNum(100);
		for(Integer i : list){
			System.out.println(i + " ");
		}
		System.out.println(list.size());*/
		
		int[] arr = {-10, -4,-7, -5};
		
		System.out.println(aNumber.maxOfSubArr_2(arr));
	}
}
