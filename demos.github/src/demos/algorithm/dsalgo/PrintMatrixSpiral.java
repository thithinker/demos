package demos.algorithm.dsalgo;

/**
 * 螺旋式地输出数组
 * @author yize
 * 2014年6月15日
 */
public class PrintMatrixSpiral {
	public static void main(String[] args) {
		int[][] arr = { 
				{ 1, 2, 3,4 }, 
				{ 5, 6, 7,8}, 
				{ 9, 10, 11,12},
				{22,33,44,55}
				};
		new PrintMatrixSpiral().printMatrixSpiral2(arr);
		System.out.println();
		printSpiral(arr);
	}

	/**
	 * @author yize
	 * @param m 二维数组
	 * 	螺旋式地输出数组，该数组的行与列可以不同
	 */
	private void printMatrixSpiral2(int[][] m) {
		int minr = 0, minc = 0;
		int maxr = m.length, maxc = m[0].length;

		while (minc < maxc && minr < maxr) {
			for (int i = minc; i < maxc; i++) {
				System.out.print(m[minr][i] + " ");
			}
			minr++;
			for (int i = minr; i < maxr; i++) {
				System.out.print(m[i][maxc - 1] + " ");
			}
			maxc--;
			for (int i = maxc - 1; i >= minc && minr != maxr; i--) {
				System.out.print(m[maxr - 1][i] + " ");
			}
			maxr--;
			for (int i = maxr - 1; i >= minr && minc != maxc; i--) {
				System.out.print(m[i][minc] + " ");
			}
			minc++;
		}
	}

	/**
	 * @author dsalgo
	 * @param matrix
	 * 	螺旋地输出数组，行列数目要相等
	 */
	public static void printSpiral(int[][] matrix) {
		printSpiral(matrix, 0);
	}

	private static void printSpiral(int[][] matrix, int depth) {
		if (matrix == null || matrix.length == 0)
			return;
		int rows = matrix.length;
		int cols = matrix[0].length;
		if (2 * depth > Math.min(rows, cols))
			return;
		for (int i = depth; i < cols - depth - 1; ++i) {
			System.out.print(matrix[depth][i] + ",");
		}
		for (int i = depth; i < rows - depth - 1; ++i) {
			System.out.print(matrix[i][cols - depth - 1] + ",");
		}
		for (int i = rows - depth; i > depth; --i) {
			System.out.print(matrix[rows - depth - 1][i - 1] + ",");
		}
		for (int i = rows - depth - 1; i > depth + 1; --i) {
			System.out.print(matrix[i - 1][depth] + ",");
		}
		printSpiral(matrix, ++depth);
	}
}
