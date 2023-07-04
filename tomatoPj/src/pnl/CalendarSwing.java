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
import java.io.File;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

//import javax.rmi.CORBA.Util;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import frame.MainFrame;
import tomatoPj.Member;
import tomatoPj.MemberRepository;
import tomatoPj.Project;
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

	// 투두 리스트 패널 ------------------------------------
	JPanel todoListPnl = new JPanel();
	JLabel currentDate = new JLabel();
	String printDate; // 투두 표시 날짜
	JPanel listPnl;

	// 달력 출력 패널 ------------------------------------
	JPanel centerPane = new JPanel();
	JPanel dayPane = new JPanel(new GridLayout(0, 7, 0, 0));
	JPanel barPane = new JPanel();

	String[] title = { "일", "월", "화", "수", "목", "금", "토" };

	Calendar date;
	LocalDate selDate;
	int year;
	int month;
	int day;
	PrintPlannerList ppl = new PrintPlannerList();
	MemberRepository mr = new MemberRepository();
	CalendarData cd = new CalendarData();
	Utility util = new Utility();
	Member loginMember;
	List<Project> ppList;
	List<PrintPlanner> printCurrentList;
	int loginMemberNo = 0;
	boolean flag = false;
	boolean toggleSwitch = true;
	List<JButton> dateBtnList;
	boolean isSelectDate = true;

	public CalendarSwing() {
		super();
		System.out.println("달력창 기본 생성자");
		date = Calendar.getInstance();// 현재의 날짜 시간 객체 생성 + 객체를 받아옴
		year = date.get(Calendar.YEAR);
		month = date.get(Calendar.MONTH) + 1;
		selDate = LocalDate.now();

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
//		setPrintDayOfWeek(LocalDate.now());
		printDate = cd.getCurrentDate();
		currentDate.setText(printDate);
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
		barPane.setBounds(0, 0, 790, 740);
		barPane.setOpaque(false);
		centerPane.add(barPane);
		dayPane.setBounds(0, 0, 790, 740);
		dayPane.setOpaque(false);
		centerPane.add(dayPane);
		centerPane.setBounds(0, 130, 790, 740);
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
			ppList = mr.returnMemberPj(loginMemberNo);
			printCurrentList = ppl.getPrintTest(toggleSwitch, ppList, loginMember);
//			ppList = ppl.setView(toggleSwitch, loginMemberNo);
			System.out.println("달력창 확인: " + loginMember.getName() + "토글상태: " + toggleSwitch);
			flag = true;
			selDate = LocalDate.now();
		} catch (SQLException e) {
			System.out.println("달력창 실패1");
			e.printStackTrace();
		}
		date = Calendar.getInstance();// 현재의 날짜 시간 객체 생성 + 객체를 받아옴
		year = date.get(Calendar.YEAR);
		month = date.get(Calendar.MONTH) + 1;
		day = date.get(Calendar.DATE);

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
		printDate = calManager.getCurrentDate();
		currentDate.setText(printDate);
		currentDate.setFont(fnt);
		currentDate.setLayout(null);
		currentDate.setBounds(35, 20, 240, 50);
		// 리스트 출력
		listPnl = new JPanel(new GridLayout(ppList.size(), 0, 0, 20));
		listPnl.setBounds(35, 90, 700, 610);
		listPnl.setOpaque(false);
//		printCurrentList = ppList;
		getTodoList(printCurrentList, selDate);
