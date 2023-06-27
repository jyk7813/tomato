package utility;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

public class Utility {
	private static final IconData ICON = new IconData();
	
	
	public void setButtonProperties(JButton button) {
		button.setOpaque(false);
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		button.setFocusPainted(false);
	}
	public void setButtonProperties(JToggleButton tgbtn) {
		tgbtn.setOpaque(false);
		tgbtn.setContentAreaFilled(false);
		tgbtn.setBorderPainted(false);
		tgbtn.setFocusPainted(false);
	}
	public void setButtonProperties(JTextField jtxf) {
		jtxf.setOpaque(false);
		jtxf.setBorder(null);
		jtxf.setBackground(new Color(0, 0, 0, 0));
		
	}
	
	
	//-----------------버튼 객체 관련 메소드----------------------
	/**
	 * JButton 객체 생성 후 반환해주는 메소드 (롤오버이미지 설정된)
	 * 
	 * @param int x(x좌표값), int y(y좌표값), String iconName(버튼에 설정할 아이콘 이미지 파일명)
	 * @return JButton 
	 */
	public JButton getBtn(int x, int y, String iconName) {
		JButton btn = new JButton();
		btn.setIcon(ICON.getImageIcon(iconName));
		btn.setRolloverIcon(ICON.getRollImageIcon(iconName)); // 롤오버이미지 설정 (마우스 올렸을 때 이미지)
		btn.setSize(ICON.getImageIcon(iconName).getIconWidth(), ICON.getImageIcon(iconName).getIconHeight());
		btn.setBounds(x, y, btn.getWidth(), btn.getHeight());
		btn.setLayout(null);
		defaultSet(btn);
		return btn;
	}
	
	// JButton 기본 박스 아이콘을 투명하게 설정하는 메소드
	public JButton defaultSet(JButton btn) {
		btn.setBorderPainted(false); // JButton의 외곽선 삭제
		btn.setContentAreaFilled(false); // JButton의 내용 영역 채우기 안함
		btn.setFocusPainted(false); // JButton이 선택(focus)되었을 때 생기는 테두리 사용 안함

		return btn;
	}
	//----------------------------------------------------
	
}
