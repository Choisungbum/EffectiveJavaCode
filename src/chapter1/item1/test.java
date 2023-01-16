package chapter1.item1;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

public class test {

	public static void main(String[] args) {
//		SoftReference strongRef = new SoftReference();
//		WeakReference<T>

		// TODO Auto-generated method stub
		int i = 0;
		int j = 0;
		int x;
		x = i;
		System.out.println("i : " + i);
		System.out.println("j : " + j);
		System.out.println("x : " + x);
		System.out.println("---------");
		x = i++;
		System.out.println("i : " + ++i);
		System.out.println("j : " + j++);
		System.out.println("x : " + x);
		System.out.println("---------");
		x = --i;
		System.out.println("i : " + i--);
		System.out.println("j : " + j--);
		System.out.println("x : " + x);
		System.out.println("---------");
		x = i;
		System.out.println("i : " + i);
		System.out.println("j : " + j);
		System.out.println("x : " + x);
		System.out.println("---------");
	}

}
