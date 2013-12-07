package zgl.design.abstractFactory;

public class NvWa {
	public static void main(String[] args){
		HumanFactory maleFactory = new MaleFactory();
		HumanFactory femaleFactory = new FemaleFactory();
		
		Human maleYellow = maleFactory.createYellowHuman();
		Human maleBlack = maleFactory.createBlackHuman();
		
		Human femaleYellow = femaleFactory.createYellowHuman();
		Human femaleBlack= femaleFactory.createBlackHuman();
		
		System.out.println(maleYellow);
		System.out.println(maleBlack);
		System.out.println(femaleYellow);
		System.out.println(femaleBlack);
	}
}
