import java.awt.Graphics;
import java.util.ArrayList;
public class Circle extends MyGraphics
{
	static private int num=0;
	private int radius;
	private int centroid_x,centroid_y;
	ArrayList<MyPoint> myCircle;
	public Circle()
	{
		//无参构造
		radius=10;
		centroid_x=50;
		centroid_y=50;
		myCircle=new ArrayList<MyPoint>();
		num++;
		name="Circle"+num;
	}
	public Circle(int a,int b1,int b2)
	{
		radius=a;
		centroid_x=b1;
		centroid_y=b2;
		myCircle=new ArrayList<MyPoint>();
		num++;
		name="Circle"+num;
	}
	public void drawCircleBresenham(Graphics g)
	{
		//Bresenham八分法画圆
		int x=0,y=radius;
		int d=1-radius;
		while (y>x)
		{
			g.drawString(".", x+centroid_x,y+centroid_y);
			myCircle.add(new MyPoint(x+centroid_x,y+centroid_y));
			g.drawString(".", y+centroid_x,x+centroid_y);
			myCircle.add(new MyPoint(y+centroid_x,x+centroid_y));
			g.drawString(".", -x+centroid_x,y+centroid_y);
			myCircle.add(new MyPoint(-x+centroid_x,y+centroid_y));
			g.drawString(".", -y+centroid_x,x+centroid_y);
			myCircle.add(new MyPoint(-y+centroid_x,x+centroid_y));
			
			g.drawString(".", -x+centroid_x,-y+centroid_y);
			myCircle.add(new MyPoint(-x+centroid_x,-y+centroid_y));
			g.drawString(".", -y+centroid_x,-x+centroid_y);
			myCircle.add(new MyPoint(-y+centroid_x,-x+centroid_y));
			g.drawString(".", x+centroid_x,-y+centroid_y);
			myCircle.add(new MyPoint(x+centroid_x,-y+centroid_y));
			g.drawString(".", y+centroid_x,-x+centroid_y);
			myCircle.add(new MyPoint(y+centroid_x,-x+centroid_y));
			
		    if (d<0)
		    {
		    	d=d+2*x+3;
		    }
		    else
		    {
		    	d=d+2*(x-y)+5;
		        y--;
		    }
		        x++;
		    }
	}
	public void move(Circle c,int hor,int ver,Graphics g)
	{
		centroid_x=c.centroid_x+hor;
		centroid_y=c.centroid_y+ver;
		radius=c.radius;
		drawCircleBresenham(g);
	}
	public void rotate(double theta,Graphics g)
	{
		theta=theta/180*Math.PI;
		double cos=Math.cos(theta);
		double sin=Math.sin(theta);
		double tempx1=centroid_x;
		double tempx2=centroid_y;
		centroid_x=(int)(tempx1*cos-tempx2*sin);
		centroid_y=(int)(tempx1*sin+tempx2*cos);
		drawCircleBresenham(g);
	}
	public void zoom(double time,Graphics g)
	{
		System.out.println(radius);
		radius=(int)(time*radius+0.5);
		System.out.println(radius);
		drawCircleBresenham(g);
	}
	public ArrayList<MyPoint> getCircle()
	{
		return myCircle;
	}
}
