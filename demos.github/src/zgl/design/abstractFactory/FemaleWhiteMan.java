package zgl.design.abstractFactory;

public class FemaleWhiteMan extends WhiteMan{
	@Override
	public void getSex(){
		System.out.println("����Ů��");
	}
	
	@Override
	public String toString(){
		return "I'm a white woman";
	}
}
