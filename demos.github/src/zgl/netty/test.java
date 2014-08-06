package zgl.netty;

public class test {
	public static void main(String[] args) {
		final int port = 8088;
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				PlainEchoServer server = new PlainEchoServer(port);
				server.server();
			}
		}).start();
		
		

		for(int i = 0; i < 5; i++){
			new Thread(new Runnable() {
				@Override
				public void run() {
					PlainEchoClient c = new PlainEchoClient(port);
					//System.out.println(c);
					c.client();
				}
			}).start();
			
		}
	}
}
