package zgl.design.abstractFactory;

public class MaleBlackMan extends BlackMan{
	@Override
	public void getSex(){
		System.out.println("��������");
	}
	
	@Override
	public String toString(){
		return "I'm a black man.";
	}
}
