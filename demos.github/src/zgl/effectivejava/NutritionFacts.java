package zgl.effectivejava;

/**
 * @author yize
 *
 */
public class NutritionFacts {
	private final int servingSize;
	private final int calories;
	private final int fat;
	
	
	
	public int getServingSize() {
		return servingSize;
	}

	public int getCalories() {
		return calories;
	}

	public int getFat() {
		return fat;
	}

	public static class Builder{
		private final int servingSize;
		
		private int calories = 0;
		private int fat = 0;
		
		public Builder(int servingSize){
			this.servingSize = servingSize;
		}
		
		public Builder calories(int val){
			this.calories = val;
			return this;
		}
		public Builder fat(int val){
			this.fat = val;
			return this;
		}
		
		public NutritionFacts build(){
			return new NutritionFacts(this);
		}
	}
	
	private NutritionFacts(Builder b){
		servingSize = b.servingSize;
		calories = b.calories;
		fat = b.fat;
	}
	
	public static void main(String[] args) {
		NutritionFacts nutritionFacts = new NutritionFacts.Builder(240).calories(100).fat(0).build();
		System.out.println(nutritionFacts.getCalories());
	}
}
