package demos.algorithm;

public class StringContain {
	/**
	 * �ַ���str1�Ƿ����str2���г��ֵ��ַ�, ʱ�临�Ӷ�O(m * n)
	 * @param str1 ���ַ���
	 * @param str2 ���ַ���
	 * @return true or false
	 */
	public boolean isContain1(String str1, String str2){
		for(int i = 0; i < str2.length(); i++){
			for(int j = 0; j < str1.length(); j++){
				//�����ǰ�ַ�str2�ĵ�ǰ�ַ���str1�У���������
				if(str2.charAt(i) == str1.charAt(j)){
					break;
				}
				//�������str1����󳤶Ȼ�û�ҵ�������ж�
				if(j == str1.length() - 1)
					return false;
			}
			//�������str2����󳤶ȣ�����ж�
			if(i == str2.length() - 1){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * �������򣬷���str��Ӧ��������ʽ
	 * @param str ��������ַ���
	 * @return str�������ַ���
	 */
	private String countSort(String str){
		//�洢A-Zÿ���ַ����ֵĴ��� 
		int[] tmp = new int[26];
		int index = 0;
		//A-Zÿ���ַ����ֵĴ���
		for(int i = 0; i < str.length(); i++){
			index = str.charAt(i) - 'A';
			tmp[index]++;
		}
		
		for(int i = 1; i < 26; i++){
			tmp[i] += tmp[i - 1];
		}
		//str������Ӧ���ַ�����
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
	 * �ַ���str1�Ƿ����str2���г��ֵ��ַ�, ʱ�临�Ӷ�O(m + n)
	 * @param str1 ���ַ���
	 * @param str2 ���ַ���
	 * @return true or false
	 */
	public boolean isContain2(String str1, String str2){
		//str1��str2��Ӧ�������ַ���
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

	public static void main(String[] args){
		System.out.println(new StringContain().isContain2("ABCDEFGHLMNOPQRSZY", "ZDCGDSRQPOMY"));
		System.out.println(new StringContain().countSort("ABCDECDS"));

	}
}
