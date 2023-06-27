package pnl;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import utility.IconData;
import utility.Utility;
 
public class TestTodoPnl extends JFrame{
	static final IconData ICON = new IconData();
	static final Utility UT = new Utility();
	
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
		Image background = ICON.getImageIcon("selectTest").getImage();
		public void paint(Graphics g) {
			g.drawImage(background, 0, 0, null);
			super.paintChildren(g); // 패널 위 요소를 위에 다시 그리기
			g.dispose();
		}
	};
	
	// 배경이미지 패널
	static JPanel bgPnl = new JPanel() {
		Image background = new ImageIcon("img/todobgTest.png").getImage();
		public void paint(Graphics g) {//그리는 함수
			g.drawImage(background, 0, 0, null);//background를 그려줌		
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
		
		
	}
	
//	// JButton 기본 박스 아이콘을 투명하게 설정하는 메소드
//	JButton defaultSet(JButton btn) {
//		btn.setBorderPainted(false); // JButton의 외곽선 삭제
//		btn.setContentAreaFilled(false); // JButton의 내용 영역 채우기 안함
//		btn.setFocusPainted(false); // JButton이 선택(focus)되었을 때 생기는 테두리 사용 안함
//
//		return btn;
//	}
	
	// 콤보박스
//	JPanel comboPnl(String[] name) {
//		JPanel pnl = new JPanel();
//		JPanel top = new JPanel();
//		List<String> contents = new ArrayList<>(Arrays.asList(name));
//		String midName = "";
//		
//		for (int i = 0; i < contents.size(); i++) {
//			JPanel 
//		}
//		JPanel mid = new JPanel();
//		JPanel Bot = new JPanel();
//		
//		pnl.add(top);
//		pnl.add(mid);
//		pnl.add(bot);
//		
//		
//		return pnl;
//	}
	
	/**
	 * JButton 객체 생성 후 반환해주는 메소드
	 * 
	 * @param int x(x좌표값), int y(y좌표값), String iconName(버튼에 설정할 아이콘 이미지 파일명)
	 * @return JButton 
	 */
//	JButton getBtn(int x, int y, String iconName) {
//		JButton btn = new JButton();
//		btn.setIcon(ICON.getImageIcon(iconName));
//		btn.setRolloverIcon(ICON.getRollImageIcon(iconName)); // 롤오버이미지 설정 (마우스 올렸을 때 이미지)
//		btn.setSize(ICON.getImageIcon(iconName).getIconWidth(), ICON.getImageIcon(iconName).getIconHeight());
//		btn.setBounds(x, y, btn.getWidth(), btn.getHeight());
//		btn.setLayout(null);
//		defaultSet(btn);
//		return btn;
//	}
	
	public TestTodoPnl() {
		homeframe();
	}
	
	
	public static void main(String[] args){
		new TestTodoPnl();
	}
}