package chapter1.item5;

import java.util.Objects;

// 자원을 직접 명시하지말고 의존 객체 주입을 사용하라
public class SpellChecker {

	// 하나이상의 자원을 사용하는데 싱글톤이나 정적유틸리티를 사용하면 유연하지않고 테스트하기 어렵다
	// 의존 객체 주입은 유연성과 테스트의 용이성을 높여준다
	private final Lexicon distionary;
	
	public SpellChecker (Lexicon distionary) {
		this.distionary = Objects.requireNonNull(distionary);
	}
	
}
