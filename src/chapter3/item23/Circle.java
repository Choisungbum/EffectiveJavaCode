package chapter3.item23;

class Circle extends AbstractFigure{

	final double radius;
	
	Circle(double radius) {
		this.radius = radius;
	}
	
	@Override
	double area() {
		return Math.PI * (radius * radius);
	}

}
