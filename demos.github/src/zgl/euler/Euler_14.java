package zgl.euler;

import java.math.BigInteger;

/**
 * @date 2014.4.6
 * @author yize
 *
 */
public class Euler_14 {
	public int euler_14(){
		boolean flag = false;
		int num = -1;
		int curLongth = 1;
		int maxLongth = 1;
		//int cur = 0;
		BigInteger bi;
		final BigInteger TWO = new BigInteger(String.valueOf(2));
		final BigInteger THREE = new BigInteger(String.valueOf(3));
		for(int i = 13; i < 1000000; i++){
			bi = new BigInteger(String.valueOf(i));
			flag = false;
			curLongth = 1;
			while(!flag){
				if(bi.mod(TWO).compareTo(BigInteger.ZERO) == 0){
					bi = bi.divide(TWO);
					curLongth++;
				}else if(bi.mod(TWO).compareTo(BigInteger.ONE) == 0){
					bi = bi.multiply(THREE).add(BigInteger.ONE);
					curLongth++;
				}
				if(curLongth < 0){
					return -3;
				}
				if(bi.compareTo(BigInteger.ONE) == 0){
					flag = true;
					if(curLongth > maxLongth){
						maxLongth = curLongth;
						num = i;
					}
				}
			}
		}
		System.out.println("Longth: " + maxLongth);
		return num;
	}
	
	public static void main(String[] args) {
		System.out.println(new Euler_14().euler_14());
	}
}
