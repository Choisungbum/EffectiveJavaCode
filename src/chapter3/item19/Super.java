package chapter3.item19;

/*
 * item19 - 상속을 고려해 설계하고 문서화하라. 그러지 않았다면 상속을 금지하라
 * 
 * */

public class Super {
	// 잘못된 예 - 생성자가 재정의가능한 메서드를 호출한다.
	public Super() {
		overrideMe();
	}
	
	public void overrideMe() 
	{}
}
