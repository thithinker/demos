package demos.algorithm;

import java.util.BitSet;

public class StringContain {
	/**
	 * 字符串str1是否包含str2所有出现的字符, 时间复杂度O(m * n)
	 * @param str1 长字符串
	 * @param str2 短字符串
	 * @return true or false
	 */
	public boolean isContain1(String str1, String str2){
		for(int i = 0; i < str2.length(); i++){
			for(int j = 0; j < str1.length(); j++){
				//如果当前字符str2的当前字符在str1中，结束本轮
				if(str2.charAt(i) == str1.charAt(j)){
					break;
				}
				//如果到达str1的最大长度还没找到，完成判断
				if(j == str1.length() - 1)
					return false;
			}
			//如果到达str2的最大长度，完成判断
			if(i == str2.length() - 1){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 计数排序，返回str对应的有序形式
	 * @param str 待排序的字符串
	 * @return str的有序字符串
	 */
	private String countSort(String str){
		//存储A-Z每个字符出现的次数 
		int[] tmp = new int[26];
		int index = 0;
		//A-Z每个字符出现的次数
		for(int i = 0; i < str.length(); i++){
			index = str.charAt(i) - 'A';
			tmp[index]++;
		}
		
		for(int i = 1; i < 26; i++){
			tmp[i] += tmp[i - 1];
		}
		//str排序后对应的字符数组
		char[] chs = new char[str.length()];
		for(int i = str.length() - 1; i >= 0; i--){
			index = str.charAt(i) - 'A';
			int pos = tmp[index] - 1;
			chs[pos] = str.charAt(i);
			tmp[index]--;
		}
		return String.valueOf(chs);
	}
	
	/**
	 * 字符串str1是否包含str2所有出现的字符, 时间复杂度O(m + n)
	 * @param str1 长字符串
	 * @param str2 短字符串
	 * @return true or false
	 */
	public boolean isContain2(String str1, String str2){
		//str1与str2对应的有序字符串
		str1 = countSort(str1);
		str2 = countSort(str2);
		int pos1 = 0;
		int pos2 = 0;
		while(pos2 < str2.length() && pos1 < str1.length()){
			while((pos1 < str1.length() && str1.charAt(pos1) != str2.charAt(pos2))){
				pos1++;
			}
			
			while(pos2 < str2.length() - 1 && str2.charAt(pos2 + 1) == str2.charAt(pos2)){
				pos2++;
			}
			pos1++;
			pos2++;
			if(pos2 == str2.length()){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 字符串str1是否包含str2所有出现的字符, 时间复杂度O(n)
	 * @param str1 长字符串
	 * @param str2 短字符串
	 * @return true or false
	 */
	public boolean isContain3(String str1, String str2){
		int[] tmp = new int[26];
		int index = 0;
		for(int i = 0; i < str1.length(); i++){
			index = str1.charAt(i) - 'A';
			tmp[index] ++;
		}
		
		for(int i = 0; i < str2.length(); i++){
			index = str2.charAt(i) - 'A';
			if(tmp[index] == 0){
				return false;
			}
		}
		return true;
	}

	/**
	 * 方法4：
	 * 1.定义最小的26 个素数分别与字符'A'到'Z'对应。
	 * 2.遍历长字符串，求得每个字符对应素数的乘积。
	 * 3.遍历短字符串，判断乘积能否被短字符串中的字符对应的素数整除。
	 * 4.输出结果。
	 */
	
	/**
	 * 利用Java中的位向量，进行位映射
	 * 字符串str1是否包含str2所有出现的字符, 时间复杂度O(n + m)
	 * @param str1 长字符串
	 * @param str2 短字符串
	 * @return true or false
	 */
	public boolean isContain5(String str1, String str2){
		BitSet bs = new BitSet(26);
		//将长字符串所包含的字符放进位向量
		for(int i = 0; i < str1.length(); i++)
			bs.set(str1.charAt(i) - 'A');
		//确定短字符串的每一个字符是否包含在长字符串中
		for(int i = 0; i < str2.length(); i++){
			if(!bs.get(str2.charAt(i) - 'A'))
				return false;
		}
		return true;
	}
	
	public static void main(String[] args){
		System.out.println(new StringContain().isContain5("ABCDEFGHLMNOPQRSZY", "ZDCGDSRQPOMYZ"));
		System.out.println(1<<32);
	}
}
