package pnl;

import utility.CalendarData;
import utility.FontData;
import utility.IconData;
import utility.Utility;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CalendarSwing extends JPanel implements ItemListener, ActionListener {
	FontData fontManager = new FontData();
	IconData iconManager = new IconData();
	Utility utilManager = new Utility();
	CalendarData calManager = new CalendarData();
	Font fnt = fontManager.nanumFontBold(18);
	Font fnt2 = fontManager.nanumFontBold(15);

	// 상단 패널 ---------------------------------------
	JPanel selectPane = new JPanel();
	JButton prevBtn = utilManager.getBtn(0, 10, "before_btn"); 
	JButton nextBtn = utilManager.getBtn(250, 10, "next_btn");
	JComboBox<Integer> yearCombo = new JComboBox<Integer>(); 
	JComboBox<Integer> monthCombo = new JComboBox<Integer>(); 
	JLabel yearLBl = new JLabel("년"); 
	JLabel monthLBl = new JLabel("월"); 

	// 투두 리스트 패널 ------------------------------------
	JPanel todoListPnl = new JPanel();
	JLabel currentDate = new JLabel(); 
	String todoDate; // 투두 표시 날짜

	// 달력 출력 패널 ------------------------------------
	JPanel centerPane = new JPanel();
	JPanel dayPane = new JPanel(new GridLayout(0, 7));

	String[] title = { "일", "월", "화", "수", "목", "금", "토" };

	Calendar date;
	int year;
	int month;

	public CalendarSwing() {
		super(); 
		date = Calendar.getInstance();// 현재의 날짜 시간 객체 생성 + 객체를 받아온다.
		year = date.get(Calendar.YEAR); 
		month = date.get(Calendar.MONTH) + 1; 
		
		// 상단 패널 ---------------------------------------
		yearCombo.setBounds(50, 10, 80, 34);
		yearLBl.setBounds(140, 10, 50, 34);
		monthCombo.setBounds(170, 10, 50, 34);
		monthLBl.setBounds(230, 10, 50, 34);
		selectPane.add(prevBtn);
		selectPane.add(yearCombo);
		yearCombo.setFont(fnt);
		selectPane.add(yearLBl);
		yearLBl.setFont(fnt);
		selectPane.add(monthCombo);
		monthCombo.setFont(fnt);
		selectPane.add(monthLBl);
		monthLBl.setFont(fnt);
		selectPane.add(nextBtn);
		selectPane.setBounds(0, 30, 300, 300);
		selectPane.setLayout(null);
		selectPane.setOpaque(false);

		// 투두리스트 패널 -----------------------------------
		// 오늘 날짜 출력 라벨
		todoDate = calManager.getCurrentDate();
		currentDate.setText(todoDate);
		currentDate.setFont(fnt);
		currentDate.setLayout(null);
		currentDate.setBounds(0, 50, 240, 50);
		
		todoListPnl.add(currentDate);
		todoListPnl.setBounds(920, 100, 760, 700);
		todoListPnl.setLayout(null);
		todoListPnl.setOpaque(false);
		
		// 달력 출력 패널 ------------------------------------
		// 현재 년, 월 세팅
		setYear();
		setMonth();
		setDay();
		dayPane.setBounds(0, 0, 781, 733);
		dayPane.setOpaque(false);
		centerPane.add(dayPane); 
		centerPane.setBounds(0, 130, 880, 750);
		centerPane.setLayout(null);
		centerPane.setOpaque(false);
		
		
		
		// 패널 붙이기 --------------------------------------
		add(selectPane);
		add(todoListPnl);
		add(centerPane);


		// 기능이벤트를 추가 ----------------------------------
		prevBtn.addActionListener(this);
		nextBtn.addActionListener(this);
		// 년월 이벤트 다시등록 --------------------------------
		yearCombo.addItemListener(this);
		monthCombo.addItemListener(this);
		
		// ---------------------------------------------
		setBounds(0, 0, 1718, 870);
		setOpaque(false);
		setVisible(true);
	}

	// 날짜셋팅
	public void setDay() {
		ImageIcon dayNull = iconManager.getImageIcon("calendarNull4Pnl");
		ImageIcon dayImg = iconManager.getImageIcon("calendar4Pnl");
		// 요일
		date.set(year, month - 1, 1); // date를 세팅하는데, 일(day)를 1로 세팅한다.
		int week = date.get(Calendar.DAY_OF_WEEK); // DAY_OF_WEEK는 일월화수목금토이며 이데이터를 받아와서 week에 넣는다.
		// 마지막날
		int lastDay = date.getActualMaximum(Calendar.DATE); // getActualMaximum 는 날짜가 셋팅 된 Calender 가 가질수 있는 값
															// getMaximum 는 Calender 자체가 최대로 가질수 있는 값
															// 마지막날을 불러온다.
		// 공백
		for (int s = 1; s < week; s++) { // 반복문을 돌린다.
			JPanel box = new JPanel();
			JLabel dayBox = new JLabel();
			JLabel lbl = new JLabel(" "); // 들여쓰기
			lbl.setBounds(0, 0, 20, 20);
			lbl.setLayout(null);
//			dayBox.setBounds(0, 0, 103, 116);
//			dayBox.setLayout(null);
			box.setOpaque(false);
			box.add(lbl);
			box.setBounds(0, 0, 100, 116);
//			box.add(dayBox);
			dayPane.add(box);
			
		}
		// 날짜추가
		for (int day = 1; day <= lastDay; day++) {
			JLabel lbl = new JLabel(String.valueOf(day),JLabel.CENTER); // 라벨선언해주는데 String.value 는 형변환이다.
			JPanel box = new JPanel();
			JLabel dayBox = new JLabel();
			dayBox.setIcon(dayImg);
			lbl.setFont(fnt2);
			// 출력하는 날짜에 대한 요일
			date.set(Calendar.DATE, day); // 19 ->1
			int w = date.get(Calendar.DAY_OF_WEEK); // 요일
			if (w == 1)
				lbl.setForeground(Color.red); // 일월화수목금토 (1~7) 1은 일요일이므로 일요일에 red색깔
			if (w == 7)
				lbl.setForeground(Color.blue); // 7이므로 blue색깔
			lbl.setBounds(30, 0, 30, 30);
//			dayBox.setBounds(0, 0, 103, 116);
//			dayBox.setLayout(null);
//			dayBox.setOpaque(false);
			box.add(lbl);
//			box.add(dayBox);
			box.setBounds(0, 0, 100, 116);
			box.setLayout(null);
			box.setOpaque(false);
			dayPane.add(box);
		}
	}

	// 월화수목금토일 설정
	public void setCalendarTitle() { // 메소드
		for (int i = 0; i < title.length; i++) { // 만들어준 배열의 수만큼 돌린다.
			JLabel lbl = new JLabel(title[i], JLabel.CENTER); // 만들어준 배열의 수만큼 label에 주입시키고 가운데로 오게한다.
			lbl.setFont(fnt); // 폰트적용
			if (i == 0)
				lbl.setForeground(Color.red); // setForeground폰트속성을 변경해주는데 쓰는것,
			if (i == 6)
				lbl.setForeground(Color.blue);
		}
	}

	// 년도세팅
	public void setYear() {
		for (int i = year - 50; i < year + 20; i++) { // 해당구문을 반복문을돌려서
			yearCombo.addItem(i); // yearCombo박스에 담는다.
		}
		yearCombo.setSelectedItem(year); // 콤보박스에 담지만 이벤트와 연동시켜주기위해 선언
	}

	// 월세팅
	public void setMonth() {
		for (int i = 1; i <= 12; i++) {
			monthCombo.addItem(i);
		}
		monthCombo.setSelectedItem(month); // 위와동
	}

	// 콤보박스클릭이벤트
	public void itemStateChanged(ItemEvent e) { // 콤보박스를 변경하였을때에 선택되는 이벤트이다.
		year = (int) yearCombo.getSelectedItem(); // 형변환한것을볼수 있으며 yearCombo바뀌었을때 yearCombo의 값을 getSelected 가져와서 찾는것을 볼수
													// 있다.
		month = (int) monthCombo.getSelectedItem();

		dayPane.setVisible(false); // 패널을 닫는다.
		dayPane.removeAll(); // 원래있는 날짜 지우기
		setDay(); // 날짜 처리 함수 호출
		dayPane.setVisible(true); // 패널을 볼수있게 처리한다.

		// 여기서 닫고 지웠다가 호출하고, 다시 보여주는 이유는 안그러면 화면이 지워지지않기 때문이다.

	}

	// 버튼이벤트
	public void actionPerformed(ActionEvent ae) { // 액션이벤트(버튼이벤트)
		Object obj = ae.getSource(); // Obejct에 액션이벤트의 소스를 가져온다.
		if (obj == prevBtn) {// 이전버튼을 눌렀을때
			// 이전월을 눌렀을때
			prevMonth(); // 이전버튼메소드호출
			setDayReset(); // Day를 Reset해주는 메소드 호출
		} else if (obj == nextBtn) { // 이후 버튼을 눌렀을때
			// 다음월을 눌렀을떄
			nextMonth(); // 위와동
			setDayReset(); // 위와동
		}
	}

	private void setDayReset() {
		// 년월 이벤트 등록해제
		yearCombo.removeItemListener(this); // 등록이벤트를 해제시켜주고
		monthCombo.removeItemListener(this);

		yearCombo.setSelectedItem(year); // yearCombo의 year에 해당되는 값을 가져온다.
		monthCombo.setSelectedItem(month);

		dayPane.setVisible(false); // 패널을 보여주기를 숨킨다.
		dayPane.removeAll(); // 전부지운다.
		setDay(); // 해당메소드를 호출한다.
		dayPane.setVisible(true); // 다시보여준다.

		yearCombo.addItemListener(this); // 다시 이벤트를 등록시킨다.
		monthCombo.addItemListener(this); // 다시 이벤트 등록

	}

	public void prevMonth() { // 월
		if (month == 1) { // 21.01월 일때에 12월로 떨어지면서 year를 전년도로 바꾼다.
			year--;
			month = 12;
		} else { // 그외의 경우
			month--;
		}
	}

	public void nextMonth() {
		if (month == 12) { // 12월일때에는 년도를 추가시키고 월을 1로바꾼다.
			year++;
			month = 1;
		} else { // 그외의 경우
			month++;
		}
	}

//	// 시작메소드
//	public static void main(String[] args) {
//		new CalendarSwing();
//	}
}