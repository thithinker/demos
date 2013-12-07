package zgl.design.factory;

//�򵥹���ģʽ
public class _2_HumanFactory {
	@SuppressWarnings("unchecked")
	public static <T extends Human> T createHuman(Class<T> c){
		Human h = null;
		try{
			h = (Human)Class.forName(c.getName()).newInstance();
		}catch(Exception e){
			System.out.println("����Human����.");
		}
		
		return (T) h;
	}
}
