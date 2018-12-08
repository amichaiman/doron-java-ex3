package hw3;

public class TemporalMain {
	public static void main(String[] args) {
		Point center = new Point(0, 0);
		Point p0 = new Point(0, 1);
		Point p1 = new Point(1, 1);
		Point p2 = new Point(-1, -1);

		RegularPolygon polygon1 = new RegularPolygon(center, 3, p0);
		RegularPolygon polygon2 = new RegularPolygon(center, 3, p0);
		assertPolygon(polygon1, 1.30, 5.20, 1, center, 3);
	}
	private static void assertPolygon(RegularPolygon p, double area, double perimeter, double radius, Point center,
			int n) {
		assert (round2Digits(p.getArea()) == area);
		assert (round2Digits(p.getPerimeter()) == perimeter);
		assert (round2Digits(p.getRadius()) == radius);
		assert (p.getCenter().equals(center));
		assert (p.getN() == n);
	}
	
	private static double round2Digits(double x) {
		return Math.round(x * 100) / 100.0;
	}

}
