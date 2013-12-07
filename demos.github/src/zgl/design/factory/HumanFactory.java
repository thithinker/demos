package zgl.design.factory;

//����ģʽ
public class HumanFactory extends AbstractHumanFactory{
	@SuppressWarnings("unchecked")
	@Override
	public <T extends Human> T createHuman(Class<T> c) {
		Human h = null;
		try{
			h = (Human)Class.forName(c.getName()).newInstance();
		}catch(Exception e){
			System.out.println("�޷�����Human.");
		}
		
		return (T)h;
	}
}
