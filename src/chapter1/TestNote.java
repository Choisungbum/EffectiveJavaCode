package chapter1;

import chapter1.item7.Stack;

public class TestNote {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Stack test = new Stack();
		
		for(int i = 0; i < 10 ; i++) {
			test.push(i);
		}
		
		for(int i = 0; i < 10 ; i++) {
			System.out.println("tset"+i +" : " + test.pull());
		}
	}

}
