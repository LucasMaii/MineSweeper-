package myc.minesweeper;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import myc.snake.SnakeFrame;



public class MineSweepers extends JFrame{
	
	static private int mineNum = 0;//���õ�������
	private static int mytime = 300;
	private static ImageIcon face0 = new ImageIcon("face.jpg");//����Ц��ͼƬ
	private static ImageIcon face = new ImageIcon("smileface.png");//����Ц��ͼƬ
	static private MainStart gamepanel;//��Ϸ����
//	static private JFrame gameWindows;
	static private JFrame frame;
	
	Image view = new ImageIcon("view.jpg").getImage();
	ImageIcon clock0 = new ImageIcon("images/clock.png");
	private static ImageIcon clock = new ImageIcon("����.png");
    private static ImageIcon bombbomb = new ImageIcon("����.png");
	
	//��Ϸ����Ĳ������
	private static int row;
	private static int col;
	private static int level;
	private static int numOfMines;
	private static int simpleR = 275;
	private static int simpleC = 350;
	private static int difficultR = 455;
	private static int difficultC = 530;
	private static int hardR = 750;
	private static int hardC = 530;
	
	
	
	static JLabel timeLabel = new JLabel();//ʱ����ʾ
	static JLabel BombC = new JLabel();//����ʣ����ʾ
	
	private static JRadioButtonMenuItem item3;
	private static JRadioButtonMenuItem item4;
	private static JRadioButtonMenuItem item5;
	private static JRadioButtonMenuItem item11;
	private static JRadioButtonMenuItem item22;
	private static JRadioButtonMenuItem item33;
	
	//������Ϸ�Ѷ�
	
	private static int simple = 1;
	private static int difficult = 2;
	private static int hard = 3;
	private static int GameLevel = difficult;
	
	
	public MineSweepers() {
		
		//������Ϸ����
		frame = new JFrame("ɨ��");
		 frame.getContentPane().setBackground(new Color(202,235,216));
		    
		level = 2;//��ʼ��Ϸ�Ѷ��趨Ϊ��
		row = 16;
		col = 16;
		
		frame.setIconImage(Toolkit.getDefaultToolkit().createImage("bomb.jpg"));
		frame.setBounds(400,100,500,600);
		frame.setDefaultCloseOperation(3);
		frame.setResizable(false);
		frame.setLayout(null);
		
	   //ʱ��
		JButton showclock = new JButton(clock);
		showclock.setBounds(40,50,30,30);
		showclock.setBorderPainted(false);
		showclock.setBackground(new Color(202,235,216));
		frame.add(showclock);
		timeLabel.setFont(new Font("Proggy", Font.BOLD, 13));
		timeLabel.setText("ʱ�䣺" +(mytime / 60 / 60 % 60) + ":"+ (mytime / 60 % 60)+ ":" +(mytime % 60)); 
//		timeLabel.setText("ʱ��: 60s");
		timeLabel.setBounds(80,57,120,20);
		frame.add(timeLabel);
		//���׼�����
		JButton showbomb = new JButton(bombbomb);
		showbomb.setBounds(400,50,30,30);
		showbomb.setBorderPainted(false);
		showbomb.setBackground(new Color(202,235,216));
		frame.add(showbomb);	
		BombC.setFont(new Font("Proggy", Font.BOLD, 13));
//		BombC.setText("����ʣ��: 30");
		BombC.setBounds(320,57,120,20);
		frame.add(BombC);
		
		//�������¿�ʼ��Ϸ�İ�ť
		JButton bt = new JButton(face);
		bt.setBounds(220,50,30,30);
		bt.setBorderPainted(false);
		bt.setBackground(new Color(202,235,216));
		//ȥ����ť������Χ�Ľ����
		bt.setFocusPainted(false);
		
		//��Ӽ�����ť�������ʱ������newһ��MineSweeper,�Ӷ�ʵ����Ϸ���¿�ʼ
		 bt.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                frame.dispose();//���ȫ����Ļ
	                mytime = 300;
	                new MineSweepers();//���½�����Ϸ
	            }
	        });
		frame.add(bt);
		
		//����Ϸ�������JFrame,����Ϊn��n�С�
//		GameLevel = 1;
//		gamepanel = new MainStart(9, 9);
		
		int gameLevel = GameLevel;
		gamepanel = new MainStart(16, 16, 2);
		
//		gamepanel.setBounds(40,100,400,400);
		gamepanel.LevelsetBounds(gameLevel);
		frame.add(gamepanel);
		
		setUpMenu();
		//����Ϊ���ӻ�
		frame.setVisible(true);
		
		repaint();
	}
	
