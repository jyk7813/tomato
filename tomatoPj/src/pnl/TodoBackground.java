package pnl;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

import frame.MainFrame;

public class TodoBackground extends JPanel{
	private Image image;
	
	public TodoBackground(Image image, MainFrame mainFrame) {
		
		
		this.image = image;
		setLayout(null);
		TestTodoPnl testTodoPnl = new TestTodoPnl();
		add(testTodoPnl);
		
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, this);
	}
}
