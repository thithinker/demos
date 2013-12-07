package zgl.design.abstractFactory;

public abstract class WhiteMan implements Human {
	@Override
	public void getColor(){
		System.out.println("I'm a white man. ");
	}
	
	@Override
	public void talk(){
		System.out.println("White");
	}

	@Override 
	public String toString(){
		return "I'm white.";
	}
}
