package zgl.design.template;

public abstract class HummerModel {
	//��������
	public abstract void start();
	//ֹͣ
	public abstract void stop();
	//����
	public abstract void alarm();
	//������
	public abstract void engineBoom();
	//������������У������ͬ��һ�δ��뿽�������Σ�����Ҫ����Ʋ�������
	//����
	public void run(){
		this.start();
		this.engineBoom();
		this.alarm();
		this.stop();
	}
}
