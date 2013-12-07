package zgl.design.factory;

//简单工厂模式
public class _2_HumanFactory {
	@SuppressWarnings("unchecked")
	public static <T extends Human> T createHuman(Class<T> c){
		Human h = null;
		try{
			h = (Human)Class.forName(c.getName()).newInstance();
		}catch(Exception e){
			System.out.println("创建Human错误.");
		}
		
		return (T) h;
	}
}
