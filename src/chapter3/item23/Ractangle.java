package chapter3.item23;

public class Ractangle extends AbstractFigure{
	final double length;
	final double width;
	
	public Ractangle(double length, double width) {
		this.length = length;
		this.width = width;
	}

	@Override
	double area() {
		return length * width;
	}
	
}
