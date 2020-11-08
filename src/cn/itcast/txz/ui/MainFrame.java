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


//������ʾ��������  ��Ҫ�̳�Fram��     https://weibo.com/5803273538/profile?topnav=1&wvr=6&is_all=1
public class MainFrame extends Frame implements KeyListener{
	
	/**���л�ʱΪ�˱��ְ汾�ļ����� �����ɫ����  �� */
	private static final long serialVersionUID = 1L;
	
	/**��ʾ���Ŀ��*/
	private int screenWidth;
	
	/**��ʾ���ĸ߶�*/
	private int screenHeight;
	
	JLabel lab_wolf;
	
	//��¼�û���һ�������ʲô��ť�Ա㳷��
	private int withdraw;
	
	/**��Ϸ�еĹؿ�*/
	private int pass = 0;
	
	//�趨һ�������ݴ洢��Ӧ�ŵ�JLable�洢����
	JLabel[][] sheeps = new JLabel[12][16];
		
		//�������ݵ�ģ��,ʹ�ö�ά����ģ�� 1.�����ϰ� 0.����յ� 
		int[][][] datas; 
		
		//�����������������λ��
		int wx,wy;
		
		//����ǰ�ж��ٸ������ƶ�����Ŀ����
		int num = 0;
		
		//�������ӵ�����
		int total = 3;
	
