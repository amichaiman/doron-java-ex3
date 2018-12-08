package hw3;

import java.util.ArrayList;
import java.util.Collection;

public class RegularPolygon 
{
	private Point center;
	private int n;
	private Point vertex;
	
	public RegularPolygon(Point c, int n, Point ver)
	{
		center=c;
		this.n=n;
		vertex=ver;
	}
	public Point getVertex()
	{
		return vertex;
	}
	public Point getCenter()
	{
		return center.copy();
	}
	public int getN()
	{
		return n;
	}
	public double getArea()
	{
		double hight = Math.cos(Math.PI/n)*this.getRadius();
		double base=Math.sqrt(getRadius()*getRadius()-hight*hight);
		return n*base*hight;
		
	}
	public double getPerimeter()
	{
		double hight = Math.cos(Math.PI/n)*this.getRadius();
		double base=Math.sqrt(getRadius()*getRadius()-hight*hight);
		return base*2*n;
	}
	public double getRadius()
	{
		return	this.center.distance(vertex);
				/*Math.sqrt((center.getX()-vertex.getX())*(center.getX()-vertex.getX())+
		(center.getY()-vertex.getY())*(center.getY()-vertex.getY()));*/
	}
	
	/*public Collection<Point> getVertices()
	{
		Set<Point> ver= new HashSet<Point>();
		Point c =center.copy();
		this.center=new Point (0,0);
		vertex.setX(vertex.getX()-c.getX());
		vertex.setY(vertex.getY()-c.getY());
		double radius=this.getRadius();
		double teta=vertex.getAngle();
		Point p;
		for (int i=0;i<n;i++)
		{
			teta=(teta+(2*Math.PI*i)/n)%(2*Math.PI);
			p=Point.getPointFromPolar(radius, teta);
			p.move(c);
			ver.add(p);
		}
		center=c.copy();
		vertex.setX(vertex.getX()+c.getX());
		vertex.setY(vertex.getY()+c.getY());
		return ver;
	}
	*/
	
	public boolean equals(RegularPolygon p)
	{
		if((p.getCenter().equals(center))&&(n==p.getN())&&(this.getVertices().contains(p.getVertex())))
			return true;
		return false; 
	}
	
	public RegularPolygon copy()
	{
		return new RegularPolygon(center, n, vertex);
	}
	
	public int hashcode()
	{
		final int prime =31;
		int result =1;
		result = prime*result+center.hashCode();
		result=prime*result+n;
		result=prime*result+vertex.hashCode();    
		return result;
		
	}

	
	
	public String toString()
	{
		String sb = n+"regular polygon with center point"+ "["+center.getX()+","+center.getY()+"]"
				+"and vertices="+getVertices();
		return sb;
//		StringBuilder stb = new StringBuilder();
//		stb.append(n).append(arg0)
		
	}

	public Collection<Point> getVertices()
	{
		ArrayList<Point> ver= new ArrayList<Point>();
		Point c =center.copy();
		this.center=new Point (0,0);
		vertex.setX(vertex.getX()-c.getX());
		vertex.setY(vertex.getY()-c.getY());
		double radius=this.getRadius();
		double teta=vertex.getAngle();
		Point p;
		for (int i=0;i<n;i++)
		{
			teta=(teta+(2*Math.PI*i)/n)%(2*Math.PI);
			p=Point.getPointFromPolar(radius, teta);
			p.move(c);
			ver.add(p);
		}
		center=c.copy();
		vertex.setX(vertex.getX()+c.getX());
		vertex.setY(vertex.getY()+c.getY());
		return ver;
	}
	
	
}