//	public void paint(Graphics g) {
//		
//		g.setColor(Color.BLUE);
//		g.fillRect(0, 0, 1000, 1000);
////		g.drawImage(view,0,0,null);
//		
//	}
	
	public static void setUpMenu(){
		JMenuBar menuBar = new JMenuBar();					
		JMenu menu = new JMenu("ѡ��");
		JMenuItem item = new JMenuItem("����Ϸ");
		item.addActionListener(ALnewgame);
		
		JMenuItem item6 = new JMenuItem("���а�");
//		item6.addActionListener(al6)
		
		JMenu menu2 = new JMenu("����");
		JMenuItem item2 = new JMenuItem("��Ϸ���");
		item2.addActionListener(ALhelp);
		
		JMenu menu3 = new JMenu("�Ѷ�");
		item3 = new JRadioButtonMenuItem("��+_+");
		item3.addActionListener(ALsimple);
		item4 = new JRadioButtonMenuItem("һ��>_<");
		item4.addActionListener(ALdifficult);
		item5 = new JRadioButtonMenuItem("����--_--");
		item5.addActionListener(ALhard);
		
		
		ButtonGroup group = new ButtonGroup();
		group.add(item3);
		group.add(item4);
		group.add(item5);
		
		item4.setSelected(true);
		
		JMenu menu4 = new JMenu("Ƥ��");
		JMenuItem item11 = new JRadioButtonMenuItem("Default");
		item11.addActionListener(ALdefault);
		JMenuItem item22 = new JRadioButtonMenuItem("Another");
		item22.addActionListener(ALone);
//		item33 = new JRadioButtonMenuItem("Two");
//		item33.addActionListener(ALtwo);
		
		menu.setOpaque(true);
		menu2.setOpaque(true);
		menu3.setOpaque(true);
		menu4.setOpaque(true);
		menu.setBackground(Color.WHITE);
		menu2.setBackground(Color.WHITE);
		menu3.setBackground(Color.WHITE);
		menu4.setBackground(Color.WHITE);
		
//		item11.setSelected(true);
		
		menu.add(item);
		menu.add(item6);
		menu2.add(item2);
		menu3.add(item3);
		menu3.add(item4);
		menu3.add(item5);
		
		menu4.add(item11);
		menu4.add(item22);
		
		menuBar.add(menu);
		menuBar.add(menu3);
		menuBar.add(menu2);
		menuBar.add(menu4);
		
	
		
		JButton tipmenu = new JButton("��ʾ");
		//�����Ʊ߿�
		tipmenu.setBorderPainted(false);
		tipmenu.setBackground(Color.WHITE);
		//ȥ����ť������Χ�Ľ����
		tipmenu.setFocusPainted(false);
		menuBar.add(tipmenu);
		tipmenu.addActionListener(tip);
		
		JButton AiSwepper = new JButton("AIɨ��(ûд��>_<)");
		//�����Ʊ߿�
		AiSwepper.setBorderPainted(false);
		AiSwepper.setBackground(Color.WHITE);
		//ȥ����ť������Χ�Ľ����
		AiSwepper.setFocusPainted(false);
		menuBar.add(AiSwepper);
		AiSwepper.addActionListener(AiSwp);
		
		JButton snake = new JButton("̰����");
		//�����Ʊ߿�
		snake.setBorderPainted(false);
		snake.setBackground(Color.WHITE);
		//ȥ����ť������Χ�Ľ����
		snake.setFocusPainted(false);
		menuBar.add(snake);
		snake.addActionListener(ALsnake);
		
		
		
//		tipmenu.addActionListener(new ActionListener() {
//	        @Override
//	        public void actionPerformed(ActionEvent e) {
////	        	System.out.println("����˰�ť");
//	        	
//				MainStart.tipOpen();
//	        }
//	    });
		
 		frame.setJMenuBar(menuBar);	
		
	
	}
	
	/* ����ʱ�߳� */
    static class CountTime extends Thread{
        public void run(){
            while (mytime > 0){
                try{
                	-- mytime;
                    timeLabel.setText("ʱ�䣺" +(mytime / 60 / 60 % 60) + ":"+ (mytime / 60 % 60)+ ":" +(mytime % 60));
                    Thread.sleep(1000);
                }catch (Exception e){
                    System.out.println("����" + e.toString());
                }
            }
//            if(mytime == 0) {
//                gp.showBomb();
//                JOptionPane.showMessageDialog(null,"ʱ���ѵ�","��Ϸ����",JOptionPane.PLAIN_MESSAGE);
//            }
        }
      }
	

	public static void setMineNum(int flagNum) {
		
		mineNum = flagNum;
		BombC.setText("����ʣ��:"+mineNum);
		
	}
	
	public static void resetGameLevel(int gameLevel) {
		
		//���ԭ�Ȱ�ť���޷�Ӧ
//		if(gameLevel == level) {
//			return;
//		}
		
//		int winRow = simpleR;
//		int winCol = simpleC;
		
		if(gameLevel == 1){
			row = 9;
			col = 9;
//			numOfMines = 10;
//			winRow = simpleR;
//			winCol = simpleC;
		}
		else if(gameLevel == 2){
			row = 16;
			col = 16;
//			numOfMines = 40;
//			winRow = difficultR;
//			winCol = difficultC;
		}
		else if(gameLevel == 3){
			row = 16;
			col = 30;
//			numOfMines = 99;
//			winRow = hardR;
//			winCol = hardC;
		}
		
		frame.dispose();//���ȫ����Ļ
        
		
		
      //������Ϸ����
      		frame = new JFrame("ɨ��");
    		frame.setIconImage(Toolkit.getDefaultToolkit().createImage("bomb.jpg"));
      		level = gameLevel;
      		frame.setResizable(false);
      		 frame.getContentPane().setBackground(new Color(202,235,216));
      		
      		frame.setDefaultCloseOperation(3);
      		frame.setLayout(null);
      		
      		 //ʱ��
    		JButton showclock = new JButton(clock);
    		showclock.setBounds(40,50,30,30);
    		showclock.setBorderPainted(false);
    		showclock.setBackground(new Color(202,235,216));
    		frame.add(showclock);
    		timeLabel.setFont(new Font("Proggy", Font.BOLD, 13));
    		timeLabel.setText("ʱ�䣺" +(mytime / 60 / 60 % 60) + ":"+ (mytime / 60 % 60)+ ":" +(mytime % 60)); 
//    		timeLabel.setText("ʱ��: 60s");
    		timeLabel.setBounds(80,57,120,20);
    		frame.add(timeLabel);
    		//���׼�����
    		JButton showbomb = new JButton(bombbomb);

            
      		if(level == 1 || level == 2) {
      			showbomb = new JButton(bombbomb);
        		showbomb.setBounds(400,50,30,30);
        		showbomb.setBorderPainted(false);
        		showbomb.setBackground(new Color(202,235,216));
        		frame.add(showbomb);	
      			BombC.setBounds(320,57,120,20);
          		frame.add(BombC);
          		
          		frame.setBounds(400,100,500,600);
          		
          	//�������¿�ʼ��Ϸ�İ�ť
        		JButton bt = new JButton(face);
        		bt.setBounds(220,50,30,30);
        		bt.setBorderPainted(false);
        		bt.setBackground(new Color(202,235,216));
        		//ȥ����ť������Χ�Ľ����
        		bt.setFocusPainted(false);
          		
          	//��Ӽ�����ť�������ʱ������newһ��MineSweeper,�Ӷ�ʵ����Ϸ���¿�ʼ
         		 bt.addActionListener(new ActionListener() {
         	            public void actionPerformed(ActionEvent e) {
         	                frame.dispose();//���ȫ����Ļ
         	                mytime = 300;
         	                resetGameLevel(level);//���½�����Ϸ
         	            }
         	        });
         		frame.add(bt);
      		}
      		
      		if(level == 3) {
      			frame.setBounds(200,100,850,600);
      			showbomb = new JButton(bombbomb);
        		showbomb.setBounds(760,50,30,30);
        		showbomb.setBorderPainted(false);
        		showbomb.setBackground(new Color(202,235,216));
        		frame.add(showbomb);	
      			BombC.setBounds(680,57,120,20);
          		frame.add(BombC);
          		
 	        //�������¿�ʼ��Ϸ�İ�ť
          		
          		JButton bt = new JButton(face);
          		bt.setBounds(400,50,30,30);
          		bt.setBorderPainted(false);
        		bt.setBackground(new Color(202,235,216));
          	//��Ӽ�����ť�������ʱ������newһ��MineSweeper,�Ӷ�ʵ����Ϸ���¿�ʼ
         		 bt.addActionListener(new ActionListener() {
         	            public void actionPerformed(ActionEvent e) {
         	                frame.dispose();//���ȫ����Ļ
         	                mytime = 300;
         	                resetGameLevel(level);//���½�����Ϸ
         	            }
         	        });
         		frame.add(bt);
      
      		}
      		
      		
      		//����Ϸ�������JFrame,����Ϊn��n�С�
      		
      		if(level == 1) {
      			gamepanel = new MainStart(9, 9, 1);
      		}
      		if(level == 2) {
      			gamepanel = new MainStart(16, 16, 2);
      		}
      		if(level == 3) {
      			gamepanel = new MainStart(16, 30, 3);
      		}
      		
      		gamepanel.LevelsetBounds(gameLevel);
//      		MainStart.tipOpen();
      		frame.add(gamepanel);
      		
      		//�˵���
      		setUpMenu();
      		if(level == 1) {
      			item3.setSelected(true);
      		}
      		if(level == 2) {
      			item4.setSelected(true);
      		}
      		if(level == 3) {
      			item5.setSelected(true);
      		}
      		
      		
      		//����Ϊ���ӻ�
      		frame.setVisible(true);
		
//        new MineSweepers();//���½�����Ϸ
		
	}
	
