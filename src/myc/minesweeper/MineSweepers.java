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
	
	static private int mineNum = 0;//可用的旗子数
	private static int mytime = 300;
	private static ImageIcon face0 = new ImageIcon("face.jpg");//引入笑脸图片
	private static ImageIcon face = new ImageIcon("smileface.png");//引入笑脸图片
	static private MainStart gamepanel;//游戏区域
//	static private JFrame gameWindows;
	static private JFrame frame;
	
	Image view = new ImageIcon("view.jpg").getImage();
	ImageIcon clock0 = new ImageIcon("images/clock.png");
	private static ImageIcon clock = new ImageIcon("闹钟.png");
    private static ImageIcon bombbomb = new ImageIcon("地雷.png");
	
	//游戏界面的参数设计
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
	
	
	
	static JLabel timeLabel = new JLabel();//时间显示
	static JLabel BombC = new JLabel();//地雷剩余显示
	
	private static JRadioButtonMenuItem item3;
	private static JRadioButtonMenuItem item4;
	private static JRadioButtonMenuItem item5;
	private static JRadioButtonMenuItem item11;
	private static JRadioButtonMenuItem item22;
	private static JRadioButtonMenuItem item33;
	
	//设置游戏难度
	
	private static int simple = 1;
	private static int difficult = 2;
	private static int hard = 3;
	private static int GameLevel = difficult;
	
	
	public MineSweepers() {
		
		//创建游戏窗口
		frame = new JFrame("扫雷");
		 frame.getContentPane().setBackground(new Color(202,235,216));
		    
		level = 2;//初始游戏难度设定为简单
		row = 16;
		col = 16;
		
		frame.setIconImage(Toolkit.getDefaultToolkit().createImage("bomb.jpg"));
		frame.setBounds(400,100,500,600);
		frame.setDefaultCloseOperation(3);
		frame.setResizable(false);
		frame.setLayout(null);
		
	   //时钟
		JButton showclock = new JButton(clock);
		showclock.setBounds(40,50,30,30);
		showclock.setBorderPainted(false);
		showclock.setBackground(new Color(202,235,216));
		frame.add(showclock);
		timeLabel.setFont(new Font("Proggy", Font.BOLD, 13));
		timeLabel.setText("时间：" +(mytime / 60 / 60 % 60) + ":"+ (mytime / 60 % 60)+ ":" +(mytime % 60)); 
//		timeLabel.setText("时间: 60s");
		timeLabel.setBounds(80,57,120,20);
		frame.add(timeLabel);
		//地雷计数君
		JButton showbomb = new JButton(bombbomb);
		showbomb.setBounds(400,50,30,30);
		showbomb.setBorderPainted(false);
		showbomb.setBackground(new Color(202,235,216));
		frame.add(showbomb);	
		BombC.setFont(new Font("Proggy", Font.BOLD, 13));
//		BombC.setText("地雷剩余: 30");
		BombC.setBounds(320,57,120,20);
		frame.add(BombC);
		
		//创建重新开始游戏的按钮
		JButton bt = new JButton(face);
		bt.setBounds(220,50,30,30);
		bt.setBorderPainted(false);
		bt.setBackground(new Color(202,235,216));
		//去掉按钮文字周围的焦点框
		bt.setFocusPainted(false);
		
		//添加监听按钮，当点击时，重新new一个MineSweeper,从而实现游戏重新开始
		 bt.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                frame.dispose();//清除全部屏幕
	                mytime = 300;
	                new MineSweepers();//重新建立游戏
	            }
	        });
		frame.add(bt);
		
		//将游戏区域加入JFrame,设置为n行n列。
//		GameLevel = 1;
//		gamepanel = new MainStart(9, 9);
		
		int gameLevel = GameLevel;
		gamepanel = new MainStart(16, 16, 2);
		
//		gamepanel.setBounds(40,100,400,400);
		gamepanel.LevelsetBounds(gameLevel);
		frame.add(gamepanel);
		
		setUpMenu();
		//设置为可视化
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
		JMenu menu = new JMenu("选项");
		JMenuItem item = new JMenuItem("新游戏");
		item.addActionListener(ALnewgame);
		
		JMenuItem item6 = new JMenuItem("排行榜");
