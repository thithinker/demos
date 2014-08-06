package demos.algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import zgl.util.Utils;

public class Palindrome {
	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		list.add("A");
		list.add("B");
		list.add("C");
		list.add("D");
		list.add("E");
		List<String> subList = list.subList(2, 4);
		subList.add("X");
		subList.add("Y");
		list.set(2, "O");
		//list.add("EE");
		list.set(4, "EE");
		Utils.printList(subList);
		Utils.printList(list);
		System.out.println(subList.size());
		System.out.println(list.equals("A"));
		
		list = Collections.unmodifiableList(list);
		Palindrome palindrome = new Palindrome();
		System.out.println(palindrome.length("1234545624530"));
		//System.out.println(palindrome.isPalindrome("abba"));
	}
	
	
	public int length(String str){
		if(str == null || str.trim().equals("")){
			return 0;
		}
		int max = 0;
		String maxStr = "";
		int len = str.length();
		for(int i = 0; i < len; i++){
			int j = 0;
			while(i - j > -1 && i + j + 1 <= len && isPalindrome(str.substring(i - j, i + j + 1))){
				//System.out.println(str.substring(i - j, i + j + 1));
				if(2 * j + 1 > max){
					max = 2 * j + 1;
					maxStr = str.substring(i - j, i + j + 1);
				}
				j++;
				
			}
			
			j = 0;
			while(i - j > -1 && i + j + 2 <= len && isPalindrome(str.substring(i - j, i + j + 2))){
				//System.out.println(str.substring(i - j, i + j + 2));
				if(2 * j + 2 > max){
					max = 2 * (j + 1);
					maxStr = str.substring(i - j, i + j + 2);
				}
				j++;
				
			}
		}
		System.out.println(maxStr);
		return max;
	}
	
	private boolean isPalindrome(String str){
		for(int i = 0; i < str.length() / 2; i++){
			if(str.charAt(i) == str.charAt(str.length() - i - 1)){
				continue;
			}else{
				return false;
			}
		}
		return true;
	}
}
