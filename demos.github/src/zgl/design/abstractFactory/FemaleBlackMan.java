package zgl.design.abstractFactory;

public class FemaleBlackMan extends BlackMan{
	@Override
	public void getSex(){
		System.out.println("����Ů��");
	}
	
	@Override
	public String toString(){
		return "I'm a black woman.";
	}
}
