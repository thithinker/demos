package demos.algorithm;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


public class CharPermutation {
	public static void main(String[] args) {
		Integer[] chs = {1,2,3,4};
		List<Integer> list = Arrays.asList(chs);
		permutation("", list, list.size());
	}
	
	
	private static void permutation(String ch, List<Integer> list, int len){
		if(ch.length() == 4)
			System.out.println(ch);
		for(int i = 0; i < list.size(); i++){
			List<Integer> t = new LinkedList<>(list);
			permutation(ch + t.remove(i), t, len);
		}
	}
}
