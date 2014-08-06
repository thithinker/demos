package demos.algorithm.dsalgo;

import java.util.Arrays;

public class LeastDifference {
	public static void main(String[] args) {
		int[] arr = { 64, 57, 2, 78, 43, 73, 53, 86 };
		System.out.println(new LeastDifference().getLeastDifference(arr));
	}

	private int getLeastDifference(int[] arr) {
		Arrays.sort(arr);
		int tmp = Integer.MAX_VALUE;
		for (int i = 0; i < arr.length - 1; i++) {
			if (Math.abs(arr[i] - arr[i + 1]) < tmp)
				tmp = Math.abs(arr[i] - arr[i + 1]);
		}
		return tmp;
	}
}
