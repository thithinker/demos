package zgl.test;

/**
 * 亲和数计算
 * 		若A和B两个数，A的真因子之和等于B，而B的真因子之和等于A，则称A与B为一对亲和数。
 * @date 2014.4.7
 * @author yize
 *
 */
public class AmicableNumbers {
	/**
	 * 输出max范围内所有的亲和数
	 * @param max 最大范围
	 */
	public void find(int max) {
		int[] tmp = new int[max];	//构造伴随数组
		for(int i = 0; i < max; i++){	
			tmp[i] = 1;				//1是每个数的真因子
		}
		
		int j = 0;
		for(int i = 2; i + i < max; i++){
			j = i + i;				//真因子不包括该数本身
			while(j < max){			//将所有i的倍数位置上加上i
				tmp[j] += i;		
				j += i;
			}
		}
		int sum = 0;
		for(int i = 0; i < max; i++){
			if(tmp[i] > i && tmp[i] < max &&i == tmp[tmp[i]]){	//去重，不越界，满足亲和数
				System.out.println(i + " " + tmp[i]); 
				sum += i + tmp[i];
			}
		}
		System.out.println("sum: " + sum);
	}
	
	public static void main(String[] args) {
		new AmicableNumbers().find(10000);
	}
}
