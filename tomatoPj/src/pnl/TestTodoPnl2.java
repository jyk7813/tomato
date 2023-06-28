package pnl;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;

import utility.FontData;
import utility.IconData;
import utility.Utility;

public class TestTodoPnl2 extends JFrame {
	private IconData ic = new IconData();
	private FontData ft = new FontData();
	private Utility ut = new Utility();
	
	// 최종 패널
	JPanel todoPnl = new JPanel();
	
	// 상단 패널
	JPanel topPnl = new JPanel() {
		Image background = ic.getImageIcon("bgTopGuideTest").getImage();
		public void paint(Graphics g) {
			g.drawImage(background, 0, 0, null);
		}
	};
	
	// 하단 패널
	JPanel botPnl = new JPanel() {
		Image background = ic.getImageIcon("nullbgBot").getImage();
		public void paint(Graphics g) {
			g.drawImage(background, 0, 0, null);
		}
	};
	
	JButton 

	
	public TestTodoPnl2(){
		// -----------------------------------------------
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setLayout(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// -----------------------------------------------
		
		topPnl.setBounds(0, 0, getWidth(), getHeight());
		todoPnl.add(topPnl);
		botPnl.setBounds(0, 211, getWidth(), getHeight());
		todoPnl.add(botPnl);
		
		todoPnl.setBounds(0, 0, 1920, 1080);
		todoPnl.setLayout(null);
		add(todoPnl);
		
	}
	
	public static void main(String[] args) {
		new TestTodoPnl2();
	}
}
