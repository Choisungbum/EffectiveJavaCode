package chapter3.item20;

import java.util.Map;
import java.util.Objects;
import java.util.Map.Entry;
/*
 * item 20 추상 클래스보다는 인터페이스를 우선하라
 * 
 * item 18의 컴포지션과 비슷한 느낌 
 *  컴포지션에서의 전달 클래스  - 기존 클래스를 확장하는 대신, 새로운 클래스를 만들고 private 필드로 기존 클래스의 인스턴스를 참조하게함(컴포지션)
 *  					  새 클래스의 인스턴스메서드들은 기존 클래스의 대응하는 메서드를 호출해 그 결과를 반환(전달)
 *  추상 골격 구현 클래스 - 인터페이스로 타입을 정의, 디폴트 메서드를 제공하고 인터페이스의 나머지 메서드(기반메서드)를 구현함
 *  
 *    -> 비슷하다고 느낀부분 : 필요한 것을 미리 클래스로 만들어 둬서 그것을 재사용 할 수 있게하는 부분
 * 
 * */
public abstract class AbstractMapEntry<K,V> implements Map.Entry<K,V>{

	// 변경가능한 엔트리는 이 메서드를 반드시 재정의해야 한다.
	@Override
	public V setValue(V value) {
		throw new UnsupportedOperationException();
	}

	// Map.Entry.equals의 일반규약을 구현한다.
	@Override
	public boolean equals(Object o) {
		if(o == this) {
			return true;
		}
		
		if(!(o instanceof Map.Entry)) {
			return false;
		}
		
		Map.Entry<?, ?> e = (Map.Entry) o;
		return Objects.equals(e.getKey(), e.getKey()) && Objects.equals(e.getValue(), e.getValue());
	}
	
	// Map.Entry.hashCode의 일반 규약을 구현한다.
	@Override
	public int hashCode() {
		return Objects.hashCode(getKey()) ^ Objects.hashCode(getKey());
	}
	
	@Override
	public String toString() {
		return getKey() + "=" + getValue();
	}
	
	// 몸체만 있고 선택적 단계  -> '훅'이라고 불림
	@Override
	public K getKey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V getValue() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
