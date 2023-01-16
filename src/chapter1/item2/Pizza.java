package chapter1.item2;

import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;


//코드 2-4 계층적으로 설계된 클래스와 잘 어울리는 빌더 패턴 (19쪽)

//참고: 여기서 사용한 '시뮬레이트한 셀프 타입(simulated self-type)' 관용구는
//빌더뿐 아니라 임의의 유동적인 계층구조를 허용한다.
public abstract class Pizza {
	public enum Topping {HAM, MUSHROOM, ONION, PEPPER, SAUSGE}
	final Set<Topping> toppings;

	abstract static class Builder<T extends Builder<T>> {
		EnumSet<Topping> toppings = EnumSet.noneOf(Topping.class);
		
		public T addTopping(Topping topping) {
			toppings.add(Objects.requireNonNull(topping));
			return self();
		}
		
		abstract Pizza Build();
		
		// 하위클래스에서 해당 메서드를 재정의 해야한다.
		//"this"를 반환하도록 해야한다.
		// 자바에는 self 타입이 없으므로 이러한 우회방법을 사용하는데 이러한 방법을 "시뮬레이트한 셀프타입"이라한다 
		protected abstract T self();
	}
	
	Pizza(Builder<?> builder) {
		toppings = builder.toppings.clone();
	}
}