//		getTodoList(ppList);

		todoListPnl.add(listPnl);
		todoListPnl.add(currentDate);
		todoListPnl.setBounds(880, 135, 770, 733);
		todoListPnl.setLayout(null);
		todoListPnl.setOpaque(false);

		// 달력 출력 패널 ------------------------------------
		add(selectPane);
		setLayout(null);

		// 현재 년, 월 세팅
		setYear();
		setMonth();
		setDay(loginMemberNo, toggleSwitch);
		barPane.setBounds(0, 0, 790, 735);
		barPane.setOpaque(false);
		centerPane.add(barPane);
		dayPane.setBounds(0, 0, 790, 735);
		dayPane.setOpaque(false);
		centerPane.add(dayPane);
		centerPane.setBounds(0, 130, 790, 735);
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

	public void setPrintDayOfWeek(LocalDate selDate) {
		printDate = cd.localToString(selDate);
		currentDate.setText(printDate);
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
			barBox[day].setOpaque(false);
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
			lbl.setOpaque(false);
			box.add(lbl);
			box.add(barBox[day]);
			box.setBounds(0, 0, 112, 116);
			box.setLayout(null);
			box.setOpaque(false);

			dayPane.add(box);
		}
	}

	public void setDay(int no, boolean toggleSwitch) {
//		List<PrintPlanner> ppList = ppl.setView(no, toggleSwitch);
		// 요일
		date.set(year, month - 1, 1); // date를 세팅하는데, 일(day)을 1로 세팅
		int week = date.get(Calendar.DAY_OF_WEEK); // 일월화수목금토
		// 마지막날
		int lastDay = date.getActualMaximum(Calendar.DATE); // getActualMaximum = 날짜가 셋팅된 Calender가 가질수 있는 값
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
		dateBtnList = new ArrayList<>();
		// 날짜출력
		for (int day = 1; day <= lastDay; day++) {
			JPanel box = new JPanel();
			JButton dateBtn = new JButton(iconManager.getImageIcon(String.valueOf(day)));
			dateBtn.setRolloverIcon(iconManager.getImageIcon("_c"));
			dateBtn.setBounds(0, 30, 112, 116);
			dateBtn.setLayout(null);
			dateBtn.setOpaque(false);
			dateBtn.setBorderPainted(false); // 외곽선 삭제
			dateBtn.setContentAreaFilled(false); // 내용 영역 채우기 안함
			dateBtn.setFocusPainted(false);
			dateBtnList.add(dateBtn);
			dateBtn.addActionListener(new CalBtn());
			barBox[day] = new JPanel();
			barBox[day].setBounds(0, 30, 112, 116);
			barBox[day].setLayout(null);
			barBox[day].setOpaque(false);
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
			lbl.setOpaque(false);
			box.add(lbl);
			box.add(barBox[day]);
			box.add(dateBtn);
			box.setBounds(0, 0, 112, 116);
			box.setLayout(null);
			box.setOpaque(false);

			dayPane.add(box);
		}
	}

