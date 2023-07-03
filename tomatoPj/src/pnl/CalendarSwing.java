package pnl;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import javax.swing.ImageIcon;
//import javax.rmi.CORBA.Util;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import frame.MainFrame;
import tomatoPj.Member;
import tomatoPj.MemberRepository;
import utility.CalendarData;
import utility.FontData;
import utility.IconData;
import utility.PrintPlanner;
import utility.PrintPlannerList;
import utility.Utility;

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

	// 달력 출력 패널 ------------------------------------
	JPanel centerPane = new JPanel();
	JPanel dayPane = new JPanel(new GridLayout(0, 7, 0, 0));
	JPanel barPane = new JPanel();

	String[] title = { "일", "월", "화", "수", "목", "금", "토" };

	Calendar date;
	int year;
	int month;

	PrintPlannerList ppl = new PrintPlannerList();
	MemberRepository mr = new MemberRepository();
	List<PrintPlanner> ppList;
	Member loginMember;
	int loginMemberNo = 0;
	boolean flag = false;
	boolean toggleSwitch = true;
	int listHeight = 0;

	// 투두 리스트 패널 ------------------------------------
	JPanel todoListPnl = new JPanel();
	JLabel currentDate = new JLabel();
	String todoDate; // 투두 표시 날짜
	JPanel todoBox;

	public CalendarSwing() {
		super();
		System.out.println("달력창 기본 생성자");
		date = Calendar.getInstance();// 현재의 날짜 시간 객체 생성 + 객체를 받아옴
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
		add(selectPane);
		setLayout(null);

		// 현재 년, 월 세팅
		setYear();
		setMonth();
		setDay();
		barPane.setBounds(0, 0, 790, 750);
		barPane.setOpaque(false);
		centerPane.add(barPane);
		dayPane.setBounds(0, 0, 790, 750);
		dayPane.setOpaque(false);
		centerPane.add(dayPane);
		centerPane.setBounds(0, 130, 790, 750);
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

	public CalendarSwing(int loginMemberNo, Boolean toggleSwitch) {
		super();
		try {
			loginMember = mr.searchByMemberNo(loginMemberNo);
			this.loginMemberNo = loginMemberNo;
			this.toggleSwitch = toggleSwitch;
			ppList = ppl.setView(loginMemberNo, toggleSwitch);
			listHeight = ppList.size();
			flag = true;
		} catch (SQLException e) {
			System.out.println("달력창 메인 DB 가져오기 실패");
			e.printStackTrace();
		}
		date = Calendar.getInstance();// 현재의 날짜 시간 객체 생성 + 객체를 받아옴
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
		todoBox = new JPanel(new GridLayout(listHeight, 0, 0, 0));
		addTodo(ppList);
		todoBox.setBounds(0, 0, 770, 735);
		todoBox.setOpaque(false);

		// 오늘 날짜 출력 라벨
		todoDate = calManager.getCurrentDate();
		currentDate.setText(todoDate);
		currentDate.setFont(fnt);
		currentDate.setLayout(null);
		currentDate.setBounds(30, 20, 240, 50);

		todoListPnl.add(todoBox);
		todoListPnl.add(currentDate);
		todoListPnl.setBounds(880, 130, 770, 735);
		todoListPnl.setLayout(null);
		todoListPnl.setOpaque(false);

		// 달력 출력 패널 ------------------------------------
		add(selectPane);
		setLayout(null);

		// 현재 년, 월 세팅
		setYear();
		setMonth();
		setDay(loginMemberNo, toggleSwitch);
		barPane.setBounds(0, 0, 790, 750);
		barPane.setOpaque(false);
		centerPane.add(barPane);
		dayPane.setBounds(0, 0, 790, 750);
		dayPane.setOpaque(false);
		centerPane.add(dayPane);
		centerPane.setBounds(0, 130, 790, 750);
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
		// 요일
		date.set(year, month - 1, 1); // date를 세팅하는데, 일(day)을 1로 세팅
		int week = date.get(Calendar.DAY_OF_WEEK); // 일월화수목금토
		// 마지막날
		int lastDay = date.getActualMaximum(Calendar.DATE); // getActualMaximum = 날짜가 셋팅된 Calender가 가질수 있는 값
		// getMaximum = Calender 자체가 최대로 가질수 있는 값
		// 공백처리
		for (int s = 1; s < week; s++) {
			JPanel box = new JPanel();
			JLabel lbl = new JLabel(" ", JLabel.CENTER);
			lbl.setBounds(0, 0, 112, 30);
			lbl.setLayout(null);
			box.setOpaque(false);
			box.add(lbl);
			box.setBounds(0, 0, 112, 116);
			dayPane.add(box);

		}
		JPanel[] barBox = new JPanel[32];
		// 날짜출력
		for (int day = 1; day <= lastDay; day++) {
			JPanel box = new JPanel();
			barBox[day] = new JPanel();
			barBox[day].setBounds(0, 30, 112, 116);
			barBox[day].setLayout(null);
			barBox[day].setOpaque(true);
			JLabel lbl = new JLabel(String.valueOf(day), JLabel.CENTER);
			lbl.setFont(fnt2);
			// 출력하는 날짜에 대한 요일
			date.set(Calendar.DATE, day);
			int w = date.get(Calendar.DAY_OF_WEEK); // 요일
			if (w == 1)
				lbl.setForeground(Color.red); // 1 = 일요일
			if (w == 7)
				lbl.setForeground(Color.blue); // 7 = 토요일
			lbl.setBounds(0, 0, 112, 30);
			lbl.setOpaque(true);
			box.add(lbl);
			box.add(barBox[day]);
			box.setBounds(0, 0, 112, 116);
			box.setLayout(null);
			box.setOpaque(false);

			dayPane.add(box);
		}
	}

	public void setDay(int no, boolean toggleSwitch) {
		// 요일
		date.set(year, month - 1, 1); // date를 세팅하는데, 일(day)을 1로 세팅
		int week = date.get(Calendar.DAY_OF_WEEK); // 일월화수목금토
		// 마지막날
		int lastDay = date.getActualMaximum(Calendar.DATE); // getActualMaximum = 날짜가 셋팅된 Calender가 가질수 있는 값
		// getMaximum = Calender 자체가 최대로 가질수 있는 값
		// 공백처리
		for (int s = 1; s < week; s++) {
			JPanel box = new JPanel();
			JLabel lbl = new JLabel(" ", JLabel.CENTER);
			lbl.setBounds(0, 0, 112, 30);
			lbl.setLayout(null);
			box.setOpaque(false);
			box.add(lbl);
			box.setBounds(0, 0, 112, 116);
			dayPane.add(box);

		}

		JPanel[] barBox = new JPanel[32];
		// 날짜출력
		for (int day = 1; day <= lastDay; day++) {
			JPanel box = new JPanel();
			barBox[day] = new JPanel();
			barBox[day].setBounds(0, 30, 112, 116);
			barBox[day].setLayout(null);
			barBox[day].setOpaque(true);
			JLabel lbl = new JLabel(String.valueOf(day), JLabel.CENTER);
			lbl.setFont(fnt2);
			// 출력하는 날짜에 대한 요일
			date.set(Calendar.DATE, day);
			int w = date.get(Calendar.DAY_OF_WEEK); // 요일
			if (w == 1)
				lbl.setForeground(Color.red); // 1 = 일요일
			if (w == 7)
				lbl.setForeground(Color.blue); // 7 = 토요일
			lbl.setBounds(0, 0, 112, 30);
			lbl.setOpaque(true);
			box.add(lbl);
			box.add(barBox[day]);
			box.setBounds(0, 0, 112, 116);
			box.setLayout(null);
			box.setOpaque(false);

			dayPane.add(box);
		}
	}

	public JPanel drawBar(int i, String str) {
		JPanel pnl = new JPanel() {
			String imgSrc = "calendarBar_" + str + i;
			Image barColor = iconManager.getImageIcon(imgSrc).getImage();

			public void paintComonent(Graphics g) {
				g.drawImage(barColor, 0, 0, null);
			}
		};
		pnl.setBounds(0, 0, 110, 116);
		pnl.setLayout(null);
		pnl.setOpaque(false);
		return pnl;
	}

	// 년도세팅
	public void setYear() {
		for (int i = year - 50; i < year + 20; i++) {
			yearCombo.addItem(i);
		}
		yearCombo.setSelectedItem(year); // 콤보박스 이벤트와 연동
	}

	// 월세팅
	public void setMonth() {
		for (int i = 1; i <= 12; i++) {
			monthCombo.addItem(i);
		}
		monthCombo.setSelectedItem(month);
	}

	// 콤보박스클릭이벤트 (콤보박스를 통해 날짜를 변경하였을 때 선택되는 이벤트)
	public void itemStateChanged(ItemEvent e) {
		year = (int) yearCombo.getSelectedItem();
		month = (int) monthCombo.getSelectedItem();

		// 달력 출력 패널을 닫고 지웠다가 날짜 변경 메소드 호출 후 변경된 날짜값이 적용된 패널 보여줌
		dayPane.setVisible(false);
		dayPane.removeAll();
		if (flag) {
			setDay(loginMemberNo, toggleSwitch);
		} else {
			setDay();
		}
		dayPane.setVisible(true);
	}

	// 버튼이벤트
	public void actionPerformed(ActionEvent ae) {
		Object obj = ae.getSource(); // Obejct에 액션이벤트의 소스를 가져온다.
		if (obj == prevBtn) {// 이전버튼을 눌렀을때
			// 이전월을 눌렀을때
			prevMonth(); // 이전버튼메소드호출
			setDayReset(); // Day를 Reset해주는 메소드 호출
		} else if (obj == nextBtn) {
			// 다음월을 눌렀을떄
			nextMonth();
			setDayReset();
		}
	}

	private void setDayReset() {
		// 년월 이벤트 등록해제
		yearCombo.removeItemListener(this);
		monthCombo.removeItemListener(this);

		yearCombo.setSelectedItem(year);
		monthCombo.setSelectedItem(month);

		dayPane.setVisible(false);
		dayPane.removeAll();
		if (flag) {
			setDay(loginMemberNo, toggleSwitch);
		} else {
			setDay();
		}
		dayPane.setVisible(true);
		// 다시 이벤트 등록
		yearCombo.addItemListener(this);
		monthCombo.addItemListener(this);

	}

	public void addTodo(List<PrintPlanner> list) {
		System.out.println("addTodo");
		int i = 0;
		int pk = 0;
		for (PrintPlanner p : list) {
			pk = p.getPk();
			if(p.getPk() == pk) {
				System.out.println("같은 값 확인: " + p);
			}
			System.out.println("-------");
//			String imageSrc = "calendarDot_" + i + "";
//			System.out.println(imageSrc);
//			JPanel box = new JPanel();
//			JLabel title = new JLabel(p.getTitle(), JLabel.LEFT);
//			title.setFont(fnt);
//			title.setBounds(83, 0, 678, 20);
//			JLabel color = new JLabel(iconManager.getImageIcon(imageSrc));
//			color.setBounds(42, 0, 20, 20);
//			JLabel dateText = new JLabel();
//			dateText.setText(p.getUpdate() + " - " + p.getDeadLine());
//			dateText.setFont(fnt2);
//			dateText.setBounds(83, 31, 678, 20);
//
//			box.add(title);
//			box.add(color);
//			box.add(dateText);
//			box.setBounds(0, 0, 760, 50);
//			box.setLayout(null);
//			box.setOpaque(false);
//
//			todoBox.add(box);
			i++;
		}
	}

	public void prevMonth() {
		if (month == 1) {
			year--;
			month = 12;
		} else {
			month--;
		}
	}

	public void nextMonth() {
		if (month == 12) {
			year++;
			month = 1;
		} else {
			month++;
		}
	}
}