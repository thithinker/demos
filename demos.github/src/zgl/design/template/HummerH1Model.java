package zgl.design.template;

public class HummerH1Model extends HummerModel {
	@Override
	public void start() {
		System.out.println("H1 发动。。。");
	}

	@Override
	public void stop() {
		System.out.println("H1 停下。。。");
	}

	@Override
	public void alarm() {
		System.out.println("H1 鸣笛。。。");
	}

	@Override
	public void engineBoom() {
		System.out.println("H1 引擎响。。");
	}
}
