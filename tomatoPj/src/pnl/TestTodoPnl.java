package pnl;

import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import utility.IconData;
import utility.Utility;

public class TestTodoPnl extends JFrame implements ActionListener {
	private IconData icon = new IconData();
	private JPanel fixedTopPnl, topPnl, calendarPnl, monthSelPnl, projectSelPnl, checkSelPnl;
	private JToggleButton totalTogle, projectTogle; // 전체 프로젝트 확인용 토글버튼, 프로젝트별 확인용 토글버튼
	private ButtonGroup tg = new ButtonGroup(); // 토글 관리 그룹 객체
	private static JButton kanbanMenu,todoMenu, plannerMenu;
	
	
	public TestTodoPnl(){
		
		totalTogle = new JToggleButton(); // 전체 프로젝트 토글버튼
		projectTogle = new JToggleButton(); // 프로젝트 별 토글버튼
		fixedTopPnl = fixedTopPnl(); // 최상단 페이지 간 이동 버튼 고정 패널
		topPnl = new JPanel(); // 월, 토글, 프로젝트 표시될 위치의 패널
		calendarPnl = new JPanel(); // 달력 + 우측 태스크 정보 표시 패널
		monthSelPnl = new JPanel(); // 월 선택 시 나타낼 패널
		projectSelPnl = new JPanel(); // 프로젝트 선택 시 나타낼 패널
		checkSelPnl = new JPanel(); // 햄버거 메뉴 선택 시 나타낼 패널
		
		// 토글버튼 그룹에 추가
		tg.add(totalTogle);
		tg.add(projectTogle);
		
		topPnl.add(totalTogle);
		topPnl.add(projectTogle);

		// 배경이미지 패널
		BgPanel bgPnl = new BgPanel();
		bgPnl.setLayout(null);
		bgPnl.add(fixedTopPnl);
		bgPnl.add(topPnl);
		bgPnl.add(calendarPnl);
		bgPnl.add(monthSelPnl);
		bgPnl.add(projectSelPnl);
		bgPnl.add(checkSelPnl);
		this.add(bgPnl);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
//		setUndecorated(true);
		setVisible(true);
	}
	
	JPanel fixedTopPnl(){
		JPanel p = new JPanel();
		kanbanMenu = new JButton(icon.kanbanBtnIcon());
		todoMenu = new JButton(icon.todoBtnIcon());
		plannerMenu = new JButton(icon.plannerBtnIcon());
		
		kanbanMenu.setBounds(400, 100, getWidth(), getHeight());
		todoMenu.setBounds(600, 100, getWidth(), getHeight());
		plannerMenu.setBounds(800, 100, getWidth(), getHeight());
		
		defaultSet(kanbanMenu);
		defaultSet(todoMenu);
		defaultSet(plannerMenu);
		
		return p;
	}
	
	// JButton 기본 박스 아이콘을 투명하게 설정하는 메소드
	JButton defaultSet(JButton btn) {
		btn.setBorderPainted(false); // JButton의 외곽선 삭제
		btn.setContentAreaFilled(false); // JButton의 내용 영역 채우기 안함 
		btn.setFocusPainted(false); // JButton이 선택(focus)되었을 때 생기는 테두리 사용 안함
		
		return btn;
	}
	
	
	class BgPanel extends JPanel{
		Image bg = new ImageIcon("img/todobg.jpg").getImage();
		
		public void paintComponent (Graphics g) {
			g.drawImage(bg,  0,  0,  getWidth(), getHeight(), this);
		}
	}
	
//	@Override
//	public void actionPerformed(ActionEvent e) {
//		// TODO Auto-generated method stub
//		
//	}
	
	public static void main(String[] args) {
		new TestTodoPnl();
	}
}
