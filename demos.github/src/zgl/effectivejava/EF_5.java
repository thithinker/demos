package zgl.effectivejava;

public class EF_5 {
	public long add1(){
		Long sum = 0L;
		long start = System.currentTimeMillis();
		for(int i = 0; i < Integer.MAX_VALUE; i++){
			sum += i;
		}
		long t = System.currentTimeMillis() - start;
		System.out.println(sum);
		return t;
	}
	
	public long add2(){
		long sum1 = 0;
		long start = System.currentTimeMillis();
		for(int i = 0; i < Integer.MAX_VALUE; i++){
			sum1 += i;
		}
		long tt = System.currentTimeMillis() - start;
		System.out.println(sum1);
		return tt;
	}
	
	public static void main(String[] args) {
		System.out.println(new EF_5().add1() + " " + new EF_5().add2());
	}
}
