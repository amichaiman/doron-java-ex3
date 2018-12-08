package hw3;

public  abstract class PrismPyramid 
{

	protected RegularPolygon baseShape;
	protected double baseZ;
	protected double z;
	public PrismPyramid(RegularPolygon base,double baseZ,double z)
	{
		baseShape=base;
		this.baseZ=baseZ;
		this.z=z;
	}
	public  double volume()
	{
		return baseShape.getArea()*getHeight();
	}
	public double getHeight()
	{
		return Math.abs(z-baseZ);
	}
	public String getBase()
	{
		return baseShape.toString();
	}
	public RegularPolygon getBaseShape()
	{
		return this.baseShape.copy();
	}
	public abstract String toString();
	public abstract boolean equals(Object p);
	
	public double getBaseZ()
	{
		return baseZ;
	}
	public double getZ()
	{
		return z;
	}
	public int hashCode()
	{
		int result =1;
		int prime =31;
		result=result*prime+((baseShape==null)?0:baseShape.hashcode());
		result=result*prime+(int)Double.doubleToLongBits(baseZ);
		result=result*prime+(int)Double.doubleToLongBits(z);
		return result;
	}
	
	

}
