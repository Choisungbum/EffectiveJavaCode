package chapter6;

import java.util.Optional;
import java.util.stream.Stream;

public enum Operation {
//	PLUS   {public double apply(double x, double y) {return x + y;}},
//	MINUS  {public double apply(double x, double y) {return x - y;}},
//	TIMES  {public double apply(double x, double y) {return x * y;}},
//	DIVIDE {public double apply(double x, double y) {return x / y;}};
//	
//	public abstract double apply(double x, double y);
	
	PLUS("+") {
		public double apply(double x, double y) {return x + y;}
	},
	MINUS("-") {
		public double apply(double x, double y) {return x - y;}
	},
	TIMES("*") {
		public double apply(double x, double y) {return x * y;}
	},
	DIVIDE("/") {
		public double apply(double x, double y) {return x / y;}
	};
	
	private final String symbol;
	
	Operation(String symbol) {this.symbol = symbol;}
	
	@Override public String toString() {return symbol;}
	public abstract double apply(double x, double y);
	
//	private static final Map<String, Operation> stringToEnum = Stream.of(values())
//																	.collect(toMap(Object::toString, e -> e));
//	
//	//저장한 문자열에 해당하는 Operation을 (존재한다면) 반환한다.
//	public static Optional<Operation> fromString(String symbol) {
//		return Optional.ofNullable(nullstringToEnum.get(symbol));
//	}
}
