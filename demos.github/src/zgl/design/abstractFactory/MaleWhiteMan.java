package zgl.design.abstractFactory;

public class MaleWhiteMan extends WhiteMan{
	@Override
	public void getSex(){
		System.out.println("��������");
	}
	
	@Override
	public String toString(){
		return "I'm a white man.";
	}
}