//   public JPanel drawBar(List<PrintPlanner> list) {
//	   for(PrintPlanner p : list) {
//		   
//	   }
//	   JPanel pnl = new JPanel() {
//         String imgSrc = "calendarBar_" + str + i;
//         Image barColor = iconManager.getImageIcon(imgSrc).getImage();
//
//         public void paintComonent(Graphics g) {
//            g.drawImage(barColor, 0, 0, null);
//         }
//      };
//      pnl.setBounds(0, 0, 110, 116);
//      pnl.setLayout(null);
//      pnl.setOpaque(false);
//      return pnl;
//   }
//	public List<PrintPlanner> getPrintList(List<PrintPlanner> list) {
//		List<PrintPlanner> printlist;
//		
//		int i = 1;
//		if(flag) {
//			for(PrintPlanner p : list) {
//				PrintPlanner pp = new PrintPlanner(i, p.getPk(), p.getTitle(), p.get, String update, String deadLine);
//				i++;
//			}
//		}
//		
//		return printlist;
//	}

	// 투두리스트 출력 메소드
	public void getTodoList(List<PrintPlanner> list, LocalDate date) {
		int colorNo = 1;
		System.out.println("현재 날짜: " + date);
		for(PrintPlanner p : list) {
//			System.out.println("*진입*" + p.getTitle() + p.getUp());
			if(p.getUp().equals(date)) {
				System.out.println("*******날짜동일*" + p);
				JPanel pnl = new JPanel();
				pnl.setBounds(0, 0, 700, 70);
				pnl.setLayout(null);
				pnl.setOpaque(false);
				String imgsrc = "calendarDot_" + colorNo;
				System.out.println(imgsrc);
				JLabel color = new JLabel(iconManager.getImageIcon(imgsrc));
				color.setBounds(0, 0, 20, 20);
				color.setLayout(null);
				color.setOpaque(false);
				String imgsrc2 = "t_" + colorNo;
				JButton clickBox = new JButton(iconManager.getImageIcon(imgsrc2));
				clickBox.setRolloverIcon(iconManager.getImageIcon("t_c"));
				clickBox.setBounds(0, 0, 700, 70);
				clickBox.setLayout(null);
				clickBox.setOpaque(false);
				clickBox.setBorderPainted(false); // 외곽선 삭제
				clickBox.setContentAreaFilled(false); // 내용 영역 채우기 안함
				clickBox.setFocusPainted(false);
				clickBox.addActionListener(new TodoBtn());
				JLabel title = new JLabel();
				title.setText(p.getTitle());
				title.setFont(fnt);
				title.setBounds(60, 0, 417, 30);
				title.setOpaque(false);
				JLabel dateText = new JLabel();
				dateText.setText(p.getUpdate() + " - "+ p.getDeadLine());
				dateText.setFont(fnt2);
				dateText.setBounds(60, 40, 417, 30);
				dateText.setOpaque(false);
				
				pnl.add(clickBox);
				pnl.add(color);
				pnl.add(title);
				pnl.add(dateText);
				listPnl.add(pnl);
				colorNo++;
			}
		}
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
		selDate = LocalDate.of(year, month, 1);
		System.out.println("콤보박스클릭: " + selDate);
		setPrintDayOfWeek(selDate);
		listPnl.setVisible(false);
		listPnl.removeAll();
		// 달력 출력 패널을 닫고 지웠다가 날짜 변경 메소드 호출 후 변경된 날짜값이 적용된 패널 보여줌
		dayPane.setVisible(false);
		dayPane.removeAll();
		if (flag) {
			setDay(loginMemberNo, toggleSwitch);
		} else {
			setDay();
		}
		dayPane.setVisible(true);
		listPnl.setVisible(true);
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
	
	private class TodoBtn implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton button = (JButton) e.getSource();
			String str = button.getIcon().toString();
			str = str.substring(45);
			System.out.println(str +"번 리스트가 선택되었습니다");
		}
	}

	private class CalBtn implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton button = (JButton) e.getSource();
			String str = button.getIcon().toString();
			str = str.substring(43);
			if (isSelectDate) {
//				System.out.println("**확인: " + str);
				str = str.substring(0, str.length() - 4);
//					System.out.println("확인: " + str);
				day = Integer.parseInt(str);
				selDate = LocalDate.of(year, month, day);
				setPrintDayOfWeek(selDate);
//					printDate = selDate.toString();
//					System.out.println("**달력패널클릭: "+ selDate);
//					System.out.println("**달력패널클릭2: : "+ todoDate);
//				System.out.println(str + "_c");
				button.setIcon(iconManager.getImageIcon(str + "_c"));
				isSelectDate = false;
			} else {
				for (JButton btn : dateBtnList) {
					String str2 = btn.getIcon().toString();
					str2 = str2.substring(43);
					if (str2.length() >= 7) {
						str2 = str2.substring(0, str2.length() - 6);
						btn.setIcon(iconManager.getImageIcon(str2));
					}
					isSelectDate = true;
				}
				if (str.length() >= 7) {
					str = str.substring(0, str.length() - 6);
					button.setIcon(iconManager.getImageIcon(str + "_c"));
					day = Integer.parseInt(str);
					selDate = LocalDate.of(year, month, day);
					setPrintDayOfWeek(selDate);
					button.setIcon(iconManager.getImageIcon(str + "_c"));
					isSelectDate = false;
				} else {
					str = str.substring(0, str.length() - 4);
//					System.out.println("확인: " + str);
					day = Integer.parseInt(str);
					selDate = LocalDate.of(year, month, day);
					setPrintDayOfWeek(selDate);
//					printDate = selDate.toString();
//					System.out.println("달력패널클릭: "+ selDate);
//					System.out.println("달력패널클릭2: : "+ todoDate);
					button.setIcon(iconManager.getImageIcon(str + "_c"));
					isSelectDate = false;
				}
			}
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