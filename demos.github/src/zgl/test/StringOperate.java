package zgl.test;

/**
 * @date 2014.4.3
 * @author yize
 * 实现一些基本的字符串操作
 *
 */
public class StringOperate {
	/**
	 * 判断字符串子串
	 * @param str 字符串
	 * @param subStr 字符串
	 * @return 子串在父串中的起始位置（非负）或-1
	 */
	public int subStr(String str, String subStr){
		if(str == null || subStr == null){	//忘记判断
			return -1;
		}
		int cur;
		for(int i = 0; i < str.length(); i++){
			cur = i;
			for(int j = 0; j < subStr.length() && cur < str.length(); j++){	//遗漏cur<str.length()
				if(subStr.charAt(j) == str.charAt(cur)){
					if(j == subStr.length() - 1)
						return i;
					cur++;
					continue;
				}else{
					break;
				}
			}
		}
		return -1;
	}
	
	public static void main(String[] args) {
		String str1 = "aeabcsdfgireklfgff";
		String str2 = "aeabcsdfgireklfgff";
		System.out.println(new StringOperate().subStr(str2, str1));
	}
}
