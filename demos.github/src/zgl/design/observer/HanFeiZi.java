package zgl.design.observer;

import java.util.Observable;

/**
 * Java自带观察者模式的被观察者
 * @author yize
 * 2014年7月26日
 */
public class HanFeiZi extends Observable {
	public void haveBreakfast(){
		System.out.println("韩非子：吃饭了...");
		super.setChanged();
		super.notifyObservers("韩非子在吃饭...");
	}
	
	public void haveFun(){
		System.out.println("韩非子：玩耍了...");
		super.setChanged();
		super.notifyObservers("韩非子在玩耍...");
	}
}
