package zgl.design.abstractFactory;

public class MaleYellowMan extends YellowMan{
	@Override
	public void getSex(){
		System.out.println("��������");
	}
	
	@Override
	public String toString(){
		return "I'm a Chinese man.";
	}
}