//		item6.addActionListener(al6)
		
		JMenu menu2 = new JMenu("关于");
		JMenuItem item2 = new JMenuItem("游戏相关");
		item2.addActionListener(ALhelp);
		
		JMenu menu3 = new JMenu("难度");
		item3 = new JRadioButtonMenuItem("简单+_+");
		item3.addActionListener(ALsimple);
		item4 = new JRadioButtonMenuItem("一般>_<");
		item4.addActionListener(ALdifficult);
		item5 = new JRadioButtonMenuItem("困难--_--");
		item5.addActionListener(ALhard);
		
		
		ButtonGroup group = new ButtonGroup();
		group.add(item3);
		group.add(item4);
		group.add(item5);
		
		item4.setSelected(true);
		
		JMenu menu4 = new JMenu("皮肤");
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
		
	
		
		JButton tipmenu = new JButton("提示");
		//不绘制边框
		tipmenu.setBorderPainted(false);
		tipmenu.setBackground(Color.WHITE);
		//去掉按钮文字周围的焦点框
		tipmenu.setFocusPainted(false);
		menuBar.add(tipmenu);
		tipmenu.addActionListener(tip);
		
		JButton AiSwepper = new JButton("AI扫雷(没写好>_<)");
		//不绘制边框
		AiSwepper.setBorderPainted(false);
		AiSwepper.setBackground(Color.WHITE);
		//去掉按钮文字周围的焦点框
		AiSwepper.setFocusPainted(false);
		menuBar.add(AiSwepper);
		AiSwepper.addActionListener(AiSwp);
		
		JButton snake = new JButton("贪吃蛇");
		//不绘制边框
		snake.setBorderPainted(false);
		snake.setBackground(Color.WHITE);
		//去掉按钮文字周围的焦点框
		snake.setFocusPainted(false);
		menuBar.add(snake);
		snake.addActionListener(ALsnake);
		
		
		
