package demos.algorithm;

import java.util.Random;

public class Test {
	public static void main(String[] args) {
		int max = 20;
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
		}
	}
}
