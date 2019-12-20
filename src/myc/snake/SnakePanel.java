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

	//�ߵ����ݽṹ�����
	int[] snakex = new int[750];
	int[] snakey = new int[750];
	int len=3;
	String direction = "R"; //R�� L�� U�� D��
	
	//ʳ�������
	Random r = new Random();
	int foodx = r.nextInt(30)*25+50;
	int foody = r.nextInt(16)*25+100;
	
	//��Ϸ�Ƿ�ʼ
	boolean isStarted = false;
	
	//��Ϸ�Ƿ�ʧ��
	boolean isFailed = false;
	
	Timer timer = new Timer(200, this);
	
	//ͳ�Ʒ��� 
	int score =0;
	
	public SnakePanel() {
		this.setFocusable(true);
		initSnake();
		this.addKeyListener(this);//��Ӽ��̼����ӿ�
		timer.start();
	}
	
	//��ʼ����
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
		
		//����ͷ
		if(direction.equals("R")){
			right.paintIcon(this, g, snakex[0], snakey[0]);
		}else if(direction.equals("L")){
			left.paintIcon(this, g, snakex[0], snakey[0]);
		}else if(direction.equals("U")){
			up.paintIcon(this, g, snakex[0], snakey[0]);
		}else if(direction.equals("D")){
			down.paintIcon(this, g, snakex[0], snakey[0]);
		}
		
		//������
		for(int i=1; i<len;i++) {
			body.paintIcon(this, g, snakex[i], snakey[i]);
		}
		
		if(!isStarted){
			g.setColor(Color.BLACK);
			g.setFont(new Font("arial", Font.BOLD, 15));
			g.drawString("Press Space to Start/Pause", 300, 300);
		}
		
		//��ʧ����ʾ��
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
		// TODO �Զ����ɵķ������
		if(isStarted && !isFailed){
			//�ƶ�����
			for(int i=len;i>0;i--){
				snakex[i] = snakex[i-1];
				snakey[i] = snakey[i-1];
			}
			//ͷ�ƶ�
			if(direction.equals("R")){
				//������ +25
				snakex[0] = snakex[0]+25;
				if(snakex[0] >775){
					snakex[0] = 50;
				}
				
			}else if(direction.equals("L")){
				//������ -25
				snakex[0] = snakex[0]-25;
				if(snakex[0] < 50){
					snakex[0] = 775;
				}
				
			}else if(direction.equals("U")){
				//������ -25
				snakey[0] = snakey[0] -25;
				if(snakey[0] < 100){
					snakey[0] = 475;
				}
				
			}else if(direction.equals("D")){
				//������ +25
				snakey[0] = snakey[0] +25;
				if(snakey[0]> 475){
					snakey[0] = 100;
				}
			}
		}
		
		//��ʳ����߼�
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
		// TODO �Զ����ɵķ������
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO �Զ����ɵķ������
		
	}
	
}
