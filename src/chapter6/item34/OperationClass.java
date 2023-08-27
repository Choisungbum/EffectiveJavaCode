package chapter6.item34;

import java.util.Arrays;
import java.util.Collection;

import chapter6.item38.ExtendedOperation;
import chapter6.item38.Operation;

public enum OperationClass {
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
	
	OperationClass(String symbol) {this.symbol = symbol;}
	
	@Override public String toString() {return symbol;}
	public abstract double apply(double x, double y);
	
	public static OperationClass inverse(OperationClass op) {
		switch(op) {
			case PLUS : return OperationClass.PLUS;
			case MINUS : return OperationClass.MINUS;
			case TIMES : return OperationClass.TIMES;
			case DIVIDE : return OperationClass.DIVIDE;
			
			default : throw new AssertionError("알 수 없는 연산: " + op);
		}
	}
	
//	private static final Map<String, Operation> stringToEnum = Stream.of(values())
//																	.collect(toMap(Object::toString, e -> e));
//	
//	//저장한 문자열에 해당하는 Operation을 (존재한다면) 반환한다.
//	public static Optional<Operation> fromString(String symbol) {
//		return Optional.ofNullable(nullstringToEnum.get(symbol));
//	}
	// 아이템 38
	// 타입 수준에서도, 기본 열거 타입 대신 확장된 열거 타입을 넘겨 확장된 열거 타입으리 원소 모두를 사용하게 할 수도 있다.
	private static <T extends Enum<T> & Operation> void test1 ( Class<T> opEnumType, double x, double y) {
		for(Operation op : opEnumType.getEnumConstants()) {
			System.out.printf("%f %s %f = %f%n",
							   x, op, y, op.apply(x, y));
		}
	}
	private static void test2(Collection<? extends Operation> opSet, double x, double y) {
		for(Operation op : opSet) {
			System.out.printf("%f %s %f = %f%n",
							   x, op, y, op.apply(x, y));
		}
	}
	public static void main(String[] args) {
		double x = Double.parseDouble("4");
		double y = Double.parseDouble("2");
		test1(ExtendedOperation.class, x, y);
		test2(Arrays.asList(ExtendedOperation.values()), x, y);
		
	}
}
