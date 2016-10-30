import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
public class MainGUI extends JFrame implements ActionListener,MouseListener{
	JButton btNew,btLine,btCircle,btMove,btRotate,btReMove,btZoom;     //按钮对象        
	JToolBar menu;                            //工具栏对象
	PaintPanel painter;                       //PaintPanel 对象
	JPanel radioPanel,rightBig,rightTop;
	JRadioButton randioButton1,randioButton2;
	JTextArea showObject;                        //显示对象窗口
	JTextField inputPort,disPlacePort;
	JLabel inputLabel,disPlaceLabel,showLabel;
	ArrayList<Line> obj1;                     //保存当前直线对象
	ArrayList<Circle> obj2;                   //保存当前圆形对象
	Line test;                                //删除
	JList<MyGraphics> list;                   //对象列表
	static int mount=0;
	public MainGUI()
	{
		super("Computer Grapghics");
		
		//@西侧面板
		//按钮
        btNew  = new JButton("New");
		btNew.addActionListener(this);
		btNew.setActionCommand("New");
		btLine = new JButton("Line");
		btLine.addActionListener(this);
		btLine.setActionCommand("Line");
		btCircle = new JButton("Circle");
		btCircle.addActionListener(this);
		btCircle.setActionCommand("Circle");
		btMove=new JButton("Move");
		btMove.addActionListener(this);
		btMove.setActionCommand("Move");
		btRotate=new JButton("Rotate");
		btRotate.addActionListener(this);
		btRotate.setActionCommand("Rotate");
		btReMove=new JButton("Remove");
		btReMove.addActionListener(this);
		btReMove.setActionCommand("ReMove");
		btZoom=new JButton("Zoom");
		btZoom.addActionListener(this);
		btZoom.setActionCommand("Zoom");
		
		//工具栏
		menu=new JToolBar();
		menu.setOrientation(1);
		menu.setLayout(new GridLayout(7,1));
		menu.add(btNew);
		menu.add(btLine);
		menu.add(btCircle);
		menu.add(btMove);
		menu.add(btRotate);
		menu.add(btReMove);
		menu.add(btZoom);
		menu.setFloatable(false);
		menu.setBackground(Color.blue);
		menu.setPreferredSize(new Dimension(80, 600));
		this.add(menu,"West");
		
		//@中心
		//添加画图板
		painter=new PaintPanel();
		//painter.setBackground(Color.green);
		this.add(painter,"Center");
		this.addMouseListener(this);
		
		//初始化列表
		obj1=new ArrayList<Line>(5);
		obj2=new ArrayList<Circle>(5);
		
		//@东侧
		//显示框
		rightBig=new JPanel();
		rightBig.setLayout(new GridLayout(2,1));
		showObject=new JTextArea();
		showObject.setPreferredSize(new Dimension(100, 50));
		showObject.setVisible(true);
		showObject.setBackground(Color.white);
		showObject.setLineWrap(true);
		//右上面板
		rightTop=new JPanel();
		rightTop.setBackground(Color.blue);
		GridBagLayout gridbag = new GridBagLayout();
		rightTop.setLayout(gridbag);
		GridBagConstraints c1 = new GridBagConstraints();
		c1.gridheight=1;
	    c1.gridwidth=1;
	    GridBagConstraints c2 = new GridBagConstraints();
		c2.gridheight=3;
	    c2.gridwidth=1;
		
		//复选面板
		radioPanel=new JPanel();
		randioButton1=new JRadioButton("Line");
		randioButton2=new JRadioButton("Circle");
		showLabel=new JLabel("Result",JLabel.CENTER);
		inputLabel=new JLabel("Sequence",JLabel.CENTER);
		inputPort=new JTextField();
		disPlaceLabel=new JLabel("Change",JLabel.CENTER);
		disPlacePort=new JTextField();
		radioPanel.setLayout(new GridLayout(6,1));
		radioPanel.add(inputLabel);
		radioPanel.add(inputPort);
		radioPanel.add(disPlaceLabel);
		radioPanel.add(disPlacePort);
		radioPanel.add(randioButton1);
		radioPanel.add(randioButton2);
		radioPanel.setVisible(true);
		gridbag.setConstraints(showLabel, c1);
		gridbag.setConstraints(showObject, c2);
		rightTop.add(showLabel);
		rightTop.add(showObject);
		rightBig.add(rightTop);
		rightBig.add(radioPanel);
		rightBig.setBackground(Color.gray);
		rightBig.setBorder(new EtchedBorder());
		this.add(rightBig,"East");
		
	}
	public void actionPerformed(ActionEvent e)
	{
		String s = e.getActionCommand();
		if (s.equals("New"))
		{
			painter.repaint();
			obj1.clear();
			obj2.clear();
			showObject.setText("");
		}
		else if(s.equals("Line"))
		{
			obj1.add(painter.drawMyLine(painter.getCurrentLoc(1).getPointX(),painter.getCurrentLoc(1).getPointY(),painter.getCurrentLoc(2).getPointX(),painter.getCurrentLoc(2).getPointY()));
			mount=0;
			painter.clearCur();
			showObject.append("Line"+obj1.size());
			showObject.append("\n");
		}
		else if(s.equals("Circle"))
		{
			obj2.add(painter.drawMyCircle(painter.getCurrentLoc(1).calDistance(painter.getCurrentLoc(2)),painter.getCurrentLoc(1).getPointX(),painter.getCurrentLoc(1).getPointY()));
			mount=0;
			painter.clearCur();
			showObject.append("Circle"+obj2.size());
			showObject.append("\n");
		}
		else if(s.equals("Move"))
		{
			String displaceToken=disPlacePort.getText();
			StringTokenizer splitString= new StringTokenizer(displaceToken);
			int hor=Integer.parseInt(splitString.nextToken());
			int ver=Integer.parseInt(splitString.nextToken());
			if (randioButton1.isSelected()==true&&randioButton2.isSelected()==false)
			{
				int temp=Integer.parseInt(inputPort.getText());
				if(temp>0&&temp<=obj1.size())
				{
					painter.myMove(hor, ver,obj1.get(Integer.parseInt(inputPort.getText())-1));
					upMyData();
				}
			}
			else if (randioButton1.isSelected()==false&&randioButton2.isSelected()==true)
			{
				int temp=Integer.parseInt(inputPort.getText());
				if(temp>0&&temp<=obj2.size())
				{
					painter.myMove(hor, ver,obj2.get(Integer.parseInt(inputPort.getText())-1));
					upMyData();
				}
			}
		}
		else if(s.equals("Rotate"))
		{
			String displaceToken=disPlacePort.getText();
			if (randioButton1.isSelected()==true&&randioButton2.isSelected()==false)
			{
				int temp=Integer.parseInt(inputPort.getText());
				if(temp>0&&temp<=obj1.size())
				{
				//旋转直线
					painter.myRotate(Double.parseDouble(displaceToken),obj1.get(Integer.parseInt(inputPort.getText())-1));
					upMyData();
				}
			}
			else if (randioButton1.isSelected()==false&&randioButton2.isSelected()==true)
			{
				int temp=Integer.parseInt(inputPort.getText());
				if(temp>0&&temp<=obj2.size())
				{
				//旋转圆形
					painter.myRotate(Double.parseDouble(displaceToken),obj2.get(Integer.parseInt(inputPort.getText())-1));
					upMyData();
				}
			}	
		}
		else if(s.equals("ReMove"))
		{
			//写移除对象的操作
			int dealSeq=Integer.parseInt(inputPort.getText());
			if(randioButton1.isSelected()==true && randioButton2.isSelected()==false)
			{
				int temp=Integer.parseInt(inputPort.getText());
				if(temp>0&&temp<=obj1.size())
				{
					obj1.remove(dealSeq-1);
					upMyData();
				}
			}
			else if(randioButton1.isSelected()==false&& randioButton2.isSelected()==true)
			{
				int temp=Integer.parseInt(inputPort.getText());
				if(temp>0&&temp<=obj2.size())
				{
					obj2.remove(dealSeq-1);
					upMyData();
				}
			}
			
		}
		else if(s.equals("Zoom"))
		{
			String displaceToken=disPlacePort.getText();
			//写移除对象的操作
			if(randioButton1.isSelected()==true && randioButton2.isSelected()==false)
			{
				int temp=Integer.parseInt(inputPort.getText());
				if(temp>0&&temp<=obj1.size())
				{
					painter.myZoom(Double.parseDouble(displaceToken),obj1.get(Integer.parseInt(inputPort.getText())-1));
					upMyData();
				}
			}
			else if(randioButton1.isSelected()==false&& randioButton2.isSelected()==true)
			{
				int temp=Integer.parseInt(inputPort.getText());
				if(temp>0&&temp<=obj2.size())
				{
					painter.myZoom(Double.parseDouble(displaceToken),obj2.get(Integer.parseInt(inputPort.getText())-1));
					upMyData();
				}
			}
			
		}
	}
	public void upMyData()
	{
		painter.getGraphics().clearRect(0,0,1000,600);
		showObject.setText("");
		Iterator<Line> i=obj1.iterator();
		while(i.hasNext())
		{
			Line temp=i.next();
			painter.drawMyline(temp);
			showObject.append("Line"+(obj1.indexOf(temp)+1));
			showObject.append("\n");
		}
		Iterator<Circle> j=obj2.iterator();
		while(j.hasNext())
		{
			Circle temp=j.next();
			painter.drawMyCircle(temp);
			showObject.append("Circle"+(obj2.indexOf(temp)+1));
			showObject.append("\n");
		}
	}
	public static void main(String[] args)
	{
		MainGUI frame = new MainGUI();
		frame.setSize(1000,600);
		frame.setLocation(200,70);
		frame.setResizable(false);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	@Override
	public void mouseClicked(MouseEvent e) 
	{
		if(e.getClickCount()==1 && mount<2)
		{
			mount++;
			painter.setCur(e.getX(), e.getY(), mount);
		}
	}
	public void mousePressed(MouseEvent e) 
	{
		
		
	}
	public void mouseReleased(MouseEvent e)
	{
		
		
	}
	public void mouseEntered(MouseEvent e) 
	{
		
		
	}
	public void mouseExited(MouseEvent e)
	{
		
		
	}
}
