package chapter6.item40;

import java.util.HashSet;
import java.util.Set;

public class Bigram {
	private final char first;
	private final char second;
	
	public Bigram(char first, char second) {
		this.first = first;
		this.second = second;
	}
	
//	public boolean equals(Bigram b) {
//		return b.first == first && b.second == second;
//	}
	//컴파일 오류난 부분을 수정해준다.
	@Override public boolean equals(Object o) {
		if (!(o instanceof Bigram)) return false;
		Bigram b = (Bigram) o;
		return b.first == first && b.second == second;
	}
	
	public int hahCode() {
		return 31 * first + second;
	}
	
	public static void main(String[] args) {
		Set<Bigram> s = new HashSet<>();
		for (int i = 0; i < 10; i++) {
			for (char ch = 'a'; ch <= 'z'; ch++) {
				s.add(new Bigram(ch, ch));
			}
		System.out.println(s.size());
		}
	}
}
