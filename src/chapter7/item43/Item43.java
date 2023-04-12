package chapter7.item43;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/*
 * item 43 람다보다는 메서드 참조를 사용하라
 * 
 * 간결함 순서
 * 익명클래스 -> 람다식 -> 메서드 참조 
 * 
 * 사용할 수 있는 범위
 * 익명클래스 -> 람다식 -> 메서드 참조
 * 
 * 람다 : 함수형인터페이스 일 겅우에만 사용
 * 메서드참조 : 람다로 할 수 없는 일이면 사용x
 * 
 * 
 * 메서드 참조 유형 5가지
 * 1. 정적 : 정적메서드를 가리키는 메서드 참조
 * 예  - Integer::parseInt
 * 람다 -  str -> Integer.parseInt(str)
 * 
 * 2. 한정적(인스턴스) : 수신객체를 특정하는 한정적 인스턴스 메서드 참조
 * 예  - instant.now()::isAfter
 * 람다 - Instant then = Instant.now();
 *       t -> then.isAfter(t)
 *       
 * 3. 비한정적(인스턴스) : 수신객체를 특정하지 않는 비한정적 인스턴스 메서드 참조
 * 예  - String::toLowerCase
 * 람다 - str -> str.toLowerCase()
 * 
 * 4. 클래스 생성자 : 클래스 생성자를 가리키는 메서드 참조
 * 예  - TreeMap<K,V>::new
 * 람다 - () -> new TreeMap<K,V>
 * 
 * 
 * 5. 배열 생성자 : 배열생성자를 가리키는 메서드 참조
 * 예  - int[]::new
 * 람다 - len -> new int[len]
 * 
 * 핵심!!
 * 메서드 참조쪽이 짧다면 메서드참조를 쓰고 그렇지 않다면 람다를 사용하라 
 * 
 * */
public class Item43 {
	
//	public int[] test (int len) {
//		return len -> new int[len]; 
//	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map m = new HashMap();
		int key = 0;
		int value = 0;
		
		
		
		// 1.람다 
		//m.merge(key, 1, (count, incr) -> count + incr);
		// 2.메서드참조 
		//m.merge(key, 1, Integer::sum);
		
		//Map map = TreeMap<K,V>::new;
		
	}

}
