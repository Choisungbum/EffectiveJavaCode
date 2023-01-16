package chapter1.item2;

public class Calzone extends Pizza{
	private final boolean sauceInside;
	
	public static class Builder extends Pizza.Builder<Builder> {
		private boolean sauceInside = false;
		
		public Builder sauceInside() {
			sauceInside = true;
			return this;
		}
		
		@Override
		public Calzone Build() {
			// TODO Auto-generated method stub
			return new Calzone(this);
		}

		@Override
		protected Builder self() {
			// TODO Auto-generated method stub
			return this;
		}
		
	}
	
	
	private Calzone(Builder builder) {
		super(builder);
		sauceInside = builder.sauceInside;
		// TODO Auto-generated constructor stub
	}



}
