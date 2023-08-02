package chapter6.item36;

import java.util.EnumSet;
import java.util.Set;

public class TextClass {
	//1. 36 - 1 비트 필드 열거 상수 - 구닥다리 기법
//	public static final int STYLE_BOLD 			= 1 << 0;
//	public static final int STYLE_ITALIC 		= 1 << 1;
//	public static final int STYLE_UNDERLINE 	= 1 << 2;
//	public static final int STYLE_STRIKETHROUGH = 1 << 3;
//	
//	// 매개변수 styles는 0개 이상의 STYLE_ 상수를 비트별 OR한 값이다.
//	public void applyStyles(int styles) {
//	
//	}
	
	// 2. 36 - 2 EnumSet비트 필드를 대체하는 현대적 기법
	public enum Style {BOLD, ITALIC, UNDERLINE, STRIKEROUTH};
	
	// 어떤 Set을 넘겨도 되나, EnumSet이 가장 좋다.
	public void applyStyles(Set<Style> styles) {}
	
	public static void main(String[] args) {
		TextClass cls = new TextClass();
		cls.applyStyles(EnumSet.of(Style.BOLD, Style.ITALIC));
	}
}
