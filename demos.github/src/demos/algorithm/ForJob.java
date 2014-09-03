package demos.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang3.RandomStringUtils;

class BinaryNode {
	int value;
	BinaryNode left;
	BinaryNode right;
}

/**
 * 面试题
 * @author yize
 * 2014年8月3日
 */
public class ForJob {
	public static void main(String[] args) {
		ForJob t = new ForJob();	
		
		//test continuousSum()
		//t.continuousSum(23345);
		
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
		
		/*//test sequence()
		List<String> list = new ArrayList<String>();
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		t.sequence(list);*/
		
		/*//test continuousSum()
		t.continuousSum(999);
		System.out.println("");
		t.continuousSum(9999);
		System.out.println();
		t.continuousSum(99999);*/
		
		//test uglyNumOfnth()
		//System.out.println(t.uglyNumOfnth());
		
		/*//Test FindTwoDiffNum()
		int[] arr = {4,3,5,6,5,3,1,1, 4, 7, 8, 6};
		t.findTwoDiffNum(arr);*/
		
		/*//Test
		BinaryNode root = new BinaryNode();
		root.value = 1;
		BinaryNode node1 = new BinaryNode();
		node1.value = 2;
		BinaryNode node2 = new BinaryNode();
		node2.value = 3;
		BinaryNode node3 = new BinaryNode();
		node3.value = 4;
		BinaryNode node4 = new BinaryNode();
		node4.value = 5;
		BinaryNode node5 = new BinaryNode();
		node5.value = 6;
		BinaryNode node6 = new BinaryNode();
		node6.value = 7;
		BinaryNode node7 = new BinaryNode();
		node7.value = 8;
		BinaryNode node8 = new BinaryNode();
		node8.value = 9;
		
		root.left = node1;
		root.right = node2;
		node1.left = node3;
		node1.right = node4;
		node2.right = node5;
		node4.left = node6;
		node5.right = node7;
		node7.left = node8;
		t.printBinaryTree(root);
		System.out.println();
		BinaryNode b = t.copyBinaryTree(root);
		System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^");
		t.printBinaryTree(b);*/
		
		
		/*//Test pow
		System.out.println(t.pow(0, 0));
		System.out.println(t.pow(2, -4) == 0.0625);*/
		
		/*//longestIncSubString
		String result = t.longestIncSubString("awestgermaniclanguagethatwasfirstspokeninearlymedievalenglandandisnowagloballinguafranca");
		System.out.println("长度：" + result.length() + "\n子串：" + result);*/
		
		/*//test numLLBR
		int len = 20;
		int[] arr = new int[len];
		int[] arr2 = {1,3, 3, 1, 4, 2, 5, 5, 9};
		for (int i = 0; i < len; i++) {
			arr[i] = new Random().nextInt(10);
		}
		Utils.printArr(arr2);
		t.numLLBR(arr2);*/
		
		/*//test kthNum
		int[] arr = {1,3,8, 4, 6, 2, 9};
		int k = 3;
		for(int i = 0; i< arr.length; i++){
			System.out.println(i + "th: " + t.kthNum(arr, i + 1));
		}
		
		System.out.println(k + "th: " + t.kthNum(arr, k));*/
		
		
		/*//test sumOfRoute
		BinaryNode root = new BinaryNode();
		root.value = 10;
		BinaryNode node1 = new BinaryNode();
		node1.value = 12;
		BinaryNode node2 = new BinaryNode();
		node2.value = 12;
		BinaryNode node3 = new BinaryNode();
		node3.value = 4;
		BinaryNode node4 = new BinaryNode();
		node4.value = 7;
		//BinaryNode node5 = new BinaryNode();
		//node5.value = 6;
		root.left = node1;
		root.right = node2;
		node1.left = node3;
		node1.right = node4;
		t.sumOfRoute(root, 22);*/
		
		
		/*LinkedHashSet<Integer> linkedHashSet = new LinkedHashSet<>();
		Set<Integer> treeSet = new TreeSet<>();
		Set<Integer> hashSet = new HashSet<>();
		for(int i = 0; i < 10; i++){
			int n = new Random().nextInt(1000);
			System.out.print(n + ", ");
			linkedHashSet.add(n);
			treeSet.add(n);
			hashSet.add(n);
		}
		System.out.println();
		System.out.println("LinkedHashSet: " + linkedHashSet);
		System.out.println("TreeSet: " + treeSet);
		System.out.println("HashSet: " + hashSet);*/
		
		//test getCommonLen
		char[] chs = {'a', 'b', 'c'};
		String str1 = RandomStringUtils.random(2000, chs);
		String str2 = RandomStringUtils.random(1000, chs);
		
		System.out.println(str1 + " \n" + str2);
		String comm = t.getCommonLen(str1, str2);
		System.out.println(comm);
		
		String str3 = "abcbcd";
		String str4 = "bbcbb";
		System.out.println(t.getCommonLen(str3, str4));
		
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
		if(len > chs.size()){
			System.out.println("要求排列的字符数大于列表中字符数");
			return;
		}
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
	
	/**
	 * 连续n个数的和为给定值
	 * 2014年8月8日
	 * @param sum 和值
	 */
	public void continuousSum(int sum){
		int p1 = 1;
		int p2 = 1;
		while(p2 <= sum / 2 + 1){
			if(sum(p1, p2) < sum)
				p2++;
			else if(sum(p1, p2) > sum)
				p1++;
			else {
				print(p1, p2);
				p2++;
			}
		}
	}
	/**
	 * 输出从p1到p2的数
	 * 2014年8月8日
	 * @param p1 起始值
	 * @param p2 终点值
	 */
	private void print(int p1, int p2) {
		for(int i = p1; i <= p2; i++){
			System.out.print(i + " ");
		}
		System.out.println();
	}
	/**
	 * 计算p1到p2的和（包含p1和p2）
	 * 2014年8月8日
	 * @param p1 起始值
	 * @param p2 终止值
	 * @return p1到p2的和
	 */
	private int sum(int p1, int p2) {
		return (p2 - p1 + 1) * (p1 + p2) / 2;
	}
	
	/**
	 * 改变数组中元素顺序，使得数组中所有奇数在所有偶数的左侧
	 * @param a 待处理数组
	 */
	public void oddEvenArr(int[] a){
		int left = 0;
		int right = a.length - 1;
		int tmp;
		while(left < right){
			while(left < a.length && a[left] % 2 != 0) left++;		//注意越界检查
			while(right > -1 && a[right] % 2 == 0) right--;
			
			if(left < right){
				tmp = a[left];
				a[left++] = a[right];
				a[right--] = tmp;
			}
		}
	}
	
	/**
	 * 寻找第1500个丑数（丑数：1作为第一个丑数，其余的丑数的因子只有2,3,5）
	 * 2014年8月8日
	 * @return 第1500个丑数（结果：292968750000）
	 */
	public long uglyNumOfnth(){
		return uglyNumOfnth(1500);
	}
	/**
	 * 寻找第n个丑数
	 * 2014年8月8日
	 * @param n 序号
	 * @return 第n个丑数
	 */
	public long uglyNumOfnth(int n){
		Set<Long> nums = new TreeSet<>();
		//long[] factors = {2,3,5};
		Set<Long> tmp1 = new TreeSet<>();
		Set<Long> tmp2 = new TreeSet<>();
		nums.add(1L);
		tmp1.add(1L);
		while(nums.size() < n){
			for(Long num : tmp1){
				tmp2.add(num * 2);
				tmp2.add(num * 3);
				tmp2.add(num * 5);
			}
			
			tmp1.clear();
			tmp1 = new TreeSet<>(tmp2);
			nums.addAll(tmp2);
			//System.out.println("tmp1: " + tmp1.size());
			//System.out.println("size: " + nums.size() + "\t" + nums);
			tmp2.clear();
		}
		List<Long> list = new ArrayList<>(nums);
		return list.get(n - 1);
	}
	
	/**
	 * 使用异或找出一个数组中只出现一次的数(其余的数均出现两次(或偶数次？))
	 * 2014年8月11日
	 * @param arr 输入数组
	 */
	public void findTwoDiffNum(int[] arr){
		int tmp = arr[0];
		for(int i = 1; i < arr.length; i++){
			tmp ^= arr[i];
		}
		String s = Integer.toBinaryString(tmp);
		int nonZero = 0;
		for(int i = s.length() - 1; i >= 0 ; i--){
			if(s.charAt(i) == '1'){
				nonZero = s.length() - (i + 1);
				break;
			}
		}
		List<Integer> list_0 = new ArrayList<>(arr.length);
		List<Integer> list_1 = new ArrayList<>(arr.length);
		nonZero = 1 << nonZero;
		
		for(int i = 0; i < arr.length; i++){
			if((arr[i] & nonZero) == nonZero){
				list_1.add(arr[i]);
			}else{
				list_0.add(arr[i]);
			}
		}
		int num1 = list_0.get(0);
		int num2 = list_1.get(0);
		for(int i = 1; i < list_0.size(); i++){
			num1 = num1 ^ list_0.get(i);
		}
		for(int i = 1; i < list_1.size(); i++){
			num2 = num2 ^ list_1.get(i);
		}
		System.out.println(num1 + " " + num2);
	}
	
	/**
	 * 复制二叉树
	 * 2014年8月11日
	 * @param root 原二叉树根结点
	 * @return 新二叉树根结点
	 */
	public BinaryNode copyBinaryTree(BinaryNode root){
		if(root == null){
			return null;
		}
		BinaryNode result = root;
		if(root.left != null){
			result.left = copyBinaryTree(root.left);
		}
		if(root.right != null){
			result.right = copyBinaryTree(root.right);
		}
		return result;
	}
	
	@Deprecated
	public BinaryNode copy2(BinaryNode root){
		if(root == null){
			return null;
		}
		BinaryNode result = copyTree(root);
		return result;
	}
	
	/**
	 * 递归复制二叉树
	 * 2014年8月11日
	 * @param oldRoot 当前结点
	 * @return 
	 */
	@Deprecated
	private BinaryNode copyTree(BinaryNode oldRoot){
		BinaryNode newRoot = oldRoot;
		if(oldRoot.left != null){
			newRoot.left = copy2(oldRoot.left);
		}
		if(oldRoot.right != null){
			newRoot.right = copy2(oldRoot.right);
		}
		return newRoot;
	}
	
	/**
	 * 中序遍历二叉树
	 * 2014年8月11日
	 * @param root 二叉树根结点
	 */
	public void printBinaryTree(BinaryNode root){
		if(root == null){
			return;
		}
		printBinaryTree(root.left);
		System.out.print(root.value + " ");
		printBinaryTree(root.right);
	}
	
	/**
	 * 次方计算
	 * 2014年8月11日
	 * @param base 底数
	 * @param exp 指数
	 * @return base的exp次方
	 */
	public double pow(double base, int exp){
		if(base == 0 && exp <= 0){
			throw new RuntimeException("base can't be zero with exp equals or under zero");
		}
		if(exp == 0){
			return 1;
		}
		if(exp == 1){
			return base;
		}
		boolean isNegative = false;
		if(exp < 0){
			exp = -exp;
			isNegative = true;
		}
		double result = pow(base, exp >> 1);
		result *= result;
		if(!isEven(exp)) {
			result *= base;
		}
		if(isNegative){
			result = 1 / result;
		}
		//System.out.println("RESULT: " + result + " exp: " + exp);
		return result;
	}
	/**
	 * 偶数判断
	 * 2014年8月11日
	 * @param num 输入数
	 * @return 输入数为偶数是返回true，否则返回false
	 */
	public boolean isEven(int num){
		return (num & 1) == 0;
	}
	
	/**
	 *动态规划法 最长非递减子串
	 * 2014年8月13日
	 * @param str 输入字符串
	 * @return 输入字符串的最长非递减子串
	 */
	public String longestIncSubString(String str){
		if(str == null){
			return null;
		}
		if(str.length() == 0){
			return "";
		}
		
		String[] subStrs = new String[str.length()];
		Integer record = 0;
		
		str = str.toLowerCase().replace(" ", "");
		int[] tmp = new int[str.length()];
		int len = 1;
		for(int i = 0; i < str.length(); i++){
			tmp[i] = 1;
			subStrs[i] = str.charAt(i) + "";
			for(int j = 0; j < i; j++){
				if(str.charAt(j) <= str.charAt(i) && tmp[j] + 1 > tmp[i]){
					tmp[i] = tmp[j] + 1;
					subStrs[i] = subStrs[j] + str.charAt(i);
				}
			}
			if(tmp[i] > len){
				len = tmp[i];
				record = i;
			}
		}
		return subStrs[record];
	}
	
	/**
	 * baidu 寻找数组中的所有满足条件的数：该数不小于左边的数，不大于右边的数
	 * 2014年8月15日
	 * @param arr 输入数组
	 */
	public void numLLBR(int[] arr){
		int[] tmp = new int[arr.length];
		int t = arr[arr.length - 1];
		for(int i = arr.length - 1; i >= 0; i--){
			if(arr[i] < t){
				t = arr[i];
			}
			tmp[i] = t;
		}
		int leftMax = arr[0];
		for(int i = 0; i < arr.length; i++){
			if(arr[i] >= leftMax && arr[i] <= tmp[i]){
				System.out.println(arr[i]);
			}
			if(leftMax < arr[i]){
				leftMax = arr[i];
			}
		}
	}
	
	/**
	 * 寻找一个数组中第k小的数
	 * 2014年8月19日
	 * @param arr 输入输入
	 * @param k 序数
	 * @return 第k小的数
	 *//*
	public int kthNum(int[] arr, int k){
		if(k < 0 || k > arr.length){
			throw new IllegalArgumentException("k should be in [0, " + arr.length + ")");
		}
		
		int t = partition(arr, 0, arr.length - 1);
		//System.out.println("t: " + t + ", k: " + k);
		while(t != k - 1 && t > 0 && t < arr.length){
			//System.out.println("t, k: " + t + ", " + k);
			if(t < k - 1){
				t = partition(arr, 0, t - 1);
				
				System.out.println("t: " + t);
				Utils.printArr(arr);
			}else if(t > k - 1){
				t = partition(arr, t + 1, arr.length - 1);
				
				System.out.println("t: " + t);
				Utils.printArr(arr);
			}
		}
		return arr[k - 1];
	}
	*//**
	 * 对输入数组进行分区，小于枢纽元的数在枢纽元的左侧，大于枢纽元的数在枢纽元的右侧
	 * 2014年8月19日
	 * @param arr 输入数组
	 * @param start 数组的开始下标
	 * @param end 输入的结束下标
	 * @return 枢纽元的下标
	 *//*
	private int partition(int[] arr, int start, int end){
		int pivot = midian(arr, start, end);
		//Utils.printArr(arr);
		int leftPos = start;
		int rightPos = end  - 1;
		while(leftPos < rightPos){
			while(arr[leftPos] < arr[pivot]){
				leftPos++;
			}
			while(arr[rightPos] > arr[pivot]){
				rightPos--;
			}
			if(leftPos < rightPos)
				swap(arr, leftPos, rightPos);
		}
		
		swap(arr, leftPos, pivot);
		//Utils.printArr(arr);
		return leftPos;
	}
	*//**
	 * 枢纽元的下标
	 * 2014年8月19日
	 * @param arr 输入数组
	 * @param start 开始下标
	 * @param end 结束下标
	 * @return 枢纽元的下标
	 *//*
	private int midian(int[] arr, int start, int end){
		int mid = (start + end) / 2;
		if(arr[start] > arr[mid]){
			swap(arr, start, mid);
		}
		if(arr[start] > arr[end]){
			swap(arr, start, end);
		}
		if(arr[mid] > arr[end]){
			swap(arr, mid, end);
		}
		swap(arr, mid, end);
		return end;
	}
	*//**
	 * 交换数组中两个给定下标的值
	 * 2014年8月19日
	 * @param arr 输入数组
	 * @param p1 下标
	 * @param p2 下标
	 *//*
	private void swap(int arr[], int p1, int p2){
		int tmp = arr[p1];
		arr[p1] = arr[p2];
		arr[p2] = tmp;
	}*/
	/**
	 * 在二叉树中找出和为某一值的所有路径（路径：从根节点到叶节点）
	 * 2014年8月24日
	 * @param root 二叉树的根节点
	 * @param sum 待求的总和
	 */
	public void sumOfRoute(BinaryNode root, int sum){
		List<Integer> list = new ArrayList<>();
		route(root, list, sum);
	}
	/**
	 * 递归求解
	 * 2014年8月24日
	 * @param node 当前节点
	 * @param list 记录已搜索过的值
	 * @param sum 待求的总和
	 */
	private void route(BinaryNode node, List<Integer> list, int sum){
		if(node != null){
			list.add(node.value);
			int currSum = sumOfList(list);
			//当前节点是叶节点并且路径的和为所求
			if(node.left == null && node.right == null && currSum == sum){
				System.out.println(list);
			}else{
				if(node.left != null && sumOfList(list) + node.left.value <= sum){
					List<Integer> tmpList = new ArrayList<>(list);
					route(node.left, tmpList, sum);
				}
				if(node.right != null && sumOfList(list) + node.right.value <= sum){
					List<Integer> tmpList = new ArrayList<>(list);
					route(node.right, tmpList, sum);
				}
			}
		}
	}
	/**
	 * 求列表中值的和
	 * 2014年8月24日
	 * @param list 输入列表
	 * @return 输入列表中值的和
	 */
	private int sumOfList(List<Integer> list){
		int sum = 0;
		for(Integer i : list){
			sum += i;
		}
		//System.out.println(list);
		return sum;
	}
	
	/**
	 * 两个输入字符串的连续公共子串
	 * 2014年8月30日
	 * @param str1 输入字符串
	 * @param str2 输入字符串
	 * @return 公共子串
	 */
	private String getCommonLen(String str1, String str2){
		String small, large;
		if(str1.length() > str2.length()){
			small = str2;
			large = str1;
		}else{
			small = str1;
			large = str2;
		}
		
		String tmp = "";
		String commonStr = "";
		
		for(int i = 0; i < small.length(); i++){
			for(int j = i + commonStr.length(); j < small.length(); j++){
				tmp = small.substring(i, j + 1);
				if(tmp.length() > commonStr.length() && large.contains(tmp)){
					commonStr = tmp;
				}else {
					break;
				}
			}
		}
		return commonStr;
	}
	
	
	
	
	
	
	
	
	
	
}
