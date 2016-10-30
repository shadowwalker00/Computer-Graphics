public class MyPoint 
{
	private int x;
	private int y;
	public MyPoint()
	{
		
	}
	public MyPoint(int a,int b)
	{
		x=a;
		y=b;
	}
	public void setPoint(int a,int b)
	{
		x=a;
		y=b;
	}
	public int getPointX()
	{
		return x;
	}
	public int getPointY()
	{
		return y;
	}
	public MyPoint getPoint()
	{
		return this;
	}
	
	public void showPoint()
	{
		System.out.println("x="+x+" y="+y);
	}
	public int calDistance(MyPoint p)
	{
		return (int)Math.sqrt((p.x-x)*(p.x-x)+(p.y-y)*(p.y-y));
	}
}
