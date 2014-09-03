package demos.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class Test {
	public static void main(String[] args) {
		/*int max = 20;
		VList<Integer> list = new VList<>();
		for(int i = 0; i < max; i++){
			list.add(new Random().nextInt(max * 10));
		}
		
		for(Integer i : list){
			System.out.print(i + " ");
		}
		
		for(int i = 1; i < max / 2; i++){
			list.remove(i);
		}
		System.out.println();
		for(Integer i : list){
			System.out.print(i + " ");
		}*/
		
		/*//Test Collection
		List<Integer> list = new ArrayList<Integer>();
		for(int i = 0; i < 10; i++){
			list.add(new Random().nextInt() % 100);
		}
		System.out.println("list: " + list);
		
		Set<Integer> set = new TreeSet<>(list);
		System.out.println(set);
		for(Integer i : set){
			System.out.print(i + " ");
		}*/
		
		//Test String
		String str = "they are students";
		String s = str.replaceAll("[aeiou]", "");
		System.out.println(s);
		
		
	}
}