/*****	
****---------------------------ActionListener---------------------------------------
*****/
	static ActionListener ALnewgame = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			
			frame.dispose();//���ȫ����Ļ
            mytime = 300;
            new MineSweepers();//���½�����Ϸ
            
		}
	};
	
	//���Ѷ�
	static ActionListener ALsimple = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			resetGameLevel(1);
		}
	};
	
	//һ���Ѷ�
    static ActionListener ALdifficult = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			
			resetGameLevel(2);
		}
	};
	
	//һ���Ѷ�
    static ActionListener ALhard = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			resetGameLevel(3);
			
		}
	};
	
	
	 static ActionListener tip = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			MainStart.tipOpen();
		}
	}; 

	static ActionListener ALhelp = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			new Help();
		}
	}; 
	
	static ActionListener AiSwp = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			MainStart.luckyOpen();
		}
	}; 
	
	static ActionListener ALsnake = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			new SnakeFrame();
		
		}
	}; 
	
	static ActionListener ALdefault = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			try {
				resetGameSkin(1);
			} catch (ClassNotFoundException e1) {
				// TODO �Զ����ɵ� catch ��
				e1.printStackTrace();
			} catch (InstantiationException e1) {
				// TODO �Զ����ɵ� catch ��
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				// TODO �Զ����ɵ� catch ��
				e1.printStackTrace();
			} catch (UnsupportedLookAndFeelException e1) {
				// TODO �Զ����ɵ� catch ��
				e1.printStackTrace();
			}
		}
	}; 
	static ActionListener ALone = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			try {
				resetGameSkin(2);
			} catch (ClassNotFoundException e1) {
				// TODO �Զ����ɵ� catch ��
				e1.printStackTrace();
			} catch (InstantiationException e1) {
				// TODO �Զ����ɵ� catch ��
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				// TODO �Զ����ɵ� catch ��
				e1.printStackTrace();
			} catch (UnsupportedLookAndFeelException e1) {
				// TODO �Զ����ɵ� catch ��
				e1.printStackTrace();
			}
		}
	}; 
	static ActionListener ALtwo = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			try {
				resetGameSkin(3);
			} catch (ClassNotFoundException e1) {
				// TODO �Զ����ɵ� catch ��
				e1.printStackTrace();
			} catch (InstantiationException e1) {
				// TODO �Զ����ɵ� catch ��
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				// TODO �Զ����ɵ� catch ��
				e1.printStackTrace();
			} catch (UnsupportedLookAndFeelException e1) {
				// TODO �Զ����ɵ� catch ��
				e1.printStackTrace();
			}
		}
	}; 


	
	//��Ϸ���
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		
//		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//		UIManager.setLookAndFeel(com.sun.java.swing.plaf.windows.WindowsLookAndFeel.class.getName());//�����ԣ�ֻ����windowsϵͳ���ã�Ҳ���������벻��ƽ̨
//		UIManager.setLookAndFeel(javax.swing.plaf.nimbus.NimbusLookAndFeel.class.getName());//������
//		UIManager.setLookAndFeel(javax.swing.plaf.basic.BasicLookAndFeel.class.getName());//swingĬ�ϵĳ�Ƥ��
//		UIManager.setLookAndFeel(javax.swing.plaf.metal.MetalLookAndFeel.class.getName());//swingĬ�ϵĳ�Ƥ��
//		UIManager.setLookAndFeel(com.sun.java.swing.plaf.motif.MotifLookAndFeel.class.getName());//��
//		UIManager.setLookAndFeel(com.sun.java.swing.plaf.gtk.GTKLookAndFeel.class.getName());//������
		
		//�½���Ϸ����
		new MineSweepers();
		
		CountTime cd = new CountTime();
	       cd.start();
	       
	}

	protected static void resetGameSkin(int i) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		
		if(i == 1) {
			UIManager.setLookAndFeel(javax.swing.plaf.metal.MetalLookAndFeel.class.getName());//swingĬ�ϵĳ�Ƥ��
			
			resetGameLevel(2);
		}
		else if(i == 2) {
			
			UIManager.setLookAndFeel(javax.swing.plaf.nimbus.NimbusLookAndFeel.class.getName());//������
			resetGameLevel(2);
		}
		else if(i == 3) {
			
			
			resetGameLevel(2);
		}
		
		
	}
	
}


