package demos.algorithm;

/**
 * �����ַ�����ʱ�临�Ӷ�O(n)���ռ临�Ӷ�O(1)
 * @author yize
 * 2013��12��7��
 */
public class Rotation {
	public String rightRotation(String s, int k){
		return rightRotation(s.toCharArray(), k);
	}
	
	public String leftRotation(String s, int k){
		return leftRotation(s.toCharArray(), k);
	}
	//���ַ���������k��λ��
	private String rightRotation(char[] s, int k){
		//kΪ��ֵʱ������kλ�൱������-kλ
		if(k < 0){
			return leftRotation(s, -k);
		}
		k %= s.length;
		//����s.length-k���ַ�
		invert(s, 0, s.length - k - 1);
		//����k���ַ�
		invert(s, s.length - k, s.length - 1);
		//����ȫ���ַ�����
		invert(s, 0, s.length - 1);
		return String.valueOf(s);
	}
	
	// ���ַ���������k��λ��
	private String leftRotation(char[] s, int k) {
		//kΪ��ֵʱ������kλ�൱������-kλ
		if(k < 0){
			return rightRotation(s, -k);
		}
		k %= s.length;
		// ����k���ַ�
		invert(s, 0, k - 1);
		// ����s.length-k���ַ�
		invert(s, k, s.length - 1);
		// ����ȫ���ַ�����
		invert(s, 0, s.length - 1);
		return String.valueOf(s);
	}
	
	/**
	 * ���򲿷��ַ�����
	 * @param s ��������ַ�����
	 * @param b ��ʼλ��
	 * @param e �յ�λ��
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
