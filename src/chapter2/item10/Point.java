package chapter2.item10;

public class Point {
	protected final int x;
	protected final int y;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Point)) {
			return false;
		}
		
		Point p = (Point)o;
		return p.x == x && p.y == y;
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
