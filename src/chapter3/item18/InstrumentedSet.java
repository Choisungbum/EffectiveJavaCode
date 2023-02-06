package chapter3.item18;

import java.time.Instant;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
/*
 * item 18 상속보다는 컴포지션을 사용하라
 * 
 * */
public class InstrumentedSet<E> extends ForwardingSet<E>{
	private int addCount = 0;
	
	public InstrumentedSet(Set<E> s) {
		super(s);
	}
	
	@Override
	public boolean add(E e) {
		addCount++;
		return super.add(e);
	}
	
	@Override
	public boolean addAll(Collection<? extends E> c) {
		addCount++;
		return super.addAll(c);
	}
	
	public int getAddCount() {
		return addCount;
	}
	public static void main(String[] args) {
		//private static final INIT_CAPACITY = 10;
		Set<Instant> times = new InstrumentedSet<>(new TreeSet<>());
		//Set<E> s = new InstrumentedSet<>(new HashSet<>(INIT_CAPACITY));
		
	}
}
