package chapter5.item28;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/*
 *item 28 배열보다는 리스트를 사용하라 
 *
 * 배열과 리스트 차이
 * 1. 배열은 공변이지만 제네익은 불공변이다.
 * 2. 배열은 런타임시 자신이 담기로 한 원소의 타입을 인지하고 확인하지만(실체화) 
 *    제네릭은 원소타입을 컴파일타임에만 검사하면 런타임에는 알 수조차 없다.(소거) 
 * 
 * 제네릭을 배열로 만들지 못하게 하는 이유 -> 안전하지 않아서 
 * 
 * E, List<E>, List<String> 실체화 불가 타입이지만
 * List<?>, Map<?,?> 같은 비한정적 와일드카드타입은 실체화가능하다 
 * 
 * */
// 클래스를 제네릭타입으로 변경하자  
public class Chooser<T> {
	// 1 제네릭을 만들기 위해선 Object[]를 T[]로
//	/private  final T[] choiceArray;
	// 2 리스트기반으로 타입안정성 확보
	private final List<T> choiceList;
	
	// 비검사 경고 제거 및 왜 안전한지를 주석으로 적어주기
	//@SuppressWarnings("unchecked")
	public Chooser (Collection<T> choices) {
		//1 choiceArray = (T[])choices.toArray();
		choiceList = new ArrayList<T>(choices);
	}
	
	public T choose() {
		Random rnd = ThreadLocalRandom.current();
		// 1 return choiceArray[rnd.nextInt(choiceArray.length)];
		// 2  
		return choiceList.get(rnd.nextInt(choiceList.size()));
	}
	public static void main(String[] args) {
		
	}
}
