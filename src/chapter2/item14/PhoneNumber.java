package chapter2.item14;

import java.util.Comparator;
import java.util.Objects;

// 전형적인 equals 메서드 에

	// 마지막 주의사항
	// 1. equals를 재정의할 댄 hashCode도 반드시 재정의 하자
	// 2. 너무 복잡하게 해결하려 들지마라 -> 필드들의 동치성만검사해도 어렵지 않게 지칠 수 있다
	// 3. Object외의 타입을 매개변수로 받는 equals 메서드는 선언하지 말자 
	// -> 매개변수 타입이 다를경우 Object.equals를 재정의 한 것이 아니라 다중정의 한 것이다
	// * 제일중오 -> 꼭 필요한 경우가 아니라면 equals를 재정의 하지 말자...
public class PhoneNumber implements Cloneable ,Comparable <PhoneNumber>{
	private final short areaCode, prefix, lineNum;
	
	public PhoneNumber(int areaCode, int prefix, int lineNum) {
		this.areaCode = rangeCheck(areaCode, 999, "지역코드");
		this.prefix = rangeCheck(prefix, 999, "프리픽스");
		this.lineNum = rangeCheck(lineNum, 9999, "가입자번호");
	}
	
	private static short rangeCheck(int val, int max, String arg) {
		if (val < 0 || val > max) {
			throw new IllegalArgumentException(arg + " : " + val);
		}
		return (short) val;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if(!(o instanceof PhoneNumber)) {
			return false;
		}
		PhoneNumber pn = (PhoneNumber) o;
		return pn.lineNum == lineNum && pn.prefix == prefix && pn.areaCode == areaCode;
	}
	
	/*
	 * item11
	 * equals를 재정의한 클래스 모두에서 hashCode도 재정의해야 한다.
	 * 
	 * hashCode 일반규약
	 * 
	 * 1. equals 비교에 사용되는 정보가 변경되지 않았다면, 애플리케이션이 실행되는 동안 
	 *    그 객체의 hashCode 메서드는 몇 번을 호출해도 일관되게 항상 같은 값을 반환해야 한다.
	 * 2. equals(Object)가 두 객체를 같다고 판단했다면, 두 객체의 hashCode는 똑같은 값을 반환해야 한다.
	 * 3. equals(Object)가 두 객체를 다르다고 판단했더라도, 두 객체의 hashCOde가 서로 다른 값을 반환할 필요는 없다.
	 *    다른 객체에 대해서는 다른 값을 반환해야 해시데이터의 성능이 좋아진다.
	 * 
	 * */
	// case 1 : 전형적인 hashCode 메서드
//	Override
//	public int hashCode() {
//		int result = Short.hashCode(areaCode);
//		System.out.println("result : " + result);
//		// 31 곱한이유 -> 홀수이면서 소수이기때문
//		result = 31 * result +Short.hashCode(prefix);
//		result = 31 * result +Short.hashCode(lineNum);
//		return result;
//	}
	// case 2 : 한 줄짜리 hashCode -> 성능에 민감하지 않은 상황에서만 사용하자  
//	@Override
//	public int hashCode() {
//		return Objects.hash(lineNum, prefix, areaCode);
//	}
	
	// case 3 : 해시코드를 지연 초기화하는 hashCode메서드 - 스레드 안정성까지 고려해야 한다.
	private int hashCode; // 자동으로 0으로 초기화된다. -> 왜지..
	

	@Override
	public int hashCode() {
		int result = hashCode;
		System.out.println("hashCode : " + hashCode);
		if (result == 0) {
			result = Short.hashCode(areaCode);
			result = 31 * result +Short.hashCode(prefix);
			result = 31 * result +Short.hashCode(lineNum);
		}
		return result;
	}
	
	/*
	 * item 12
	 * 
	 * toString을 항상 재정의하라
	 * 
	 * 일반규약 
	 * 1. 간결함녀서 사람이 읽기 쉬운 형태의 유익한 정보를 반환하라
	 * 2. 모든 하위 클래스에서 이 메서드를 재정의하라 
	 * 
	 * toString은 그 객체가 가진 주요 정보를 모두 반환하는게 좋다
	 * */

	/*
	 * 이 정화번호의 문자열 표현을 반환한다.
	 * 이 문자열은 "XXX-YYY-ZZZZ" 형태의 12글자로 구성한다.
	 * XXX는 지역코드, YYY는 프리픽스, ZZZZ는 가입자 번호다.
	 * 각각의 대문자는 10진수 숫자 하나를 나타낸다.
	 * 
	 * 전화번호의 각 부분의 값이 너무 작아서 자릿수를 채울 수 없다면,
	 * 앞에서부터 0으로 채워나간다. 예컨대 가입자 번호가 123이라면
	 * 전화번호의 마지막 네 문자는 "0123"이 된다.
	 * */
	@Override
	public String toString() {
		return String.format("%03d-%03d-%04d", areaCode, prefix, lineNum);
	}
	
	/*
	 * item 13
	 * 
	 * clone 재정의는 주의해서 진행하라
	 * 
	 * Cloneable 구현 후 super.clne() 호출
	 */
	//가변상태를 참조하지 않는 클래스용 clone메서드
	@Override
	public PhoneNumber clone() {
		try {
			return (PhoneNumber) super.clone();
		} catch (CloneNotSupportedException e){
			throw new AssertionError();
		}
	}

	public static void main(String[] args) {
		PhoneNumber pn1 = new PhoneNumber(1, 1, 1);
		PhoneNumber pn2 = new PhoneNumber(1, 1, 1);
		
		System.out.println("pn1.equals(pn2) : " + pn1.equals(pn2));
		System.out.println("pn2.equals(pn1) : " + pn2.equals(pn1));
		System.out.println("pn1.hashCode : " + pn1.hashCode());
		System.out.println("pn2.hashCode : " + pn2.hashCode());
		
		System.out.println("pn1.toString() : " + pn1.toString());
		PhoneNumber pn3 = pn1.clone();
		System.out.println("pn3.toString() : " + pn3.toString());
		
	}
	
	/*
	 * item 14
	 * Comparable을 구현할지 고민하라
	 * 
	 * 클래스에 팩심필드가 여러개라면 어느 것을 먼저 비교하느냐가 중요해진다. 
	 * 가장 핵심적인 필드부터 비교해나간다.
	 * 가장 핵심이 되는 필드가 똑같다면  똑같지 않은 필드를 찾을 떄까지 그다음으로 중요한 필드를 비교해 나간다
	 * 
	 * */
	// 기본 타입 필드가 여럿일 대의 비교자
	@Override
	public int compareTo(PhoneNumber pn) {
		int result = Short.compare(areaCode, pn.areaCode); // 가장 중요한 필드
		if(result == 0) {
			result = Short.compare(prefix, pn.prefix); // 두 번째로 중요한 필드
			if(result == 0) {
				result = Short.compare(lineNum, pn.lineNum); // 세 번째로 중요한 필드
			}
		}
		return result;
	}
	
//	// 비교자 생성 메서드를 활용한 비교자 - 간결하지만 약간의 성능저하가 뒤따른다.
//	private static final Comparator<PhoneNumber> COMPARATOR = 
//			 comparingInt((PhoneNumber pn) -> pn.areaCode)
//             .thenComparingInt(pn -> pn.prefix)
//             .thenComparingInt(pn -> pn.lineNum);
//	@Override
//	public int compareTo(PhoneNumber pn) {
//		return COMPARATOR.compare(this, pn);
//	}

}
