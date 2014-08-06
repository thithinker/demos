package demos.algorithm.dsalgo;

/**
 * 寻找子数组的最大和
 * @author yize
 * 2014年6月15日
 */
public class LargestSumSubarray {
	public static void main(String[] args) {
		int[] arr1 = { 1, 0, -1, -3, 0, 8, 9, -4, 7, -1 };
		int[] arr2 = { -1, -2, 3, 0, 1, -2 };
		int[] arr3 = { -2, -1, -5, -4 };
		int[] arr4 = { 4, 3, -5, 0, 6, -8, 12, 3, -9, 2, 5, 8, -3, 4, 8, 0, 3, -3, -5, -9, 4, 2 };
		System.out.println(new LargestSumSubarray().sumSubArray(arr1));
		System.out.println(new LargestSumSubarray().sumSubArray(arr2));
		System.out.println(new LargestSumSubarray().sumSubArray(arr3));
		System.out.println(new LargestSumSubarray().sumSubArray(arr4));
		
		maxSumSubArray(arr1);
		maxSumSubArray(arr2);
		maxSumSubArray(arr3);
		maxSumSubArray(arr4);
	}

	/**
	 * @author yize
	 * @param arr 输入数组
	 * @return 最大和
	 */
	public int sumSubArray(int[] arr) {
		int max = arr[0];
		int sum = 0;
		int start = 0, end = 0;
		for (int i = 0; i < arr.length; i++) {
			sum += arr[i];
			if (sum > max) {
				max = sum;
				end = i;
				if(max == arr[i])
					start = i;
			}
			if (sum < 0) {
				sum = 0;
			}
		}
		System.out.println("[" + start + ", " + end + "]");

		return max;
	}

	/**
	 * @author dsalgo
	 * @param arr 输入数组
	 */
	private static void maxSumSubArray(int[] arr) {
		int currentStart = 0;
		int currentEnd = 0;
		int currentSum = 0;
		int maxStart = 0;
		int maxEnd = 0;
		int maxSum = 0;
		while (currentEnd != arr.length) {
			currentSum += arr[currentEnd];
			if (currentSum > maxSum) {
				maxSum = currentSum;
				maxStart = currentStart;
				maxEnd = currentEnd;
			}
			if (currentSum <= 0) {
				currentStart = currentEnd + 1;
				currentSum = 0;
			}
			currentEnd++;
		}
		System.out.println("Maximum sum = " + maxSum);
		System.out.println("Indexes (" + maxStart + "," + maxEnd + ")");
	}
}
