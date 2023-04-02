package effectiveJava.item33;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/*
 * 
 * item 33 타입 안전 이종 컨테이너를 고려하라
 * 
 * 제네릭은 컬렉션과 단일원소 컨테이너에도 흔히 쓰인다. 이때 매개변수화 되는 대상은 
 * 컨테이너 자신이다 따라서 하나의 컨테이너에서 매개변수화할 수 있는 타입의 수가 제한된다
 * 
 * 더 유연한 수단이 필요할 경우 컨테이너 대신 키를 매개변수화한 다음, 컨테이너에 값을 넣거나 뺄 떄
 * 매개변수화한 키를 함께 제공한다. 제네릭 타입 시스템이 값의 타입이 키와 같음을 보장해주는데 
 * 이것을 '타입안전 이종 컨테이너 패턴'이라한다.
 * 
 * 
 * Favorites에 알아두어야할 제약이 2가지가 있다
 * 
 * 1. 악의적인 클아이언트가 Class객체를 로타입으로 넘기면 Favorites 이늣턴스의 타입 안전성이 쉽게 깨진다
 * 2. 실체화 불가타입에는 사용할 수 없다는 것이다.
 * 
 * Favorite가 사용하는 타입 토큰은 비한정적이다.
 * -> 타입을 제한하고 싶을 경우 '한정적 타입 토큰'을 활용하면 가능하다(한정적 와일드카드를 사용)
 * 
 * */

public class Favorites {
	/*
	 * 비한정적 와일드카드 타입이라 이 맵 안에 아무것도 넗을 수 없다고 생각할 수 있지만 
	 * 와잍드 카드 타입이 중첩되었다는 점을 깨달아야 한다. 맵이아니라 키가 와일드 카드 타입인 것이다
	 * 모든 키가 서로 다른 매개변수화 타입일 수 있다는 뜻으로 다양한 타입을 지원할 수 있도록 만들어준다.
	 * 
	 * favorites 맵의 값은 단순히 Object이다
	 * -> 이 맵은 키와 값 사이의 타입 관계를 보증하지 않는다는 말이다. 즉 모든 값이 키로 명시한 타입임을 보증하지 않는다.
	 * 
	 * */
	private Map<Class<?>, Object> favorites = new HashMap<>();

	/*
	 * 주어진 Class 객체와 즐겨찾기 인스턴스를 favorites에 추가해 관계를 지으면 끝이다
	 * 위와 같이 키와 값사이의 '타입링크' 정보는 버려진다. 즉 그 값이 그 키 타입의 인스턴스라는 정보가 사라진다
	 * 하진만 getFavorite에서 이 관계를 되살릴 수 있다.
	 * 
	 * */
	public <T> void putFavorite(Class<T> type, T instance) {
		favorites.put(Objects.requireNonNull(type), instance);
	}
	
	/*
	 * 이 객체가 바로 반환해야할 객체가 맞지만, 잘못된 컴파일타임 타입을 가지고 있다.
	 * 이 객체 타입은 Object이나 우리는 이를 T로 바꿔 반환해야한다. ->  cast 메서드를 사용해 이객체 참조를 Class객체가 가리키는 타입으로 동적 형변환한다.
	 * */
	public <T> T getFavoites(Class<T> type) {
		return type.cast(favorites.get(type));
	}
	
	public static void main(String[] args) {
		Favorites f = new Favorites();
		
		f.putFavorite(String.class, "Java");
		f.putFavorite(Integer.class, 0xcafebabe);
		f.putFavorite(Class.class, Favorites.class);
		
		String favoriteString = f.getFavoites(String.class);
		int favoriteInteger = f.getFavoites(Integer.class);
		Class<?> favoriteClass =f .getFavoites(Class.class);
		
		System.out.printf("%s %x $s%n",favoriteString, favoriteInteger, favoriteClass.getName());
	}

}
