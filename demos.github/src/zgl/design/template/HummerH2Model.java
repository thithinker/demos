package zgl.design.template;

public class HummerH2Model extends HummerModel{
	@Override
	public void start() {
		System.out.println("H2 发动。。。");
	}

	@Override
	public void stop() {
		System.out.println("H2 停下。。。");
	}

	@Override
	public void alarm() {
		System.out.println("H2 鸣笛。。。");
	}

	@Override
	public void engineBoom() {
		System.out.println("H2 引擎响。。");
	}
	
}
