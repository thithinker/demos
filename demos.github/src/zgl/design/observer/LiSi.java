package zgl.design.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * Java自带观察者模式的观察者
 * @author yize
 * 2014年7月26日
 */
public class LiSi implements Observer{

	@Override
	public void update(Observable o, Object arg) {
		System.out.println("开始汇报：");
		this.reportToQinShiHuang(arg.toString());
		System.out.println("汇报完毕");
	}
	
	private void reportToQinShiHuang(String reportContext){
		System.out.println("李斯：" + reportContext);
	}
}
