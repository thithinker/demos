package zgl.euler;

import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;

/**
 * @date 2014.4.14
 * @author yize
 * result: 4179871
 */
public class Euler_23 {
	public int euler_23(){
		final int MAX = 28124;
		int[] tmp = sumOfDivisor(MAX);
		
		List<Integer> abundantList = new ArrayList<>();
		for(int i = 1; i < tmp.length; i++){
			if(tmp[i] > i){
				abundantList.add(i);
			}
		}
		Set<Integer> sumOfAbundant = new HashSet<>();
		for(int i = 0; i < abundantList.size(); i++){
			for(int j = 0; j < abundantList.size(); j++){
				if(abundantList.get(i) + abundantList.get(j) < MAX){
					sumOfAbundant.add(abundantList.get(i) + abundantList.get(j));
				}
			}
		}
		List<Integer> list = new ArrayList<>();
		int sum = 0; 
		for(int i = 0; i < tmp.length; i++){
			if(!sumOfAbundant.contains(i)){
				sum += i;
				list.add(i);
			}
		}
		return sum;
		
	}
	
	private int[] sumOfDivisor(int max){
		int[] res = new int[max];
		for(int i = 0; i < max; i++){
			res[i] = 1;
		}
		
		int tt;
		for(int i = 2; i < max; i++){
			tt = 2 * i;
			while(tt < max){
				res[tt] += i;
				tt += i;
			}
		}
		return res;
	}
	
	public static void main(String[] args){
		System.out.println("##: " + new Euler_23().euler_23());;
	}
}
