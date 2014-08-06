package zgl.design.proxy2;

/**
 * 强制代理：必须通过真实的角色找到代理角色
 * @author yize
 * 2014年7月17日
 */
public class Client {
	public static void main(String[] args) {
		//不能直接访问
		IGamePlayer gamePlayer = new GamePlayer("张");
		gamePlayer.login("zhang", "123");
		gamePlayer.killBoss();
		gamePlayer.upgrade();
		
		
		//不能自定义代理访问
		IGamePlayer gamePlayer2 = new GamePlayer("张");
		IGamePlayer proxy = new GamePlayerProxy(gamePlayer2);
		proxy.login("zhang", "123");
		proxy.killBoss();
		proxy.upgrade();
		
		//只能使用指定代理访问
		IGamePlayer gamePlayer3 = new GamePlayer("张");
		IGamePlayer proxy2 = gamePlayer3.getProxy();
		proxy2.login("zhang", "123");
		proxy2.killBoss();
		proxy2.upgrade();
	}
}
