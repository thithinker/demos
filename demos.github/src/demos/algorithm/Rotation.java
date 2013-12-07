package demos.algorithm;

/**
 * 左旋字符串，时间复杂度O(n)，空间复杂度O(1)
 * @author yize
 * 2013年12月7日
 */
public class Rotation {
	public String rightRotation(String s, int k){
		return rightRotation(s.toCharArray(), k);
	}
	
	public String leftRotation(String s, int k){
		return leftRotation(s.toCharArray(), k);
	}
	//将字符数组右旋k个位置
	private String rightRotation(char[] s, int k){
		//k为负值时，右移k位相当于左移-k位
		if(k < 0){
			return leftRotation(s, -k);
		}
		k %= s.length;
		//逆序s.length-k个字符
		invert(s, 0, s.length - k - 1);
		//逆序k个字符
		invert(s, s.length - k, s.length - 1);
		//逆序全部字符数组
		invert(s, 0, s.length - 1);
		return String.valueOf(s);
	}
	
	// 将字符数组左旋k个位置
	private String leftRotation(char[] s, int k) {
		//k为负值时，左移k位相当于右移-k位
		if(k < 0){
			return rightRotation(s, -k);
		}
		k %= s.length;
		// 逆序k个字符
		invert(s, 0, k - 1);
		// 逆序s.length-k个字符
		invert(s, k, s.length - 1);
		// 逆序全部字符数组
		invert(s, 0, s.length - 1);
		return String.valueOf(s);
	}
	
	/**
	 * 逆序部分字符数组
	 * @param s 待逆序的字符数组
	 * @param b 开始位置
	 * @param e 终点位置
	 */
	private void invert(char[] s, int b, int e){
		char tmp;
		while(b < e){
			tmp = s[b];
			s[b++] = s[e];
			s[e--] = tmp;
		}
	}
	
	public static void main(String[] args){
		Rotation lr = new Rotation();
		String s = "student";
		for(int i = 0; i <= s.length(); i++){
			System.out.println(lr.leftRotation(s, i));
		}
		System.out.println("-----------------");
		for(int i = 0; i <= s.length(); i++){
			System.out.println(lr.rightRotation(s, i));
		}
	}
}
