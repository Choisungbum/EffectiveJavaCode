package chapter3.item22;

/*
 * item 22
 * 인터페이스는 타입을 정의하는 용도로만 사용하라 
 * 
 * */

/*
 * 상수를 공개하고 싶으면 아래 해당방법을 따르자  
 * 
 * 1. 특정 클래스나 인터페이스와 강하게 연관된 상수라면 그 클래스나 인터페이스 자체에 추가해야 한다.( ex) 박싱클래스 Integer, Double)
 * 2. 열거 타입으로 나타내기 적합한 상수라면 열거 타입으로 만들어 공개
 * 3. 인스턴스화할 수 없는 유틸리티 클래스에 담아 공개 
 * 
 * */

// 해당방법은 위 3번째 방법
public class PhysicalConstantsClass {
	private PhysicalConstantsClass() {}; // 인스턴스화 방지 
	
	// 숫자 리터럴에 사용된 밑줄 -> 자바 7부터 허용되는 방법, 숫자리터럴의 값에는 아무런 영향을 주지 않으면서 읽기 편하게 해준다. 
	// 아보가드로 수
	static final  double AVOGADRO_NUMBER = 6.022_140_852e23;
	
	// 볼츠만 상수
	static final double BOLTZMANN_CONSTANT = 1.380_648_52e-23;

	
}
