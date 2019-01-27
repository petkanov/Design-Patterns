package Structural.Adapter;

class Line {
    public void drawLine(int x1, int y1, int x2, int y2) {
        System.out.println("Line from point A(" + x1 + ";" + y1 + "), to point B(" + x2 + ";" + y2 + ")");
    }
}

class Rectangle {
    public void drawRectangel(int x, int y, int width, int height) {
        System.out.println("Rectangle with coordinate left-upper point (" + x + ";" + y + "), width: " + width
                + ", height: " + height);
    }
}

interface Shape {
	void draw(int x, int y, int z, int j);
}

class LineAdapter implements Shape {
    private Line adaptee;

    public LineAdapter(Line line) {
        this.adaptee = line;
    }
    @Override
    public void draw(int x1, int y1, int x2, int y2) {
        adaptee.drawLine(x1, y1, x2, y2);
    }
}

class RectangleAdapter implements Shape {
    private Rectangle adaptee;

    public RectangleAdapter(Rectangle rectangle) {
        this.adaptee = rectangle;
    }
    @Override
    public void draw(int x1, int y1, int x2, int y2) {
        int x = Math.min(x1, x2);
        int y = Math.min(y1, y2);
        int width = Math.abs(x2 - x1);
        int height = Math.abs(y2 - y1);
        adaptee.drawRectangel(x, y, width, height);
    }
}


public class AdapterDemo {
	public static void main(String[] args) {
		
		Shape[] shapes = { new RectangleAdapter(new Rectangle()), 
				  		   new LineAdapter(new Line()) };
		int x1 = 10, y1 = 20;
		int x2 = 30, y2 = 60;
		
		for (Shape shape : shapes) {
			shape.draw(x1, y1, x2, y2);
		}
	}
}
