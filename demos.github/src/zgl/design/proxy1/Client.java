package zgl.design.proxy1;

/**
 * 普通代理：调用者需要知道代理的存在
 * @author yize
 * 2014年7月17日
 */
public class Client {
	public static void main(String[] args) {
		IGamePlayer proxy = new GamePlayerProxy("张");
		proxy.login("zhang", "123");
		proxy.killBoss();
		proxy.upgrade();
	}
}
