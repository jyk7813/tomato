package pnl;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import utility.FontData;
import utility.IconData;
import utility.Utility;

public class TestTodoPnl2 extends JFrame {
	private final static IconData IC = new IconData();
	private final static FontData FT = new FontData();
	private final static Utility UT = new Utility();
	
	// 최종 패널
	JPanel todoPnl = new JPanel();
	
	// 하단 패널
	JPanel botPnl = new JPanel() {
		Image background = IC.getImageIcon("nullbgBot").getImage();
		public void paint(Graphics g) {
			g.drawImage(background, 0, 0, null);
		}
	};
	
	public TestTodoPnl2(){
		// -----------------------------------------------
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setLayout(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// -----------------------------------------------
		
		// 상단 패널 -----------------------------------------
		JPanel topPnl = new JPanel();
		topPnl.setBounds(0, 0, getWidth(), getHeight());
		topPnl.setLayout(null);
		
		JButton logoBtn = UT.getBtn(100, 45, "topLogo");
		topPnl.add(logoBtn);
		JButton kanbanMenuBtn = UT.getBtn(753, 55, "navi_board2");
		topPnl.add(kanbanMenuBtn);
		JButton todoMenuBtn = UT.getBtn(915, 55, "navi_todo2");
		topPnl.add(todoMenuBtn);
		JButton projectMenuBtn = UT.getBtn(1064, 55, "navi_planner2");
		topPnl.add(projectMenuBtn);
		JButton logoutBtn = UT.getBtn(1649, 33, "logout_btn");
		topPnl.add(logoutBtn);
		
		
		
		// -----------------------------------------------
		todoPnl.add(topPnl);
		todoPnl.setBounds(0, 0, 1920, 1080);
		todoPnl.setLayout(null);
		add(todoPnl);
	}
	
	public static void main(String[] args) {
		new TestTodoPnl2();
	}
}
