package cn.itcast.txz.ui;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;


//用作显示的主窗体  需要继承Fram类     https://weibo.com/5803273538/profile?topnav=1&wvr=6&is_all=1
public class MainFrame extends Frame implements KeyListener{
	
	/**序列化时为了保持版本的兼容性 解决黄色警报  ↑ */
	private static final long serialVersionUID = 1L;
	
	/**显示器的宽度*/
	private int screenWidth;
	
	/**显示器的高度*/
	private int screenHeight;
	
	JLabel lab_wolf;
	
	//记录用户上一步点击了什么按钮以便撤回
	private int withdraw;
	
	/**游戏中的关卡*/
	private int pass = 0;
	
	//设定一个与数据存储对应着的JLable存储数组
	JLabel[][] sheeps = new JLabel[12][16];
		
		//场景数据的模拟,使用二维数组模拟 1.代表障碍 0.代表空地 
		int[][][] datas; 
		
		//代表人物横向和纵向的位置
		int wx,wy;
		
		//代表当前有多少个箱子移动到了目标中
		int num = 0;
		
		//代表箱子的总数
		int total = 3;
	
	//构造方法
	public MainFrame(){
		
		//获取屏幕的宽高 使得窗口在任何屏幕上都居中显示 leon添加
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();  
        screenWidth = (int) screenSize.getWidth();  
        screenHeight = (int) screenSize.getHeight();  
        System.out.println("屏幕宽度：" + screenWidth + "，屏幕高度：" + screenHeight);  
		
        initGame();
        
        //设置窗口的位置  高度-20 是 底部状态栏  在屏幕上垂直居中
		this.setLocation((int) ((screenWidth - 826) * 0.5),(int) ((int)(screenHeight - 650) * 0.5) - 20);
		
		
		//监听浏览器关闭事件
		this.addWindowListener(new WindowListener() {
			
			@Override
			public void windowClosing(WindowEvent e) {//关闭窗口
				// TODO Auto-generated method stub
				System.exit(0);
			}

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		
		});
		
	}
	
	
	/**用户点击了回车键 进行的那关重新开始*/
	private void initGame() {
		
		int substitutes[][][] = {
				   //第一关
				   {{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
					{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
					{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
					{1,0,1,0,0,0,0,0,1,1,0,0,0,0,0,1},
					{1,0,1,0,0,0,0,0,1,0,1,0,0,0,0,1},
					{1,0,1,0,0,0,0,0,1,0,1,0,0,0,0,1},
					{1,0,1,0,0,0,0,0,1,1,0,0,0,0,8,1},
					{1,0,1,0,0,0,0,0,1,0,1,0,0,0,8,1},
					{1,0,1,0,0,0,0,0,1,0,0,1,0,0,8,1},
					{1,0,1,1,1,0,0,0,1,1,1,0,0,0,0,1},
					{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
					{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}},
				   //第二关	
				   {{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
					{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
					{1,0,0,0,0,0,0,1,1,0,1,1,0,0,0,1},
					{1,0,0,0,0,0,0,1,1,0,1,1,0,0,0,1},
					{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
					{1,0,0,0,0,0,0,1,1,0,1,1,0,0,0,1},
					{1,0,0,0,0,0,0,1,1,0,1,1,0,0,8,1},
					{1,1,1,1,1,1,0,0,0,0,0,0,0,0,8,1},
					{1,0,0,0,1,1,0,0,0,0,0,0,0,0,8,1},
					{1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,1},
					{1,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1},
					{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}},
				   //第三关	
				   {{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
					{1,1,1,0,0,0,0,0,0,0,0,0,0,1,1,1},
					{1,1,0,0,0,0,0,8,8,8,0,0,0,0,1,1},
					{1,0,0,0,1,1,1,1,1,1,1,1,0,0,0,1},
					{1,0,0,0,0,0,0,0,0,0,0,1,0,0,0,1},
					{1,0,0,0,1,1,1,0,0,1,1,1,0,0,0,1},
					{1,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1},
					{1,0,0,0,1,1,1,1,1,1,1,1,0,0,0,1},
					{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
					{1,1,0,0,0,0,0,0,0,0,0,0,0,0,1,1},
					{1,1,1,0,0,0,0,0,0,0,0,0,0,1,1,1},
					{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}},
				   //第4关	
				   {{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
					{1,0,0,0,0,1,0,1,0,0,0,0,0,0,0,1},
					{1,0,0,0,0,1,0,1,0,0,0,0,0,0,0,1},
					{1,0,0,0,0,1,8,1,0,0,0,0,0,0,0,1},
					{1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1},
					{1,0,0,0,8,0,0,0,0,8,0,0,0,0,0,1},
					{1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1},
					{1,0,0,0,0,0,1,0,1,0,0,0,0,0,0,1},
					{1,0,0,0,0,0,1,0,1,0,0,0,0,0,0,1},
					{1,0,0,0,0,0,1,0,1,0,0,0,0,0,0,1},
					{1,0,0,0,0,0,1,0,1,0,0,0,0,0,0,1},
					{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}},
				   //第5关	
				   {{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
					{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
					{1,0,1,1,1,1,1,0,0,0,0,0,0,0,0,1},
					{1,0,1,0,0,0,1,0,0,0,0,0,0,0,0,1},
					{1,0,1,0,0,0,1,0,1,1,1,0,0,0,0,1},
					{1,0,1,0,0,0,1,0,1,8,1,0,0,0,0,1},
					{1,0,1,1,1,0,1,1,1,8,1,0,0,0,0,1},
					{1,0,0,1,1,0,0,0,0,8,1,0,0,0,0,1},
					{1,0,0,1,0,0,0,1,0,0,1,0,0,0,0,1},
					{1,0,0,1,0,0,0,1,1,1,1,0,0,0,0,1},
					{1,0,0,1,1,1,1,1,0,0,0,0,0,0,0,1},
					{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}},
				   //第6关	
				   {{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
					{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
					{1,0,0,1,1,1,1,1,1,1,0,0,0,0,0,1},
					{1,0,0,1,0,0,0,0,0,1,1,1,0,0,0,1},
					{1,0,1,1,0,1,1,1,0,0,0,1,0,0,0,1},
					{1,0,1,0,0,0,0,0,0,0,0,1,0,0,0,1},
					{1,0,1,0,8,8,1,0,0,0,1,1,0,0,0,1},
					{1,0,1,1,8,0,1,0,0,0,1,0,0,0,0,1},
					{1,0,0,1,1,1,1,1,1,1,1,0,0,0,0,1},
					{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
					{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
					{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}},
			};
		
		datas = substitutes;
		
		//WARING:注意添加组件的顺序 最先添加的在最上面
        //做笼子（目标位置）
      	targetInit();
      	
      	//做灰太狼(人物)
      	wolfInit();
      	
      	//做懒洋洋（箱子）
      	sheepInit();
      	
      	//做树木（障碍）
      	treeInit();
      	
      	//制作背景，并将背景添加到窗体中
      	backgroundInit();
        
		//設置整個窗體
		setMainFrameUI();
		
		//监听键盘点击事件
		this.addKeyListener(this);
		
	}

	
	
	/**障碍的初始化*/
	private void treeInit(){
		
		//1.创建图片
		Icon ic = new ImageIcon("1.png");
		
		//遍历二维数组
		for(int l = 0; l < datas.length ; l++) {
		for (int i = 0; i < datas[l].length; i++) {
			for (int j = 0; j < datas[l][i].length; j++) {
				
				//判断如果是1做树木
				if (datas[pass][i][j] == 1) {
				//2.创建JLable 
				JLabel lab_tree = new JLabel(ic);
				//3.设置大小和位置
				lab_tree.setBounds(12 + 50 * j, 36 + 50 * i, 50, 50);
				//4.添加到窗体里
				this.add(lab_tree);
				}
			}
		  }
		}
	}
	
	/**目的地的初始化*/
	private void targetInit() {
		
		//制作笼子的方式同人物或者箱子的制作 1.创建图片
		Icon i = new ImageIcon("8.png");
		
		//2.JLable
		JLabel lab_target1 = new JLabel(i);
		
		//制作其他两个笼子	
		JLabel lab_target2 = new JLabel(i);
		JLabel lab_target3 = new JLabel(i);
		
		//3.设位置 每关笼子 放的位置 不一样才好玩  注意datas 数组中对应的位置应该是 8
		switch (pass) {
		case 0://第一关
			lab_target1.setBounds(700 + 12, 300 + 36, 50, 50);
			lab_target2.setBounds(700 + 12, 350 + 36, 50, 50);
			lab_target3.setBounds(700 + 12, 400 + 36, 50, 50);
			break;
		case 1://第二关 笼子
			lab_target1.setBounds(700 + 12, 300 + 36, 50, 50);
			lab_target2.setBounds(700 + 12, 350 + 36, 50, 50);
			lab_target3.setBounds(700 + 12, 400 + 36, 50, 50);
			break;
		case 2://第三关  笼子
			lab_target2.setBounds(350 + 12, 100 + 36, 50, 50);
			lab_target3.setBounds(400 + 12, 100 + 36, 50, 50);
			lab_target1.setBounds(450 + 12, 100 + 36, 50, 50);
			break;
		case 3://第4关  笼子
			lab_target2.setBounds(300 + 12, 150 + 36, 50, 50);
			lab_target3.setBounds(200 + 12, 250 + 36, 50, 50);
			lab_target1.setBounds(450 + 12, 250 + 36, 50, 50);
			break;
		case 4://第5关  笼子
			lab_target2.setBounds(450 + 12, 250 + 36, 50, 50);
			lab_target3.setBounds(450 + 12, 300 + 36, 50, 50);
			lab_target1.setBounds(450 + 12, 350 + 36, 50, 50);
			break;
		case 5://第5关  笼子
			lab_target2.setBounds(200 + 12, 300 + 36, 50, 50);
			lab_target3.setBounds(250 + 12, 300 + 36, 50, 50);
			lab_target1.setBounds(200 + 12, 350 + 36, 50, 50);
			break;
		
		}
		
		
		//4.添加到窗体里
		this.add(lab_target1);
		this.add(lab_target2);
		this.add(lab_target3);
	}

	/**推箱子箱子的初始化*/
	private void sheepInit() {
		
		//使用一张图片模拟箱子
		Icon i = new ImageIcon("4.png");
		//使用JLable组件模拟箱子
		JLabel lab_sheep1 = new JLabel(i);
		//制作第二只羊，而图片不需要重新制作
		JLabel lab_sheep2 = new JLabel(i);
		//制作第三只羊，而图片不需要重新制作
		JLabel lab_sheep3 = new JLabel(i);
		
		switch (pass) {
		case 0:
			//设置羊的位置
			lab_sheep1.setBounds(300 + 12, 200 + 36, 50, 50);
			//修改箱子对应位置上的数据为4
			datas[pass][4][6] = 4;
			//把这个组件添加到数组中
			sheeps[4][6] = lab_sheep1;
			lab_sheep2.setBounds(300 + 12, 300 + 36, 50, 50);
			datas[pass][6][6] = 4;
			sheeps[6][6] = lab_sheep2;
			lab_sheep3.setBounds(300 + 12, 400 + 36, 50, 50);
			datas[pass][8][6] = 4;
			sheeps[8][6] = lab_sheep3;
			break;

		case 1:
			//设置羊的位置
			lab_sheep1.setBounds(300 + 12, 200 + 36, 50, 50);
			//修改箱子对应位置上的数据为4
			datas[pass][4][6] = 4;
			//把这个组件添加到数组中
			sheeps[4][6] = lab_sheep1;
			lab_sheep2.setBounds(300 + 12, 300 + 36, 50, 50);
			datas[pass][6][6] = 4;
			sheeps[6][6] = lab_sheep2;
			lab_sheep3.setBounds(300 + 12, 400 + 36, 50, 50);
			datas[pass][8][6] = 4;
			sheeps[8][6] = lab_sheep3;
			break;
		case 2:	
			//设置羊的位置
			lab_sheep1.setBounds(300 + 12, 450 + 36, 50, 50);
			//修改箱子对应位置上的数据为4
			datas[pass][9][6] = 4;
			//把这个组件添加到数组中
			sheeps[9][6] = lab_sheep1;
			lab_sheep2.setBounds(350 + 12, 450 + 36, 50, 50);
			datas[pass][9][7] = 4;
			sheeps[9][7] = lab_sheep2;
			lab_sheep3.setBounds(400 + 12, 450 + 36, 50, 50);
			datas[pass][9][8] = 4;
			sheeps[9][8] = lab_sheep3;
			break;
		case 3:	
			//设置羊的位置
			lab_sheep1.setBounds(300 + 12, 200 + 36, 50, 50);
			//修改箱子对应位置上的数据为4
			datas[pass][4][6] = 4;
			//把这个组件添加到数组中
			sheeps[4][6] = lab_sheep1;
			lab_sheep2.setBounds(300 + 12, 250 + 36, 50, 50);
			datas[pass][5][6] = 4;
			sheeps[5][6] = lab_sheep2;
			lab_sheep3.setBounds(400 + 12, 250 + 36, 50, 50);
			datas[pass][5][8] = 4;
			sheeps[5][8] = lab_sheep3;
			break;
		case 4:	
			//设置羊的位置
			lab_sheep1.setBounds(200 + 12, 250 + 36, 50, 50);
			//修改箱子对应位置上的数据为4
			datas[pass][5][4] = 4;
			//把这个组件添加到数组中
			sheeps[5][4] = lab_sheep1;
			lab_sheep2.setBounds(200 + 12, 200 + 36, 50, 50);
			datas[pass][4][4] = 4;
			sheeps[4][4] = lab_sheep2;
			lab_sheep3.setBounds(250 + 12, 200 + 36, 50, 50);
			datas[pass][4][5] = 4;
			sheeps[4][5] = lab_sheep3;
			break;
		case 5:	
			//设置羊的位置
			lab_sheep1.setBounds(200 + 12, 200 + 36, 50, 50);
			//修改箱子对应位置上的数据为4
			datas[pass][4][4] = 4;
			//把这个组件添加到数组中
			sheeps[4][4] = lab_sheep1;
			lab_sheep2.setBounds(250 + 12, 250 + 36, 50, 50);
			datas[pass][5][5] = 4;
			sheeps[5][5] = lab_sheep2;
			lab_sheep3.setBounds(400 + 12, 300 + 36, 50, 50);
			datas[pass][6][8] = 4;
			sheeps[6][8] = lab_sheep3;
			break;	
		}
		
		
		
		//将箱子添加到窗体里
		this.add(lab_sheep2);
		this.add(lab_sheep1);
		this.add(lab_sheep3);
		//修改箱子对应位置上的数据为4
		
	}

	/**推箱子人物的初始化*/
	private void wolfInit() {
		
		switch (pass) {
		case 0://第一关
			//人物的最初位置
			wx = 1;
			wy = 1;
			break;

		case 1://第二关
			//人物的最初位置
			wx = 1;
			wy = 8;
			break;
		case 2://第三关
			//人物的最初位置
			wx = 8;
			wy = 10;
			break;
		case 3://第4关
			//人物的最初位置
			wx = 7;
			wy = 8;
			break;
		case 4://第5关
			//人物的最初位置
			wx = 3;
			wy = 3;
			break;
		case 5://第6关
			//人物的最初位置
			wx = 4;
			wy = 5;
			break;
		}
		
		
		//使用一张图片来模拟人物
		Icon i = new ImageIcon("wolf-zm.png");
		
		//使用JLable制作背景
		lab_wolf = new JLabel(i);
		
		//设置人物在屏幕上的显示位置
		lab_wolf.setBounds(12 + wx * 50, 36 + wy * 50, 50, 50);
		
		//将人物添加到窗体里
		this.add(lab_wolf);
		
		//把人物的数据添加到数组中
		//datas[6][4] = 2;
	}

	/**背景初始化*/
	private void backgroundInit() {
		
		//创建一个图片对象 
		Icon i = new ImageIcon("floor.png");
		
		//使用JLable制作背景
		JLabel lab_bg = new JLabel(i);
		
		lab_bg.setBounds(12, 36, 800, 600);
		
		//将JLable添加到窗体里
		this.add(lab_bg);
	}

	/**設置主窗體界面顯示效果*/
	private void setMainFrameUI(){
		
		//设置整个布局的模式为自由布局 传null
		this.setLayout(null);
        
        //设置窗口的标题
        this.setTitle("波波娛樂  Leon Amusement");
		
//		//设置窗口的位置  高度-20 是 底部状态栏  在屏幕上垂直居中
//		this.setLocation((int) ((screenWidth - 826) * 0.5),(int) ((int)(screenHeight - 650) * 0.5) - 20);
		
		//设置窗口的尺寸
		this.setSize(826,650);
		
		//设置窗口不能最大化
		this.setResizable(false);
		
		//设置窗口 显示出来
		this.setVisible(true);
	}


	@Override
	public void keyReleased(KeyEvent e) {
		
		int key = e.getKeyCode();
		int x = (int) lab_wolf.getLocation().getX();
		int y = (int) lab_wolf.getLocation().getY();
		
		switch (key) {
		case 37://用户点击了 左键 让灰太狼向左移动
			
			
			if (datas[pass][wy][wx - 1] == 0) {
				wx = wx - 1;
				lab_wolf.setLocation(x - 50, y);
				//人物移动后图片更换
				Icon lI = new ImageIcon("01.png");
				lab_wolf.setIcon(lI); 
				withdraw = 0;
				return;
			}
			
			if (datas[pass][wy][wx - 1] == 1) {
				withdraw = 0;
				return;//遇到障碍返回什么事情都不做
			}
			
			if (datas[pass][wy][wx - 1] == 4 && datas[pass][wy][wx - 2] == 1) {
				withdraw = 0;
				return;//遇到障碍返回什么事情都不做
			}
			
			if (datas[pass][wy][wx - 1] == 4 && datas[pass][wy][wx - 2] == 4) {
				withdraw = 0;
				return;//遇到障碍返回什么事情都不做
			}
			
			if (datas[pass][wy][wx - 1] == 4 && datas[pass][wy][wx - 2] == 12) {
				withdraw = 0;
				return;//遇到障碍返回什么事情都不做
			}
			
			if (datas[pass][wy][wx - 1] == 12 && datas[pass][wy][wx - 2] == 1) {
				withdraw = 0;
				return;//遇到障碍返回什么事情都不做
			}
			
			if (datas[pass][wy][wx - 1] == 12 && datas[pass][wy][wx - 2] == 4) {
				withdraw = 0;
				return;//遇到障碍返回什么事情都不做
			}
			
			if (datas[pass][wy][wx - 1] == 12 && datas[pass][wy][wx - 2] == 12) {
				withdraw = 0;
				return;//遇到障碍返回什么事情都不做
			}
			
			if (datas[pass][wy][wx - 1] == 8) {
				wx = wx - 1;
				lab_wolf.setLocation(x - 50, y);
				//人物移动后图片更换
				Icon lI = new ImageIcon("01.png");
				lab_wolf.setIcon(lI); 
				withdraw = 0;
				return;
			}
			
			if (datas[pass][wy][wx - 1] == 4 && datas[pass][wy][wx - 2] == 0) {
				//箱子位置的值移动
				datas[pass][wy][wx - 1] = 0;
				datas[pass][wy][wx - 2] = 4;
				
			}
			
			if (datas[pass][wy][wx - 1] == 4 && datas[pass][wy][wx - 2] == 8) {
				//箱子位置的值移动
				datas[pass][wy][wx - 1] = 0;
				datas[pass][wy][wx - 2] = 12;
				num++;
			}
			
			if (datas[pass][wy][wx - 1] == 12 && datas[pass][wy][wx - 2] == 0) {
				//箱子位置的值移动
				datas[pass][wy][wx - 1] = 8;
				datas[pass][wy][wx - 2] = 4;
				num--;
			}
			
			if (datas[pass][wy][wx - 1] == 12 && datas[pass][wy][wx - 2] == 8) {
				//箱子位置的值移动
				datas[pass][wy][wx - 1] = 8;
				datas[pass][wy][wx - 2] = 12;
			}
			
			//箱子显示位置移动
			sheeps[wy][wx - 1].setLocation(12 + wx * 50 - 100, 36 + wy * 50);
			sheeps[wy][wx - 2] = sheeps[wy][wx - 1];
			sheeps[wy][wx - 1] = null;
			wx = wx - 1;
			lab_wolf.setLocation(x - 50, y);
			//人物移动后图片更换
			Icon lI = new ImageIcon("01.png");
			lab_wolf.setIcon(lI);
			withdraw = 37;
			
			
			break;
		case 38://用户点击了 上键 让灰太狼向上移动
			
			
			if (datas[pass][wy - 1][wx] == 0) {
				wy = wy - 1;
				lab_wolf.setLocation(x , y - 50);
				//人物移动后图片更换
				Icon tI = new ImageIcon("10.png");
				lab_wolf.setIcon(tI); 
				withdraw = 0;
				return;
			}
			
			if (datas[pass][wy - 1][wx] == 1) {
				withdraw = 0;
				return;//遇到障碍返回什么事情都不做
			}
			
			if (datas[pass][wy - 1][wx] == 4 && datas[pass][wy - 2][wx] == 1) {
				withdraw = 0;
				return;//遇到障碍返回什么事情都不做
			}
			
			if (datas[pass][wy - 1][wx] == 4 && datas[pass][wy - 2][wx] == 4) {
				withdraw = 0;
				return;//遇到障碍返回什么事情都不做
			}
			
			if (datas[pass][wy - 1][wx] == 4 && datas[pass][wy - 2][wx] == 12) {
				withdraw = 0;
				return;//遇到障碍返回什么事情都不做
			}
			
			if (datas[pass][wy - 1][wx] == 12 && datas[pass][wy - 2][wx] == 1) {
				withdraw = 0;
				return;//遇到障碍返回什么事情都不做
			}
			
			if (datas[pass][wy - 1][wx] == 12 && datas[pass][wy - 2][wx] == 4) {
				withdraw = 0;
				return;//遇到障碍返回什么事情都不做
			}
			
			if (datas[pass][wy - 1][wx] == 12 && datas[pass][wy - 2][wx] == 12) {
				withdraw = 0;
				return;//遇到障碍返回什么事情都不做
			}
			
			if (datas[pass][wy - 1][wx] == 8) {
				wy = wy - 1;
				lab_wolf.setLocation(x, y - 50);//lab_wolf.setLocation(x, y + 50);
				//人物移动后图片更换
				Icon tI = new ImageIcon("10.png");
				lab_wolf.setIcon(tI); 
				withdraw = 0;
				return;
			}
			
			if (datas[pass][wy - 1][wx] == 4 && datas[pass][wy - 2][wx] == 0) {
				//箱子位置的值移动
				datas[pass][wy - 1][wx] = 0;
				datas[pass][wy - 2][wx] = 4;
				
			}
			
			if (datas[pass][wy - 1][wx] == 4 && datas[pass][wy - 2][wx] == 8) {
				//箱子位置的值移动
				datas[pass][wy - 1][wx] = 0;
				datas[pass][wy - 2][wx] = 12;
				num++;
			}
			
			if (datas[pass][wy - 1][wx] == 12 && datas[pass][wy - 2][wx] == 0) {
				//箱子位置的值移动
				datas[pass][wy - 1][wx] = 8;
				datas[pass][wy - 2][wx] = 4;
				num--;
			}
			
			if (datas[pass][wy - 1][wx] == 12 && datas[pass][wy - 2][wx] == 8) {
				//箱子位置的值移动
				datas[pass][wy - 1][wx] = 8;
				datas[pass][wy - 2][wx] = 12;
			}
			
			//箱子显示位置移动
			sheeps[wy - 1][wx].setLocation(12 + wx * 50, 36 + wy * 50 - 100);
			sheeps[wy - 2][wx] = sheeps[wy - 1][wx];
			sheeps[wy - 1][wx] = null;
			wy = wy - 1;
			lab_wolf.setLocation(x, y - 50);
			//人物移动后图片更换
			Icon tI = new ImageIcon("10.png");
			lab_wolf.setIcon(tI); 
			withdraw = 38;
			
			
			
			break;
		case 39://用户点击了 右键 让灰太狼向右移动
			
			
			if (datas[pass][wy][wx + 1] == 0) {
				wx = wx + 1;
				lab_wolf.setLocation(x + 50, y);
				//人物移动后图片更换
				Icon rI = new ImageIcon("0-1.png");
				lab_wolf.setIcon(rI); 
				withdraw = 0;
				return;
			}
			
			if (datas[pass][wy][wx + 1] == 1) {
				withdraw = 0;
				return;//遇到障碍返回什么事情都不做
			}
			
			if (datas[pass][wy][wx + 1] == 4 && datas[pass][wy][wx + 2] == 1) {
				withdraw = 0;
				return;//遇到障碍返回什么事情都不做
			}
			
			if (datas[pass][wy][wx + 1] == 4 && datas[pass][wy][wx + 2] == 4) {
				withdraw = 0;
				return;//遇到障碍返回什么事情都不做
			}
			
			if (datas[pass][wy][wx + 1] == 4 && datas[pass][wy][wx + 2] == 12) {
				withdraw = 0;
				return;//遇到障碍返回什么事情都不做
			}
			
			if (datas[pass][wy][wx + 1] == 12 && datas[pass][wy][wx + 2] == 1) {
				withdraw = 0;
				return;//遇到障碍返回什么事情都不做
			}
			
			if (datas[pass][wy][wx + 1] == 12 && datas[pass][wy][wx + 2] == 4) {
				withdraw = 0;
				return;//遇到障碍返回什么事情都不做
			}
			
			if (datas[pass][wy][wx + 1] == 12 && datas[pass][wy][wx + 2] == 12) {
				withdraw = 0;
				return;//遇到障碍返回什么事情都不做
			}
			
			if (datas[pass][wy][wx + 1] == 8) {
				wx = wx + 1;
				lab_wolf.setLocation(x + 50, y);
				//人物移动后图片更换
				Icon rI = new ImageIcon("0-1.png");
				lab_wolf.setIcon(rI); 
				withdraw = 0;
				return;
			}
			
			if (datas[pass][wy][wx + 1] == 4 && datas[pass][wy][wx + 2] == 0) {
				//箱子位置的值移动
				datas[pass][wy][wx + 1] = 0;
				datas[pass][wy][wx + 2] = 4;
				
			}
			
			if (datas[pass][wy][wx + 1] == 4 && datas[pass][wy][wx + 2] == 8) {
				//箱子位置的值移动
				datas[pass][wy][wx + 1] = 0;
				datas[pass][wy][wx + 2] = 12;
				num++;
			}
			
			if (datas[pass][wy][wx + 1] == 12 && datas[pass][wy][wx + 2] == 0) {
				//箱子位置的值移动
				datas[pass][wy][wx + 1] = 8;
				datas[pass][wy][wx + 2] = 4;
				num--;
			}
			
			if (datas[pass][wy][wx + 1] == 12 && datas[pass][wy][wx + 2] == 8) {
				//箱子位置的值移动
				datas[pass][wy][wx + 1] = 8;
				datas[pass][wy][wx + 2] = 12;
			}
			
			//箱子显示位置移动
			sheeps[wy][wx + 1].setLocation(12 + wx * 50 + 100, 36 + wy * 50);
			sheeps[wy][wx + 2] = sheeps[wy][wx + 1];
			sheeps[wy][wx + 1] = null;
			wx = wx + 1;
			lab_wolf.setLocation(x + 50, y);
			//人物移动后图片更换
			Icon rI = new ImageIcon("0-1.png");
			lab_wolf.setIcon(rI); 
			withdraw = 39;
			
			
			
			break;
		case 40://用户点击了 下键 让灰太狼向下移动
			
			
			
			if (datas[pass][wy + 1][wx] == 0) {
				wy = wy + 1;
				lab_wolf.setLocation(x , y + 50);
				//人物移动后图片更换
				Icon dI = new ImageIcon("wolf-zm.png");
				lab_wolf.setIcon(dI);
				withdraw = 0;
				return;
			}
			
			if (datas[pass][wy + 1][wx] == 1) {
				withdraw = 0;
				return;//遇到障碍返回什么事情都不做
			}
			
			if (datas[pass][wy + 1][wx] == 4 && datas[pass][wy + 2][wx] == 1) {
				withdraw = 0;
				return;//遇到障碍返回什么事情都不做
			}
			
			if (datas[pass][wy + 1][wx] == 4 && datas[pass][wy + 2][wx] == 4) {
				withdraw = 0;
				return;//遇到障碍返回什么事情都不做
			}
			
			if (datas[pass][wy + 1][wx] == 4 && datas[pass][wy + 2][wx] == 12) {
				withdraw = 0;
				return;//遇到障碍返回什么事情都不做
			}
			
			if (datas[pass][wy + 1][wx] == 12 && datas[pass][wy + 2][wx] == 1) {
				withdraw = 0;
				return;//遇到障碍返回什么事情都不做
			}
			
			if (datas[pass][wy + 1][wx] == 12 && datas[pass][wy + 2][wx] == 4) {
				withdraw = 0;
				return;//遇到障碍返回什么事情都不做
			}
			
			if (datas[pass][wy + 1][wx] == 12 && datas[pass][wy + 2][wx] == 12) {
				withdraw = 0;
				return;//遇到障碍返回什么事情都不做
			}
			
			if (datas[pass][wy + 1][wx] == 8) {
				wy = wy + 1;
				lab_wolf.setLocation(x, y + 50);
				//人物移动后图片更换
				Icon dI = new ImageIcon("wolf-zm.png");
				lab_wolf.setIcon(dI); 
				withdraw = 0;
				return;
			}
			
			if (datas[pass][wy + 1][wx] == 4 && datas[pass][wy + 2][wx] == 0) {
				//箱子位置的值移动
				datas[pass][wy + 1][wx] = 0;
				datas[pass][wy + 2][wx] = 4;
				
			}
			
			if (datas[pass][wy + 1][wx] == 4 && datas[pass][wy + 2][wx] == 8) {
				//箱子位置的值移动
				datas[pass][wy + 1][wx] = 0;
				datas[pass][wy + 2][wx] = 12;
				
				num++;
			}
			
			if (datas[pass][wy + 1][wx] == 12 && datas[pass][wy + 2][wx] == 0) {
				//箱子位置的值移动
				datas[pass][wy + 1][wx] = 8;
				datas[pass][wy + 2][wx] = 4;
				
				num--;
			}
			
			if (datas[pass][wy + 1][wx] == 12 && datas[pass][wy + 2][wx] == 8) {
				//箱子位置的值移动
				datas[pass][wy + 1][wx] = 8;
				datas[pass][wy + 2][wx] = 12;
				
			}
			
			//箱子显示位置移动
			sheeps[wy + 1][wx].setLocation(12 + wx * 50 , 36 + wy * 50 + 100);
			sheeps[wy + 2][wx] = sheeps[wy + 1][wx];
			sheeps[wy + 1][wx] = null;
			wy = wy + 1;
			lab_wolf.setLocation(x, y + 50);
			//人物移动后图片更换
			Icon dI = new ImageIcon("wolf-zm.png");
			lab_wolf.setIcon(dI); 
			withdraw = 40;
			break;
		case 10:
			
			switch (withdraw) {
			case 37:
				
				if (datas[pass][wy][wx - 1] == 4 && datas[pass][wy][wx] == 0) {
					//箱子位置的值移动
					datas[pass][wy][wx - 1] = 0;
					datas[pass][wy][wx] = 4;
					
				}
				
				if (datas[pass][wy][wx - 1] == 4 && datas[pass][wy][wx] == 8) {
					//箱子位置的值移动
					datas[pass][wy][wx - 1] = 0;
					datas[pass][wy][wx] = 12;
					num++;
				}
				
				if (datas[pass][wy][wx - 1] == 12 && datas[pass][wy][wx] == 0) {
					//箱子位置的值移动
					datas[pass][wy][wx - 1] = 8;
					datas[pass][wy][wx] = 4;
					num--;
				}
				
				if (datas[pass][wy][wx - 1] == 12 && datas[pass][wy][wx] == 8) {
					//箱子位置的值移动
					datas[pass][wy][wx - 1] = 8;
					datas[pass][wy][wx] = 12;
				}
				
				
				if (datas[pass][wy][wx] == 4 || datas[pass][wy][wx] == 12 ) {
					//箱子显示位置移动
					sheeps[wy][wx - 1].setLocation(12 + wx * 50 , 36 + wy * 50);
					sheeps[wy][wx] = sheeps[wy][wx - 1];
					sheeps[wy][wx - 1] = null;
				}
				
				
				wx = wx + 1;
				lab_wolf.setLocation(x + 50, y);
				//人物移动后图片更换
				Icon lI2 = new ImageIcon("01.png");
				lab_wolf.setIcon(lI2);
				
				withdraw = 0;
				
				break;
				
				//--------------------------------------------------↑37-------------------------------
			case 38://用户点击了 上键 让灰太狼向上移动
				
				
				if (datas[pass][wy - 1][wx] == 4 && datas[pass][wy][wx] == 0) {
					//箱子位置的值移动
					datas[pass][wy - 1][wx] = 0;
					datas[pass][wy][wx] = 4;
					
				}
				
				if (datas[pass][wy - 1][wx] == 4 && datas[pass][wy][wx] == 8) {
					//箱子位置的值移动
					datas[pass][wy - 1][wx] = 0;
					datas[pass][wy][wx] = 12;
					sheeps[wy - 1][wx].setLocation(12 + wx * 50 , 36 + wy * 50);
					sheeps[wy][wx] = sheeps[wy - 1][wx];
					sheeps[wy - 1][wx] = null;
					num++;
				}
				
				if (datas[pass][wy - 1][wx] == 12 && datas[pass][wy][wx] == 0) {
					//箱子位置的值移动
					datas[pass][wy - 1][wx] = 8;
					datas[pass][wy][wx] = 4;
					num--;
				}
				
				if (datas[pass][wy - 1][wx] == 12 && datas[pass][wy][wx] == 8) {
					//箱子位置的值移动
					datas[pass][wy - 1][wx] = 8;
					datas[pass][wy][wx] = 12;
					sheeps[wy - 1][wx].setLocation(12 + wx * 50 , 36 + wy * 50);
					sheeps[wy][wx] = sheeps[wy - 1][wx];
					sheeps[wy - 1][wx] = null;
				}
				
				if (datas[pass][wy][wx] == 4 || datas[pass][wy - 1][wx] == 12 ) {
					System.out.println("向上撤回-----------------");
					//箱子显示位置移动
					sheeps[wy - 1][wx].setLocation(12 + wx * 50 , 36 + wy * 50);
					sheeps[wy][wx] = sheeps[wy - 1][wx];
					sheeps[wy - 1][wx] = null;
				}
				
				wy = wy + 1;
				lab_wolf.setLocation(x, y + 50);
				//人物移动后图片更换
				Icon tI2 = new ImageIcon("10.png");
				lab_wolf.setIcon(tI2);
				
				withdraw = 0;
				
				break;
				
				//--------------------------------------------------↓39-------------------------------

			case 39:
				
				if (datas[pass][wy][wx + 1] == 4 && datas[pass][wy][wx] == 0) {
					//箱子位置的值移动
					datas[pass][wy][wx + 1] = 0;
					datas[pass][wy][wx] = 4;
					
				}
				
				if (datas[pass][wy][wx + 1] == 4 && datas[pass][wy][wx] == 8) {
					//箱子位置的值移动
					datas[pass][wy][wx + 1] = 0;
					datas[pass][wy][wx] = 12;
					//箱子显示位置移动
					sheeps[wy][wx + 1].setLocation(12 + wx * 50 , 36 + wy * 50);
					sheeps[wy][wx] = sheeps[wy][wx + 1];
					sheeps[wy][wx + 1] = null;
					num++;
				}
				
				if (datas[pass][wy][wx + 1] == 12 && datas[pass][wy][wx] == 0) {
					//箱子位置的值移动
					datas[pass][wy][wx + 1] = 8;
					datas[pass][wy][wx] = 4;
					num--;
				}
				
				if (datas[pass][wy][wx + 1] == 12 && datas[pass][wy][wx] == 8) {
					//箱子位置的值移动
					datas[pass][wy][wx + 1] = 8;
					datas[pass][wy][wx] = 12;
					//箱子显示位置移动
					sheeps[wy][wx + 1].setLocation(12 + wx * 50 , 36 + wy * 50);
					sheeps[wy][wx] = sheeps[wy][wx + 1];
					sheeps[wy][wx + 1] = null;
				}
				
				if (datas[pass][wy][wx] == 4 || datas[pass][wy][wx + 1] == 12 ) {
					System.out.println("9999999999999999999999999");
					//箱子显示位置移动
					sheeps[wy][wx + 1].setLocation(12 + wx * 50 , 36 + wy * 50);
					sheeps[wy][wx] = sheeps[wy][wx + 1];
					sheeps[wy][wx + 1] = null;
				}
				
				
				wx = wx - 1;
				lab_wolf.setLocation(x - 50, y);
				//人物移动后图片更换
				Icon rI2 = new ImageIcon("0-1.png");
				lab_wolf.setIcon(rI2);
				
				withdraw = 0;
				
				break;
				//--------------------------------------------------↑38-------------------------------
			case 40://用户点击了 下键 让灰太狼向下移动
				
				
				if (datas[pass][wy + 1][wx] == 4 && datas[pass][wy][wx] == 0) {
					//箱子位置的值移动
					datas[pass][wy + 1][wx] = 0;
					datas[pass][wy][wx] = 4;
					
				}
				
				if (datas[pass][wy + 1][wx] == 4 && datas[pass][wy][wx] == 8) {
					//箱子位置的值移动
					datas[pass][wy + 1][wx] = 0;
					datas[pass][wy][wx] = 12;
					sheeps[wy + 1][wx].setLocation(12 + wx * 50 , 36 + wy * 50);
					sheeps[wy][wx] = sheeps[wy + 1][wx];
					sheeps[wy + 1][wx] = null;
					num++;
				}
				
				if (datas[pass][wy + 1][wx] == 12 && datas[pass][wy][wx] == 0) {
					//箱子位置的值移动
					datas[pass][wy + 1][wx] = 8;
					datas[pass][wy][wx] = 4;
					num--;
				}
				
				if (datas[pass][wy + 1][wx] == 12 && datas[pass][wy][wx] == 8) {
					//箱子位置的值移动
					datas[pass][wy + 1][wx] = 8;
					datas[pass][wy][wx] = 12;
					sheeps[wy + 1][wx].setLocation(12 + wx * 50 , 36 + wy * 50);
					sheeps[wy][wx] = sheeps[wy + 1][wx];
					sheeps[wy + 1][wx] = null;
				}
				
				if (datas[pass][wy][wx] == 4 || datas[pass][wy + 1][wx] == 12 ) {
					System.out.println("向下撤回-----------------");
					//箱子显示位置移动
					sheeps[wy + 1][wx].setLocation(12 + wx * 50 , 36 + wy * 50);
					sheeps[wy][wx] = sheeps[wy + 1][wx];
					sheeps[wy + 1][wx] = null;
				}
				
				wy = wy - 1;
				lab_wolf.setLocation(x, y - 50);
				//人物移动后图片更换
				Icon dI2 = new ImageIcon("wolf-zm.png");
				lab_wolf.setIcon(dI2);
				
				withdraw = 0;
				
				break;
			}
		
			break;
		case 27://用户点击了初始化 Esc
			
			newStart();
			
			break;	
		}
		
		
		//判断胜利
		victory();
	}
	
	
	
	/**判断玩家每关是否胜利
	 * @throws InterruptedException */
	private void victory() {
		
			
		System.out.println(num);
		
		
		if (num >= total) {
			if (pass == datas.length - 1) {
				System.out.println("用户 通过最后一关");
				
			} else {
			System.out.println("第"+ pass +"关胜利");
			//最后一关  弹框可以用DiaLog
			if (pass > datas.length - 1) {
				pass = 0;//最后一关通过后进入第1关
			}else {
				//继续平常的管卡	
				pass++;
				Timer timer = new Timer();
				timer.schedule(new TimerTask() {
				        public void run() {
				        	newStart();
				        	cancel();
				        }
				}, 500,1);
			
			}	
		  }
		}
		
	}
	
	private void newStart() {
		sheeps = null;
		datas = null;
		wx = 0;
		wy = 0;
		num = 0;
		this.removeAll();
		this.removeKeyListener(this);
		sheeps = new JLabel[12][16];
		initGame();
	}

	
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		
		
	}
}
