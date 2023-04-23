package chapter1.item7;

import java.util.Arrays;
import java.util.EmptyStackException;

public class Stack implements Cloneable {
	private Object[] elements;
	private int size = 0;
	private static final int DEFAULT_INITIAL_CAPACITY = 16; 

	public Stack() {
		elements = new Object[DEFAULT_INITIAL_CAPACITY];
	}
	
	public void push(Object e) {
		ensureCapacity();
		elements[size++] = e;
	}
	
	public Object pull() {
		if(size == 0)
			throw new EmptyStackException();
		// 메모리 누수 발생 !!
		// 다 쓴 참조를 여전히 가지고 있기 때문 -> 해당 참조를 다 썻을 경우 null로 참조해제
		// 하지만 위 경우는 예외적이어야 한다.
	    // 자기메모리를 직접 관리하는 경우 항상 메모리 누수를 신경쓰자
		//return elements[--size];
		Object result = elements[--size];
		elements[size] = null;
		return result;
	}
	
	// 사이즈확인후 사이즈가 0이거나 해당 배열이 사이즈 크기가 되었을 경우 사이즈를 늘려준다.
	private void ensureCapacity() {
		if(elements.length == size) {
			elements = Arrays.copyOf(elements, 2 * size + 1);
		}
	}
	
	/*
	 * item 13
	 * 
	 * clone 재정의는 주의해서 진행하라
	 */
	// 가변상태를 참조하는 클래스용 clone 메서드
	@Override
	public Stack clone() {
		try {
			Stack result = (Stack) super.clone();
			result.elements = elements.clone();
			return result;
		} catch (CloneNotSupportedException e){
			throw new AssertionError();
		}
	}
}
