package zgl.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Pongo_01 {
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			String line = br.readLine();
			cal(Integer.parseInt(line.split(" ")[0]), Integer.parseInt(line.split(" ")[1]));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static void cal(int a, int b) {
		int small = a;
		int big = b;
		int small_sqrt;
		int big_sqrt;

		small_sqrt = (int) Math.sqrt(small);
		big_sqrt = (int) Math.sqrt(big);

		if (small == big && (int) Math.pow(small_sqrt, 2) == small) {
			System.out.println(1);
		} else if (((int) Math.pow(small_sqrt, 2) != small && (int) Math.pow(big_sqrt, 2) != big)
				|| (((int) Math.pow(small_sqrt, 2) != small && (int) Math.pow(big_sqrt, 2) == big))) {
			// [a, b]: a和b均不是完全平方数， 或者a不是完全平方数而b是完全平方数
			System.out.println(big_sqrt - small_sqrt);
		} else {
			// [a, b]: a和b均为完全平方数，或者a是完全平方数而b不是完全平方数
			System.out.println(big_sqrt - small_sqrt + 1);
		}
	}

	private static void method() {
		try {
			BufferedReader br = new BufferedReader(new FileReader("./pongo_01.txt"));
			PrintWriter out = new PrintWriter(new File("./pongo_01_out.txt"));
			String line;
			int small, big;
			int small_sqrt, big_sqrt;
			while ((line = br.readLine()) != null) {
				small = Integer.parseInt(line.split(" ")[0]);
				big = Integer.parseInt(line.split(" ")[1]);

				small_sqrt = (int) Math.sqrt(small);
				big_sqrt = (int) Math.sqrt(big);

				if (small == big && (int) Math.pow(small_sqrt, 2) == small) {
					out.write("T1: " + 1 + "\n");
				} else if (((int) Math.pow(small_sqrt, 2) != small && (int) Math.pow(big_sqrt, 2) != big)
						|| (((int) Math.pow(small_sqrt, 2) != small && (int) Math.pow(big_sqrt, 2) == big))) {
					// [a, b]: a和b均不是完全平方数， 或者a不是完全平方数而b是完全平方数
					out.write("T2: " + (big_sqrt - small_sqrt) + "\n");
				} else {
					// [a, b]: a和b均为完全平方数，或者a是完全平方数而b不是完全平方数
					out.write("T3: " + (big_sqrt - small_sqrt + 1) + "\n");
				}
			}
			br.close();
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
