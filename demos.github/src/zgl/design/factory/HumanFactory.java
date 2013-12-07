package zgl.design.factory;

//工厂模式
public class HumanFactory extends AbstractHumanFactory{
	@SuppressWarnings("unchecked")
	@Override
	public <T extends Human> T createHuman(Class<T> c) {
		Human h = null;
		try{
			h = (Human)Class.forName(c.getName()).newInstance();
		}catch(Exception e){
			System.out.println("无法创建Human.");
		}
		
		return (T)h;
	}
}
