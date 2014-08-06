package zgl.design.observer;

/**
 * 测试
 * @author yize
 * 2014年7月26日
 */
public class Client {
	public static void main(String[] args) {
		HanFeiZi hanFeiZi = new HanFeiZi();
		LiSi liSi = new LiSi();
		/**
		 * 注册观察者
		 */
		hanFeiZi.addObserver(liSi);
		hanFeiZi.haveBreakfast();
		hanFeiZi.haveFun();
	}
}
