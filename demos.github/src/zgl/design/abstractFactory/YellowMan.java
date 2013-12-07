package zgl.design.abstractFactory;

public abstract class YellowMan implements Human {
	@Override
	public void getColor(){
		System.out.println("I'm a Chinese.");
	}
	
	@Override
	public void talk(){
		System.out.println("I speak Chinese. ");
	}
	
	@Override
	public String toString(){
		return "I'm Chinese.";
	}
}
