package zgl.test;

import java.util.BitSet;

/**
 * @date 2014.4.2
 * @author yize
 * 判断一个字符串中的所有字符均出现在另一个字符串中
 */
public class StringContain {
	/**
	 * 利用位图（BitSet）进行判断。同样类似的也可利用计数数组实现
	 * @param str1 字符串1
	 * @param str2 字符串2
	 * @return 是否包含
	 */
	public boolean isStr1ContainStr2(String str1, String str2){
		BitSet bitSet = new BitSet();
		for(int j = 0; j < str1.length(); j++){
			bitSet.set(str1.charAt(j) - 'A', true);
		}
		for(int j = 0; j < str2.length(); j++){
			if(!bitSet.get(str2.charAt(j) - 'A'))
				return false;
		}
		return true;
	}
	
	/**
	 * 此处假设如果两个字符的各个字符的个数相同即认为两字符串相匹配
	 * @param str1 字符串1
	 * @param str2 字符串2
	 * @return 是否匹配
	 */
	public boolean isMatch(String str1, String str2){
		int[] chsInStr1 = new int[26];
		int[] chsInStr2 = new int[26];
		if(str1.length() != str2.length())
			return false;
		for(int j = 0; j < str1.length(); j++){
			chsInStr1[str1.charAt(j) - 'a']++;
			chsInStr2[str2.charAt(j) - 'a']++;
		}
		for(int j = 0; j < 26; j++){
			if(chsInStr1[j] != chsInStr2[j])
				return false;
		}
		return true;
	}
	
	public static void main(String[] args) {
		String str1 = "ABCDEFGHLMNOPQRS";
		String str2 = "DCGSRQPOM";
		System.out.println(new StringContain().isStr1ContainStr2(str1, str2));
	}
	
}
