package zgl.design.factory;

public class _2_BlackMan implements Human{
	@Override
	public void getColor(){
		System.out.println("I'm black. ");
	}
	
	@Override
	public void talk(){
		System.out.println("Black");
	}
}
