package zgl.design.factory;

public class _2_YellowMan implements Human {
	@Override
	public void getColor(){
		System.out.println("I'm a Chinese.");
	}
	
	@Override
	public void talk(){
		System.out.println("I speak Chinese. ");
	}
}
