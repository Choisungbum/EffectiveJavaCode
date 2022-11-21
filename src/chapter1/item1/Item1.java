package chapter1.item1;


/*
 * 생성자대신 정적 팩터리 메서드를 고려하라
 * 생성자 대신 다양한 정적 팩터리 메서드 사용 방식을 알아볼 수 있었다
 * 
 * + 추가
 * new 연산자 
 * new 연산자는 객체를 Heap이라는 메모리 영역에 메모리 공간을 할당해주고 
 * 메모리주소를 반환한 후 생성자를 실행시켜준다. 
 * 리터럴과는 달리 new 연산자로 생성된 객체는 똑같은 값을 가진(?) 
 * 객체가 있어도 서로 다른 메모리를 할당하기 때문에 서로 다른 객체로 분류된다.
 * 
 * */

public class Item1 {
	String name;
	
	String address;
	
	private static final Item1 GOOD_MORNING = new Item1();
	
	public Item1() { // 생성자 
	}
	public Item1(String name) { // 생성자 오버로딩
		this.name = name;
	}
	
	// 정적팩터리 메서드 - 1. 이름을 지정할 수 있다
	public static Item1 withName(String name) { 
		return new Item1(name);
	}
	
	public static Item1 withAddress(String address) {
		Item1 item1 = new Item1();
		item1.address = address;
		return item1;
	}
	
	// 기존에 생성한 인스턴스를 사용하여 새로운 인스턴스를 생성할 필요가 없다.
	public static Item1 getItem1() { 
		return GOOD_MORNING;
	}
	
	// 리턴하는 객체의 클래스가 입력 매개변수에 따라 매번 다를 수 있다
	public static Item1 getItem1(boolean flag) { 
		return flag ? new Item1() : new packItem1();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Item1 item = new Item1("sungbum1");
		System.out.println("name : " + item.name);
		
		Item1 item2 = Item1.withName("sungbum2");
		System.out.println("name : " + item2.name);
		
		Item1 item3 = Item1.withAddress("해운대");
		System.out.println("address : " + item3.address);
		
		Item1 item4 = Item1.getItem1(true);
		System.out.println("address : " + item4.address);
	}
	
	static class packItem1 extends Item1 {
		
	}
	
}
