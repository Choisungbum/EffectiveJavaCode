package chapter7.item42;

import java.util.function.DoubleBinaryOperator;

/*
 * item 42 익명 클래스보다는 람다를 사용하라 
 * 
 * 자바에서 함수 타입을 표현할 때 추상 메서드를 하나만 담은 인터페이스를 사용했다. 이런 인터페이스의 인스턴스를 함수객체라고 하여
 * 특정함수나 동작을 나타내는데 썼다. -> 주로 '익명클래스'로 구현
 * 
 * 자바 8로 넘어와 이 같은 경우, 함수형 인터페이스라 부르는 이 인터페이스들의 인스턴스를 
 * '람다식'(혹은 람다)을 사용해 만들 수 있게 되었다.
 * 
 * 람다는 함수형 인터페이스에서만 쓰인다.
 * 추상클래스의 인스턴스를 만들 때 람다를 쓸 수 없으니 익명 클래스를 써야한다.
 * 또 추상 메서드가 여러 개인 인터페이스의 인스턴스를 만들 대도 익명 클래스를 쓸 수 있다.
 * 
 * 람다는 자신을 참조할 수 없다.
 * 람다에서의 this 키워드는 바깥 인스턴스를 가리킨다 
 * 반면 익명 클래스에서의 this는 익명 클래스의 인스턴스 자신을 가리킨다.
 * 그래서 함수 객체가 자신을 참조해야 한다면 반드시 익명 클래스르 써야한다.
 * 
 * 람다도 익명 클래스처럼 직렬화 형태가 구현별로 다를 수 있어서 람다를 직력화하는 일은 극치 삼가야한다.
 * 
 * 익명클래스는 함수형 인터페이스가 아닌 타입의 인스턴스를 만들 때만 사용하라!!
 * */
public enum Operation {
	/*
	 * 아래 메서드로 인해 상수별 클래스 몸체 는 더이상 사용할 필요가 없다고 생각하지만
	 * 메서드나 클래스와 달리 람다는 이름이 없고 문서화도 못 한다. 따라서 코드 자체로 동작이 명확히 설명되지 않거나 코드 줄 수가
	 * 많아지면 람다를 쓰지말아야 한다.
	 * */
	PLUS("+", (x,y) -> x + y),
	MINUS("-", (x,y) -> x - y),
	TIMES("*", (x,y) -> x * y),
	DIVIDE("/", (x,y) -> x / y);
	
	private final String symbol;
	private final DoubleBinaryOperator op;
	
	Operation(String symbol, DoubleBinaryOperator op) {
		this.symbol = symbol;
		this.op = op;
	}
	
	@Override
	public String toString() { return symbol;}
	
	public double apply(double x, double y) {
		return op.applyAsDouble(x, y);
	}
	
}
