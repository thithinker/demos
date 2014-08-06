package zgl.thread;

public class WorkThread implements Runnable{
	String command;
	public WorkThread(String cmd){
		command = cmd;
	}
	
	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + " start command:" + command);
		work();
		System.out.println(Thread.currentThread().getName() + " end command:" + command);
	}
	
	private void work(){
		try{
			Thread.sleep(5000);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
	
	@Override
	public String toString() {
		return this.command;
	}
}
