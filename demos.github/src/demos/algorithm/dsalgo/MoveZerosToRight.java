package demos.algorithm.dsalgo;

import zgl.util.Utils;

public class MoveZerosToRight {
	public static void main(String[] args) {
		int[] arr = { 4, 0, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 3, 3, 4, 5, 6, 2, 0, 4, 0, 0 };
		new MoveZerosToRight().moveZerosToright(arr);
		Utils.printArr(arr);
		int[] a1 = {0, 1};
		new MoveZerosToRight().moveZerosToright(a1);
		Utils.printArr(a1);
		int[] a2 = {0, 1};
		moveZeroesToRight(a2);
		Utils.printArr(a2);
	}

	/**
	 * @author yize 
	 * @param arr	待移动的数组
	 * 	将数组中的零移到数组的右侧
	 */
	public void moveZerosToright(int[] arr) {
		int idxLeft = 0;
		int idxRight = arr.length - 1;

		int zero = 0;
		while (idxLeft < idxRight) {
			while (arr[idxRight] == 0) { // 当idxRight指向0时idxRight往前移
				idxRight--;
			}
			if (arr[idxLeft] == 0) {
				arr[idxLeft] = arr[idxRight];
				arr[idxRight--] = zero;
			}
			idxLeft++;
		}
	}

	/**
	 * @author dsalgo
	 * @param arr
	 */
	private static void moveZeroesToRight(int[] arr) {
		int start = 0;
		int end = arr.length - 1;
		while (start < end) {
			if (arr[start] == 0 && arr[end] != 0) {
				int temp = arr[start];
				arr[start] = arr[end];
				arr[end] = temp;
				start++;
				end--;
			} else {
				if (arr[start] != 0)
					start++;
				if (arr[end] == 0)
					end--;
			}
		}
	}
}
