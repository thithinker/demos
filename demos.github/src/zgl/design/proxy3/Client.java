package zgl.design.proxy3;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class Client {
	public static void main(String[] args) {
		IGamePlayer gamePlayer = new GamePlayer("张三");
		InvocationHandler handler = new GamePlayerIH(gamePlayer);
		ClassLoader classLoader = gamePlayer.getClass().getClassLoader();
		IGamePlayer proxy = (IGamePlayer) Proxy.newProxyInstance(classLoader, new Class[]{IGamePlayer.class}, handler);
		proxy.login("zhangsan", "123");
		proxy.killBoss();
		proxy.upgrade();
	}
}
