package demos.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 插入排序:直接插入排序、折半插入排序和系尔排序 交换排序:冒泡排序和快速排序 选择排序:简单选择排序和堆排序 归并排序:归并排序
 * 
 * 基本思想 插入排序:将第N个记录插入到前面(N-1)个有序的记录当中。 交换排序:按照某种顺序比较两个记录的关键字大小，然后根据需要交换两个记录的位置。
 * 选择排序:根据某种方法选择一个关键字最大的记录或者关键字最小的记录，放到适当的位置。
 * 
 * 排序方法比较 排序方法 平均时间 最坏时间 辅助存储 直接插入排序 O(N2) O(N2) O(1) 起泡排序 O(N2) O(N2) O(1) 快速排序
 * O(Nlog2N) O(N2) O(Nlog2N) 简单选择排序 O(N2) O(N2) O(1) 堆排序 O(Nlog2N) O(Nlog2N)
 * O(1) 归并排序 O(Nlog2N) O(Nlog2N) O(n) 基数排序 O(d(n+radix)) O(d(n+radix)) O(radix)
 * 
 * 
 * 
 * @author Administrator
 * 
 */
public class SortTest {

	public static void main(String[] args) throws Exception {
		// 测试排序是否正确
		// String[] testErr=new
		// String[]{"冒泡排序","简单选择排序","直接插入排序","折半插入排序","系尔排序","堆排序","归并排序","快速排序"};
		// new SortTest().testErr(testErr);

		// 排序1（全部）
		String[] strs = new String[] { "冒泡排序", "简单选择排序", "直接插入排序", "折半插入排序", "希尔排序", "堆排序", "归并排序", "快速排序" };
		new SortTest().test(strs, 10000, 50000, 5000);

		// 排序2（加强）
		String[] strs2 = new String[] { "希尔排序", "堆排序", "归并排序", "快速排序" };
		new SortTest().test(strs2, 100000, 1900000, 100000);

	}

	private void testErr(String[] strings) throws Exception {

		// System.out.println(Arrays.toString(old));
		System.out.println(Arrays.toString(strings));

		Number[] old = getRundom(50);
		Integer[] oo = { 1, 2, 3, 3, 2, 21, 5, 6, 7, 78, 5, 65, 8, 7, 6, 6, 6, 6, 6, 9, 56544, 354, 32, 4, 456, 8, 89, -9, 0, 3, 243, -321, 321, -3,
				-2, 21 };
		old = oo;
		for (String s : strings) {
			Number[] testNum = Arrays.copyOf(old, old.length);
			long begin = System.currentTimeMillis();
			SortTest.class.getMethod(s, Number[].class).invoke(this, (Object) testNum);

			long end = System.currentTimeMillis();
			System.out.println(s + ":" + (end - begin) + "/t");
			System.out.println(Arrays.toString(testNum));
		}
		System.out.println();

	}

	private void test(String[] strings, long begin, long end, long step) throws Exception {
		System.out.print("数量/t");
		for (String str : strings) {
			System.out.print(str + "/t");
		}
		System.out.println();
		for (long i = begin; i < end; i = i + step) {
			System.out.print(i + "个/t");
			Number[] old = getRundom(i);
			for (String s : strings) {
				Number[] testNum = Arrays.copyOf(old, old.length);
				long beginTime = System.currentTimeMillis();
				SortTest.class.getMethod(s, Number[].class).invoke(this, (Object) testNum);

				long endTime = System.currentTimeMillis();
				System.out.print((endTime - beginTime) + "/t");
				// System.out.println(Arrays.toString(testNum));
			}
			System.out.println();
		}

	}

	private static Integer[] getRundom(long num) {
		List<Integer> list = new ArrayList<Integer>();
		for (long i = 0; i < num; i++) {
			int k;
			if (Math.random() > 0.5) {
				k = (int) (Math.random() * Integer.MAX_VALUE);
			} else {
				k = (int) (Math.random() * Integer.MIN_VALUE);
			}
			list.add(k);
		}
		return list.toArray(new Integer[list.size()]);
	}

	/**
	 * 插入排序————直接插入排序
	 * 
	 * @param data
	 */
	public static void 直接插入排序(Number[] data) {
		Number tmp = null;

		for (int i = 1; i < data.length; i++) {
			tmp = data[i];
			int j = i - 1;
			while (j >= 0 && tmp.doubleValue() < data[j].doubleValue()) {
				data[j + 1] = data[j];
				j--;
			}
			data[j + 1] = tmp;
		}
	}

	/**
	 * 插入排序————折半插入排序
	 * 
	 * @param data
	 */
	public static void 折半插入排序(Number[] data) {
		Number tmp = null;
		for (int i = 1; i < data.length; i++) {
			tmp = data[i];
			int smallpoint = 0;
			int bigpoint = i - 1;

			while (bigpoint >= smallpoint) {
				int mid = (smallpoint + bigpoint) / 2;
				if (tmp.doubleValue() > data[mid].doubleValue()) {
					smallpoint = mid + 1;
				} else {
					bigpoint = mid - 1;
				}
			}
			for (int j = i; j > smallpoint; j--) {
				data[j] = data[j - 1];
			}
			data[bigpoint + 1] = tmp;
		}
	}

