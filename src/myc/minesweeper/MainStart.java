package myc.minesweeper;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;


public class MainStart extends JPanel{
	
	private static final Component JPanel = null;
	private static int rows;
	private static int cols;
	private int bombCount;
	private static int flagNum;
    private final int BLOCKWIDTH = 25;
    private final int BLOCKHEIGHT = 25;
    private static JLabel[][] label;
    private static boolean[][] state;
    private static Btn[][] btns;
    private static byte[][] click;
    private static int[][] bombNumber;
    private static ImageIcon flag = new ImageIcon("flag.png");
    private static ImageIcon bomb = new ImageIcon("bomb.jpg");
    private static ImageIcon lucency = new ImageIcon("lucency.png");    
    private static ImageIcon one = new ImageIcon("one.jpg");
    private static ImageIcon grass = new ImageIcon("grass.png");


    
//    private static ImageIcon blank = new ImageIcon("blank.jpg");
    
 
    public MainStart(int row, int col,int level) {
        rows = row;/* 行数 */
        cols = col;/* 列数 */
        
        bombCount = bombCount(level); /* 地雷数 */
        flagNum = bombCount;/* 标记数（用于插旗） */
        label = new JLabel[rows][cols];
        state = new boolean[rows][cols];/* 用于存储是否有地雷 */
        btns = new Btn[rows][cols];
        click = new byte[rows][cols];/* 用于存储按钮点击状态（0-未点击，1-已点击，2-未点击但周围有雷，3-插旗） */
        bombNumber = new int[rows][cols];
        MineSweepers.setMineNum(flagNum);
        setLayout(null);
        
        initLable();
        randomBomb();
        writeNumber(); 
        randomBtn();
        
    }

    public static void tipOpen() {
    	
//      测试代码
//    	Btn btn;
//    	btn = btns[1][2];
//    	open(btn);
    	
    	A: for(int i = 0; i < rows; i++) {
    		for(int j = 0; j < cols; j++) {
    			Btn btn;
    	    	btn = btns[i][j];  	
    	    	if(click[i][j] == 1) continue;
    	    	if(state[btn.i][btn.j] == true && click[i][j] != 3)
    	    	{
    	    		placeFlag(btn);
    	    	}
    	    	if(state[btn.i][btn.j] == false ) {
    	    		open(btn);
    	    		break A;
    	    	}
    		}
    	}
    	
    	
    }
    
    
	

	private int bombCount(int level) {
		
		int count = 10;
		if(level == 1) {
			count = 10;
		}
		else if(level == 2) {
			count = 35;
		}
		else if(level == 3) {
			count = 60;
		}
		
		return count;
	}




