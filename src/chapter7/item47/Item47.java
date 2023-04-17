package chapter7.item47;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/*
 * item 47 반환 타입으로는 스트림보다 컬렉션이 낫다 
 * 
 * 원소 시퀀스를 반환할 때는 당연히 스트림을 사용해야하지만, 스트림은 반복을(Iterable) 지원하지 않는다.
 * 따라서 스트림과 반복을 알맞게 조합해야 좋은 코드가 나온다.
 * 
 * Stream 인터페이스는 Iterable 인터페이스가 정의한 추상 메서드를 전부 포함할 뿐만 아니라 , Iterable 인터페이스가 정의한 방식대로 동작한다.
 * 그럼에도 for-each로 반복할 수 없는 까닭은 바로 Stream이 Iterable을 확장하지 않아서이다!!
 * 
 * 스트림을 반복하기 위한 우회방법
 * 1. 최악의 방법
 * for(ProcessHandle ph : (Iterable<ProcessHandle>) ProcessHandle.allProcesses()::iterable)
 * -> stream의 Iterator 메서드에 메서드 참조를 건네고, 매개변수화된 Iterable로 적절히 형변환 한다.
 * 
 * 2. 어댑터사용 
 * public static <E> Iterable<E> iterableOf(Stream<E> stream) {
 * 	return stream::iterator;
 * }
 * 
 * 객체 시퀀스를 반환하는 메서드를 작성하는데, 이 메서드가 스트림파이프라인에서만 쓰일걸 안다면 스트림반환
 * 반대로 반환된 객체들이 반복문에서만 쓰일 걸 안다면 Iterable을 반환
 * 
 * 공개 API 작성시 위 모두를 배려해야한다.
 * -> 원소 시퀀스를 반환하는 공개 API의 반환 타입에는 Collection이나 그 하위 타입을 쓰는게 일반적을 최선이다
 * 	  Collection 인터페이스는 Iterable의 하위 타입이고 stream 메서드도 제공하니 반복과 스트림을 동시에 지원하기 때문 !!
 *    Arrays 역시 Arrays.asList와 Stream.of 메서드로 손쉽게 반복과 스트림을 지원할 수 있다.
 *    
 * 반환하는 시퀀스의 크기가 메모리에 올려도 안전할 만큼 작다면 ArrayList나 HashSet같은 표준 컬렉션 구현체를 반환하는게 최선이다\
 * 하지만 단지 컬렉션을 반환한다는 이유로 덩치 큰 시퀀스를 메모리에 올려서는 안된다.
 * -> 표현을 간결할게 할 수 있다면 전용컬렉션을 구현하는 방안을 검토해보자.
 *    AbstractList를 이용하면 훌륭하게 구현할 수 있다. (*1)
 * 
 *
 * AbstractCollection을 활용해서Collection 구현체를 작성할 때는 Iterable용 메서드 외에 2개만 더 구현하면 된다.
 * 바로 contains와 size이다. contains와 size 구현하는게 불가능 할 때는 걸렉션보다는 스트림이나 Iterable을 반환하는 편이 낫다.
 * 
 * 
 * 입력리스트의 부분리스트를 반환하는 메서드를 작성한다고 할 떄, 앞에서처럼 전용 컬렉션을 구현하기는 지루하다.
 * 하지만 입력리스트의 모든 부분리스트를 스트림으로 구현하기는 어렵지 않다. (*2)
 * 
 * 
 * */
public class Item47 {
	// *1 AbstractList를 이용
	public static final <E> Collection<Set<E>> of (Set<E> s){
		List<E> src = new ArrayList<>(s);
		if (src.size() > 30) {
			throw new IllegalArgumentException("원소가 많습니다.");
		}
		return new AbstractList<Set<E>>() {

			@Override
			public Set<E> get(int index) {
				Set<E> result = new HashSet();
				for(int i = 0; index != 0; i++, index >>= 1) {
					if ((index & 1) == 1) {
						result.add(src.get(i));
					}
				}
				return result;
			}
			
			public boolean contains(Object o) {
				return o instanceof Set && src.containsAll(src);
			}

			@Override
			public int size() {
				// 멱집합의 크기는 2를 원래 집합의 원소 수만큼 거듭제곱 한 것과 같다.
				return 1 << src.size();
			}
			
		};
	}
	
	// *2
	// Stream.concat메서드는 반환되는 스트림에 빈 리스트를 추가하며, flatMap메서드는 모든 프리픽스의 모든 서픽스로 구선된 하나의 스트림을 만든다
	public static <E> Stream<List<E>> of(List<E> list) {
		return Stream.concat(Stream.of(Collections.emptyList()), prefixes(list).flatMap(Item47::suffixes));
	}

	private static <E> Stream<List<E>> prefixes(List<E> list) {
		return IntStream.rangeClosed(1, list.size()).mapToObj(end -> list.subList(0, end));
	}
	
	private static <E> Stream<List<E>> suffixes(List<E> list) {
		return IntStream.range(1, list.size()).mapToObj(start -> list.subList(start, list.size()));
	}
	// 위와 같은 소스
//	for(int start = 0; strat < src.size(); strat++) {
//		for (int end = start + 1; end <= src.size(); end++) {
//			System.out.println(src.subList(start, end));
//		}
//	}
	
	
	
}
