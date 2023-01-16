package chapter1.item2;

import java.util.Objects;

public class NyPizza extends Pizza{
	public enum Size {SMALL, MEDIUM, LARGE}
	private final Size size;
	
	public static class Builder extends Pizza.Builder<Builder> {
		private final Size size;
		
		public Builder(Size size) {
			this.size = Objects.requireNonNull(size);
		}
		
		@Override
		NyPizza Build() {
			// TODO Auto-generated method stub
			return new NyPizza(this);
		}

		@Override
		protected Builder self() {
			// TODO Auto-generated method stub
			return this;
		}
		
	}

	private NyPizza(Builder builder) {
		super(builder);
		size = builder.size;
		// TODO Auto-generated constructor stub
	}


}
