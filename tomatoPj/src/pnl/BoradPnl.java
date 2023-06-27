package pnl;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

import frame.MainFrame;

public class BoradPnl extends JPanel{
	private Image image;
	
	public BoradPnl(Image image, MainFrame mainFrame) {
		this.image = image;
		setLayout(null);
		
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, this);
	}
}
