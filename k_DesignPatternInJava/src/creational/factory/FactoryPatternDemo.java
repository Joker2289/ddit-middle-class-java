package creational.factory;

/*
			# 유지보수가 좋다
 */
public class FactoryPatternDemo {
	public static void main(String[] args) {
		
		ShapeFactory shapeFactory = new ShapeFactory();
		
		//get an Object if Circle and call its draw method
		
		Shape shape1 = shapeFactory.getShape("CIRCLE");
		shape1.draw();
		
		Shape shape2 = shapeFactory.getShape("RECTANGLE");
		shape2.draw();
		
		Shape shape3 = shapeFactory.getShape("SQUARE");
		shape3.draw();
		
	}
}
