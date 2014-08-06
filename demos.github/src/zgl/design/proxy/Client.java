package zgl.design.proxy;

public class Client {
	public static void main(String[] args) {
		IGamePlayer gamePlayer = new GamePlayer("张");
		IGamePlayer proxy = new GamePlayerProxy(gamePlayer);
		proxy.login("zhang", "123");
		proxy.killBoss();
		proxy.upgrade();
	}
}
