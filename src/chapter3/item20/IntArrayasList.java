package chapter3.item20;

import java.util.AbstractList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class IntArrayasList {

	static List<Integer> intArrayAsList(int[] a) {
		Objects.requireNonNull(a);
		
		return new AbstractList<>() {

			@Override
			public int size() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public Integer set(int i, Integer val) {
				int oldVal = a[i]; // 오토 언박싱
				a[i] = val; //오토박싱
				return oldVal;
			}

			@Override
			public Integer get(int index) {
				return a[index];
			}
			
		};
	}
}
