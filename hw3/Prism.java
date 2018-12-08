package hw3;

public class Prism extends PrismPyramid 
{

	public Prism (RegularPolygon base,double baseZ,double z)
	{
		super(base,baseZ,z);
	}
	//@Override
/*	public double volume()
	{
		return baseShape.getArea()*super.getHeight();
	}*/

	@Override
	public String toString() 
	{
		double top;
		double down;
		if(this.z>baseZ)
		{
			 top =this.z;
			 down=this.baseZ;
		}
		else
		{
			 top =this.baseZ;
			 down=this.z;
		}
		return "Prism: Base shape=" + baseShape.toString() + ", z-values for bases=" + top + down;
	}
	@Override
	public boolean equals (Object p)
	{
		if (p.getClass()==this.getClass())
		{
			if(baseShape.equals(((Prism) p).getBaseShape()))
			{
				if((this.baseZ==((Prism) p).getBaseZ()&&this.z==((Prism) p).getZ())||
				(this.baseZ==((Prism) p).getZ()&&this.z==((Prism) p).getBaseZ()))
					return true;
			}
		}
		return false;
	}
	
	

}
