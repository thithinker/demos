package zgl.design.abstractFactory;

public class FemaleFactory implements HumanFactory{
	@Override
	public Human createYellowHuman(){
		return new FemaleYellowMan();
	}
	
	@Override
	public Human createBlackHuman(){
		return new FemaleBlackMan();
	}
	
	@Override
	public Human createWhiteHuman(){
		return new FemaleWhiteMan();
	}
}