	//���췽��
	public MainFrame(){
		
		//��ȡ��Ļ�Ŀ�� ʹ�ô������κ���Ļ�϶�������ʾ leon���
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();  
        screenWidth = (int) screenSize.getWidth();  
        screenHeight = (int) screenSize.getHeight();  
        System.out.println("��Ļ��ȣ�" + screenWidth + "����Ļ�߶ȣ�" + screenHeight);  
		
        initGame();
        
        //���ô��ڵ�λ��  �߶�-20 �� �ײ�״̬��  ����Ļ�ϴ�ֱ����
		this.setLocation((int) ((screenWidth - 826) * 0.5),(int) ((int)(screenHeight - 650) * 0.5) - 20);
		
		
		//����������ر��¼�
		this.addWindowListener(new WindowListener() {
			
			@Override
			public void windowClosing(WindowEvent e) {//�رմ���
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
	
	
	/**�û�����˻س��� ���е��ǹ����¿�ʼ*/
	private void initGame() {
		
		int substitutes[][][] = {
				   //��һ��
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
				   //�ڶ���	
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
				   //������	
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
				   //��4��	
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
				   //��5��	
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
				   //��6��	
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
		
		//WARING:ע����������˳�� ������ӵ���������
        //�����ӣ�Ŀ��λ�ã�
      	targetInit();
      	
      	//����̫��(����)
      	wolfInit();
      	
      	//�����������ӣ�
      	sheepInit();
      	
      	//����ľ���ϰ���
      	treeInit();
      	
      	//��������������������ӵ�������
      	backgroundInit();
        
		//�O���������w
		setMainFrameUI();
		
		//�������̵���¼�
		this.addKeyListener(this);
		
	}

	
	
	/**�ϰ��ĳ�ʼ��*/
	private void treeInit(){
		
		//1.����ͼƬ
		Icon ic = new ImageIcon("1.png");
		
		//������ά����
		for(int l = 0; l < datas.length ; l++) {
		for (int i = 0; i < datas[l].length; i++) {
			for (int j = 0; j < datas[l][i].length; j++) {
				
				//�ж������1����ľ
				if (datas[pass][i][j] == 1) {
				//2.����JLable 
				JLabel lab_tree = new JLabel(ic);
				//3.���ô�С��λ��
				lab_tree.setBounds(12 + 50 * j, 36 + 50 * i, 50, 50);
				//4.��ӵ�������
				this.add(lab_tree);
				}
			}
		  }
		}
	}
	
	/**Ŀ�ĵصĳ�ʼ��*/
	private void targetInit() {
		
		//�������ӵķ�ʽͬ����������ӵ����� 1.����ͼƬ
		Icon i = new ImageIcon("8.png");
		
		//2.JLable
		JLabel lab_target1 = new JLabel(i);
		
		//����������������	
		JLabel lab_target2 = new JLabel(i);
		JLabel lab_target3 = new JLabel(i);
		
		//3.��λ�� ÿ������ �ŵ�λ�� ��һ���ź���  ע��datas �����ж�Ӧ��λ��Ӧ���� 8
		switch (pass) {
		case 0://��һ��
			lab_target1.setBounds(700 + 12, 300 + 36, 50, 50);
			lab_target2.setBounds(700 + 12, 350 + 36, 50, 50);
			lab_target3.setBounds(700 + 12, 400 + 36, 50, 50);
			break;
		case 1://�ڶ��� ����
			lab_target1.setBounds(700 + 12, 300 + 36, 50, 50);
			lab_target2.setBounds(700 + 12, 350 + 36, 50, 50);
			lab_target3.setBounds(700 + 12, 400 + 36, 50, 50);
			break;
		case 2://������  ����
			lab_target2.setBounds(350 + 12, 100 + 36, 50, 50);
			lab_target3.setBounds(400 + 12, 100 + 36, 50, 50);
			lab_target1.setBounds(450 + 12, 100 + 36, 50, 50);
			break;
		case 3://��4��  ����
			lab_target2.setBounds(300 + 12, 150 + 36, 50, 50);
			lab_target3.setBounds(200 + 12, 250 + 36, 50, 50);
			lab_target1.setBounds(450 + 12, 250 + 36, 50, 50);
			break;
		case 4://��5��  ����
			lab_target2.setBounds(450 + 12, 250 + 36, 50, 50);
			lab_target3.setBounds(450 + 12, 300 + 36, 50, 50);
			lab_target1.setBounds(450 + 12, 350 + 36, 50, 50);
			break;
		case 5://��5��  ����
			lab_target2.setBounds(200 + 12, 300 + 36, 50, 50);
			lab_target3.setBounds(250 + 12, 300 + 36, 50, 50);
			lab_target1.setBounds(200 + 12, 350 + 36, 50, 50);
			break;
		
		}
		
		
		//4.��ӵ�������
		this.add(lab_target1);
		this.add(lab_target2);
		this.add(lab_target3);
	}

	/**���������ӵĳ�ʼ��*/
	private void sheepInit() {
		
		//ʹ��һ��ͼƬģ������
		Icon i = new ImageIcon("4.png");
		//ʹ��JLable���ģ������
		JLabel lab_sheep1 = new JLabel(i);
		//�����ڶ�ֻ�򣬶�ͼƬ����Ҫ��������
		JLabel lab_sheep2 = new JLabel(i);
		//��������ֻ�򣬶�ͼƬ����Ҫ��������
		JLabel lab_sheep3 = new JLabel(i);
		
		switch (pass) {
		case 0:
			//�������λ��
			lab_sheep1.setBounds(300 + 12, 200 + 36, 50, 50);
			//�޸����Ӷ�Ӧλ���ϵ�����Ϊ4
			datas[pass][4][6] = 4;
			//����������ӵ�������
			sheeps[4][6] = lab_sheep1;
			lab_sheep2.setBounds(300 + 12, 300 + 36, 50, 50);
			datas[pass][6][6] = 4;
			sheeps[6][6] = lab_sheep2;
			lab_sheep3.setBounds(300 + 12, 400 + 36, 50, 50);
			datas[pass][8][6] = 4;
			sheeps[8][6] = lab_sheep3;
			break;

		case 1:
			//�������λ��
			lab_sheep1.setBounds(300 + 12, 200 + 36, 50, 50);
			//�޸����Ӷ�Ӧλ���ϵ�����Ϊ4
			datas[pass][4][6] = 4;
			//����������ӵ�������
			sheeps[4][6] = lab_sheep1;
			lab_sheep2.setBounds(300 + 12, 300 + 36, 50, 50);
			datas[pass][6][6] = 4;
			sheeps[6][6] = lab_sheep2;
			lab_sheep3.setBounds(300 + 12, 400 + 36, 50, 50);
			datas[pass][8][6] = 4;
			sheeps[8][6] = lab_sheep3;
			break;
		case 2:	
			//�������λ��
			lab_sheep1.setBounds(300 + 12, 450 + 36, 50, 50);
			//�޸����Ӷ�Ӧλ���ϵ�����Ϊ4
			datas[pass][9][6] = 4;
			//����������ӵ�������
			sheeps[9][6] = lab_sheep1;
			lab_sheep2.setBounds(350 + 12, 450 + 36, 50, 50);
			datas[pass][9][7] = 4;
			sheeps[9][7] = lab_sheep2;
			lab_sheep3.setBounds(400 + 12, 450 + 36, 50, 50);
			datas[pass][9][8] = 4;
			sheeps[9][8] = lab_sheep3;
			break;
		case 3:	
			//�������λ��
			lab_sheep1.setBounds(300 + 12, 200 + 36, 50, 50);
			//�޸����Ӷ�Ӧλ���ϵ�����Ϊ4
			datas[pass][4][6] = 4;
			//����������ӵ�������
			sheeps[4][6] = lab_sheep1;
			lab_sheep2.setBounds(300 + 12, 250 + 36, 50, 50);
			datas[pass][5][6] = 4;
			sheeps[5][6] = lab_sheep2;
			lab_sheep3.setBounds(400 + 12, 250 + 36, 50, 50);
			datas[pass][5][8] = 4;
			sheeps[5][8] = lab_sheep3;
			break;
		case 4:	
			//�������λ��
			lab_sheep1.setBounds(200 + 12, 250 + 36, 50, 50);
			//�޸����Ӷ�Ӧλ���ϵ�����Ϊ4
			datas[pass][5][4] = 4;
			//����������ӵ�������
			sheeps[5][4] = lab_sheep1;
			lab_sheep2.setBounds(200 + 12, 200 + 36, 50, 50);
			datas[pass][4][4] = 4;
			sheeps[4][4] = lab_sheep2;
			lab_sheep3.setBounds(250 + 12, 200 + 36, 50, 50);
			datas[pass][4][5] = 4;
			sheeps[4][5] = lab_sheep3;
			break;
		case 5:	
			//�������λ��
			lab_sheep1.setBounds(200 + 12, 200 + 36, 50, 50);
			//�޸����Ӷ�Ӧλ���ϵ�����Ϊ4
			datas[pass][4][4] = 4;
			//����������ӵ�������
			sheeps[4][4] = lab_sheep1;
			lab_sheep2.setBounds(250 + 12, 250 + 36, 50, 50);
			datas[pass][5][5] = 4;
			sheeps[5][5] = lab_sheep2;
			lab_sheep3.setBounds(400 + 12, 300 + 36, 50, 50);
			datas[pass][6][8] = 4;
			sheeps[6][8] = lab_sheep3;
			break;	
		}
		
		
		
		//��������ӵ�������
		this.add(lab_sheep2);
		this.add(lab_sheep1);
		this.add(lab_sheep3);
		//�޸����Ӷ�Ӧλ���ϵ�����Ϊ4
		
	}

	/**����������ĳ�ʼ��*/
	private void wolfInit() {
		
		switch (pass) {
		case 0://��һ��
			//��������λ��
			wx = 1;
			wy = 1;
			break;

		case 1://�ڶ���
			//��������λ��
			wx = 1;
			wy = 8;
			break;
		case 2://������
			//��������λ��
			wx = 8;
			wy = 10;
			break;
		case 3://��4��
			//��������λ��
			wx = 7;
			wy = 8;
			break;
		case 4://��5��
			//��������λ��
			wx = 3;
			wy = 3;
			break;
		case 5://��6��
			//��������λ��
			wx = 4;
			wy = 5;
			break;
		}
		
		
		//ʹ��һ��ͼƬ��ģ������
		Icon i = new ImageIcon("wolf-zm.png");
		
		//ʹ��JLable��������
		lab_wolf = new JLabel(i);
		
		//������������Ļ�ϵ���ʾλ��
		lab_wolf.setBounds(12 + wx * 50, 36 + wy * 50, 50, 50);
		
		//��������ӵ�������
		this.add(lab_wolf);
		
		//�������������ӵ�������
		//datas[6][4] = 2;
	}

	/**������ʼ��*/
	private void backgroundInit() {
		
		//����һ��ͼƬ���� 
		Icon i = new ImageIcon("floor.png");
		
		//ʹ��JLable��������
		JLabel lab_bg = new JLabel(i);
		
		lab_bg.setBounds(12, 36, 800, 600);
		
		//��JLable��ӵ�������
		this.add(lab_bg);
	}

	/**�O�������w�����@ʾЧ��*/
	private void setMainFrameUI(){
		
		//�����������ֵ�ģʽΪ���ɲ��� ��null
		this.setLayout(null);
        
        //���ô��ڵı���
        this.setTitle("�����ʘ�  Leon Amusement");
		
//		//���ô��ڵ�λ��  �߶�-20 �� �ײ�״̬��  ����Ļ�ϴ�ֱ����
//		this.setLocation((int) ((screenWidth - 826) * 0.5),(int) ((int)(screenHeight - 650) * 0.5) - 20);
		
		//���ô��ڵĳߴ�
		this.setSize(826,650);
		
		//���ô��ڲ������
		this.setResizable(false);
		
		//���ô��� ��ʾ����
		this.setVisible(true);
	}


	@Override
	public void keyReleased(KeyEvent e) {
		
		int key = e.getKeyCode();
		int x = (int) lab_wolf.getLocation().getX();
		int y = (int) lab_wolf.getLocation().getY();
		
		switch (key) {
		case 37://�û������ ��� �û�̫�������ƶ�
			
			
			if (datas[pass][wy][wx - 1] == 0) {
				wx = wx - 1;
				lab_wolf.setLocation(x - 50, y);
				//�����ƶ���ͼƬ����
				Icon lI = new ImageIcon("01.png");
				lab_wolf.setIcon(lI); 
				withdraw = 0;
				return;
			}
			
			if (datas[pass][wy][wx - 1] == 1) {
				withdraw = 0;
				return;//�����ϰ�����ʲô���鶼����
			}
			
			if (datas[pass][wy][wx - 1] == 4 && datas[pass][wy][wx - 2] == 1) {
				withdraw = 0;
				return;//�����ϰ�����ʲô���鶼����
			}
			
			if (datas[pass][wy][wx - 1] == 4 && datas[pass][wy][wx - 2] == 4) {
				withdraw = 0;
				return;//�����ϰ�����ʲô���鶼����
			}
			
			if (datas[pass][wy][wx - 1] == 4 && datas[pass][wy][wx - 2] == 12) {
				withdraw = 0;
				return;//�����ϰ�����ʲô���鶼����
			}
			
			if (datas[pass][wy][wx - 1] == 12 && datas[pass][wy][wx - 2] == 1) {
				withdraw = 0;
				return;//�����ϰ�����ʲô���鶼����
			}
			
			if (datas[pass][wy][wx - 1] == 12 && datas[pass][wy][wx - 2] == 4) {
				withdraw = 0;
				return;//�����ϰ�����ʲô���鶼����
			}
			
			if (datas[pass][wy][wx - 1] == 12 && datas[pass][wy][wx - 2] == 12) {
				withdraw = 0;
				return;//�����ϰ�����ʲô���鶼����
			}
			
			if (datas[pass][wy][wx - 1] == 8) {
				wx = wx - 1;
				lab_wolf.setLocation(x - 50, y);
				//�����ƶ���ͼƬ����
				Icon lI = new ImageIcon("01.png");
				lab_wolf.setIcon(lI); 
				withdraw = 0;
				return;
			}
			
			if (datas[pass][wy][wx - 1] == 4 && datas[pass][wy][wx - 2] == 0) {
				//����λ�õ�ֵ�ƶ�
				datas[pass][wy][wx - 1] = 0;
				datas[pass][wy][wx - 2] = 4;
				
			}
			
			if (datas[pass][wy][wx - 1] == 4 && datas[pass][wy][wx - 2] == 8) {
				//����λ�õ�ֵ�ƶ�
				datas[pass][wy][wx - 1] = 0;
				datas[pass][wy][wx - 2] = 12;
				num++;
			}
			
			if (datas[pass][wy][wx - 1] == 12 && datas[pass][wy][wx - 2] == 0) {
				//����λ�õ�ֵ�ƶ�
				datas[pass][wy][wx - 1] = 8;
				datas[pass][wy][wx - 2] = 4;
				num--;
			}
			
			if (datas[pass][wy][wx - 1] == 12 && datas[pass][wy][wx - 2] == 8) {
				//����λ�õ�ֵ�ƶ�
				datas[pass][wy][wx - 1] = 8;
				datas[pass][wy][wx - 2] = 12;
			}
			
			//������ʾλ���ƶ�
			sheeps[wy][wx - 1].setLocation(12 + wx * 50 - 100, 36 + wy * 50);
			sheeps[wy][wx - 2] = sheeps[wy][wx - 1];
			sheeps[wy][wx - 1] = null;
			wx = wx - 1;
			lab_wolf.setLocation(x - 50, y);
			//�����ƶ���ͼƬ����
			Icon lI = new ImageIcon("01.png");
			lab_wolf.setIcon(lI);
			withdraw = 37;
			
			
			break;
		case 38://�û������ �ϼ� �û�̫�������ƶ�
			
			
			if (datas[pass][wy - 1][wx] == 0) {
				wy = wy - 1;
				lab_wolf.setLocation(x , y - 50);
				//�����ƶ���ͼƬ����
				Icon tI = new ImageIcon("10.png");
				lab_wolf.setIcon(tI); 
				withdraw = 0;
				return;
			}
			
			if (datas[pass][wy - 1][wx] == 1) {
				withdraw = 0;
				return;//�����ϰ�����ʲô���鶼����
			}
			
			if (datas[pass][wy - 1][wx] == 4 && datas[pass][wy - 2][wx] == 1) {
				withdraw = 0;
				return;//�����ϰ�����ʲô���鶼����
			}
			
			if (datas[pass][wy - 1][wx] == 4 && datas[pass][wy - 2][wx] == 4) {
				withdraw = 0;
				return;//�����ϰ�����ʲô���鶼����
			}
			
			if (datas[pass][wy - 1][wx] == 4 && datas[pass][wy - 2][wx] == 12) {
				withdraw = 0;
				return;//�����ϰ�����ʲô���鶼����
			}
			
			if (datas[pass][wy - 1][wx] == 12 && datas[pass][wy - 2][wx] == 1) {
				withdraw = 0;
				return;//�����ϰ�����ʲô���鶼����
			}
			
			if (datas[pass][wy - 1][wx] == 12 && datas[pass][wy - 2][wx] == 4) {
				withdraw = 0;
				return;//�����ϰ�����ʲô���鶼����
			}
			
			if (datas[pass][wy - 1][wx] == 12 && datas[pass][wy - 2][wx] == 12) {
				withdraw = 0;
				return;//�����ϰ�����ʲô���鶼����
			}
			
			if (datas[pass][wy - 1][wx] == 8) {
				wy = wy - 1;
				lab_wolf.setLocation(x, y - 50);//lab_wolf.setLocation(x, y + 50);
				//�����ƶ���ͼƬ����
				Icon tI = new ImageIcon("10.png");
				lab_wolf.setIcon(tI); 
				withdraw = 0;
				return;
			}
			
			if (datas[pass][wy - 1][wx] == 4 && datas[pass][wy - 2][wx] == 0) {
				//����λ�õ�ֵ�ƶ�
				datas[pass][wy - 1][wx] = 0;
				datas[pass][wy - 2][wx] = 4;
				
			}
			
			if (datas[pass][wy - 1][wx] == 4 && datas[pass][wy - 2][wx] == 8) {
				//����λ�õ�ֵ�ƶ�
				datas[pass][wy - 1][wx] = 0;
				datas[pass][wy - 2][wx] = 12;
				num++;
			}
			
			if (datas[pass][wy - 1][wx] == 12 && datas[pass][wy - 2][wx] == 0) {
				//����λ�õ�ֵ�ƶ�
				datas[pass][wy - 1][wx] = 8;
				datas[pass][wy - 2][wx] = 4;
				num--;
			}
			
			if (datas[pass][wy - 1][wx] == 12 && datas[pass][wy - 2][wx] == 8) {
				//����λ�õ�ֵ�ƶ�
				datas[pass][wy - 1][wx] = 8;
				datas[pass][wy - 2][wx] = 12;
			}
			
			//������ʾλ���ƶ�
			sheeps[wy - 1][wx].setLocation(12 + wx * 50, 36 + wy * 50 - 100);
			sheeps[wy - 2][wx] = sheeps[wy - 1][wx];
			sheeps[wy - 1][wx] = null;
			wy = wy - 1;
			lab_wolf.setLocation(x, y - 50);
			//�����ƶ���ͼƬ����
			Icon tI = new ImageIcon("10.png");
			lab_wolf.setIcon(tI); 
			withdraw = 38;
			
			
			
			break;
		case 39://�û������ �Ҽ� �û�̫�������ƶ�
			
			
			if (datas[pass][wy][wx + 1] == 0) {
				wx = wx + 1;
				lab_wolf.setLocation(x + 50, y);
				//�����ƶ���ͼƬ����
				Icon rI = new ImageIcon("0-1.png");
				lab_wolf.setIcon(rI); 
				withdraw = 0;
				return;
			}
			
			if (datas[pass][wy][wx + 1] == 1) {
				withdraw = 0;
				return;//�����ϰ�����ʲô���鶼����
			}
			
			if (datas[pass][wy][wx + 1] == 4 && datas[pass][wy][wx + 2] == 1) {
				withdraw = 0;
				return;//�����ϰ�����ʲô���鶼����
			}
			
			if (datas[pass][wy][wx + 1] == 4 && datas[pass][wy][wx + 2] == 4) {
				withdraw = 0;
				return;//�����ϰ�����ʲô���鶼����
			}
			
			if (datas[pass][wy][wx + 1] == 4 && datas[pass][wy][wx + 2] == 12) {
				withdraw = 0;
				return;//�����ϰ�����ʲô���鶼����
			}
			
			if (datas[pass][wy][wx + 1] == 12 && datas[pass][wy][wx + 2] == 1) {
				withdraw = 0;
				return;//�����ϰ�����ʲô���鶼����
			}
			
			if (datas[pass][wy][wx + 1] == 12 && datas[pass][wy][wx + 2] == 4) {
				withdraw = 0;
				return;//�����ϰ�����ʲô���鶼����
			}
			
			if (datas[pass][wy][wx + 1] == 12 && datas[pass][wy][wx + 2] == 12) {
				withdraw = 0;
				return;//�����ϰ�����ʲô���鶼����
			}
			
			if (datas[pass][wy][wx + 1] == 8) {
				wx = wx + 1;
				lab_wolf.setLocation(x + 50, y);
				//�����ƶ���ͼƬ����
				Icon rI = new ImageIcon("0-1.png");
				lab_wolf.setIcon(rI); 
				withdraw = 0;
				return;
			}
			
			if (datas[pass][wy][wx + 1] == 4 && datas[pass][wy][wx + 2] == 0) {
				//����λ�õ�ֵ�ƶ�
				datas[pass][wy][wx + 1] = 0;
				datas[pass][wy][wx + 2] = 4;
				
			}
			
			if (datas[pass][wy][wx + 1] == 4 && datas[pass][wy][wx + 2] == 8) {
				//����λ�õ�ֵ�ƶ�
				datas[pass][wy][wx + 1] = 0;
				datas[pass][wy][wx + 2] = 12;
				num++;
			}
			
			if (datas[pass][wy][wx + 1] == 12 && datas[pass][wy][wx + 2] == 0) {
				//����λ�õ�ֵ�ƶ�
				datas[pass][wy][wx + 1] = 8;
				datas[pass][wy][wx + 2] = 4;
				num--;
			}
			
			if (datas[pass][wy][wx + 1] == 12 && datas[pass][wy][wx + 2] == 8) {
				//����λ�õ�ֵ�ƶ�
				datas[pass][wy][wx + 1] = 8;
				datas[pass][wy][wx + 2] = 12;
			}
			
			//������ʾλ���ƶ�
			sheeps[wy][wx + 1].setLocation(12 + wx * 50 + 100, 36 + wy * 50);
			sheeps[wy][wx + 2] = sheeps[wy][wx + 1];
			sheeps[wy][wx + 1] = null;
			wx = wx + 1;
			lab_wolf.setLocation(x + 50, y);
			//�����ƶ���ͼƬ����
			Icon rI = new ImageIcon("0-1.png");
			lab_wolf.setIcon(rI); 
			withdraw = 39;
			
			
			
			break;
		case 40://�û������ �¼� �û�̫�������ƶ�
			
			
			
			if (datas[pass][wy + 1][wx] == 0) {
				wy = wy + 1;
				lab_wolf.setLocation(x , y + 50);
				//�����ƶ���ͼƬ����
				Icon dI = new ImageIcon("wolf-zm.png");
				lab_wolf.setIcon(dI);
				withdraw = 0;
				return;
			}
			
			if (datas[pass][wy + 1][wx] == 1) {
				withdraw = 0;
				return;//�����ϰ�����ʲô���鶼����
			}
			
			if (datas[pass][wy + 1][wx] == 4 && datas[pass][wy + 2][wx] == 1) {
				withdraw = 0;
				return;//�����ϰ�����ʲô���鶼����
			}
			
			if (datas[pass][wy + 1][wx] == 4 && datas[pass][wy + 2][wx] == 4) {
				withdraw = 0;
				return;//�����ϰ�����ʲô���鶼����
			}
			
			if (datas[pass][wy + 1][wx] == 4 && datas[pass][wy + 2][wx] == 12) {
				withdraw = 0;
				return;//�����ϰ�����ʲô���鶼����
			}
			
			if (datas[pass][wy + 1][wx] == 12 && datas[pass][wy + 2][wx] == 1) {
				withdraw = 0;
				return;//�����ϰ�����ʲô���鶼����
			}
			
			if (datas[pass][wy + 1][wx] == 12 && datas[pass][wy + 2][wx] == 4) {
				withdraw = 0;
				return;//�����ϰ�����ʲô���鶼����
			}
			
			if (datas[pass][wy + 1][wx] == 12 && datas[pass][wy + 2][wx] == 12) {
				withdraw = 0;
				return;//�����ϰ�����ʲô���鶼����
			}
			
			if (datas[pass][wy + 1][wx] == 8) {
				wy = wy + 1;
				lab_wolf.setLocation(x, y + 50);
				//�����ƶ���ͼƬ����
				Icon dI = new ImageIcon("wolf-zm.png");
				lab_wolf.setIcon(dI); 
				withdraw = 0;
				return;
			}
			
			if (datas[pass][wy + 1][wx] == 4 && datas[pass][wy + 2][wx] == 0) {
				//����λ�õ�ֵ�ƶ�
				datas[pass][wy + 1][wx] = 0;
				datas[pass][wy + 2][wx] = 4;
				
			}
			
			if (datas[pass][wy + 1][wx] == 4 && datas[pass][wy + 2][wx] == 8) {
				//����λ�õ�ֵ�ƶ�
				datas[pass][wy + 1][wx] = 0;
				datas[pass][wy + 2][wx] = 12;
				
				num++;
			}
			
			if (datas[pass][wy + 1][wx] == 12 && datas[pass][wy + 2][wx] == 0) {
				//����λ�õ�ֵ�ƶ�
				datas[pass][wy + 1][wx] = 8;
				datas[pass][wy + 2][wx] = 4;
				
				num--;
			}
			
			if (datas[pass][wy + 1][wx] == 12 && datas[pass][wy + 2][wx] == 8) {
				//����λ�õ�ֵ�ƶ�
				datas[pass][wy + 1][wx] = 8;
				datas[pass][wy + 2][wx] = 12;
				
			}
			
			//������ʾλ���ƶ�
			sheeps[wy + 1][wx].setLocation(12 + wx * 50 , 36 + wy * 50 + 100);
			sheeps[wy + 2][wx] = sheeps[wy + 1][wx];
			sheeps[wy + 1][wx] = null;
			wy = wy + 1;
			lab_wolf.setLocation(x, y + 50);
			//�����ƶ���ͼƬ����
			Icon dI = new ImageIcon("wolf-zm.png");
			lab_wolf.setIcon(dI); 
			withdraw = 40;
			break;
		case 10:
			
			switch (withdraw) {
			case 37:
				
				if (datas[pass][wy][wx - 1] == 4 && datas[pass][wy][wx] == 0) {
					//����λ�õ�ֵ�ƶ�
					datas[pass][wy][wx - 1] = 0;
					datas[pass][wy][wx] = 4;
					
				}
				
				if (datas[pass][wy][wx - 1] == 4 && datas[pass][wy][wx] == 8) {
					//����λ�õ�ֵ�ƶ�
					datas[pass][wy][wx - 1] = 0;
					datas[pass][wy][wx] = 12;
					num++;
				}
				
				if (datas[pass][wy][wx - 1] == 12 && datas[pass][wy][wx] == 0) {
					//����λ�õ�ֵ�ƶ�
					datas[pass][wy][wx - 1] = 8;
					datas[pass][wy][wx] = 4;
					num--;
				}
				
				if (datas[pass][wy][wx - 1] == 12 && datas[pass][wy][wx] == 8) {
					//����λ�õ�ֵ�ƶ�
					datas[pass][wy][wx - 1] = 8;
					datas[pass][wy][wx] = 12;
				}
				
				
				if (datas[pass][wy][wx] == 4 || datas[pass][wy][wx] == 12 ) {
					//������ʾλ���ƶ�
					sheeps[wy][wx - 1].setLocation(12 + wx * 50 , 36 + wy * 50);
					sheeps[wy][wx] = sheeps[wy][wx - 1];
					sheeps[wy][wx - 1] = null;
				}
				
				
				wx = wx + 1;
				lab_wolf.setLocation(x + 50, y);
				//�����ƶ���ͼƬ����
				Icon lI2 = new ImageIcon("01.png");
				lab_wolf.setIcon(lI2);
				
				withdraw = 0;
				
				break;
				
				//--------------------------------------------------��37-------------------------------
			case 38://�û������ �ϼ� �û�̫�������ƶ�
				
				
				if (datas[pass][wy - 1][wx] == 4 && datas[pass][wy][wx] == 0) {
					//����λ�õ�ֵ�ƶ�
					datas[pass][wy - 1][wx] = 0;
					datas[pass][wy][wx] = 4;
					
				}
				
				if (datas[pass][wy - 1][wx] == 4 && datas[pass][wy][wx] == 8) {
					//����λ�õ�ֵ�ƶ�
					datas[pass][wy - 1][wx] = 0;
					datas[pass][wy][wx] = 12;
					sheeps[wy - 1][wx].setLocation(12 + wx * 50 , 36 + wy * 50);
					sheeps[wy][wx] = sheeps[wy - 1][wx];
					sheeps[wy - 1][wx] = null;
					num++;
				}
				
				if (datas[pass][wy - 1][wx] == 12 && datas[pass][wy][wx] == 0) {
					//����λ�õ�ֵ�ƶ�
					datas[pass][wy - 1][wx] = 8;
					datas[pass][wy][wx] = 4;
					num--;
				}
				
				if (datas[pass][wy - 1][wx] == 12 && datas[pass][wy][wx] == 8) {
					//����λ�õ�ֵ�ƶ�
					datas[pass][wy - 1][wx] = 8;
					datas[pass][wy][wx] = 12;
					sheeps[wy - 1][wx].setLocation(12 + wx * 50 , 36 + wy * 50);
					sheeps[wy][wx] = sheeps[wy - 1][wx];
					sheeps[wy - 1][wx] = null;
				}
				
				if (datas[pass][wy][wx] == 4 || datas[pass][wy - 1][wx] == 12 ) {
					System.out.println("���ϳ���-----------------");
					//������ʾλ���ƶ�
					sheeps[wy - 1][wx].setLocation(12 + wx * 50 , 36 + wy * 50);
					sheeps[wy][wx] = sheeps[wy - 1][wx];
					sheeps[wy - 1][wx] = null;
				}
				
				wy = wy + 1;
				lab_wolf.setLocation(x, y + 50);
				//�����ƶ���ͼƬ����
				Icon tI2 = new ImageIcon("10.png");
				lab_wolf.setIcon(tI2);
				
				withdraw = 0;
				
				break;
				
				//--------------------------------------------------��39-------------------------------

			case 39:
				
				if (datas[pass][wy][wx + 1] == 4 && datas[pass][wy][wx] == 0) {
					//����λ�õ�ֵ�ƶ�
					datas[pass][wy][wx + 1] = 0;
					datas[pass][wy][wx] = 4;
					
				}
				
				if (datas[pass][wy][wx + 1] == 4 && datas[pass][wy][wx] == 8) {
					//����λ�õ�ֵ�ƶ�
					datas[pass][wy][wx + 1] = 0;
					datas[pass][wy][wx] = 12;
					//������ʾλ���ƶ�
					sheeps[wy][wx + 1].setLocation(12 + wx * 50 , 36 + wy * 50);
					sheeps[wy][wx] = sheeps[wy][wx + 1];
					sheeps[wy][wx + 1] = null;
					num++;
				}
				
				if (datas[pass][wy][wx + 1] == 12 && datas[pass][wy][wx] == 0) {
					//����λ�õ�ֵ�ƶ�
					datas[pass][wy][wx + 1] = 8;
					datas[pass][wy][wx] = 4;
					num--;
				}
				
				if (datas[pass][wy][wx + 1] == 12 && datas[pass][wy][wx] == 8) {
					//����λ�õ�ֵ�ƶ�
					datas[pass][wy][wx + 1] = 8;
					datas[pass][wy][wx] = 12;
					//������ʾλ���ƶ�
					sheeps[wy][wx + 1].setLocation(12 + wx * 50 , 36 + wy * 50);
					sheeps[wy][wx] = sheeps[wy][wx + 1];
					sheeps[wy][wx + 1] = null;
				}
				
				if (datas[pass][wy][wx] == 4 || datas[pass][wy][wx + 1] == 12 ) {
					System.out.println("9999999999999999999999999");
					//������ʾλ���ƶ�
					sheeps[wy][wx + 1].setLocation(12 + wx * 50 , 36 + wy * 50);
					sheeps[wy][wx] = sheeps[wy][wx + 1];
					sheeps[wy][wx + 1] = null;
				}
				
				
				wx = wx - 1;
				lab_wolf.setLocation(x - 50, y);
				//�����ƶ���ͼƬ����
				Icon rI2 = new ImageIcon("0-1.png");
				lab_wolf.setIcon(rI2);
				
				withdraw = 0;
				
				break;
				//--------------------------------------------------��38-------------------------------
			case 40://�û������ �¼� �û�̫�������ƶ�
				
				
				if (datas[pass][wy + 1][wx] == 4 && datas[pass][wy][wx] == 0) {
					//����λ�õ�ֵ�ƶ�
					datas[pass][wy + 1][wx] = 0;
					datas[pass][wy][wx] = 4;
					
				}
				
				if (datas[pass][wy + 1][wx] == 4 && datas[pass][wy][wx] == 8) {
					//����λ�õ�ֵ�ƶ�
					datas[pass][wy + 1][wx] = 0;
					datas[pass][wy][wx] = 12;
					sheeps[wy + 1][wx].setLocation(12 + wx * 50 , 36 + wy * 50);
					sheeps[wy][wx] = sheeps[wy + 1][wx];
					sheeps[wy + 1][wx] = null;
					num++;
				}
				
				if (datas[pass][wy + 1][wx] == 12 && datas[pass][wy][wx] == 0) {
					//����λ�õ�ֵ�ƶ�
					datas[pass][wy + 1][wx] = 8;
					datas[pass][wy][wx] = 4;
					num--;
				}
				
				if (datas[pass][wy + 1][wx] == 12 && datas[pass][wy][wx] == 8) {
					//����λ�õ�ֵ�ƶ�
					datas[pass][wy + 1][wx] = 8;
					datas[pass][wy][wx] = 12;
					sheeps[wy + 1][wx].setLocation(12 + wx * 50 , 36 + wy * 50);
					sheeps[wy][wx] = sheeps[wy + 1][wx];
					sheeps[wy + 1][wx] = null;
				}
				
				if (datas[pass][wy][wx] == 4 || datas[pass][wy + 1][wx] == 12 ) {
					System.out.println("���³���-----------------");
					//������ʾλ���ƶ�
					sheeps[wy + 1][wx].setLocation(12 + wx * 50 , 36 + wy * 50);
					sheeps[wy][wx] = sheeps[wy + 1][wx];
					sheeps[wy + 1][wx] = null;
				}
				
				wy = wy - 1;
				lab_wolf.setLocation(x, y - 50);
				//�����ƶ���ͼƬ����
				Icon dI2 = new ImageIcon("wolf-zm.png");
				lab_wolf.setIcon(dI2);
				
				withdraw = 0;
				
				break;
			}
		
			break;
		case 27://�û�����˳�ʼ�� Esc
			
			newStart();
			
			break;	
		}
		
		
		//�ж�ʤ��
		victory();
	}
	
	
	
	/**�ж����ÿ���Ƿ�ʤ��
	 * @throws InterruptedException */
	private void victory() {
		
			
		System.out.println(num);
		
		
		if (num >= total) {
			if (pass == datas.length - 1) {
				System.out.println("�û� ͨ�����һ��");
				
			} else {
			System.out.println("��"+ pass +"��ʤ��");
			//���һ��  ���������DiaLog
			if (pass > datas.length - 1) {
				pass = 0;//���һ��ͨ��������1��
			}else {
				//����ƽ���Ĺܿ�	
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
