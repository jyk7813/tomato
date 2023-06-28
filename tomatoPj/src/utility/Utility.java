package utility;

import java.awt.Color;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
	
	
	/**
	 * (달력)날짜 관련 메소드
	 * 
	 * 현재 날짜 구해서 포맷 적용된 String타입으로 결과 반환 (2023년 06월 28일 수요일)
	 * 
	 * @return String타입의 현재 날짜
	 */
	public String getCurrentDate() {
		LocalDate now = LocalDate.now();
		int year = now.getYear();
		String month = now.getMonth().toString();
		int monthValue = now.getMonthValue();
		int dayOfMonth = now.getDayOfMonth();
		int dayOfYear = now.getDayOfYear();
		String dayOfWeek = now.getDayOfWeek().toString();
		int dayOfWeekValue = now.getDayOfWeek().getValue();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 E요일");
		String formatedNow = now.format(formatter);

		return formatedNow;
	}

	/**
	 * (달력)날짜 관련 메소드
	 * 
	 * 현재 날짜를 기준으로 년, 월(문자열, 숫자), 일, 요일, 일(Year 기준) 추출
	 * 
	 * @param String 원하는날짜값 (메소드 내 변수명 참고)
	 * @return String타입의 원하는 날짜값
	 */
	public String getCurrentSelDate(String select) {
		LocalDate now = LocalDate.now();
		int year = now.getYear();
		String month = now.getMonth().toString(); 
		int monthValue = now.getMonthValue(); // 달을 숫자로 표현
		int dayOfMonth = now.getDayOfMonth();
		int dayOfYear = now.getDayOfYear();
		String dayOfWeek = now.getDayOfWeek().toString();
		int dayOfWeekValue = now.getDayOfWeek().getValue(); // 요일을 숫자로 표현 (ex. 월-1)
		
		if(select.equals("year")) {
			return String.valueOf(year);
		} else if(select.equals("month")) {
			return month;
		} else if(select.equals("monthValue")) {
			return String.valueOf(monthValue);
		} else if(select.equals("dayOfMonth")) {
			return String.valueOf(dayOfMonth);
		} else if(select.equals("dayOfYear")) {
			return String.valueOf(dayOfYear);
		} else if(select.equals("dayOfWeek")) {
			return dayOfWeek;
		} else {
			return String.valueOf(dayOfWeekValue);
		}
	}

	/**
	 * 버튼 객체 관련 메소드
	 * 
	 * JButton 객체 생성 후 반환해주는 메소드 (롤오버이미지 설정된)
	 * 
	 * @param int x좌표값, int y좌표값, String 버튼에 설정할 아이콘 이미지 파일명
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
		btn.setBorderPainted(false); // 외곽선 삭제
		btn.setContentAreaFilled(false); // 내용 영역 채우기 안함
		btn.setFocusPainted(false); // 선택(focus)되었을 때 생기는 테두리 사용 안함

		return btn;
	}
	// ----------------------------------------------------

}
