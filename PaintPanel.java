import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.*;
public class PaintPanel extends JPanel 
{
	private Line l;
	private Circle round;
	private Graphics g;
	private MyPoint currentLoc1,currentLoc2;
	BufferedImage img;
	Canvas can;
	public PaintPanel()
	{
		setVisible(true);
		setSize(10,10);
		currentLoc1=new MyPoint();
		currentLoc2=new MyPoint();
	}
	public Line drawMyLine(int a1,int a2,int b1,int b2)
	{
		g = this.getGraphics();
		l=new Line(a1,a2,b1,b2);
		l.drawDDAline(g);
		return l;
	}
	public Line drawMyline(Line newLine)
	{
		l=newLine;
		g = this.getGraphics();
		l.drawDDAline(g);
		return l;
	}
	public Circle drawMyCircle(int r,int px,int py)
	{
		g = this.getGraphics();
		round=new Circle(r,px,py);
		round.drawCircleBresenham(g);
		return round;
	}
	public Circle drawMyCircle(Circle c)
	{
		round=c;
		g = this.getGraphics();
		round.drawCircleBresenham(g);
		return round;
	}
	public void myMove(int hor,int ver,Line l)
	{
		g = this.getGraphics();//在g里绘制线条
		if(l==null)
			return;
		else
		{
			l.move(l,hor, ver, g);
		}
	}
	public void myMove(int hor,int ver,Circle c)
	{
		g = this.getGraphics();//在g里绘制线条
		if(c==null)
			return;
		else
		{
			c.move(c,hor, ver, g);
		}
	}
	public void myRotate(double theta,Line l)
	{
		g = this.getGraphics();//在g里绘制线条
		if(l==null)
			return;
		else
		{
			l.rotate(theta, g);
		}
	}
	public void myZoom(double time,Circle c)
	{
		g = this.getGraphics();//在g里绘制线条
		if(c==null)
			return;
		else
		{
			c.zoom(time, g);
		}
	}
	public void myZoom(double time,Line l)
	{
		g = this.getGraphics();//在g里绘制线条
		if(l==null)
			return;
		else
		{
			l.zoom(time, g);
		}
	}
	public void myRotate(double theta,Circle c)
	{
		g = this.getGraphics();//在g里绘制线条
		if(c==null)
			return;
		else
		{
			c.rotate(theta, g);
		}
	}
	public void cleanPainter()
	{
		g=this.getGraphics();
		this.setBackground(Color.white);
	}
	public void setCur(int a1,int a2,int i)
	{
		if (i==1)
			currentLoc1.setPoint(a1, a2);
		else 
			currentLoc2.setPoint(a1, a2);
	}
	public MyPoint getCurrentLoc(int i)
	{
		if (i==1)
			return currentLoc1;
		else 
			return currentLoc2;
	}
	public void clearCur()
	{
		currentLoc1=null;
		currentLoc2=null;
		currentLoc1=new MyPoint();
		currentLoc2=new MyPoint();
	}

}