	/**
	 * 插入排序————希尔排序
	 * 
	 * @param data
	 */
	public static void 希尔排序(Number[] data) {
		int span = data.length / 7;
		if (span == 0)
			span = 1;
		while (span >= 1) {
			for (int i = 0; i < span; i++) {
				for (int j = i; j < data.length; j = j + span) {
					// 组内直接插入排序
					int p = j - span;
					Number temp = data[j];
					while (p >= 0 && data[p].doubleValue() > temp.doubleValue()) {
						data[p + span] = data[p];
						p -= span;
					}
					data[p + span] = temp;
				}
			}
			span = span / 2;
		}

	}

	/**
	 * 交换排序————冒泡排序
	 * 
	 * @param data
	 */
	public static void 冒泡排序(Number[] data) {
		for (int i = 0; i < data.length; i++) {
			// 将相邻两个数进行比较，较大的数往后冒泡
			for (int j = 0; j < data.length - i - 1; j++) {
				if (data[j].doubleValue() > data[j + 1].doubleValue()) {
					// 交换相邻两个数
					swap(data, j, j + 1);
				}
			}
		}
	}

	/**
	 * 交换排序————快速排序
	 * 
	 * @param data
	 */
	public static void 快速排序(Number[] data) {
		QuickSort(data, 0, data.length - 1);
	}

	private static void QuickSort(Number[] data, int begin, int end) {
		// System.out.println(begin+":"+end);
		if (begin < end) {
			// 取中点
			int mid = (begin + end) / 2;
			if (data[end].doubleValue() < data[begin].doubleValue()) {
				swap(data, end, begin);
			}
			if (data[end].doubleValue() < data[mid].doubleValue()) {
				swap(data, end, mid);
			}
			if (data[mid].doubleValue() < data[begin].doubleValue()) {
				swap(data, mid, begin);
			}

			swap(data, mid, begin);

			// System.out.println(Arrays.toString(Arrays.copyOfRange(data,
			// begin, end)) );
			int min = begin + 1;
			int big = end;

			while (true) {
				while (min < big && data[min].doubleValue() < data[begin].doubleValue()) {
					min++;
				}
				while (min < big && data[big].doubleValue() >= data[begin].doubleValue()) {
					big--;
				}
				if (min >= big) {
					break;
				}
				swap(data, min, big);
			}
			if (data[begin].doubleValue() > data[min].doubleValue()) {
				swap(data, begin, min);
			}

			if (min > 1)
				QuickSort(data, begin, min - 1);
			// if(min<end)
			QuickSort(data, min, end);
		}
	}

	/**
	 * 选择排序————简单选择排序
	 * 
	 * @param data
	 */
	public static void 简单选择排序(Number[] data) {
		for (int i = 0; i < data.length - 1; i++) {
			int smallPoint = i;
			for (int j = i + 1; j < data.length; j++) {
				if (data[smallPoint].doubleValue() > data[j].doubleValue()) {
					smallPoint = j;
				}
			}
			swap(data, i, smallPoint);
		}

	}

	/**
	 * 选择排序————堆排序
	 * 
	 * @param data
	 */
	public static void 堆排序(Number[] data) {

		int n = data.length;
		for (int i = n / 2; i >= 0; i--) {
			keepHeap(data, n, i);
		}
		while (n > 0) {
			swap(data, 0, n - 1);
			keepHeap(data, --n, 0);
		}
	}

	private static void keepHeap(Number[] a, int n, int i) {
		Number x = a[i];
		int j = 2 * i + 1;
		while (j <= n - 1) {
			if (j < n - 1 && a[j].doubleValue() < a[j + 1].doubleValue())
				++j;
			if (a[j].doubleValue() > x.doubleValue()) {
				a[i] = a[j];
				i = j;
				j = 2 * i;
			} else {
				break;
			}
		}
		a[i] = x;
	}

	/**
	 * 归并排序法————归并排序
	 * 
	 * @param data
	 */
	public static void 归并排序(Number[] data) {
		Number[] result = merge_sort(data, 0, data.length - 1);
		for (int i = 0; i < result.length; i++) {
			data[i] = result[i];
		}
	}

	private static Number[] merge_sort(Number[] array, int start, int end) {
		Number[] result = new Number[end - start + 1];
		if (start < end) {
			int mid = (start + end) / 2;
			Number[] left = merge_sort(array, start, mid);
			Number[] right = merge_sort(array, mid + 1, end);
			result = merge(left, right);
		} else if (start == end) {
			result[0] = array[start];
			return result;
		}
		return result;
	}

	private static Number[] merge(Number[] left, Number[] right) {
		Number[] result = new Number[left.length + right.length];
		int i = 0;
		int j = 0;
		int k = 0;
		while (i < left.length && j < right.length) {
			if (left[i].doubleValue() < right[j].doubleValue()) {
				result[k++] = left[i++];
			} else {
				result[k++] = right[j++];
			}
		}
		while (i < left.length) {
			result[k++] = left[i++];
		}
		while (j < right.length) {
			result[k++] = right[j++];
		}
		return result;
	}

	/**
	 * 交换数组中指定的两元素的位置
	 * 
	 * @param data
	 * @param x
	 * @param y
	 */
	private static void swap(Number[] data, int x, int y) {
		Number temp = data[x];
		data[x] = data[y];
		data[y] = temp;
	}
}