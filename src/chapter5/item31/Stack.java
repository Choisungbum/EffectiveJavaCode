package chapter5.item31;

import java.util.Arrays;
import java.util.Collection;
import java.util.EmptyStackException;
import java.util.List;

/*
 * item 31 한정적 와일드카드를 사용해 API 유연성을 높이라
 * 
 * 매개변수화 타입은 불공변이다(ex) List<E>)
 * 즉 String 이 Object의 하위타입이라 해도 
 * List<String> 은 List<Object>의 하위타입이 아니다.
 * 
 * 유연성을 극대화하려면 원소의 생산자나 소비자용 입력 매개변수에 와일드카드 타입을 사용하라!
 * 입력매개변수가 생상자와 소비자 역할을 동시에 한다면 와일드 카드 타입을 써도 좋을 게 없다.
 * 
 * - 생산자 : 입력 매개변수를 통해 원소를 컬렉션에 옮기는 것
 * - 소비자 : 컬렉션 인스턴스의 원소를 입력매개변수로 옮기는 것 
 * 
 *  PECS : producer-extends, consumer-super
 *  : 매개변수화 타입 T 가 생산자라면 <? extends T> 소비자라면 <? super T>
 * 
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
	
	/*
	 * Stack<Number> numberStack = new Stack<Number>
	 * Iterable<Integer> integers = ...
	 * numberStack.pushAll(integers)
	 * 
	 * IntegerNumber 클래스의 하위타입이니 잘 동작할 것 같지만
	 * 매개변수화 타입은 불공변이므로 해당 구문은 실행되지 않는다.
	 * 
	 * 위 구문이 실행되려면 '한정적 와일드카드타입' 이라는 특별한 매개변수화 타입을 사용해야한다.
	 * */
	public void pushAll(Iterable<? extends E> src) {
		for(E e : src) {
			push(e);
		}
	}
	
	/*
	 * Stack<Number> numberStack = new Stack<Number>
	 * Collection<Object> objects = ..
	 * numberStack.popAll(objects) 
	 * 
	 *  popAll()의 입력 매개변수의 타입이 'E의 Collection'이 아니라 'E의 상위타입의 Collection' 이어야 한다
	 * 
	 * 
	 * */
	public void popAll(Collection<? super E> dst) {
		while(!isEmpty()) {
			dst.add(pop());
		}
		
	}
	
	/*
	 * public static <E> void swap_1(List<E> list, int i, int j)
	 * public static void swap_2(List<?> list, int i, int j)
	 * 
	 * 다음 두 메서드중 어느것을 사용해야 하는가?
	 * : 기본원칙은 메서드 선언에 타입 매개변수가 한 번만 나오면 와일드 카드로 대체해라
	 * 
	 * 하지만 두 번째 메서드를 사용하면 컴파일에서 문제가 발생한다 - List<?>에는 null외에 어떤 값도 넣을 수 없기 때문에 
	 * -> private 도우미 메서드를 사용하면 해결가능 - 더 복잡하지만 외부에서는 와일드카드 기반의 선언을 유지할 수 있음 
	 * 
	 * 
	 * */
	public static <E> void swap_1(List<E> list, int i, int j) {
		swap_2(list, i, j);
	}
	private static void swap_2(List<?> list, int i, int j) {
		
	}
	
	public static void main(String[] args) {
		
	}
 }
