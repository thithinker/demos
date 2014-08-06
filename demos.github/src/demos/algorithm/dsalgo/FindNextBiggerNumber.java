package demos.algorithm.dsalgo;

import java.util.ArrayList;
import java.util.List;

public class FindNextBiggerNumber {
	public static void main(String[] args) {
		System.out.println(getNextBiggerNumber(1235655));
	}
	
	public static int getNextBiggerNumber(int n){
		String str = String.valueOf(n);
		if(str.length() == 1){
			return 0;
		}
		List<Character> chs = new ArrayList<>();
		for(int i = 0; i < str.length(); i++){
			chs.add(str.charAt(i));
		}
		boolean flag = true;
		for(int i = 0; i < str.length() - 1; i++){
			if(flag){
				if(!(chs.get(i) < chs.get(i + 1))){
					flag = false;
				}
			}
		}
		if(flag){
			return 0;
		}
		for(int i = str.length() - 1; i >= 0; i--){
			if(chs.get(i) > chs.get(i - 1)){
				char t = chs.get(i);
				chs.set(i, chs.get(i - 1));
				chs.set(i - 1, t);
				break;
			}
		}
		String tt = "";
		for(int i = 0; i < str.length(); i++){
			tt += String.valueOf(chs.get(i));
		}
		
		return Integer.valueOf(tt);
	}
}
