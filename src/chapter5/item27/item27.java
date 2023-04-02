package effectiveJava.item27;

import java.util.Arrays;

/*
 * item 27 비검사경고를  제거하라 
 * 
 * 할 수 있는 한 모든 비검사 경고를 제거해라 -> 타입안정성이 보장됨 
 * 
 * 경고를 제거할 수 없지만 타입 안전하다고 확신할 수 있다면 
 * @Suppress Warning ("unchecked") 애너테이션을 달아 경고를 숨기자 - 그 경고를 무시해도 안전한 이류를 항상 주석으로 남기자 
 * 
 * */
public class item27 {
	static int size = 10;
	static public Object[] elements = {1,2,3};

	@SuppressWarnings("unchecked")
	// 생성한 배열과 매개변수로받은 배열의 타입이 모두 T[]로 같으므로
	// 올바른 형변환이다 
	public static <T> T[] toArray(T[] a) {
		if(a.length < size) {
			System.out.println("if (a.length < size)" + " a.getClass() = " + a.getClass().getName());
			return (T[]) Arrays.copyOf(elements, size, a.getClass());
		}
		System.arraycopy(elements, 0, a, 0, size);
		if (a.length > size) {
			a[size] = null;
		}
		System.out.println("if (a.length > size)");
		return a;
	}
	
	public static void main(String[] args) {
		Object[] ele = {1,2};
		Object[] aa = toArray(ele);
		
		for (int i = 0; i < aa.length; i++) {
			System.out.println(aa[i]);
		}
	}

}
