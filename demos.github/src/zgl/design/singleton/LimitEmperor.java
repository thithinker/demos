package zgl.design.singleton;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 
 * @author yize
 * 2013Äê12ÔÂ6ÈÕ
 */
public class LimitEmperor {
	private static final int LIMIT = 2; 
	private static final List<LimitEmperor> emperors = new ArrayList<LimitEmperor>();
	private LimitEmperor(){
		
	}

	static {
		for(int i = 0; i < LIMIT; i++){
			emperors.add(new LimitEmperor());
		}
	}
	
	public static LimitEmperor getLimitEmperor(){
		Random r = new Random();
		return emperors.get(r.nextInt(LIMIT));
	}
	
	public static void main(String[] args){
		for(int i = 0; i < 10; i++){
			System.out.println(LimitEmperor.getLimitEmperor());
		}
	}
	
	
}
