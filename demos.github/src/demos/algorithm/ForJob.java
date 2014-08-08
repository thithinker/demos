package demos.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.sound.midi.Sequence;

/**
 * 面试题
 * @author yize
 * 2014年8月3日
 */
public class ForJob {
	public static void main(String[] args) {
		ForJob t = new ForJob();	
		/*//System.out.println(t.add(-20, 52));
	//	t.itemSum(20, 15);
		
		int[] arr = {5, 9,4,3,2,5,4,3,2};
		
		int[][] arr2 = {
				{1, 2, 0, 3, 4},
				{2, 3, 4, 5, 1},
				{1, 1, 5, 3, 0}
		};
		int[][] r2 = t.maxSubMatrix(arr2);
		//System.out.println(Arrays.deepToString(r2));
		
		System.out.println(t.numOfOne(1210000));
		
		System.out.println(t.transNum(81, 4));*/
		
		//test sequence()
		List<String> list = new ArrayList<String>();
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		t.sequence(list);
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
	
	/**
	   *   求一个矩阵中最大的二维矩阵(四个数的和最大)
	   *   2014年8月6日
	 * @param arr 输入矩阵
	 * @return 行数和列数均为2的矩阵
	 */
	public int[][] maxSubMatrix(int[][] arr){
		int[][] tmp = new int[arr.length - 1][arr[0].length - 1];
		int max = Integer.MIN_VALUE;
		int m = 0, n = 0;
		for(int i = 0; i < tmp.length; i++){
			for(int j = 0; j < tmp[0].length; j++){
				tmp[i][j] = arr[i][j] + arr[i][j + 1] + arr[i + 1][j] + arr[i + 1][j + 1];
				if(tmp[i][j] > max){
					max = tmp[i][j];
					m = i;
					n = j;
				}
			}
		}
		
		int[][] r = {{arr[m][n], arr[m][n + 1]}, {arr[m + 1][n], arr[m + 1][n +1]}};
		return r;
	}
	
	/**
	 * 求某一正数范围内所有数中1出现的次数
	 * 2014年8月6日
	 * @param max 该范围的最大值
	 * @return 1到max中1出现的次数
	 */
	public int numOfOne(int max){
		int total = 0;
		for(int i = 1; i <= max; i++){
			total += numOfOneInNum(i);
		}
		return total;
	}
	
	/**
	 * 计算该数中包含1的个数
	 * 2014年8月6日
	 * @param n 输入数字
	 * @return 数字n的十进制表示中1的个数
	 */
	private int numOfOneInNum(int n){
		int r = 0;
		String numToString = String.valueOf(n);
		for(int i = 0; i < numToString.length(); i++){
			if(numToString.charAt(i) == '1')
				r++;
		}
		return r;
	}
	
	/**
	 * 将十进制数decimal转换为2进制数
	 * 2014年8月6日
	 * @param decimal 十进制数
	 * @return 该十进制数的二进制数的字符串表示形式
	 */
	public String transNum(int decimal){
		return transNum(decimal, 2);
	}
	/**
	 * 将十进制数decimal转换为n进制数
	 * 2014年8月6日
	 * @param decimal 十进制数
	 * @param n n进制数
	 * @return 该十进制数的n进制数的字符串表示形式
	 */
	public String transNum(int decimal, int n){
		if(decimal == 0){
			return 0 + "";
		}
		StringBuilder r = new StringBuilder();
		
		while(decimal != 0){
			r.insert(0, decimal % n);
			decimal /= n;
		}
		
		return r.toString();
	}
	
	/**
	 * 对列表中的字符进行全排列
	 * 2014年8月8日
	 * @param chs 字符列表
	 */
	public <E> void sequence(List<E> chs){
		sequence(chs, chs.size());
	}
	
	/**
	 * 选取列表的部分元素进行排列
	 * 2014年8月8日
	 * @param chs 字符列表
	 * @param len 进行排列的字符个数
	 */
	public <E> void sequence(List<E> chs, int len){
		List<E> tmp = new ArrayList<>();
		sequence(tmp, chs, 4);
	}
	
	/**
	 * 递归求解全排列
	 * 2014年8月8日
	 * @param tmp 已排列字符
	 * @param chs 未排列字符
	 * @param len 进行排列的字符个数
	 */
	private <E> void sequence(List<E> tmp, List<E> chs, int len){
		if(tmp.size() == len){
			System.out.println(tmp);
			return;
		}
		
		for(int i = 0; i < chs.size(); i++){
			List<E> newTmp = new ArrayList<>(tmp);
			List<E> newChs = new ArrayList<>(chs);
			newTmp.add(newChs.get(i));
			newChs.remove(i);
			sequence(newTmp, newChs, len);
		}
	}
}
