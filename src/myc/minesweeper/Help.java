package myc.minesweeper;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import myc.snake.SnakeFrame;

public class Help extends JFrame{
	
	Image help = new ImageIcon("view.png").getImage();
//	ImageIcon help = new ImageIcon(this.getClass().getClassLoader().getResource("view.png"));
	
	
	public Help() {
		
//		JFrame fm = new JFrame("°ïÖú");
//		fm.setBounds(500,100,400,600);
//		fm.setResizable(false);
//		fm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Help");
		this.setBounds(450,100,400,600);
		this.setResizable(false);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
		
		this.setVisible(true);
		repaint();
	}
	
	public void paint(Graphics g) {
//		g.setFont(new Font("arial", Font.BOLD , 20 ));
		g.setColor(Color.BLACK);
		g.drawString("É¨À×ÓÎÏ·",50,50);
		g.drawImage(help,0,0,400,600,null);
	}
	
	
	
//	public static void main(String[] args) {
//		
//		new Help();
//		
//		
//	}
	
	
}
