package chapter1.item4;

public class UtilityClass {
	//기본생성자가 만들어지는 것을 막는다
	private UtilityClass() {
		// 클래스에서 실수로 생성자를 호출하지 않도록 해준다
		throw new AssertionError(); 
	};
	
}
