package chapter3.item22;

/*
 * item 22
 * 인터페이스는 타입을 정의하는 용도로만 사용하라 
 * 
 * 
 * */

// 상수 인터페이스 안티패턴 - 사용금지 !
// 클래스 내부에서 사용하는 상수는 외부 인터페이스가 아니라 내부 구현에 해당 
// -> 내부 구현을 클래스의 API로 노출하는 행위이다
// -> final이 아닌 클래스가 상수 인터페이스를 구현한다면 모든 하위 클래스의 이름공간이 그 인터페이스가 정의한 상수들로 오염되어 버린다.

public interface PhysicalConstants {
	// 아보가드로 수
	static final  double AVOGADRO_NUMBER = 6.022_140_852e23;
	
	// 볼츠만 상수
	static final double BOLTZMANN_CONSTANT = 1.380_648_52e-23;
}
