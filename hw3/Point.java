package hw3;

/**
 * @author Michal Hotovitz
 */
public class Point {

	private double x;
	private double y;

	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public Point() {
		this(0, 0);
	}

	public Point(Point p) {
		this(p.x, p.y);
	}

	public static Point getPointFromPolar(double r, double angle) {
		double x = round3Digits(r * Math.cos(angle));
		double y = round3Digits(r * Math.sin(angle));
		return new Point(x, y);
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public Point copy() {
		return new Point(this);
	}

	public void moveHorizontal(double delta) {
		x += delta;
	}

	public void moveVertical(double delta) {
		y += delta;
	}

	public void move(Point delta) {
   		x += delta.x;
		y += delta.y;
	}

	public double getRadius() {
		return distance(new Point(0, 0));
	}

	public double getAngle() {
		double a = 0;
		if (x > 0 && y > 0)
			a = Math.atan(y / x);
		if (x > 0 && y < 0)
			a = Math.atan(y / x) + 2 * Math.PI;
		if (x < 0)
			a = Math.atan(y / x) + Math.PI;
		if (x == 0 && y > 0)
			a = Math.PI / 2;
		if (x == 0 && y < 0)
			a = 3 * Math.PI / 2;
		return a;
	}

	public double distance(Point p2) {
		double yDistance = getY() - p2.getY();
		double xDistance = getX() - p2.getX();
		return round3Digits(Math.sqrt(yDistance * yDistance + xDistance * xDistance));
	}

	private static double round3Digits(double x) {
		return Math.round(x * 1000) / 1000.0;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(x);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(y);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point other = (Point) obj;
		if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
			return false;
		if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
			return false;
		return true;
	}

	@Override
	public String toString() {

		return "Point [x=" + round3Digits(x) + ", y=" + round3Digits(y) + "]";
	}

}
