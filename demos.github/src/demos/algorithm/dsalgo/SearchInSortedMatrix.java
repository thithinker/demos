package demos.algorithm.dsalgo;

/**
 * 排序矩阵中查找值
 * @author yize
 * 2014年6月17日
 */
public class SearchInSortedMatrix {
	public static void main(String[] args) {
		int[][] matrix = { 
				{ 5, 7, 8, 9 }, 
				{ 6, 9, 11, 13 }, 
				{ 7, 11, 12, 14 }, 
				{ 8, 13, 16, 17 } 
				};
		System.out.println(new SearchInSortedMatrix().contain(matrix, 11));
	}
	
	public boolean contain(int[][] m, int n){
		int row = m.length;
		int col = m[0].length;
		
		int currRow = 0;
		int currCol = col - 1;
		
		while(currRow != row && currCol != -1){
			if(m[currRow][currCol] == n)
				return true;
			else if(m[currRow][currCol] > n)
				currCol--;
			else 
				currRow++;
		}
		return false;
	}
	
}
