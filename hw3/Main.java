package hw3;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Michal Hotovitz
 *
 */
public class Main {

	public static void main(String[] args) {
		Point center = new Point(0, 0);
		Point p0 = new Point(0, 1);
		Point p1 = new Point(1, 1);
		Point p2 = new Point(-1, -1);

		RegularPolygon polygon1 = new RegularPolygon(center, 3, p0);
		RegularPolygon polygon2 = new RegularPolygon(center, 3, p0);
		assertPolygon(polygon1, 1.30, 5.20, 1, center, 3);
		assert (polygon1.equals(polygon2));
		PrismPyramid pr1 = new Prism(polygon1, 0, 2);
		PrismPyramid pr2 = new Prism(polygon1, 2, 0);
		assertPrismPyramid(pr1, 2.6, 2, polygon1);
		assert (pr1.equals(pr2));

		polygon1 = new RegularPolygon(center, 4, p1);
		polygon2 = new RegularPolygon(center, 4, p2);
		assertPolygon(polygon1, 4.0, 8.0, 1.41, center, 4);
		assert (polygon1.equals(polygon2));
		pr1 = new Pyramid(polygon1, 0, 0, 0, 2);
		pr2 = new Pyramid(polygon1, 2, 0, 0, 0);
		assertPrismPyramid(pr1, 2.67, 2, polygon1);
		assertPrismPyramid(pr2, 2.67, 2, polygon1);
		assert (!pr1.equals(pr2));

		polygon1 = new RegularPolygon(center, 4, center);
		assertPolygon(polygon1, 0, 0, 0, center, 4);
		pr1 = new Pyramid(polygon1, 0, 0, 0, 2);
		pr2 = new Prism(polygon2, 2, 0);
		assert (!polygon1.equals(polygon2));
		assert (!pr1.equals(pr2));

		testDifferentCenters();
		testRotations();
		System.out.println("good");
	}

	private static void testRotations() {
		Point center = new Point(-5, -5);
		Point vertex = new Point(-5, -4);
		RegularPolygon polygon = new RegularPolygon(center, 4, vertex);
		Set<Point> vertices = new HashSet<Point>();
		verticesRotateList(vertices, center.getX(), center.getY());
		assert (vertices.containsAll(polygon.getVertices()));
		assert (polygon.getVertices().containsAll(vertices));
		assert (center.equals(polygon.getCenter()));

		center = new Point(-5, 5);
		vertex = new Point(-5, 4);
		polygon = new RegularPolygon(center, 4, vertex);
		verticesRotateList(vertices, center.getX(), center.getY());
		assert (vertices.containsAll(polygon.getVertices()));
		assert (polygon.getVertices().containsAll(vertices));
		assert (center.equals(polygon.getCenter()));

		center = new Point(0.5, 0.5);
		vertex = new Point(0.5, -0.5);
		polygon = new RegularPolygon(center, 4, vertex);
		verticesRotateList(vertices, center.getX(), center.getY());
		assert (vertices.containsAll(polygon.getVertices()));
		assert (polygon.getVertices().containsAll(vertices));
		assert (center.equals(polygon.getCenter()));
	}

	private static void testDifferentCenters() {
		Point center = new Point(-5, -5);
		Point movePoint = new Point(1, 1);
		Point p = center.copy();
		p.move(movePoint);
		RegularPolygon polygon = new RegularPolygon(center, 4, p);
		Set<Point> vertices = new HashSet<Point>();
		verticesList(vertices, -4, -6, -4, -6);
		assert (vertices.containsAll(polygon.getVertices()));
		assert (polygon.getVertices().containsAll(vertices));
		assert (center.equals(polygon.getCenter()));

		center = new Point(-5, 5);
		p = center.copy();
		p.move(movePoint);
		polygon = new RegularPolygon(center, 4, p);
		verticesList(vertices, -4, -6, 4, 6);
		assert (vertices.containsAll(polygon.getVertices()));
		assert (polygon.getVertices().containsAll(vertices));
		assert (center.equals(polygon.getCenter()));

		center = new Point(5, 5);
		p = center.copy();
		p.move(movePoint);
		polygon = new RegularPolygon(center, 4, p);
		verticesList(vertices, 4, 6, 4, 6);
		assert (vertices.containsAll(polygon.getVertices()));
		assert (polygon.getVertices().containsAll(vertices));
		assert (center.equals(polygon.getCenter()));

		center = new Point(5, -5);
		p = center.copy();
		p.move(movePoint);
		polygon = new RegularPolygon(center, 4, p);
		verticesList(vertices, 4, 6, -4, -6);
		assert (vertices.containsAll(polygon.getVertices()));
		assert (polygon.getVertices().containsAll(vertices));
		assert (center.equals(polygon.getCenter()));

		center = new Point(0.5, 0.5);
		p = center.copy();
		p.move(movePoint);
		polygon = new RegularPolygon(center, 4, p);
		verticesList(vertices, -0.5, 1.5, -0.5, 1.5);
		assert (vertices.containsAll(polygon.getVertices()));
		assert (polygon.getVertices().containsAll(vertices));
		assert (center.equals(polygon.getCenter()));
	}

	private static void verticesList(Set<Point> vertices, double x1, double x2, double y1, double y2) {
		vertices.clear();
		Point v1 = new Point(x1, y1);
		Point v2 = new Point(x1, y2);
		Point v3 = new Point(x2, y1);
		Point v4 = new Point(x2, y2);
		vertices.add(v1);
		vertices.add(v2);
		vertices.add(v3);
		vertices.add(v4);
	}

	private static void verticesRotateList(Set<Point> vertices, double x, double y) {
		vertices.clear();
		Point v1 = new Point(x - 1, y);
		Point v2 = new Point(x + 1, y);
		Point v3 = new Point(x, y - 1);
		Point v4 = new Point(x, y + 1);
		vertices.add(v1);
		vertices.add(v2);
		vertices.add(v3);
		vertices.add(v4);
	}

	private static void assertPolygon(RegularPolygon p, double area, double perimeter, double radius, Point center,
			int n) {
		assert (round2Digits(p.getArea()) == area);
		assert (round2Digits(p.getPerimeter()) == perimeter);
		assert (round2Digits(p.getRadius()) == radius);
		assert (p.getCenter().equals(center));
		assert (p.getN() == n);
	}

	private static void assertPrismPyramid(PrismPyramid pr, double volume, double height, RegularPolygon base) {
		assert (round2Digits(pr.volume()) == volume);
		assert (round2Digits(pr.getHeight()) == height);
		assert (pr.getBase().equals(base.toString()));
	}

	private static double round2Digits(double x) {
		return Math.round(x * 100) / 100.0;
	}
	//RegularPolygon rp = new RegularPolygon(new Point (0,0), 3, new Point(1,1)); 
//	Pyramid pp = new Pyramid(rp, 0,0,1,1);
	
}
