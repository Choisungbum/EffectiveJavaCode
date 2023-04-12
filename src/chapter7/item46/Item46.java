package chapter7.item46;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap; 
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

import chapter7.item42.Operation;

import java.util.stream.Collector;
import java.util.stream.Collectors;

/*
 * 
 * item 46 스르팀에서는 부작용 없는 함수를 사용하라
 * 
 * 스트림 -> 함수형 프로그래밍에 기초한 패러다임
 * 스트림패러다임의 핵심
 * -> 일련의 변환으로 재구성하는 부분
 *    각 변환단계는 가능한 한 이전 단계의 결과를 받아 처리하는 순수 함수여야 한다.    
 *    이렇게 하려면 스트림 연산에 건네는 함수 객체는 모두 부작용이 없어야 한다.
 *    순수함수 - 오직 입력만이 결과에 영향을 주는 함수, 다른 가변 상태를 참조하지 않고, 함수 스스로도 다른 상태를 변경하지 않음
 * 
 * 수집기
 * -> 스트림을 사용하려면 꼭 알아야하는 개념
 *    java.util.stream.Collectors 클래스 메서드(39개)
 *    축소전략을 캡슐화한 블랙박스 객체라고 생각하자 (축소 - 스트림의 원소들을 객체 하나에 취합한다는 뜻)
 *    수집기가 생성하는 객체는 일반적으로 컬렉션이며 그래서 'collector'라는 이름을 쓴다.
 *    수집기를 사용하면 스트림의 원소를 손쉽게 컬렉션으로 모을 수 있다.
 * 
 * 수집기 종류 
 * 1. toList()
 * 2. toSet()
 * 3. toCollection(collectionFactory)
 * 4. toMap()...?
 * 
 * https://velog.io/@sa833591/Java-Stream-4 여기에 잘 정리되어 있음 
 * 
 * 여러 다운스트림 수집기가 존재한다.
 *summing, averaging, summarizing 으로 시작하는 이름에 int double long 스트림용으로 하나씩 존재한다.
 * 
 * */
public class Item46{ 
	public static void main(String[] args) {
		Map<String, Long> freq = new HashMap<>();
		Readable file = null;
		// 1. 스트림 패러다임을 이해하지 못한채 API 만 사용
		// 스트림API 이점을 살리지 못해 읽기 어렵고, 유지보수가 힘들다.
		// forEach면산은 스트림 계산 결과를 보고할 때만 사용하고, 계산하는 데는 쓰지 말자
		try(Stream<String> words = new Scanner(file).tokens()) {
			words.forEach(word -> {freq.merge(word.toLowerCase(), 1L, Long::sum);
			});
		}
		
		// 2. 스트림 API를 제대로 사용하고 짧고, 명확하다.
		// Scanner의 스트림에서드 -> tokens()
//		try(Stream<String> words = new Scanner(file).tokens()){
//			freq = words.collect(Collectors.groupingBy(String::toLowerCase, Collectors.counting()));
//		}
		
		// 수집기 표현 
		List<String> topTen = freq.keySet().stream().sorted(Comparator.comparing(freq::get).reversed())
								.limit(10)
								.collect(Collectors.toList());
		
		
		/// 맵수집기
		// toMap(?,?) 수집기를 사용하여 문자열을 열거 타입 상수에 매핑한다.
		// toMap(keyMapper, valueMapper) 스트림 원소를 키에 매핑하는 함수와 캆에 매핑하는 함수를 인수로 받는다
		// 스트림의 각 원소가 고유한 키에 매핑되어 있을 때 적합 
		// 스트림 원소 다수가 같은 키를 사용한다면 파이프라인이 IlleagalStateException을 던지며 종료한다.
		// 더 복잡한 형태의 toMap이나 groupingBy는 이런 충돌을 다루는 다양한 전략을 제공한다.
		// toMap에 키 매퍼와 값 매퍼는 병합함수까지 제공하는데 병합함수는 BinaryOperator<U> 이며 U는 맵의 값 타입이다
		
//		final Map<String, Operation> StringToEnum = 
//				Stream.of(value()).collect(Collectors.toMap(Object::toString, e -> e));
		
		// toMap(?,?,?) 형태는 어떤 키와 그 키에 연관된 원소들 중 하나를 골라 연관짓는 맵을 만들 때 유용하다
		// 비교자로 BinaryOperation에서 정적임포트한 maxBy라는 정적 팩터리 메서드를 사용했다.
		 
//		Map<Artist, Album> topHits = albums.collect(
//				toMap(Album::artist, a -> a, maxBy(comparing(Album::sales))));
		
		// toMap(?,?,?)은 충돌이 나면 마지막 값을 취하는 수집기를 만들 때도 유용하다.
		// 마지막에 쓴 값을 취하는 수집기 
		// toMap(keyValue, valueMapper, (oldVal, newVal) -> newVal)
	
		// toMap(?,?,?,?) -> 마지막 인수로 맵 팩터리를 받는다.
		// EnumMap, TreeMap처럼 원하는 특정 맵 구현체를 직접 지정할 수 있다.
		
		
		// groupingBy - 이 메서드는 입력으로 분류 함수를 받고 출력으로는 원소들을 카테고리별로 모아 놓은 맵을 담은 수집기를 반환한다.
		// groupingBy(stream. downStream)가 반환하는 수집기각 리스트 외의 값을 갖는 맵을 생성하게 하려면 분류함수와 함께 다운스트름수집기도 명시해야 한다.
//		Stream<String> words;
//		freq = words.collect(groupingBy(String::toLowerCase, Collectors.counting()));
		
		// groupingBy(stream, mapFactory, downStream) 인수가 3개일 경우  값이 TreeSet인 TreeMap을 반환하는 수집기를 만들 수 있다.
		
		// minBy, maxBy는 인수로 받은 비교자를 이용해 스트림에서 값이 가장 작은 혹은 가장 큰 원소를 찾아 반환한다.
		// joining는  CharSequence에만 적용할 수 있다.
		// 매개변수가 없는 joining은 단순히 원소들을 연결하는 수집기르 반환하며, 인수가 하나가 있으면 타입의 구분자를 매개변수로 맏는다.
		
		
	}

}
