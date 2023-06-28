package pnl;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Label;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import utility.FontData;
import utility.IconData;
import utility.Utility;

public class TestTodoPnl extends JFrame{
	static final IconData ICON = new IconData();
	static final FontData FONT = new FontData();
	static final Utility UT = new Utility();
	
//	static List<String> list = new ArrayList<>(Arrays.asList("테스트1","테스트2","테스트3"));
//	static List<String> month ;
	
	// 최상단 고정 패널
	static JPanel fixedTopPnl = new JPanel() {
		Image background = ICON.getImageIcon("topPnlTestCom").getImage();
		public void paint(Graphics g) {
			g.drawImage(background, 0, 0, null);
			super.paintChildren(g); // 패널 위 요소를 위에 다시 그리기
			g.dispose();
		}
	};
	
	
	
	// 선택 패널
	static JPanel selectPnl = new JPanel() {
		Image background = ICON.getImageIcon("comboSection").getImage();
		public void paint(Graphics g) {
			g.drawImage(background, 0, 0, null);
			super.paintChildren(g);
			g.dispose();
		}
	};
	
	
	// 배경이미지 패널
	static JPanel bgPnl = new JPanel() {
		Image background = new ImageIcon("img/todobgTest.png").getImage();
		public void paint(Graphics g) { //그리는 함수
			g.drawImage(background, 0, 0, null); //background를 그려줌		
		}
	};
	
	public void homeframe() {
		// -----------------------------------------------
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setLayout(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// -----------------------------------------------
		// 최상단 고정 패널 추가
		fixedTopPnl.setBounds(0, 0, getWidth(), getHeight());
		fixedTopPnl.setLayout(null);
		add(fixedTopPnl);
		
		// 선택 패널 추가
		selectPnl.setBounds(0, 140, getWidth(), getHeight());
		selectPnl.setLayout(null);
		add(selectPnl);
		
		// 배경이미지 패널 추가
		bgPnl.setLayout(null);
		bgPnl.setBounds(0, 0, getWidth(), getHeight());
		add(bgPnl);
		// -----------------------------------------------
		
		// 최상단 고정 패널
		JButton kanbanMenu = UT.getBtn(650, 60, "navi_board2");
		fixedTopPnl.add(kanbanMenu);
		JButton todoMenu = UT.getBtn(870, 60, "navi_todo2");
		fixedTopPnl.add(todoMenu);
		JButton projectMenu = UT.getBtn(1060, 60, "navi_planner2");
		fixedTopPnl.add(projectMenu);
		
		// 선택 패널
		JButton monthSel = UT.getBtn(700, 55, "monthSelTest");
		selectPnl.add(monthSel);
		JButton toggleSel = UT.getBtn(850, 55, "toggleSelTest");
		selectPnl.add(toggleSel);
		
	}
	
	public TestTodoPnl() {
		homeframe();
	}
	
	
	public static void main(String[] args){
		new TestTodoPnl();
	}
}