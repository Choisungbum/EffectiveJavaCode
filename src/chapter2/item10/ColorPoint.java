package chapter2.item10;

import java.util.Objects;

public class ColorPoint extends Point{
	private final Color color;
	//case 4
	private final Point point;
	
	public ColorPoint(int x, int y, Color color) {
		super(x, y);
		point = new Point(x,y);
		this.color = Objects.requireNonNull(color);
		// TODO Auto-generated constructor stub
	}
	
	// case 4
	// equals 규약을 지키면서 값 추가하기
	// ColorPoint의 point뷰를 반환한다.
	public Point asPoint() {
		return point;
	}
	
	@Override
	public boolean equals(Object o) {
		if(!(o instanceof ColorPoint)) {
			return false;
		}
		ColorPoint cp = (ColorPoint) o;
		return cp.point.equals(point) && cp.color.equals(color);
	}
	

//	public ColorPoint(int x, int y, Color color) {
//		super(x, y);
//		this.color = Objects.requireNonNull(color);
//		// TODO Auto-generated constructor stub
//	}
	
	// case 1
	// 대칭성위배 - 일반 Point를 ColorPoint와 비교한 결과와 그 둘을 바꿔 비교한 결과가 다를 수 있다
	// Point는 color이 없고 ColorPoint는 입력배개변수의 클래스 종류가 다르다며 매번 false를 반환 할 것이다
//	@Override
//	public boolean equals(Object o) {
//		if(!(o instanceof ColorPoint)) {
//			return false;
//		}
//		return super.equals(o) && ((ColorPoint) o).color == color;
//	}
	// case 2
	// 추이성위배
//	@Override
//	public boolean equals(Object o) {
//		if(!(o instanceof Point)) { // Point, ColorPoint 둘다 true
//			return false;
//		}
//		
//		// o가 일반 Point이면 생상을 무시하고 비교한다.
//		if (!(o instanceof ColorPoint)) { //  ColorPoint true
//			return o.equals(this);
//		}
//		
//		// o가 ColorPoint이면 색상까지 비교한다.
//		return super.equals(o) && ((ColorPoint) o).color == color;
//		
//	}
	
	// case 3
	// 리스코프 치화원칙 위배 - 리스코프치환원칙 : 어떤 타입에 있어 중요한 속성이라면 그 하위 타입에서도 마찬가지로 중요하다.
//	@Override
//	public boolean equals(Object o) {
//		System.out.println(" o.getClass() : " +  o.getClass());
//		System.out.println(" getClass() : " +  getClass());
//		Class cls = getClass();
//		System.out.println(" cls.getName() : " +  cls.getName());
//		if (o == null || o.getClass() != getClass()) {
//			return false;
//		}
//		Point p = (Point) o;
//		return p.x == x && p.y == y;
//	}


	public static void main(String[] args) {
		Point p = new Point(1,2);
		ColorPoint cp1 = new ColorPoint(1,2,Color.RED);
		ColorPoint cp2 = new ColorPoint(1,2,Color.BLUE);
		
		// case 1
//		System.out.println("p.equals(cp1) :" + p.equals(cp1));
//		System.out.println("cp1.equals(p) :" + cp1.equals(p));
	
		// case 2
//		System.out.println("p.equals(cp1) :" + p.equals(cp1));
//		System.out.println("cp1.equals(cp2) :" + cp1.equals(cp2));
//		System.out.println("p.equals(cp2) :" + p.equals(cp2));
		
		// case 3
		System.out.println("p.equals(cp1) :" + p.equals(cp1));
		System.out.println("cp1.equals(p) :" + cp1.equals(p));
	}
	
}
