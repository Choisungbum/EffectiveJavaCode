package chapter1.item3;

// 싱글턴생성방법 
// 공통 -> 생성자는 "private"
//
public class Elvis {
	// 1 번째 방법 -> 해당클래스가 싱글턴임이 API에 잘드러난다
//	public static final Elvis INSTANSE = new Elvis();
//	private Elvis() {
//	}
	
	// 2번째 방법  -> 마음이 바뀌면 API를 바꾸지 않아도 싱글턴이아니게 만들 수 있다.
	// 			-> 원한다면 정적팩터리를 제네릭 싱글턴 팩터리로 만들 수 있다.
	private static final Elvis INSTANCE = new Elvis();
	private Elvis() {};
	
	public static Elvis getInstance() {
		return INSTANCE;
	}
	
	// 가짜 Elvis 인스턴스를 막는 방법, 싱글턴임을 보장
	private Object readResolve() {
		// 진짜 Elvis를 반환하고 가짜Elvis는 가비지컬렉터에 맡긴다.
		return INSTANCE;
	}
	
//	// 3번째 방법 -> 최고의방법
//	public enum Elvis {
//		INSTANCE;
//		
//	}
	
}