	private void randomBtn() {
		
    	for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                Btn btn = new Btn();
                btn.i = i;
                btn.j = j;
//                设置按钮位置
                btn.setBounds(j * BLOCKWIDTH, i * BLOCKHEIGHT, BLOCKWIDTH, BLOCKHEIGHT);
                this.add(btn);	
                btns[i][j] = btn;
                
                btn.setBorderPainted(false);
                btn.setFocusPainted(false);
                
        		btn.setBackground(new Color(202,235,216));
        		btn.setIcon(grass);
//        		btn.setBackground(new Color(202,235,235));
//        		btn.setIcon();
        		
                
                btn.addMouseListener(new MouseAdapter() {
                	
                	//Button1,Button2,Button3,分别对应鼠标左键，滑轮，右键
                	
                    public void mouseClicked(MouseEvent e) {
                        /* 左键点击 */
                        if(e.getButton() == MouseEvent.BUTTON1) open(btn); //调用open()函数打开格子
                        /* 右键点击 */
                        if(e.getButton() == MouseEvent.BUTTON3) placeFlag(btn);//右键点击标记旗子
                    }
 
                }
                );
 
            }
        }
		
	}

	private static void placeFlag(Btn btn) {
		
		if(flagNum > 0) {
			
			if(click[btn.i][btn.j] == 3) {
				
				if(label[btn.i][btn.j].getText() == "[0-9]") {
					click[btn.i][btn.j] = 2;
				}else {
					click[btn.i][btn.j] = 0;
				}
				btn.setIcon(grass);
				++ flagNum;
				MineSweepers.setMineNum(flagNum);
				
			}else{
				btn.setIcon(flag);
				click[btn.i][btn.j] = 3;
				-- flagNum;
				MineSweepers.setMineNum(flagNum);
			}
			
			
			 if(flagNum == 0){
	                boolean flagstate = true;
	                for(int i = 0;i < rows; ++i){
	                    for(int j = 0;j < cols; ++j){
	                        if (click[i][j] != 3 && state[i][j]) flagstate = false;
	                    }
	                }
	                if(flagstate) {
//	                JOptionPane.showMessageDialog(JPanel,"太棒了,您成功了扫除了全部地雷 ！","游戏结束",JOptionPane.PLAIN_MESSAGE);
	                Object[] options ={ "确定", "再玩一局" };  
	                
	                JOptionPane.showOptionDialog(null, "太棒了,您成功了扫除了全部地雷 ！", "游戏结束",
	                		JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);  
	                }
	                
	                
	               }
	        }else /* 旗子用完了，不能插 */{
	            JOptionPane.showMessageDialog(JPanel,"标记已用尽","错误操作",JOptionPane.ERROR_MESSAGE);
	        }
		
		
	}

	public static void open(Btn btn) {
		
        //踩到地雷,使用for循环打开全部格子
		if(state[btn.i][btn.j]) {
			for(int r = 0; r < rows; ++r ) {
				for(int c = 0; c < cols; c++) {
					btns[r][c].setVisible(false);
					label[r][c].setVisible(true);
					
				}
			}
			//弹框显示游戏失败
//			JOptionPane.showMessageDialog(null, "你失败了！");
			JOptionPane.showMessageDialog(JPanel, "你失败了,下次好运 !", "扫雷",JOptionPane.WARNING_MESSAGE);  
		}else {
			//调用函数继续打开可以打开的格子
			subopen(btn);
		}
		
	}

	//递归不停打开格子
	private static void subopen(Btn btn) {
		
		//如果格子为地雷，则停止打开操作
		if(state[btn.i][btn.j]) {
			return;
		}
		
		//如果格子为已经点击过的,则返回。防止死循环，不停的执行打开格子的操作。
		if(click[btn.i][btn.j] == 1) {
			return;
		}
		
		//如果格子为未打开状态
		 if(click[btn.i][btn.j] == 2) {
	            btn.setVisible(false);
	            label[btn.i][btn.j].setVisible(true);
	            click[btn.i][btn.j] = 1;
	            return;
		 }
		 
		 //标记了红旗,但是却是错误的,重新加回到地雷数目中
		 if(click[btn.i][btn.j] == 3 && state[btn.i][btn.j] == false) {
			 flagNum++;
		 }
		 
		 /* 打开当前这个按钮 */
	        btn.setVisible(false);
	        label[btn.i][btn.j].setVisible(true);
	        click[btn.i][btn.j] = 1;
	        
	        /* 递归检测周边八个按钮 */
	        
	        
	        
	        for (int r = -1; (r + btn.i < rows) && (r < 2); ++r) {
	            if (r + btn.i < 0) continue;
	            for (int c = -1; (c + btn.j < cols) && (c < 2); ++c) {
	                if (c + btn.j < 0) continue;
	                if (r==0 && c==0) continue;
	                
	                //记录位置，递归调用
	                Btn newbtn = btns[r + btn.i][c + btn.j];
	                subopen(newbtn);
	            }
	        }
		
	}

	private void writeNumber() {
		
    	for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                if (state[i][j]) {
                    continue;
                }
                int bombCount = 0;
                
                
                
                /* 寻找以自己为中心的九个格子中的地雷数 */
                //使用两个for循环遍历周围8个格子
                for (int r = -1; (r + i < rows) && (r < 2); ++r) {
                    if (r + i < 0) continue;//防止越界
                    for (int c = -1; (c + j < cols) && (c < 2); ++c) {
                        if (c + j < 0) continue;//防止越界
                        if (state[r + i][c + j]) ++bombCount;
                    }
                }
                
//              if (i>0 && j>0 && state[i-1][j-1] == 1) bombCount++;
//				if (i>0 && state[i-1][j] == 1) bombCount++;
//				if (i>0 && j<COL-1 && state[i-1][j+1] == 1) bombCount++;
//				if (j>0 && state[i][j-1] == 1) bombCount++;
//				if (j<ROW-1 && state[i][j+1] == 1) bombCount++;
//				if (i<ROW-1 && j>0 && state[i+1][j-1] == 1) bombCount++;
//				if (i<ROW-1 && state[i+1][j] == 1) bombCount++;
//				if (i<ROW-1 && j<COL-1 && state[i+1][j+1] == 1) bombCount++;		
                
                //设置数字表示周围有多少个地雷
                if (bombCount > 0) {
                    click[i][j] = 2;
                    label[i][j].setText(String.valueOf(bombCount));
//                    label[i][j].setFont(new java.awt.Font("Dialog", 1, 15));
            		label[i][j].setFont(new Font("arial", Font.BOLD , 18 ));

                    
                    if(bombCount == 1) {
                    	label[i][j].setForeground(new Color(0,119,220));
                    }
                    else if(bombCount == 2) {
                    	label[i][j].setForeground(new Color(0,170,17));
                    }
                    else if(bombCount == 3) {
                    	label[i][j].setForeground(new Color(220,0,0));
                    }
                    else if(bombCount == 4) {
                    	label[i][j].setForeground(new Color(0,0,120));
                    }
                    else if(bombCount == 5) {
                    	label[i][j].setForeground(new Color(136,0,0));
                    }
                    else if(bombCount == 6) {
                    	label[i][j].setForeground(Color.GREEN);
                    }
                    else if(bombCount == 7) {
                    	label[i][j].setForeground(new Color(0,17,85));
                    }
                    else if(bombCount == 8) {
                    	label[i][j].setForeground(new Color(0,102,119));
                    }
                    
                    bombNumber[i][j] = bombCount;
                   
                }
            }
        }
	}

	//实现埋放地雷
	private void randomBomb() {
		
		for (int i = 0; i < bombCount; ) {
			
			
            int rRow = (int) (Math.random() * rows);
            int rCol = (int) (Math.random() * cols);
            
            //防止地雷重复埋放在同一个位置
            if(state[rRow][rCol] != true) {
            
            	label[rRow][rCol].setIcon(bomb);
                state[rRow][rCol] = true;/* 有地雷的格子state为真 */
                i++;
            }
        }
		
	}

	public void initLable() {

		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < cols; j++) {
				 JLabel l = new JLabel("", JLabel.CENTER);
				 
				 	
	                // 设置每个小方格的边界
	                l.setBounds(j * BLOCKWIDTH, i * BLOCKHEIGHT, BLOCKWIDTH, BLOCKHEIGHT);
	                // 绘制方格边框
	                l.setBorder(BorderFactory.createLineBorder(Color.GRAY));

	                // 设置方格为透明,便于我们填充颜色
	                l.setOpaque(true);
	                // 背景填充
//	                l.setBackground(new Color(0,255,167));
//	                l.setBackground(new Color(240,248,255));
	                l.setBackground(new Color(202,235,216));
	                
	                // 将方格加入到容器中(即面板JPanel)
	                this.add(l);
	                // 将方格存到类变量中,方便公用	
	                
	                label[i][j] = l;
//	                label[i][j].setVisible(true);
	                label[i][j].setVisible(false);
	                
			}
		}
	}




	public void LevelsetBounds(int GameLevel) {
		// TODO 自动生成的方法存根
		
		if(GameLevel == 1) {
	
			this.setBounds(130, 150, 225, 225);
		}
		
		if(GameLevel == 2) {
			
			this.setBounds(40, 100, 400, 400);
		}
		
		if(GameLevel == 3) {
			
			this.setBounds(50, 100, 750, 400);
		}
		
		
	}
	
