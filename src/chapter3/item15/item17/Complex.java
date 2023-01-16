package chapter3.item15.item17;

	/*
	 * item 17
	 * 변경가능성을 최소화 하라
	 * 
	 * 만드는법
	 * 1. 객체의 상태를 변경하는 메서드(변경자)를 제공하지 않는다
	 * 2. 클래스를 확장할 수 없도록 한다.
	 * 3. 모든 필드를 final로 선언한다.
	 * 4. 모든 필드를 private로 선언한다.
	 * 5. 자신 외에는 내부의 가변 컴포넌트에 접근할 수 없도록 한다.
	 * 
	 * */
public class Complex {
	private final double re;
	private final double im;
	
	// case 1 
//	public Complex(double re, double im) {
//		this.re = re;
//		this.im = im;
//	}
	// case 2
	private Complex(double re, double im) {
		this.re = re;
		this.im = im;
	}
	
	public double realPart() {return re;}
	public double imaginaryPart() {return im;}
	
	// 함수형 프로그램밍 (아래 4개의 메서드)
	// -> 피연산자에 함수를 적용해 그 결과를 반환하지만 피연산자 자체는 그대로인 프로그래밍 패턴 
	public Complex plus(Complex c) {
		return new Complex(re + c.re, im + c.im);
	}
	
	public Complex minus(Complex c) {
		return new Complex(re - c.re, im - c.im);
	}
	
	public Complex times(Complex c) {
		return new Complex(re * c.re - im * c.im, re * c.re + im * c.im);
	}
	
	public Complex dividedBy(Complex c) {
		double tmp = c.re * c.re + c.im * c.im;
		return new Complex((re * c.re + im * c.im) / tmp, (re * c.re - im * c.im) / tmp);
	}
	
	@Override
	public boolean equals(Object o) {
		if(o == this) {
			return true;
		} 
		
		if(!(o instanceof Complex)) {
			return false;
		}
		
		Complex c = (Complex) o;
		
		// == 대신 compare를 사용 -> float, double 타입은 Float.compare, Double.compare를 사용해서 비교 
		return Double.compare(c.re, re) == 0 && Double.compare(c.im, im) == 0;
	}
	
	@Override
	public int hashCode() {
		return 31 * Double.hashCode(re) + Double.hashCode(im);
	}
	
	@Override
	public String toString() {
		return "(" + re + " + " + im + ")";
	}
	
	// 자신을 상속하지 못하게 하는 가장 쉬운 방법
	// 1. final 클래스로 선언
	// 2. 모든 생성자를 private 혹은 package-private으로 만들고, public 정적 팩터리를ㄹ 제공하는 방법
	// 정적팩터리 생성 -> 이게 최선일 때가 많다 
	public static Complex valueof(double re, double im) {
		return new Complex(re, im);
	}
	
	public static void main(String[] args) {
		Complex cp = new Complex(1.0, 2.0);
	}
}
