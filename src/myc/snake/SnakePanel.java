package myc.snake;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

public class SnakePanel extends JPanel implements KeyListener, ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3512561103519047079L;
	ImageIcon up = new ImageIcon("up.png");
	ImageIcon down = new ImageIcon("down.png");
	ImageIcon left = new ImageIcon("left.png");
	ImageIcon right = new ImageIcon("right.png");
//	ImageIcon title = new ImageIcon("title.png");
	ImageIcon food = new ImageIcon("food.png");
	ImageIcon body = new ImageIcon("body.png");

	//蛇的数据结构的设计
	int[] snakex = new int[750];
	int[] snakey = new int[750];
	int len=3;
	String direction = "R"; //R右 L左 U上 D下
	
	//食物的数据
	Random r = new Random();
	int foodx = r.nextInt(30)*25+50;
	int foody = r.nextInt(16)*25+100;
	
	//游戏是否开始
	boolean isStarted = false;
	
	//游戏是否失败
	boolean isFailed = false;
	
	Timer timer = new Timer(200, this);
	
	//统计分数 
	int score =0;
	
	public SnakePanel() {
		this.setFocusable(true);
		initSnake();
		this.addKeyListener(this);//添加键盘监听接口
		timer.start();
	}
	
	//初始化蛇
	public void initSnake(){
		
		isStarted = false;
		isFailed=false;
		len=3;
		direction="R";
		snakex[0] = 100;
		snakey[0] = 100;
		snakex[1] = 75;
		snakey[1] = 100;
		snakex[2] = 50;
		snakey[2] = 100;
	}
	
	public void paint(Graphics g) {

		g.setColor(new Color(202,235,216));
		g.fillRect(0, 0, 850, 600);
		g.setColor(Color.BLACK);
		g.fillRect(50, 100, 750, 400);
//		title.paintIcon(this, g, 0, 11);
		
		//画蛇头
		if(direction.equals("R")){
			right.paintIcon(this, g, snakex[0], snakey[0]);
		}else if(direction.equals("L")){
			left.paintIcon(this, g, snakex[0], snakey[0]);
		}else if(direction.equals("U")){
			up.paintIcon(this, g, snakex[0], snakey[0]);
		}else if(direction.equals("D")){
			down.paintIcon(this, g, snakex[0], snakey[0]);
		}
		
		//画蛇身
		for(int i=1; i<len;i++) {
			body.paintIcon(this, g, snakex[i], snakey[i]);
		}
		
		if(!isStarted){
			g.setColor(Color.BLACK);
			g.setFont(new Font("arial", Font.BOLD, 15));
			g.drawString("Press Space to Start/Pause", 300, 300);
		}
		
		//画失败提示语
		if(isFailed){
			g.setColor(Color.BLACK);
			g.setFont(new Font("arial", Font.BOLD, 15));
			g.drawString("Game Over, Press Space to Start", 300, 300);
		}
		
		food.paintIcon(this, g, foodx, foody);
		
		g.setColor(Color.BLACK);
		g.setFont(new Font("arial", Font.BOLD, 15));
		
		g.drawString("Score:"+score, 50, 75);
		g.drawString("Length:"+len, 700, 75);
		
		g.setFont(new Font("arial", Font.BOLD, 20));
		g.drawString("SnakeGame", 370, 75);
		repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		if(isStarted && !isFailed){
			//移动身体
			for(int i=len;i>0;i--){
				snakex[i] = snakex[i-1];
				snakey[i] = snakey[i-1];
			}
			//头移动
			if(direction.equals("R")){
				//横坐标 +25
				snakex[0] = snakex[0]+25;
				if(snakex[0] >775){
					snakex[0] = 50;
				}
				
			}else if(direction.equals("L")){
				//横坐标 -25
				snakex[0] = snakex[0]-25;
				if(snakex[0] < 50){
					snakex[0] = 775;
				}
				
			}else if(direction.equals("U")){
				//纵坐标 -25
				snakey[0] = snakey[0] -25;
				if(snakey[0] < 100){
					snakey[0] = 475;
				}
				
			}else if(direction.equals("D")){
				//纵坐标 +25
				snakey[0] = snakey[0] +25;
				if(snakey[0]> 475){
					snakey[0] = 100;
				}
			}
		}
		
		//吃食物的逻辑
		if(snakex[0] == foodx && snakey[0] == foody){
			len++;
			score++;
			foodx = r.nextInt(30)*25+50;
			foody = r.nextInt(16)*25+100;
		}
		
		for(int i=1;i<len;i++){
			if(snakex[0] == snakex[i] && snakey[0] == snakey[i]){
				isFailed = true;
			}
		}
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		int keyCode = e.getKeyCode();
		if(keyCode == KeyEvent.VK_SPACE) {
			if(isFailed) {
				initSnake();
			}
			else {
			isStarted = !isStarted;
			}
		}
		
		else if(keyCode == KeyEvent.VK_UP && !direction.equals("D")){
			direction="U";
		}else if(keyCode == KeyEvent.VK_DOWN && !direction.equals("U")){
			direction="D";
		}else if(keyCode == KeyEvent.VK_LEFT && !direction.equals("R")){
			direction="L";
		}else if(keyCode == KeyEvent.VK_RIGHT && !direction.equals("L")){
			direction="R";
		}
	
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO 自动生成的方法存根
		
	}
	
}
