package demos.algorithm.dsalgo;

public class NextPalindrome {
	public static void main(String[] args) {
		System.out.println(new NextPalindrome().getNextPalindrome(3453676));
	}

	private int getNextPalindrome(int num) {
		String strnum = "" + num;
		if (strnum.length() % 2 == 0) {
			String left = strnum.substring(0, strnum.length() / 2);
			String right = strnum.substring(strnum.length() / 2);
			if (Integer.valueOf(left) > Integer.valueOf(right)) {
				return Integer.valueOf(left + new StringBuffer(left).reverse());
			} else {
				String t = Integer.valueOf(left) + 1 + "";
				return Integer.valueOf(t + new StringBuffer(t).reverse());
			}
		} else {
			String left = strnum.substring(0, strnum.length() / 2);
			String mid = strnum.substring(strnum.length() / 2, strnum.length() / 2 + 1);
			String right = strnum.substring(strnum.length() / 2 + 1);

			if (Integer.valueOf(left) > Integer.valueOf(right)) {
				return Integer.valueOf(left + mid + new StringBuffer(left).reverse());
			} else {
				String t = Integer.valueOf(left + mid) + 1 + "";
				return Integer.valueOf(t + new StringBuffer(left).reverse());
			}
		}
	}

	public static int nextPalindrome(int num) {
		return nextPalindrome(num, true);
	}

	private static int nextPalindrome(int num, boolean firstTime) {
		String numString = "" + num;
		int leftEndIndex = -1;
		int rightStartIndex = -1;
		boolean isOdd = numString.length() % 2 == 1;
		if (isOdd) {
			leftEndIndex = numString.length() / 2;
			rightStartIndex = leftEndIndex + 1;
		} else {
			leftEndIndex = rightStartIndex = numString.length() / 2;
		}
		String leftHalf = numString.substring(0, leftEndIndex);
		String rightHalf = numString.substring(rightStartIndex);
		String leftReversed = new StringBuffer(leftHalf).reverse().toString();
		String palindrome = null;
		if (Integer.parseInt(leftReversed) > Integer.parseInt(rightHalf) || !firstTime) {
			if (isOdd)
				palindrome = leftHalf + numString.charAt(leftEndIndex) + leftReversed;
			else
				palindrome = leftHalf + leftReversed;
			return Integer.parseInt(palindrome);
		} else {
			if (isOdd) {
				String leftAndMiddle = leftHalf + numString.charAt(leftEndIndex);
				int incrementedLeft = Integer.parseInt(leftAndMiddle) + 1;
				return nextPalindrome(Integer.parseInt(incrementedLeft + rightHalf), false);
			} else {
				int incrementedLeft = Integer.parseInt(leftHalf) + 1;
				return nextPalindrome(Integer.parseInt(incrementedLeft + rightHalf), false);
			}
		}
	}
}
