package zgl.design.abstractFactory;

public class FemaleYellowMan extends YellowMan{
	@Override
	public void getSex(){
		System.out.println("����Ů��");
	}
	
	@Override
	public String toString(){
		return "I'm a chinese woman.";
	}
}
