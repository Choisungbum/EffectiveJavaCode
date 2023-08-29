package chapter6.item39;

import java.lang.reflect.*;

public class Runtests {
	public static void main(String[] args) throws Exception{
		int tests = 0;
		int passed = 0;
		Class<?> testClass = Class.forName(args[0]);
		for (Method m : testClass.getDeclaredMethods()) {
			if (m.isAnnotationPresent(Test.class)) {
				tests++;
				try {
					m.invoke(null);
					passed++;
				//Sample1//////////////////////////////////////
//				} catch (InvocationTargetException wrappedExc){
//					Throwable exc = wrappedExc.getCause();
//					System.out.println(m + " 실패" + exc);
//				} catch (Exception exc) {
//					System.out.println("잘못 사용한 @Test: " + m);
//				}
				///////////////////////////////////////////////
				//Sample2//////////////////////////////////////
				} catch (InvocationTargetException wrappedExc){
					Throwable exc = wrappedExc.getCause();
					///////////////////////////////////////////////
//					Class<? extends Throwable> excType = 
//							m.getAnnotation(ExceptionTest.class).value();
//					if (excType.isInstance(exc)) {
//						passed++;
//					}
					///////////////////////////////////////////////
					Class<? extends Throwable>[] excTypes = 
							m.getAnnotation(ExceptionTest.class).value();
					for (Class<? extends Throwable> excType : excTypes) {
						if (excType.isInstance(exc)) {
							passed++;
							break;
						}
					}
				} catch (Exception exc) {
					System.out.println("잘못 사용한 @Test: " + m);
				}
				///////////////////////////////////////////////	
			}
		}
		System.out.printf("성공 : %d. 실패 : %d%n", passed, tests - passed);
	}
}
