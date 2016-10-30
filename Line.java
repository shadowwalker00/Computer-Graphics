import java.awt.*;
import java.math.*;
import java.util.*;
public class Line extends MyGraphics
{
	static private int num=0;
	private int point1_x,point1_y;
	private int point2_x,point2_y;
    ArrayList<MyPoint> myLine;
	public Line(int a1,int a2,int b1,int b2)
	{
		point1_x=a1;
		point1_y=a2;
		point2_x=b1;
		point2_y=b2;
		myLine=new ArrayList<MyPoint>();
		num++;
		name="Line"+num;
	}
	public void drawDDAline(Graphics g)
	{
		//程序还是有点小bug，默认的是点1一定要比点2的小
		float dx,dy,k,y;
		dy = point2_y-point1_y;
		dx = point2_x-point1_x;
		if (dx != 0)                          //这种情况是一条倾斜的直线
		{
			k = dy/dx;                        //确定直线的斜率
			y = point1_y;
			for (int x=point1_x;x<=point2_x;x ++){
				g.drawString(".", x, (int)(y + 0.5f));
				myLine.add(new MyPoint(x,(int)(y + 0.5f)));
				y = y + k;
			}
		}
		else
		{
			for (int i = point1_y;i <= point2_y;i ++)
			{
				g.drawString(".", point1_x, i);
				myLine.add(new MyPoint(point1_x,(int)(i + 0.5f)));
			}
		}
	}
	public ArrayList<MyPoint> getLine()
	{
		return myLine;
	}
	public void move(Line l,int hor,int ver,Graphics g)
	{
		point1_x=l.point1_x+hor;
		point1_y=l.point1_y+ver;
		point2_x=l.point2_x+hor;
		point2_y=l.point2_y+ver;
		drawDDAline(g);
	}
	public void rotate(double theta,Graphics g)
	{
		theta=theta/180*Math.PI;
		double cos=Math.cos(theta);
		double sin=Math.sin(theta);
		double tempx1=point1_x;
		double tempx2=point1_y;
		double tempy1=point2_x;
		double tempy2=point2_y;
		point1_x=(int)(tempx1*cos-tempx2*sin);
		point1_y=(int)(tempx1*sin+tempx2*cos);
		point2_x=(int)(tempy1*cos-tempy2*sin);
		point2_y=(int)(tempy1*sin+tempy2*cos);
		drawDDAline(g);
	}
	public void zoom(double time,Graphics g)
	{
		int midx=(int)((point1_x+point2_x)/2+0.5);
		int midy=(int)((point1_y+point2_y)/2+0.5);
		int hor=midx;
		int ver=midy;
		point1_x=point1_x-hor;
		point1_y=point1_y-ver;
		point2_x=point2_x-hor;
		point2_y=point2_y-ver;
		point1_x=(int)(point1_x*time+0.5);
		point1_y=(int)(point1_y*time+0.5);
		point2_x=(int)(point2_x*time+0.5);
		point2_y=(int)(point2_y*time+0.5);
		point1_x=point1_x+hor;
		point1_y=point1_y+ver;
		point2_x=point2_x+hor;
		point2_y=point2_y+ver;
		drawDDAline(g);
	}
}
