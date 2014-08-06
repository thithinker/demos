package demos.algorithm;

/**
 * 递归法和非递归法移动数组元素，使得所有奇数在所有偶数的左侧
 * @author yize
 * 2014年8月2日
 */
public class MoveArrayItem {
	/**
	 * 改变数组中元素顺序，使得数组中所有奇数在所有偶数的左侧
	 * @param a 待处理数组
	 */
	public void move1(int[] a){
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
	 * 递归 改变数组中元素顺序，使得数组中所有奇数在所有偶数的左侧
	 * @param a 待处理数组
	 */
	public void move(int[] a){
		move(a, 0, a.length - 1);
	}
	
	/**
	 * 递归处理数组
	 * @param a 待处理数组
	 * @param left 待处理数组的左侧下标
	 * @param right 待处理数组的右侧下标
	 */
	private void move(int[] a, int left, int right){
		if(left < right){
			while(left < a.length && a[left] % 2 != 0) left++;
			while(right > -1 && a[right] % 2 == 0) right--;
			
			if(left < right){
				int tmp = a[left];
				a[left] = a[right];
				a[right] = tmp;
				
				move(a, ++left, --right);
			}
		}
	}
}
