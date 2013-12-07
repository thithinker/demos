package zgl.design.abstractFactory;

public class MaleFactory implements HumanFactory{
	@Override
	public Human createYellowHuman(){
		return new MaleYellowMan();
	}
	
	@Override
	public Human createBlackHuman(){
		return new MaleBlackMan();
	}
	
	@Override
	public Human createWhiteHuman(){
		return new MaleWhiteMan();
	}
}
