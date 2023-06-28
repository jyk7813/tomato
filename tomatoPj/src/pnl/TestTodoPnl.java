package pnl;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import utility.FontData;
import utility.IconData;
import utility.Utility;

public class TestTodoPnl extends JFrame {
	private final static IconData IC = new IconData();
	private final static FontData FT = new FontData();
	private final static Utility UT = new Utility();
	
	JScrollPane scrollPane;
	
	public TestTodoPnl(){
		// 상단 배경 패널 ------------------------------------
		JPanel topBgPnl = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(IC.getImageIcon("topLine").getImage(), 0, 0, null);
				setOpaque(false); // 이미지 불투명도 설정 : false = 불투명(이미지 표시) / true = 투명
				super.paintComponent(g);
			}
		};
		
		// 메인 영역 배경 패널 ------------------------------------
		JPanel calBgPnl = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(IC.getImageIcon("calendarWeek").getImage(), 0, 0, null);
				setOpaque(false); 
				super.paintComponent(g);
			}
		};
		
		// 배경 패널 -----------------------------------------
		JPanel bgPnl = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(IC.getImageIcon("Background").getImage(), 0, 0, null);
				setOpaque(false); 
				super.paintComponent(g);
			}
		};
		
		// 상단 패널 -----------------------------------------
		JPanel topPnl = new JPanel();
		
		// 메뉴 이동 버튼
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
		
		topPnl.setBounds(0, -10, 1920, 135);
		topPnl.setLayout(null);
		topPnl.setOpaque(false);
		
		topBgPnl.setBounds(0, -10, 1920, 135);
		topBgPnl.setLayout(null);
		topBgPnl.setOpaque(false);
		
		
		// 달력 패널 -----------------------------------------
		JPanel calPnl = new JPanel();
		calPnl.setBounds(164, 160, 1718, 870);
		calPnl.setLayout(null);
		calPnl.setOpaque(false);
		
		
		
		// 투두 리스트 패널 ------------------------------------
		JPanel todoListPnl = new JPanel();
		todoListPnl.setBounds(164, 160, 1718, 870);
		todoListPnl.setLayout(null);
		todoListPnl.setOpaque(false);
		
		
		// 토글 버튼
		JButton toggleTotal = UT.getBtn(1400, 200, "prijectAll_toggle");
		
		// 현재 날짜 출력 라벨
		JLabel currentDate = new JLabel();
		String res = UT.getCurrentDate();
		currentDate.setText(res);
		currentDate.setFont(FT.nanumFontBold(18));
		currentDate.setForeground(Color.DARK_GRAY);
		
		todoListPnl.add(currentDate);
		currentDate.setBounds(950, 140, 240, 30);
		
		
		calBgPnl.setBounds(164, 160, 1718, 870);
		calBgPnl.setLayout(null);
		calBgPnl.setOpaque(false);
		
		
		// 배경 패널에 각 패널 붙이기 ------------------------------
		bgPnl.add(topPnl); // 상단 패널
		bgPnl.add(topBgPnl); // 상단 배경 패널
		bgPnl.add(todoListPnl); // 투두 리스트 패널
		bgPnl.add(calBgPnl); // 달력 배경 패널
		
		
		// -----------------------------------------------
//		scrollPane = new JScrollPane(bgPnl);
//		setContentPane(scrollPane);
		bgPnl.setBounds(0, 0, 1920, 1080);
		bgPnl.setLayout(null);
		add(bgPnl);
		// -----------------------------------------------
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setVisible(true);
		// -----------------------------------------------
	}
	
	public static void main(String[] args) {
		new TestTodoPnl();
	}
}
