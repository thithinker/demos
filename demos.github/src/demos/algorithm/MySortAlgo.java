package demos.algorithm;

/**
 * 各种排序算法的实现和比较
 * @author yize
 * 2014年8月1日
 */
public class MySortAlgo {
	/**
	 * 插入排序
	 * @param a 待排序序列
	 */
	public static <T extends Comparable<? super T>> void insertSort(T[] a){
		int pos;
		for(int i = 1; i < a.length; i++){
			T tmp = a[i];
			for(pos = i; pos > 0 && tmp.compareTo(a[pos - 1]) < 0; pos--){
				a[pos] = a[pos - 1];
			}
			a[pos] = tmp;
		}
	}
	
	/**
	 * 希尔排序
	 * @param a 待排序序列
	 */
	public static <T extends Comparable<? super T>> void shellSort(T[] a){
		int pos;
		for(int gap = a.length / 2; gap > 0; gap /= 2){
			for(int i = gap; i < a.length; i++){
				T tmp = a[i];
				for(pos = i; pos >= gap && tmp.compareTo(a[pos - gap]) < 0; pos -= gap){
					a[pos] = a[pos - gap];
				}
				a[pos] = tmp;
			}
		}
	}
	
	/**
	 * 合并排序
	 * @param a 待排序序列
	 */
	public static <T extends Comparable<? super T>> void mergeSort(T[] a){
		@SuppressWarnings("unchecked")
		T[] tmpArray = (T[]) new Comparable[a.length];
		mergeSort(a, tmpArray, 0, a.length - 1);
	}
	/**
	 * 合并排序递归部分
	 * @param a 待排序数组
	 * @param tmpArray 临时数组
	 * @param left 待排序数组的左侧下标
	 * @param right 待排序数组的右侧下标
	 */
	private static <T extends Comparable<? super T>> void mergeSort(T[] a, T[] tmpArray, int left, int right){
		int center = (left + right) / 2;
		if(left < right){
			mergeSort(a, tmpArray, left, center);
			mergeSort(a, tmpArray, center + 1, right);
			merge(a, tmpArray, left, center + 1, right);
		}
	}
	/**
	 * 合并排序合并部分
	 * @param a 待排序数组
	 * @param tmpArray 临时数组
	 * @param leftPos 左侧开始下标
	 * @param rightPos 右侧开始下标
	 * @param rightEnd 右侧终点下标
	 */
	private static <T extends Comparable<? super T>> void merge(T[] a, T[] tmpArray, int leftPos, int rightPos, int rightEnd){
		int leftEnd = rightPos - 1;		//左侧终点下标
		int tmpPos = leftPos;			//临时数组开始下标
		int length = rightEnd - leftPos + 1;	//待排序数长度
		
		/**
		 * 交叉部分
		 */
		while(leftPos <= leftEnd && rightPos <= rightEnd){
			if(a[leftPos].compareTo(a[rightPos]) <= 0){
				tmpArray[tmpPos++] = a[leftPos++];
			}else{
				tmpArray[tmpPos++] = a[rightPos++];
			}
		}
		
		/**
		 * 左侧剩余
		 */
		while(leftPos <= leftEnd){
			tmpArray[tmpPos++] = a[leftPos++];
		}
		
		/**
		 * 右侧剩余
		 */
		while(rightPos <= rightEnd){
			tmpArray[tmpPos++] = a[rightPos++];
		}
		
		/**
		 * 复制回原数组
		 */
		for(int i = 0; i < length; i++, rightEnd--){
			a[rightEnd] = tmpArray[rightEnd];
		}
	}
	
	/**
	 * 快速排序驱动程序
	 * @param a 待排序数组
	 */
	public static <T extends Comparable<? super T>> void quickSort(T[] a){
		quickSort(a, 0, a.length - 1);
	}
	/**
	 * 快速排序
	 * @param a 待排序数组
	 * @param left 起始位置
	 * @param right 终止位置
	 */
	private static <T extends Comparable<? super T>> void quickSort(T[] a, int left, int right){
		/**
		 * 元素较多时使用快排
		 */
		if(left + 10 < right){
			T pivot = median(a, left, right);
			int i = left; int j = right - 1;
			
			for(;;){
				while(a[++i].compareTo(pivot) < 0){}
				while(a[--j].compareTo(pivot) > 0){}
				if(i < j){
					swap(a, i, j);
				}else{
					break;
				}
			}
			
			swap(a, i, right - 1);
			quickSort(a, left, i - 1);
			quickSort(a, i + 1, right);
			
		}else{		//元素少时使用插入排序
			insertSort(a, left, right);
		}
	}
	/**
	 * 三数中值分割
	 * @param a 输入数组
	 * @param left 起始点下标
	 * @param right 终止点下标
	 * @return 数组a的起始点值、终止点值和中间点值三个值中中间大小的值
	 */
	private static <T extends Comparable<? super T>> T median(T[] a, int left, int right){
		int center = (left + right) / 2;
		if(a[center].compareTo(a[left]) < 0){
			swap(a, left, center);
		}
		if(a[right].compareTo(a[left]) < 0){
			swap(a, left, right);
		}
		if(a[right].compareTo(a[center]) < 0){
			swap(a, center, right);
		}
		//将枢纽元放到倒数第二个位置(最后一个位置比枢纽元大)
		swap(a, center, right - 1);
		return a[right - 1];
	}
	/**
	 * 交换数组中的两个数
	 * @param a 输入数组
	 * @param i 待交换数的下标
	 * @param j 待交换数的下标
	 */
	private static <T extends Comparable<? super T>> void swap(T[] a, int i, int j){
		T tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}
	/**
	 * 插入排序
	 * @param a 待排序数组
	 * @param left 起点
	 * @param right 终点
	 */
	private static <T extends Comparable<? super T>> void insertSort(T[] a, int left, int right){
		int pos;
		for(int i = left; i <= right; i++){
			T tmp = a[i];
			for(pos = i; pos > left && tmp.compareTo(a[pos - 1]) < 0; pos--){
				a[pos] = a[pos - 1];
			}
			a[pos] = tmp;
		}
	}
}