//		tipmenu.addActionListener(new ActionListener() {
//	        @Override
//	        public void actionPerformed(ActionEvent e) {
////	        	System.out.println("点击了按钮");
//	        	
//				MainStart.tipOpen();
//	        }
//	    });
		
 		frame.setJMenuBar(menuBar);	
		
	
	}
	
	/* 倒计时线程 */
    static class CountTime extends Thread{
        public void run(){
            while (mytime > 0){
                try{
                	-- mytime;
                    timeLabel.setText("时间：" +(mytime / 60 / 60 % 60) + ":"+ (mytime / 60 % 60)+ ":" +(mytime % 60));
                    Thread.sleep(1000);
                }catch (Exception e){
                    System.out.println("错误：" + e.toString());
                }
            }
//            if(mytime == 0) {
//                gp.showBomb();
//                JOptionPane.showMessageDialog(null,"时间已到","游戏结束",JOptionPane.PLAIN_MESSAGE);
//            }
        }
      }
	

	public static void setMineNum(int flagNum) {
		
		mineNum = flagNum;
		BombC.setText("地雷剩余:"+mineNum);
		
	}
	
	public static void resetGameLevel(int gameLevel) {
		
		//点击原先按钮则无反应
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
		
		frame.dispose();//清除全部屏幕
        
		
		
      //创建游戏窗口
      		frame = new JFrame("扫雷");
    		frame.setIconImage(Toolkit.getDefaultToolkit().createImage("bomb.jpg"));
      		level = gameLevel;
      		frame.setResizable(false);
      		 frame.getContentPane().setBackground(new Color(202,235,216));
      		
      		frame.setDefaultCloseOperation(3);
      		frame.setLayout(null);
      		
      		 //时钟
    		JButton showclock = new JButton(clock);
    		showclock.setBounds(40,50,30,30);
    		showclock.setBorderPainted(false);
    		showclock.setBackground(new Color(202,235,216));
    		frame.add(showclock);
    		timeLabel.setFont(new Font("Proggy", Font.BOLD, 13));
    		timeLabel.setText("时间：" +(mytime / 60 / 60 % 60) + ":"+ (mytime / 60 % 60)+ ":" +(mytime % 60)); 
//    		timeLabel.setText("时间: 60s");
    		timeLabel.setBounds(80,57,120,20);
    		frame.add(timeLabel);
    		//地雷计数君
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
          		
          	//创建重新开始游戏的按钮
        		JButton bt = new JButton(face);
        		bt.setBounds(220,50,30,30);
        		bt.setBorderPainted(false);
        		bt.setBackground(new Color(202,235,216));
        		//去掉按钮文字周围的焦点框
        		bt.setFocusPainted(false);
          		
          	//添加监听按钮，当点击时，重新new一个MineSweeper,从而实现游戏重新开始
         		 bt.addActionListener(new ActionListener() {
         	            public void actionPerformed(ActionEvent e) {
         	                frame.dispose();//清除全部屏幕
         	                mytime = 300;
         	                resetGameLevel(level);//重新建立游戏
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
          		
 	        //创建重新开始游戏的按钮
          		
          		JButton bt = new JButton(face);
          		bt.setBounds(400,50,30,30);
          		bt.setBorderPainted(false);
        		bt.setBackground(new Color(202,235,216));
          	//添加监听按钮，当点击时，重新new一个MineSweeper,从而实现游戏重新开始
         		 bt.addActionListener(new ActionListener() {
         	            public void actionPerformed(ActionEvent e) {
         	                frame.dispose();//清除全部屏幕
         	                mytime = 300;
         	                resetGameLevel(level);//重新建立游戏
         	            }
         	        });
         		frame.add(bt);
      
      		}
      		
      		
      		//将游戏区域加入JFrame,设置为n行n列。
      		
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
      		
      		//菜单栏
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
      		
      		
      		//设置为可视化
      		frame.setVisible(true);
		
//        new MineSweepers();//重新建立游戏
		
	}
	
/*****	
****---------------------------ActionListener---------------------------------------
*****/
	static ActionListener ALnewgame = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			
			frame.dispose();//清除全部屏幕
            mytime = 300;
            new MineSweepers();//重新建立游戏
            
		}
	};
	
	//简单难度
	static ActionListener ALsimple = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			resetGameLevel(1);
		}
	};
	
	//一般难度
    static ActionListener ALdifficult = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			
			resetGameLevel(2);
		}
	};
	
	//一般难度
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
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			} catch (InstantiationException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			} catch (UnsupportedLookAndFeelException e1) {
				// TODO 自动生成的 catch 块
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
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			} catch (InstantiationException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			} catch (UnsupportedLookAndFeelException e1) {
				// TODO 自动生成的 catch 块
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
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			} catch (InstantiationException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			} catch (UnsupportedLookAndFeelException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}
		}
	}; 


	
	//游戏入口
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		
//		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//		UIManager.setLookAndFeel(com.sun.java.swing.plaf.windows.WindowsLookAndFeel.class.getName());//还可以，只能在windows系统中用，也就是这句代码不跨平台
//		UIManager.setLookAndFeel(javax.swing.plaf.nimbus.NimbusLookAndFeel.class.getName());//还可以
//		UIManager.setLookAndFeel(javax.swing.plaf.basic.BasicLookAndFeel.class.getName());//swing默认的丑皮肤
//		UIManager.setLookAndFeel(javax.swing.plaf.metal.MetalLookAndFeel.class.getName());//swing默认的丑皮肤
//		UIManager.setLookAndFeel(com.sun.java.swing.plaf.motif.MotifLookAndFeel.class.getName());//丑
//		UIManager.setLookAndFeel(com.sun.java.swing.plaf.gtk.GTKLookAndFeel.class.getName());//还可以
		
		//新建游戏对象
		new MineSweepers();
		
		CountTime cd = new CountTime();
	       cd.start();
	       
	}

	protected static void resetGameSkin(int i) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		
		if(i == 1) {
			UIManager.setLookAndFeel(javax.swing.plaf.metal.MetalLookAndFeel.class.getName());//swing默认的丑皮肤
			
			resetGameLevel(2);
		}
		else if(i == 2) {
			
			UIManager.setLookAndFeel(javax.swing.plaf.nimbus.NimbusLookAndFeel.class.getName());//还可以
			resetGameLevel(2);
		}
		else if(i == 3) {
			
			
			resetGameLevel(2);
		}
		
		
	}
	
}


