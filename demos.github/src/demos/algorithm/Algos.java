package demos.algorithm;

public class Algos {
	/**
	 * 折半查找
	 * @param arr 输入数组
	 * @param x 待查找元素
	 * @return x在数组中返回在输入数组中的位置，不在数组中则返回-1
	 */
	public <T extends Comparable<? super T>> int binarySearch(T[] arr, T x){
		int low = 0;
		int high = arr.length - 1;
		
		while(low <= high){
			int mid = (low + high) / 2;
			if(arr[mid].compareTo(x) < 0){
				low = mid + 1;
			}else if(arr[mid].compareTo(x) > 0){
				high = mid - 1;
			}else{
				return mid;
			}
		}
		return -1;
	}
	
	/**
	 * 求数组的最大子数组和
	 * @param arr 输入数组
	 * @return 最大子数组和
	 */
	public int maxSubSum(int[] arr){
		int sum = 0;
		int max = arr[0];
		for(int i = 0; i < arr.length; i++){
			sum += arr[i];
			if(sum > max){
				max = sum;
			}
			else if(sum < 0){
				sum = 0;
			}
		}
		return max;
	}
	
	/**
	 * 输入两个数的最大公约数
	 * @param m 输入数
	 * @param n 输入数
	 * @return 两个数的最大公约数
	 */
	public int gcd(int m, int n){
		int mod = 0;
		while(n != 0){
			mod = m % n;
			m = n;
			n =mod;
		}
		return m;
	}
	/**
	 * 计算一个数的n次方
	 * @param x 底数
	 * @param n 次数
	 * @return x的n次方
	 */
	public long pow(long x, int n){
		if(n == 0){
			return 1;
		}else if(n == 1){
			return x;
		}else if(n % 2 == 0){
			return pow(x * x, n / 2);
		}else {
			return pow(x * x, n / 2) * x;
		}
	}
	
}
