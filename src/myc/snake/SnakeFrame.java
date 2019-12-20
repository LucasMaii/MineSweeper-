package myc.snake;

import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class SnakeFrame extends JFrame {
	
	static private JFrame frame;

	
	public SnakeFrame() {
		
//		frame = new JFrame("Ã∞≥‘…ﬂ");
		this.setTitle("Snake");
		this.getContentPane().setBackground(new Color(202,235,216));
		this.setBounds(200, 100, 850, 600);
		this.setResizable(false);
	
		this.addWindowListener(new WindowAdapter()
	      {
	         public void windowClosing(WindowEvent event)
	         {
	        	 dispose();
	         }
	      });
//		
		SnakePanel panel = new SnakePanel();
		this.add(panel);
		this.setVisible(true);
		
	}

//	public static void main(String[] args) {
//		
//		new SnakeFrame();
//
//	}

}
