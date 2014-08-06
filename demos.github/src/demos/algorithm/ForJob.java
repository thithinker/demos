package demos.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * 面试题
 * @author yize
 * 2014年8月3日
 */
public class ForJob {
	public static void main(String[] args) {
		ForJob t = new ForJob();	
		System.out.println(t.add(-20, 52));
		//t.itemSum(20, 215);
	}
	/**
	 * 计算两个数的和
	 * @param num1 整数
	 * @param num2 整数
	 * @return 两个数的和
	 */
	public int add(int num1, int num2){
		if(num2 == 0)
			return num1;
		int sum = num1 ^ num2;
		int carry = (num1 & num2) << 1;
		return add(sum, carry);
	}
	
	/**
	 * 列出所有从1，2，...，max中和为sum的数（类似背包问题）
	 * @param max 最大数
	 * @param sum 和
	 */
	public void itemSum(int max, int sum){
		int up = max > sum ? sum : max;
		
		List<Integer> list = new ArrayList<>();
		printResult(sum, up, list);
	}
	
	/**
	 * 递归求解
	 * @param sum 当前要求的和
	 * @param max 当前的最大值
	 * @param list 已用数的列表
	 */
	private void printResult(int sum, int max, List<Integer> list){
		if(sum == 0){
			System.out.println(list);
			return ;
		}
		if(max < 0 || sum < 0){
			return;
		}
		//不使用最大值
		printResult(sum, max - 1, list);
		
		//使用最大值
		List<Integer> tmp = new ArrayList<>(list);
		tmp.add(max);
		printResult(sum - max, max - 1, tmp);
	}
}
