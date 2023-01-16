package chapter1.item2;

public class TestBuilderEx {
	
	private final int servingSize;
	private final int setvings;
	private final int calories;
	private final int fat;
	private final int sodium;
	private final int carbohydrate;
	
	public static class Builder {
		// 필수매개변수
		private final int servingSize;
		private final int servings;
		
		// 선택 매개변수 - 초기화
		private int calories 	 = 0;
		private int fat 		 = 0;
		private int sodium 		 = 0;
		private int carbohydrate = 0;
		
		public Builder(int servingSize, int servings) {
			this.servingSize = servingSize;
			this.servings = servings;
		}
		
		public Builder calories(int val) {
			calories = val;
			return this;
		}
		
		public Builder fat(int val) {
			fat = val;
			return this;
		}
		
		public Builder sodium(int val) {
			sodium = val;
			return this;
		}
		
		public Builder carbohydrate(int val) {
			carbohydrate = val;
			return this;
		}
		
		public TestBuilderEx build() {
			return new TestBuilderEx(this);
		}
	}
	
	private TestBuilderEx(Builder builder) {
		servingSize  = builder.servingSize;
		setvings     = builder.servings;
		calories     = builder.calories;
		fat          = builder.fat;
		sodium       = builder.sodium;
		carbohydrate = builder.carbohydrate;
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestBuilderEx cocaCola = new TestBuilderEx.Builder(240,8).calories(1).build();
		System.out.println("calories : " +cocaCola.calories);
		
	}

}
