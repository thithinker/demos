package com.zgl.test;
/**
 * @date 2014.4.2
 * @author yize
 * 左旋字符串，如“abcdefg”左旋3位为“defgabc”，两种方法实质上一样，即先整体上旋转整个字符串，然后旋转前一部分，再旋转后一部分。
 */
public class StringRotation {
	/**
	 * 左旋字符串方法一
	 * @param str：待处理字符串
	 * @param k：左旋字符的个数
	 * @return：旋转后的字符串
	 */
	public String leftRotation(String str, int k){
		k = k % str.length();
		char[] strToChar = str.toCharArray();
		char ch;
		for(int i = 0; i < str.length() / 2; i++){
			ch = strToChar[i];
			strToChar[i] = strToChar[str.length() - i - 1];
			strToChar[str.length() - i - 1] = ch;
		}
		for(int i = 0; i < (str.length() - k) / 2; i++){
			ch = strToChar[i];
			strToChar[i] = strToChar[str.length() - k - i - 1];
			strToChar[str.length() - k - i - 1] = ch;
		}
		for(int i = str.length() - k; i < str.length() - k + k / 2; i++){
			ch = strToChar[i];
			strToChar[i] = strToChar[str.length() - i - 1 + (str.length() - k)];
			strToChar[str.length() - i - 1 + (str.length() - k)] = ch;
		}
		return String.valueOf(strToChar);
	}
	
	/**
	 * 旋转指定位置的字符串
	 * @param str：整体字符串
	 * @param start：旋转起始位置
	 * @param off：要旋转字符的个数
	 * @return：整体字符串
	 */
	private String rotationStr(String str, int start, int off){
		char[] strToChar = str.toCharArray();
		char ch;
		for(int i = start; i < (start + off) / 2; i++){
			ch = strToChar[i];
			strToChar[i] = strToChar[start + off - i - 1];
			strToChar[start + off - i - 1] = ch;
		}
		return String.valueOf(strToChar);
	}
	
	/**
	 * 左旋字符串方法二
	 * @param str：待处理字符串
	 * @param k：左旋字符的个数
	 * @return：旋转后的字符串
	 */
	public String leftRotation_2(String str, int k){
		k = k % str.length();
		str = rotationStr(str, 0, str.length());
		str = rotationStr(str, 0, str.length() - k);
		str = rotationStr(str, str.length() - k, str.length());
		return str;
		
	}
}
