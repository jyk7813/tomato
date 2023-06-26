package pnl;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

public class TestTodoPnl extends JFrame implements ActionListener {
	private JPanel fixedTopPnl, topPnl, calendarPnl, monthSelPnl, projectSelPnl, checkSelPnl;
	private JToggleButton totalTogle, projectTogle; // 전체 프로젝트 확인용 토글버튼, 프로젝트별 확인용 토글버튼
	private ButtonGroup tg = new ButtonGroup(); // 토글 관리 그룹 객체
	
	public TestTodoPnl(){
		fixedTopPnl = new JPanel();
		topPnl = new JPanel();
		calendarPnl = new JPanel();
		monthSelPnl = new JPanel();
		projectSelPnl = new JPanel();
		checkSelPnl = new JPanel();
		
		// 토글버튼 그룹에 추가
		tg.add(totalTogle);
		tg.add(projectTogle);
		
		this.start();
		
	}
	
	public void start() {
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice gs = ge.getDefaultScreenDevice();
		setUndecorated(true);
		gs.setFullScreenWindow(this);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	public static void main(String[] args) {
		new TestTodoPnl();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
