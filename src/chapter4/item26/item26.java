package chapter4.item26;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/*
 * item 26 로 타입은 사용하지 말라 
 * 
 * 1. List 와 List<Object> 의 차이 
 * -> List는 제네릭타입에서 완전히 발을 뺀것이고 List<Object>는 모든 타입을 허용한다는 의사를 컴파일러에 명확히 전달 
 * 
 * 2. 로타입을 사용할 경우 (예외적상황)
 * - class 리터럴에는 로 타입을 써야한다.
 *   ex) 되는것  : List.class, String[].class, int.class
 *       안되는것 : List<?>.class 등 
 * 
 * - instanceof 연산자
 *   런타임시 제네릭 타입정보가 지워지므로 instanceof 연산자는 비한정적 와일드 카드 타입이외의
 *   매개변수화 타입에는 적용할 수 없다. 그리고 로 타입이든 비한정ㅈ거 와일드 카드 타입이든 instanceof는
 *   완전히 똑같이 동작하기 때문
 *   ex)
 *   	 if (o instanceof Set) {
 *   			Set<?> s = (Set<?>) o;
 *   		}       
 * 
 * */
public class item26 {

	public static void main(String[] args) {
		List<String> strings = new ArrayList<>();
		//unsafeAdd(strings, Integer.valueOf(42));
		String s = strings.get(0); // 컴파일러가 자동으로 형변환 코드를 넣어준다 
	}
	// case 1 : unsafeAdd 메서드가 로타입을 사용 
	/*
	private static void unsafeAdd(List list, Object o) {
		list.add(o);
	}
	*/
	private static void unsafeAdd(List<Object> list, Object o) {
		list.add(o);
	}
	
	// case 2 : 모르는 타입의 원소도 받는 로타입을 사용했다.
	/*
	static int numElementsInCommon(Set s1, Set s2) {
		int result = 0;
		for (Object o1 : s1) {
			if (s2.contains(o1)) {
				result++;
			}
		}
		return result;
	}
	*/
	// 비한정적 와일드 카드를 사용해서 타입매개변수가 무엇인지 신경쓰지 않을 수 있음 
	static int numElementsInCommon(Set<?> s1, Set<?> s2) {
		int result = 0;
		for (Object o1 : s1) {
			if (s2.contains(o1)) {
				result++;
			}
		}
		return result;
	}
}
