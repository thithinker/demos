package zgl.test;

import java.util.BitSet;

/**
 * @date 2014.4.5
 * @author yize
 * 实现排序算法
 */
public class SortAlgorithm {
	/**
	 * 插入排序
	 * @param arr 输入数组
	 * @return 有序数组
	 */
	public int[] insertSort(int[] arr){
		int tmp = 0;
		int pos = 0;
		for(int i = 1; i < arr.length; i++){
			tmp = arr[i];
			pos = i;
			while(pos > 0 && tmp < arr[pos - 1]){
				arr[pos] = arr[pos - 1];
				pos--;
			}
			arr[pos] = tmp;
		}
		return arr;
	}
	
	/**
	 * 只可非重复的数组
	 * @param arr 一维数组
	 * @return 完成排序的一维数组
	 */
	public int[] BitSetSort(int[] arr){
		BitSet bs = new BitSet();
		for(int i = 0; i < arr.length; i++){
			bs.set(arr[i]);
		}
		int n = 0;
		for(int i = 0; i < bs.size(); i++){
			if(bs.get(i))
				arr[n++] = i;
		}
		return arr;
	}

	private int getMiddle(int[] list, int low, int high) {
		int tmp = list[low]; // 数组的第一个作为中轴
		while (low < high) {
			while (low < high && list[high] >= tmp) {
				high--;
			}
			list[low] = list[high]; // 比中轴小的记录移到低端
			while (low < high && list[low] <= tmp) {
				low++;
			}
			list[high] = list[low]; // 比中轴大的记录移到高端
		}
		list[low] = tmp; // 中轴记录到尾
		return low; // 返回中轴的位置
	}

	private void _quickSort(int[] list, int low, int high) {
		if (low < high) {
			int middle = getMiddle(list, low, high); // 将list数组进行一分为二
			_quickSort(list, low, middle - 1); // 对低字表进行递归排序
			_quickSort(list, middle + 1, high); // 对高字表进行递归排序
		}
	}
	/**
	 * 快速排序
	 * @param arr 输入数组
	 * @return 输出数组
	 */
	public int[] quickSort(int[] arr) {
		if (arr.length > 0) { // 查看数组是否为空
			_quickSort(arr, 0, arr.length - 1);
		}
		return arr;
	}
}
