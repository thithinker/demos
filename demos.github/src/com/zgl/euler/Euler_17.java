package com.zgl.euler;

import java.util.HashMap;
import java.util.Map;

/**
 * 342 (three hundred and forty-two) contains 23 letters 115 (one hundred and
 * fifteen) contains 20 letters
 * 
 * @author yize 2014年6月7日
 */
public class Euler_17 {
	static Map<Integer, String> num2Letters = new HashMap<Integer, String>() {
		private static final long serialVersionUID = 1L;

		{
			put(1, "one");
			put(2, "two");
			put(3, "three");
			put(4, "four");
			put(5, "five");
			put(6, "six");
			put(7, "seven");
			put(8, "eight");
			put(9, "nine");
			put(10, "ten");
			put(11, "eleven");
			put(12, "twelve");
			put(13, "thirteen");
			put(14, "fourteen");
			put(15, "fifteen");
			put(16, "sixteen");
			put(17, "seventeen");
			put(18, "eighteen");
			put(19, "nineteen");
			put(20, "twenty");
			put(30, "thirty");
			put(40, "forty");
			put(50, "fifty");
			put(60, "sixty");
			put(70, "seventy");
			put(80, "eighty");
			put(90, "ninety");

		}
	};

	public static String number2Letter(int n) {
		StringBuilder sb = new StringBuilder();
		if (n == 1000) {
			return "one thousand";
		}
		int n_3 = n / 100;
		if (n_3 > 0)
			sb.append(num2Letters.get(n_3) + " hundred");
		n = n - 100 * n_3;
		int n_2 = n / 10;
		if (n_2 == 1 && n_3 > 0) {
			sb.append(" and " + num2Letters.get(n % 100));
		} else if (n_2 > 1 && n_3 > 0) {
			sb.append(" and " + num2Letters.get(n_2 * 10));
		} else if (n_2 == 1 && n_3 == 0) {
			sb.append(num2Letters.get(n % 100));
		} else if (n_2 > 1 && n_3 == 0) {
			sb.append(num2Letters.get(n_2 * 10));
		}
		if (n / 10 != 1 && n % 10 > 0 && n_3 != 0) {
			int n_1 = n % 10;
			if (!sb.toString().contains("and")) {
				sb.append(" and " + num2Letters.get(n_1));
			}

			else {
				sb.append(" " + num2Letters.get(n_1));
			}
		}
		if (n / 10 != 1 && n % 10 > 0 && n_3 == 0) {
			int n_1 = n % 10;
			sb.append(" " + num2Letters.get(n_1));
		}
		return sb.toString();
	}

	public static int getLetterNumber(String ss) {
		return ss.replaceAll(" ", "").length();
	}

	public static void main(String[] args) {
		/*
		 * for (int i = 0; i < 25; i++) { System.out.println(i + "  " +
		 * number2Letter(i) + " " + getLetterNumber(number2Letter(i))); }
		 * 
		 * for (int i = 110; i < 125; i++) { System.out.println(i + "  " +
		 * number2Letter(i) + " " + getLetterNumber(number2Letter(i))); } for
		 * (int i = 200; i < 215; i++) { System.out.println(i + "  " +
		 * number2Letter(i) + " " + getLetterNumber(number2Letter(i))); }
		 * 
		 * for (int i = 990; i < 1001; i++) { System.out.println(i + "  " +
		 * number2Letter(i) + " " + getLetterNumber(number2Letter(i))); }
		 */
		/*
		 * int i = 342; System.out.println(number2Letter(i) + " " +
		 * getLetterNumber(number2Letter(i))); i = 115;
		 * System.out.println(number2Letter(i) + " " +
		 * getLetterNumber(number2Letter(i)));
		 */

		int sum = 0;
		for (int i = 1; i < 1001; i++) {
			//System.out.println(i + ":\t" + number2Letter(i) + " " + getLetterNumber(number2Letter(i)));
			sum += getLetterNumber(number2Letter(i));
		}
		System.out.println(sum);
	}
}
