package zgl.design.factory;

public class WhiteMan implements Human {
	@Override
	public void getColor(){
		System.out.println("I'm a white man. ");
	}
	
	@Override
	public void talk(){
		System.out.println("White");
	}

}
