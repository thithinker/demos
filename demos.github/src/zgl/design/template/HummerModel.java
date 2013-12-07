package zgl.design.template;

public abstract class HummerModel {
	//发动悍马
	public abstract void start();
	//停止
	public abstract void stop();
	//鸣笛
	public abstract void alarm();
	//引擎响
	public abstract void engineBoom();
	//软件开发过程中，如果相同的一段代码拷贝过两次，就需要对设计产生怀疑
	//奔跑
	public void run(){
		this.start();
		this.engineBoom();
		this.alarm();
		this.stop();
	}
}
