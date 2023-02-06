package chapter3.item18;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
/*
 * item 18 상속보다는 컴포지션을 사용하라
 * 
 * */
public class IntrumentedHashSet<E> extends HashSet<E>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8396792795879004233L;
	// 추가된 원소의 수
	private int addCount = 0;
	
	public IntrumentedHashSet() {
	}
	
	public IntrumentedHashSet(int initCap, float loadFactor) {
		super(initCap, loadFactor);
	}
	
	@Override
	public boolean add(E e) {
		addCount++;
		return super.add(e);
	}
	
	@Override
	public boolean addAll(Collection<? extends E> c) {
		addCount += c.size();
		return super.addAll(c);
	}
	
	public int getAddCount() {
		return addCount;
	}
	
	public static void main(String[] args) {
		IntrumentedHashSet<String> s = new IntrumentedHashSet<String>();
		s.addAll(List.of("틱","틱틱", "펑"));
		
		System.out.println(s.getAddCount());
	}
}
