package zgl.design.template;

public class HummerH2Model extends HummerModel{
	@Override
	public void start() {
		System.out.println("H2 ����������");
	}

	@Override
	public void stop() {
		System.out.println("H2 ͣ�¡�����");
	}

	@Override
	public void alarm() {
		System.out.println("H2 ���ѡ�����");
	}

	@Override
	public void engineBoom() {
		System.out.println("H2 �����졣��");
	}
	
}
