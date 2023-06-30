package pnl;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class TestTodoCal extends JFrame{
	public TestTodoCal() {
		JPanel pnl = new JPanel();
		JPanel calPnl = new CalendarSwing();
//		TestTodoPnl todoPnl = new TestTodoPnl();
		
		
		calPnl.setBounds(0, 0, 857, 870);
		calPnl.setLayout(null);
		calPnl.setOpaque(false);
		
		
		pnl.add(calPnl);
//		pnl.add(todoPnl);
		pnl.setBounds(0, 0, 1920, 1080);
		pnl.setLayout(null);
		add(pnl);
		
		setVisible(true);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
//		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
	}
	
	
	public static void main(String[] args) {
		new TestTodoCal();
	}
}
