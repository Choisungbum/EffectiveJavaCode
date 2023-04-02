package chapter5.item30;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.function.UnaryOperator;

/*
 * item 30 이왕이면 제네릭 메서드로 만들라
 * 
 * 불변객체를 여러타입으로 활용할 수 있도록 만들때?
 * - 제네릭은 런타임에 타입 정보가 소가되므로 하나의 객체를 어떤타입으로든 매개변수화 할 수 있다 > '제네릭 싱글턴 팩터리' 사용 
 * 
 * 자기 자신이 들어간 표현식을 사용하여 타입 매개변수의 허용 범위를 한정할 수 있다. > '재귀적 타입 한정' 사용
 * 
 * 컬렉션 클래스와 같이 배열 기반으로 되어 있는 구조에는 E가 어울리고, 그 외에는 T 가 어울린다
 * */
	
public class SetUnion {
	// 타입 매개변수 목록은 메서드의 제한자와 반환타입 사이에 온다 
	public static <E> Set<E> union(Set<E> s1, Set<E> s2) {
		Set<E> result = new HashSet(s1);
		result .addAll(s2);
		return result;
	}
	
	// 제네릭 싱글턴 패턴 //////
	private static UnaryOperator<Object> IDENTITY_FN = (t) -> t;
	
	@SuppressWarnings("unchecked")
	public static <T> UnaryOperator<T> identityFunction() {
		return (UnaryOperator<T>) IDENTITY_FN;
	}
	///////////////////////
	
	// 재귀적 타입 한정을 이용해 상호 비교할 수 있음 ////////////
	// <E extends Comparable<E>>는 "모든 타입 E는 자신과 비교할 수 있다"라고 읽을 수 있음
	public static <E extends Comparable<E>> E max(Collection<E> c) {
		if (c.isEmpty()) {
			throw new IllegalArgumentException("컬렉션이 비어 있습니다.");
		}
		
		E result = null;
		for (E e : c) {
			if(result == null || e.compareTo(result) > 0) {
				result = Objects.requireNonNull(e);
			}
		}
		return result;
	}
	////////////////////////////////////////////////
	
	public static void main(String[] args) {
		Set<String> guys = Set.of("톰", "메딕");
		Set<String> stooges = Set.of("래리","모에");
		Set<String> aflCio = union(guys, stooges);
		System.out.println(aflCio);
	}

}
