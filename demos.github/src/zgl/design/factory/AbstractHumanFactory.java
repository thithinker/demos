package zgl.design.factory;

public abstract class AbstractHumanFactory {
	//����һ����Ʒ����������������Ϳ����������ã�ͨ��ΪString��Enum��Class�ȣ�Ҳ����Ϊ��
	public abstract <T extends Human> T createHuman(Class<T> c);
}
