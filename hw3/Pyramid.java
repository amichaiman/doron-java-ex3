package hw3;

public class Pyramid extends PrismPyramid
{

	private Point top=new Point();
	public Pyramid (RegularPolygon base,double baseZ,double x, double y, double z)
	{
		super(base,baseZ,z);
		top.setX(x);
		top.setY(y);
	}
	@Override
	public double volume()
	{
		return super.volume()/3;
	}
	public Point getTop()
	{
		return top.copy();
	}
	@Override
	public String toString()
	{
		return "Pyramid: Base shape=" + baseShape.toString() + "z-base shape =" + getBaseZ() + ", Apex=" + top.getX() + top.getY() + super.getZ();
	}

	@Override
	public boolean equals(Object p)
	{
		if(p.getClass()==this.getClass())
		{
			return(baseShape.equals(((Pyramid) p).getBaseShape())&& this.baseZ==((Pyramid)p).getBaseZ()&&this.z==((Pyramid)p).getZ()&&((Pyramid)p).getTop().equals(top));
		}
		return false;
	}
}