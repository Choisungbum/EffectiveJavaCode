package chapter2.item13;

import java.security.KeyManagementException;

/*
 * item 13
 * 
 * clone 재정의는 주의해서 진행하라
 */

public class HashTable implements Cloneable {
	private Entry[] buckets;
	
	private static class Entry {
		final Object key;
		Object value;
		Entry next;
		
		Entry(Object key, Object value, Entry next) {
			this.key = key;
			this.value = value;
			this.next = next;
		}
		
		// case 2 복잡한 가변상태를 갖는 클래스용 재귀적 clone 메서드
		// 이 엔트리가 가리키는 연결 리스트를 재귀적으로 복사
		Entry deepCopy() {
			//return new Entry(key, value, next == null ? null : next.deepCopy());
		// 엔트리 자신이 가리키는 연결 리스트를 반복적으로 복사한다.
			Entry result = new Entry(key, value, next);
			for(Entry p = result; p.next != null; p = p.next) {
				p.next = new Entry(p.next.key, p.next.value, p.next.next);
			}
			return result;
		}
	}
	
	// case 1 잘못된 clone메서드 - 가변상태를 공유한다.
//	@Override
//	public HashTable clone() {
//		try {
//			HashTable result = (HashTable) super.clone();
//			result.buckets = buckets.clone();
//			return result;
//		} catch (CloneNotSupportedException e){
//			throw new AssertionError();
//		}
//	}
	
	// case 2 복잡한 가변상태를 갖는 클래스용 재귀적 clone 메서드
	@Override
	public HashTable clone() {
		try {
			HashTable result = (HashTable) super.clone();
			result.buckets = new Entry[buckets.length];
			for (int i = 0; i < buckets.length; i++) {
				if(buckets[i] != null) {
					result.buckets[i] = buckets[i].deepCopy();
				}
			}
			return result;
		} catch (CloneNotSupportedException e){
			throw new AssertionError();
		}
	}
	
	
	
}
