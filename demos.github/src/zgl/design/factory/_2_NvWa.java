package zgl.design.factory;

public class _2_NvWa {
	public static void main(String[] args){
		//简单工厂模式的使用
		Human h1 = _2_HumanFactory.createHuman(BlackMan.class);
		Human h2 = _2_HumanFactory.createHuman(WhiteMan.class);
		Human h3 = _2_HumanFactory.createHuman(YellowMan.class);
		
		h1.getColor();
		h1.talk();
		
		h2.getColor();
		h2.talk();
		
		h3.getColor();
		h3.talk();
		
	}
}
