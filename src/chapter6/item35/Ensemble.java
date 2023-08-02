package chapter6.item35;

public enum Ensemble {
	// 1. 35 - 1 ordinal을 잘못 사용한예 - 따라 하지 말 것
//	SOLO, DUET,TRIO, QUARTET, QUINTET,
//	SEXTET, SEPTET, OCTET, NONET, DECTET;
//	
//	public int numberOfMusicians() {return ordinal() +1;}
	
	// 2
	SOLO(1), DUET(2),TRIO(3), QUARTET(4), QUINTET(5),
	SEXTET(6), SEPTET(7), OCTET(8), DOUBLE_QUARTET(8),
	NONET(9), DECTET(10), TRIPLE_QUARTET(12);
	
	private final int numberOfMusicians;
	Ensemble(int size) {this.numberOfMusicians = size;}
	public int numberOfMusicians() {return numberOfMusicians;}
}
