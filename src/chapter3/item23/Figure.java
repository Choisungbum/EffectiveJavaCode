package chapter3.item23;

/*
 * item 23 태그달린 클래스보다는 클래스 계층구조를 활용하라
 * 
 * 태그란 ? -> 클래스가 어떠한 타입인지에 대한 정보를 담고있는 멤버변수(필드)를 의미한다.
 * 
 * 태그달린 클래스 단점
 * 1. 열거타입선언, 태그필드, switch 등 쓸데없는 코드가 많다.
 * 2. 여러구현이 한 클래스내에 혼합되어 있어 가독성이 나쁘다
 * 3. 메모리도 많이 사용한다.
 * 4. 필드들을 final로 선언하려면 해당 의미에 쓰이지 않는 필드를 까지 생성자에 초기화 해야 한다.
 * 5. 인스턴스의 타입만으로는 현재 나타내는 의미를 알 길이 전혀없다.
 * 
 *  -> 태그 달린 클래스는 장황하고, 오류를 내기 쉽고, 비효율적이다.
 * */
public class Figure {

	enum Shape {RECTANGLE, CIRCLE};
	
	final Shape shape;
	
	// 다음 필드들은 모양이 사각형일 때만 쓰인다.
	double length;
	double width;
	// 다음 필드는 모양ㅇ이 원일 때만 씅니다.
	double radius;
	
	// 원용 생성자
	Figure(double radius) {
		shape = Shape.CIRCLE;
		this.radius = radius;
	}
	
	Figure(double length, double width) {
		shape = Shape.RECTANGLE;
		this.length = length;
		this.width = width;
	}
	
	double area() {
		switch(shape) {
		case RECTANGLE :
			return length * width;
		case CIRCLE : 
			return Math.PI * (radius * radius);
		default :
			throw new AssertionError();
		}
		
	}
}
