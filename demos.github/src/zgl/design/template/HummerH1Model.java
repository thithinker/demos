package zgl.design.template;

public class HummerH1Model extends HummerModel {
	@Override
	public void start() {
		System.out.println("H1 ����������");
	}

	@Override
	public void stop() {
		System.out.println("H1 ͣ�¡�����");
	}

	@Override
	public void alarm() {
		System.out.println("H1 ���ѡ�����");
	}

	@Override
	public void engineBoom() {
		System.out.println("H1 �����졣��");
	}
}