//	 /* 显示雷区 */
//    public void showBomb(){
//        for (int r = 0;r < rows;++r){
//            for(int c = 0;c < cols; ++c){
//                btns[r][c].setVisible(false);/* 隐藏label */
//                label[r][c].setVisible(true);/* 显示按钮（这里只有隐藏了按钮才能显示按钮下面的label） */
//            }
//        }
//    }
//	
	
	  public static void AiSweeper() {
 	
	    	
	   }
	  
	  	// 计算周围有多少只红旗
		  static int countFlagsAround(int i, int j){
		    int mines = 0;

		    
		    // See if we're on the edge of the board
		    //检查是否在边缘
		    boolean oU = false, oD = false, oL = false, oR = false;
		    if(i == 0) oU = true;
		    if(j == 0) oL = true;
		    if(i == rows-1) oD = true;
		    if(j == cols-1) oR = true;

		    if(!oU && click[i-1][j] == 3) mines++;
		    if(!oL && click[i][j-1] == 3) mines++;
		    if(!oD && click[i+1][j] == 3) mines++;
		    if(!oR && click[i][j+1] == 3) mines++;
		    if(!oU && !oL && click[i-1][j-1] == 3) mines++;
		    if(!oU && !oR && click[i-1][j+1] == 3) mines++;
		    if(!oD && !oL && click[i+1][j-1] == 3) mines++;
		    if(!oD && !oR && click[i+1][j+1] == 3) mines++;

		    return mines;
		  }
		  
		// How many unopened squares around this square?
		  //检查周围有多少没有打开的方块
		  static int countFreeSquaresAround(int i, int j){
		    int freeSquares = 0;

		    if(click[i-1][j] == 0) freeSquares++;
		    if(click[i][j-1] == 0) freeSquares++;
		    if(click[i+1][j] == 0) freeSquares++;
		    if(click[i][j+1] == 0) freeSquares++;
		    if(click[i-1][j-1] == 0) freeSquares++;
		    if(click[i-1][j+1] == 0) freeSquares++;
		    if(click[i+1][j-1] == 0) freeSquares++;
		    if(click[i+1][j+1] == 0) freeSquares++;

		    return freeSquares;
		  }
		  
		  static void luckyOpen() {
			  	
		        
		        	int r = (int) (Math.random() * rows);
		        	int c = (int) (Math.random() * cols);
		        	
		        	do{
		        		 r = (int) (Math.random() * rows);
			        	 c = (int) (Math.random() * cols);
		        	}while(click[r][c] == 2);
		        	
	        		Btn btn;
		  	    	btn = btns[r][c];  	
		  	    	open(btn);
		        	
   
		  }
 
}
