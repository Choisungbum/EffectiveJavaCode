package chapter7.item44;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.*;

/*
 * item 44 표준 함수형 인터페이스를 사용하라 
 * 
 * 자바가 람다를 지원하면서 API 작성하는 모범사례도 바뀌었다.
 * -> 현대적인 방법은 같은 효과의 함수객체를 받는 정적팩터리나 생성자를제공하는 것
 * -> 함수 객체를 매개변수로 받는 생성자와 메서드를 더 많이 만들어야함 
 * -> 이때 함수형 매개변수 타입을 올바르게 선택해야한다.
 * 
 * java.util.function 패키지에 다양한 용도의 표준 함수형 인터페이스가 담겨있다
 * -> 필요한 용도에 맞는 게 있다면 직접 구현하지 말고 표준 함수형 인터페이스를 활용하라 
 * -> 표준 함수형 인터페이스들은 유용한 디폴트 메서드를 많이 제공하므로 다른 코드와의 상호 운용성도 크게 좋아질 것이다.
 *
 * ----------------------------------------------------------------------
 * 해당 패키지에는 총 43개의 인터페이스가 담겨있다
 * 큰 유형은 6개가 있고 나머지는 충분히 유추해 낼 수 있다
 * 
 * 1. Operator : 반환값과 인수의 타입이 같은 함수를 뜻한다 
 *               인수가 1개인 경우 UaryOperator
 *               인수가 2개인 경우 BinaryOperator 사용 
 *  
 * 2. Predicate : 인수 하나를 받아 boolean을 반환하는 함수를 뜻한다
 * 
 * 3. Function : 인수와 반환 타입이 다른 함수를 뜻한다.
 * 
 * 4. Supplier : 인수를 받지 않고 값을 반환(혹은 제공)하는 함수를 뜻한다
 * 
 * 5. Consumer : 인수를 하나 받고 반환값은 없는(인수를 소비하는) 함수를 뜻한다.
 * 
 * -> 기본 인터페이스는 기본 타입인 int, long, double용으로 각 3개씩 변형이 생성됨
 *    그 이름도 기본 인터페이스의 이름 앞에 해당 기본 타입 이름을붙여 지음
 *    ex) int를 받는 Predicate는 IntPredicate
 * 
 * -> Function 인터페이스는 기본타입을 반환하는 변형이 총 9개가 더 있다.
 *    입력과 결과 타입이 모두 기본 타입이면 접두어로 SrcToResult를 사용한다 (6개)
 *    ex) long를 받고 int를 반환하면 LongToIntFunction
 *    
 *    입력이 객체참조이고 결과가 int, long, double인 변형들로 입력을 매개변수화 하고 접두어로 ToResult를 사용한다 (3개)
 *    ex) int[] 인수를 받아 long으로 반환 ToLongFunction<int[]>
 * 
 *  기본 함수형 인터페이스 중 3개에는 인수를 2개씩 받는 변형이 있다.
 *  -> BiPredicate<T, U> 
 *     BIFunction<T,U,R> 에는 기본타입을 반환하는 세 변형이 있다
 *     					-> ToIntBiFunction
 *     					   ToLongBiFunction
 *     					   ToDoubleBiFunction
 *     BiConsumer<T,U>
 *     
 * Consumer에도 객체탐조와 기본 타입 하나 즉 인수를 2개 받는 변형인 objDoubleConsumer<T>, ObjIntConsumer, ObjLongConsumer가 있다
 * 
 * BooleanSupplier 인터페이스는 boolean을 반환하도록  한 Supplier의 변형이다.
 * ----------------------------------------------------------------------
 * 
 * 표줜 함수형 인터페이스 대부분은 기본 타입만 지원한다.
 * 그렇다고 함수형 인터페이스에 박싱된 기본 타입을 넣어 사용하지는 말자 -> 성늫이 처참해 질 수도 있다
 * 
 * 표준 함수형 인터페이스를 사용하지 않고 직접 코드를 작성해야 할 떄는 언제인가??
 * 1. 자주 쓰이며, 이름 자체가 용도를 명확히 설명해준다
 * 2. 반드시 따라야 하는 규약이 있다.
 * 3. 유용한 디폴트 메서드를 제공할 수 있다.
 * -> 인터페이스를 작성하는 것이기 때문에 아주 주의해서 설계해야한다.
 * 
 * 
 * 직접만든 함수형 인터페이스에는 @FunctionalInterface를 사용하라
 * 1. 해당 클래스의 코드나 설명문서를 읽을 이에게 그 인터페이스가 람다용으로 설계된 것임을 알려준다.
 * 2. 해당 인터페이스가 추상 메서드를 오직 하나만 가지고 있어야 컴파일되게 해준다.
 * 3. 그 결과 유지보수 과정에서 누군가 실수로 메서드를 추가하지 못하게 막아준다. 
 * 
 * */
public class Item44{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Consumer<String> consumer = (str) -> System.out.println(str);
		consumer.accept("hi");
		//Supplier<String> supplier = () -> "A String";
	}


}
