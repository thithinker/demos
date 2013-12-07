package zgl.design.abstractFactory;

public abstract class BlackMan implements Human{
	@Override
	public void getColor(){
		System.out.println("I'm black. ");
	}
	
	@Override
	public void talk(){
		System.out.println("Black");
	}
	
	@Override
	public String toString(){
		return "I'm black";
	}
}
