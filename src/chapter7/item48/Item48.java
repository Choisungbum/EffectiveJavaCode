package chapter7.item48;

import java.math.BigInteger;
import java.util.stream.Stream;

/*
 * item 48 스트림 병렬화는 주의해서 적용하라 
 * 
 * 자바 8부터 parallel 메서드만 한 번 호출하면 파이프라인을 병렬 실행할 수 있는 스트림 지원!!
 * 
 * 스트림 파이프라인을 마구잡이로 병렬화해서는 안 된다. 성능이 끔찍하게 나빠지기 때문이다.
 * 대체로 스트림의 소스가 ArrayList, HashMap, HashSet, ConcurrentHashMap의 인스턴스이거나
 * 배열, int 범위, long 범위일 떄 병렬화의 효과가 가장 좋다.
 * -> 1. 이 자료구조들은 모두 데이터를 원하는 크기로 정확하고 손쉽게 나눌 수 있어서 일을 다수의 스레드에 분배하기에 좋다는 특징이 있다.
 *       나누는 작업은 Spliterator가 담당하며, 해당 객체는 Stream이나 Iterable의 spliterator 메서드로 얻어올 수 있다.
 *    2. 이 자료구조들의 또 다른 중요한 공통점은 원소들을 순차적으로 실핼할 때의 참조지역성(locally of reference)이 뛰어나다는 것이다.
 *       이웃한 원소의 참조들이 메모리에 연속해서 저장되어 있다는 뜻이다
 * 		 (참조지역성(locally of reference) -> 다량의 데이터를 처리하는 벌크 연산을 병렬화 할 때 아주 중요한 요소로 작용)
 * 
 * 스트림 파이프라인의 종단 연산의 동작 방식 또한 병렬 수행 효율에 영향을 준다
 * -> 종단연산에서 수행하는 작업량이 파이프라인 전체 작업에서 상당 비중을 차지하면서 순차적인 연산이라면 파이프라인 병렬 수행의 효과는 제한될 수밖에 없다.
 *    종단 연산 중 병렬화에 가장 적합한 것은 축소이다. 축소는 파이프라인에서 만들어진 모든 원소를 하나로 합치는 작업으로 Stream의 reduce 메서드 중 하나, 
 *    혹은 min, max, count, sum같이 완성된 형태로 제공되는 메서드 중 하나를 선택해 수행한다.
 * 	  anyMatch, allMatch, noneMatch처럼 조건에 맞으면 바로 반환되는 메서드도 병렬화에 적합하다.
 * 
 *    반면 가변축소를 수행하는 Stream의 collect 메서드는 병렬화에 적합하지 않다.
 *    
 * 직접 구현한 Stream, Iterable, Collection이 병렬화의 이점을 제대로 누리게 하고싶다면 
 * spliterator메서드를 반드시 재정의하고 결과 스트림의 병렬화 성능을 강도 높게 테스트해야한다.
 * 
 * */
public class Item48 {
	public static void main(String[] args) {
		
		// parallel() 호출하면 성능이 빨라질까 ??
		// 아래 소스에서는 성능이 최악이 되어버린다
		// 원인은 스트림 라이브러리가 이 파이프라인을 병렬화하는 방법을 찾아내지 못했기 때문이다.
		// -> 데이터 소스가 Stream.iterate거나 중간연산으로 limit를 쓰면 파이프라인 병렬화로는 성능 개선을 기대할 수 없다.
		primes().map(p-> BigInteger.TWO.pow(p.intValueExact()).subtract(BigInteger.ONE))
		.filter(mersenne -> mersenne.isProbablePrime(50))
		.limit(20)
		.forEach(System.out::println);
		
	}
	
	static Stream<BigInteger> primes() {
		return Stream.iterate(BigInteger.TWO, BigInteger::nextProbablePrime);
	}
}
