package demos.algorithm;

import java.util.Arrays;

public class Min_K {
	/**
	 * 获取最小的k个数， 时间复杂度O(n)，空间复杂度O(n)，不适合元素范围比较大的数组
	 * @param num 输入数组
	 * @param k 获取元素个数
	 * @return 最小k个数组成的数组
	 */
	public int[] min_k(int[] num, int k) {
		//保存num中的最大数
		int max = 0;
		//遍历，获取最大数
		for (int i = 0; i < num.length; i++) {
			if (num[i] > max) {
				max = num[i];
			}
		}
		//临时数组，保存num中每个数出现的次数
		int[] tmp = new int[max + 1];
		for (int i = 0; i < num.length; i++) {
			tmp[num[i]]++;
		}
		//保存结果
		int[] result = new int[k];
		//tmp数组下标
		int index = 0;
		//result数组下标
		int pos = 0;
		while (pos < k) {
			while (tmp[index] != 0) {
				result[pos++] = index;
				tmp[index]--;
			}
			index++;
		}
		return result;
	}
	
	/**
	 * 建堆，未考虑数组的第一个元素
	 * @param a 待建堆的数组
	 * @return 堆数组
	 */
	private int[] buildHeap(int[] a){
		if(a == null){
			return null;
		}
		for(int i = a.length / 2; i >= 1; i--){
			maxHeap(a, i, a.length);
		}
		return a;
	}
	/**
	 * 调整成大根堆
	 * @param a 待调整的数组
	 * @param i 调整的起始位置
	 * @param l 数组长度
	 */
	private void maxHeap(int[] a, int i, int l){
		int large =  -1; 
		int left = 2 * i;
		int right = 2 * i + 1;
		
		//找出当前节点左右孩子中较大的元素
		if(left < l && a[i] < a[left]){
			large = left;
		}else{
			large = i;
		}
		if(right < l && a[large] < a[right]){
			large = right;
		}
		
		//若孩子中有比当前节点大的情况，交换位置，递归建堆
		if(large != i){
			int tmp = a[i];
			a[i] = a[large];
			a[large] = tmp;
			maxHeap(a, large, l);
		}
	}

	/**
	 * 获取最小的k个数， 时间复杂度O(n * log(k))，空间复杂度O(k)
	 * @param num 输入数组
	 * @param k 获取元素个数
	 * @return 最小k个数组成的数组
	 */
	public int[] min_k2(int[] num, int k) {
		//建立可包含k + 1个元素的堆数组，第一个位置不用
		int[] heap = new int[k + 1];
		//填充堆数组
		for(int i = 1; i <= k; i++){
			heap[i] = num[i - 1];
		}
		//建堆
		buildHeap(heap);
		//遍历剩余的元素，有比这k个元素小的，替换掉第一个堆元素
		for(int i = k; i < num.length; i++){
			if(num[i] < heap[1]){
				heap[1] = num[i];
				maxHeap(heap, 1, k + 1);
			}
		}
		return Arrays.copyOfRange(heap, 1, k + 1);
	}
	
	//方法3 快速选择
	public int[] min_k3(int[] num, int k){
		quickSelect(num, 0, num.length - 1, k);
		return Arrays.copyOfRange(num, 0, k);
	}
	private int quickSelect(int[] num, int low, int high, int k) {
		int low_tmp = low; 
		int high_tmp = high;
		int flag = num[low];
		while(low < high){
			while(low < high && num[high] >= flag){
				high--;
			}
			num[low] = num[high];
			while(low < high && num[low] < flag){
				low++;
			}
			num[high] = num[low];
		}
		num[low] = flag;
		
		if(low == k - 1){
			return num[low];
		}else if(low > k -1){
			return quickSelect(num, low_tmp, low - 1, k);
		}else{
			return quickSelect(num, low + 1, high_tmp, k);
		}
	}

	public static void main(String[] args){
		int[] t = new int[]{2, 34, 56, 1, 43, 6, 232, 56, 78, 2, 5, 76, 23, 56,678, 98, 75, 12, 5};
		int[] tt = new Min_K().min_k2(t, 9);
		for(int i = 0; i < tt.length; i++){
			System.out.print(tt[i] + " ");
		}
		System.out.println();
		int[] ttt = new Min_K().min_k3(t, 9);
		for(int i = 0; i < ttt.length; i++){
			System.out.print(ttt[i] + " ");
		}
		//System.out.println(new Min_K().min_k3(t, 5));
	}
}
