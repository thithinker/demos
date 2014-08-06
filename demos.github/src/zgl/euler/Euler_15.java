package zgl.euler;

/**
 * 满足组合关系 (2n)! / (n! * n!)
 * @date 2014.4.7
 * @author yize
 */
public class Euler_15 {
	public long euler(int grid) {
		//结果已经超出整数的表示范围
		long[][] tmp = new long[grid + 1][grid + 1];
		for(int i = 0; i <= grid; i++){
			tmp[i][0] = 1;
			tmp[0][i] = 1;
		}
		
		for(int i = 1; i <= grid; i++){
			for(int j = 1; j <= grid; j++){
				tmp[i][j] = tmp[i - 1][j] + tmp[i][j - 1];
			}
		}
		/*for (int i = 0; i < tmp.length; i++) {
			for (int j = 0; j < tmp.length; j++) {
				System.out.printf("%11d",tmp[i][j]);
				if(j == grid)
					System.out.println();
			}
		}*/
		return tmp[grid][grid];
	}
	
	/**
	 * 时间复杂度太大，不适合边数多的情况（最好不多于15）
	 * @date 2014.4.7
	 * @param i
	 * @param j
	 * @return
	 */
	public long euler(int i, int j){
		if(i == 1)
			return j + 1;
		if(j == 1)
			return i + 1;
		else 
			return euler(i - 1, j) + euler(i, j - 1);
	}
	
	public static void main(String[] args) {
		System.out.println(new Euler_15().euler(20, 15));
	}
}
