package zgl.design.factory;

//≥°æ∞¿‡
public class NvWa {
	public static void main(String[] args){
		AbstractHumanFactory humanFactory = new HumanFactory();
		
		Human h1 = humanFactory.createHuman(WhiteMan.class);
		Human h2 = humanFactory.createHuman(BlackMan.class);
		Human h3 = humanFactory.createHuman(YellowMan.class);
		
		h1.getColor();
		h1.talk();
		
		h2.getColor();
		h2.talk();
		
		h3.getColor();
		h3.talk();
	}
}
