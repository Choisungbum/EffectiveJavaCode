package chapter5.item29;

import java.util.Arrays;
import java.util.EmptyStackException;

/*
 * item 29 이왕이면 제네릭 타입으로 만들라 
 * 
 * 제네릭 타입 안에서 리스트르르 사용하는 게 항상 가능하지도, 꼭 더 좋은것도 아니다 .
 * 자바가 리스트를 기본타입으로 제공하지 않기때문 
 * 
 * 클라이언트에서ㅜ 직접 형변환 해야 하는 타입보다 테네릭 타입이 더 안전하고 쓰기편하다.
 * */
public class Stack<E> {
	// 1 타입 매개변수를 추가 E
	// Object를 타입매개변수로 바꾸기
	private E[] elements;
	private int size = 0;
	private static final int DEFAULT_INITIAL_CAPACITY = 16;
	
	// 2 비검사경고를 적절히 숨긴다 
	@SuppressWarnings("unchecked")
	public Stack() {
		// E는 실체화 불가타입 
		// 해결방법 -> Object배열을 생성한 다음 제네릭으로 형변환
		elements = (E[]) new Object[DEFAULT_INITIAL_CAPACITY];
	}
	
	public void push(E e) {
		ensureCapacity();
		elements[size++] = e;
	}
	
	public E pop() {
		if(size == 0) {
			throw new EmptyStackException();
		}
		E result = elements[--size];
		elements[size] = null;
		return result;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	private void ensureCapacity() {
		if (elements.length == size) {
			elements = Arrays.copyOf(elements, 2 * size + 1);
		}
	}
 }
