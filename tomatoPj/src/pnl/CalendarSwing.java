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
import java.time.Period;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

//import javax.rmi.CORBA.Util;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.drew.lang.annotations.NotNull;

import frame.MainFrame;
import tomatoPj.Member;
import tomatoPj.MemberRepository;
import tomatoPj.Project;
import tomatoPj.Task;
import tomatoPj.TaskRepository;
import utility.CalendarData;
import utility.FontData;
import utility.IconData;
import utility.PrintPlanner;
import utility.PrintPlannerList;
import utility.Utility;

public class CalendarSwing extends JPanel implements ItemListener, ActionListener {
	PrintPlannerList ppl = new PrintPlannerList();
	TaskRepository tr = new TaskRepository();
	MemberRepository mr = new MemberRepository();
	CalendarData cd = new CalendarData();
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

	// 뷰 설정 관련 패널 -----------------------------------
	List<PrintPlanner> printCurrentList;
	List<PrintPlanner> selectPrintList;
	List<Integer> pkNums;

	Member loginMember;
	int loginMemberNo = 0;
	boolean flag = false;
	boolean settingView;
	boolean canSelectDate = true;

	JPanel selectPnl = new JPanel();
	int selectPnlHeight; // 선택창 패널 그리드 세로 길이

	// 투두 리스트 패널 ------------------------------------
	JPanel todoListPnl = new JPanel();
	JLabel currentDate = new JLabel(); // 현재 날짜
	String printDate; // 투두 표시 날짜
	int listPnlHeight; // 투두 리스트 패널 그리드 세로 길이
	JPanel listPnl = new JPanel(); // 투두 리스트출력 패널

	List<JButton> dateBtnList = new ArrayList<>();
	List<JPanel> datePnlList = new ArrayList<>();
	List<JButton> todoBtnList = new ArrayList<>();
	List<JLabel> todoDateList = new ArrayList<>();
	List<Task> todoTaskList = new ArrayList<>();
	List<PrintPlanner> todoPPL = new ArrayList<>();

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
	MainFrame mainFrame;

	public CalendarSwing() {
		super();
		date = Calendar.getInstance();// 현재의 날짜 시간 객체 생성 + 객체를 받아옴
		year = date.get(Calendar.YEAR);
		month = date.get(Calendar.MONTH) + 1;
		selDate = LocalDate.now();

		// 상단 패널 ---------------------------------------
		yearCombo.setFont(fnt);
		yearCombo.setBounds(50, 10, 80, 34);
		yearLBl.setFont(fnt);
		yearLBl.setBounds(140, 10, 50, 34);
		monthCombo.setBounds(170, 10, 50, 34);
		monthCombo.setFont(fnt);
		monthLBl.setFont(fnt);
		monthLBl.setBounds(230, 10, 50, 34);

		selectPane.add(monthLBl);
		selectPane.add(yearLBl);
		selectPane.add(yearCombo);
		selectPane.add(monthCombo);
		selectPane.add(prevBtn);
		selectPane.add(nextBtn);
		selectPane.setBounds(0, 30, 300, 300);
		selectPane.setLayout(null);
		selectPane.setOpaque(false);

		// 투두리스트 패널 -----------------------------------
		// 오늘 날짜 출력 라벨
//      setPrintDayOfWeek(LocalDate.now());
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

	public CalendarSwing(int loginMemberNo, Boolean toggleSwitch, MainFrame mainFrame) {
		super();
		this.mainFrame = mainFrame;
		try {
			this.loginMemberNo = loginMemberNo;
			this.settingView = toggleSwitch;
			loginMember = mr.searchByMemberNo(loginMemberNo);
			printCurrentList = ppl.getAllPrintPlannerList(loginMemberNo);
			flag = true;
			selDate = LocalDate.now();

		} catch (SQLException e) {
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

		// 프로젝트 or 멤버 선택창 패널 -------------------------

		// 투두리스트 패널 -----------------------------------
		// 오늘 날짜 출력 라벨
		printDate = calManager.getCurrentDate();
		currentDate.setText(printDate);
		currentDate.setFont(fontManager.nanumFontBold(23));
		currentDate.setLayout(null);
		currentDate.setBounds(35, 20, 700, 50);
		// 리스트 출력
		listPnl.setLayout(new GridLayout(getTodoPnlCount(printCurrentList, selDate), 1, 0, 0));
		listPnl.setBounds(35, 90, 700, getTodoPnlCount(printCurrentList, selDate) * 90);
		listPnl.setOpaque(false);
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

		getTodoList(printCurrentList);
		setMonthOfPrintPlannerList(printCurrentList);
		addDot();

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

	public void setMonthOfPrintPlannerList(List<PrintPlanner> list) {
		int tkNo = 0;
		LocalDate tkUp;
		LocalDate tkDead;
		for (PrintPlanner p : list) {
			if (tkNo != p.getTaskPk()) {
				tkNo = p.getTaskPk();
				tkUp = cd.timeToLocal(tr.searchTaskBy_no(p.getTaskPk()).getUpdateDate());
				tkDead = cd.timeToLocal(tr.searchTaskBy_no(p.getTaskPk()).getDeadLine());

				if (cd.checkRangeOfMonth(selDate, tkUp, tkDead)) {
					todoPPL.add(p);
				}
			}
		}
	}

	public void setMonthOfTaskList(List<PrintPlanner> list) {
		int tkNo = 0;
		LocalDate tkUp;
		LocalDate tkDead;
		for (PrintPlanner p : list) {
			if (tkNo != p.getTaskPk()) {
				tkNo = p.getTaskPk();
				tkUp = cd.timeToLocal(tr.searchTaskBy_no(p.getTaskPk()).getUpdateDate());
				tkDead = cd.timeToLocal(tr.searchTaskBy_no(p.getTaskPk()).getDeadLine());

				if (cd.checkRangeOfMonth(selDate, tkUp, tkDead)) {
					todoTaskList.add(tr.searchTaskBy_no(p.getTaskPk()));
				}
			}
		}
	}

	// 투두 리스트 날짜 출력 메소드
	public void setPrintDayOfWeek(LocalDate selDate) {
		printDate = cd.localToString(selDate);
		currentDate.setText(printDate);
	}

	// 리스트 전달받아 전체 pk 리스트 반환 메소드
	public List<Integer> getAllPks(List<PrintPlanner> list, boolean view) {
		List<Integer> thisPks = new ArrayList<>();
		// 전체 프로젝트 뷰 (프로젝트 pk 반환)
		if (view) {
			for (PrintPlanner p : list) {
				thisPks.add(p.getPjPk());
			}
			return thisPks;
			// 프로젝트 별 (멤버 pk 반환)
		} else {
			for (PrintPlanner p : list) {
				thisPks.add(p.getMemPk());
			}
			return thisPks;
		}
	}

	// pk리스트 전달받아 해당 pk를 가진 리스트들만 반환 메소드
	public List<PrintPlanner> selList(List<Integer> pkList, List<PrintPlanner> list, boolean view) {
		List<Integer> selPk = new ArrayList<>();
		List<PrintPlanner> thisList = new ArrayList<>();
		// 전체 프로젝트 확인
		if (view) {
			for (int i : pkList) {
				for (PrintPlanner p : list) {
					if (i == p.getPjPk()) {
						thisList.add(p);
					}
				}
			}
			return thisList;
		} else {
			for (int i : pkList) {
				for (PrintPlanner p : list) {
					if (i == p.getMemPk()) {
						thisList.add(p);
					}
				}
			}
			return thisList;
		}
	}

	// 뷰설정에 따른 리스트 반환 메소드
	public List<PrintPlanner> viewSetList(int[] noPk, boolean view) {
		List<PrintPlanner> thisList = new ArrayList<>();
		// 참 = 전체 프로젝트 확인
		if (view) {
			for (int i = 0; i < noPk.length; i++) {
				for (PrintPlanner p : printCurrentList) {
					if (p.getPjPk() == noPk[i]) {
						thisList.add(p);
					}
				}
			}
			return thisList;
		} else {
			for (int i = 0; i < noPk.length; i++) {
				for (PrintPlanner p : printCurrentList) {
					if (p.getMemPk() == noPk[i]) {
						thisList.add(p);
					}
				}
			}
			return thisList;
		}
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
		// 날짜출력
		for (int day = 1; day <= lastDay; day++) {
			JPanel box = new JPanel();
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

		dateBtnList = new ArrayList<>();
		// 날짜출력
		for (int day = 1; day <= lastDay; day++) {
			JPanel box = new JPanel();
			JPanel todoBox = new JPanel(new GridLayout(5, 4, 0, 0));
			todoBox.setName(year + "-" + month + "-" + day);
			todoBox.setBounds(0, 30, 112, 116);
			todoBox.setOpaque(false);
			datePnlList.add(todoBox);
			JButton dateBtn = new JButton();
			dateBtn.setName(String.valueOf(day));
			dateBtn.setIcon(iconManager.getImageIcon("calBtn_null"));
			dateBtn.setRolloverIcon(iconManager.getImageIcon("_c"));
			dateBtn.setBounds(0, 30, 112, 116);
			dateBtn.setLayout(null);
			dateBtn.setOpaque(false);
			dateBtn.setBorderPainted(false);
			dateBtn.setContentAreaFilled(false);
			dateBtn.setFocusPainted(false);
			dateBtnList.add(dateBtn);
			dateBtn.addActionListener(new CalBtn());
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
			box.add(todoBox);
			box.add(dateBtn);
			box.setBounds(0, 0, 112, 116);
			box.setLayout(null);
			box.setOpaque(false);

			dayPane.add(box);
		}

	}

	public void addDot() {
		LocalDate localDate;
		LocalDate upDate;
		LocalDate deadDate;
		List<Task> tkList;
		JPanel pnl;
		int pk = 0;
		int taskPk = 0;
		int testPk = 0;
		int lastDateBox = 0;
		for (PrintPlanner pp : todoPPL) {
			if (pk != pp.getPk()) {
				pk = pp.getPk();
				try {
					todoTaskList = tr.taskListBypjNo(pp.getPk());
				} catch (SQLException e) {
					System.out.println("addDot 오류1");
					e.printStackTrace();
				}
				for (Task t : todoTaskList) {
					if (taskPk != t.getTask_no()) {
						taskPk = t.getTask_no();
						upDate = cd.timeToLocal(t.getUpdateDate());
						deadDate = cd.timeToLocal(t.getDeadLine());
						if (cd.checkRangeOfMonth(selDate, upDate, deadDate)) {
							if (!upDate.equals(deadDate)) {
								Period period = Period.between(upDate, deadDate);
								int between = period.getDays();
								dayPane.setVisible(false);
								for (int i = 0; i < between; i++) {
									localDate = upDate.plusDays(i);
									pnl = getCalPnlByDate(localDate);
									JLabel lbl = new JLabel();
									lbl.setName(String.valueOf(pk));
									System.out.println("**" + t.getTitle());
									System.out.println("**calendarDot_mini_" + testPk);
									lbl.setIcon(iconManager.getImageIcon("calendarDot_mini_" + testPk));
									lbl.setBounds(0, 0, 15, 15);
									lbl.setOpaque(false);
									pnl.add(lbl);
								}
								dayPane.setVisible(true);
							} else {
								localDate = upDate;
								pnl = getCalPnlByDate(localDate);
								dayPane.setVisible(false);
								JLabel lbl = new JLabel();
								lbl.setName(String.valueOf(pk));
								System.out.println("*****" + t.getTitle());
								System.out.println("calendarDot_mini_" + testPk);
								lbl.setIcon(iconManager.getImageIcon("calendarDot_mini_" + testPk));
								lbl.setBounds(0, 0, 15, 15);
								lbl.setOpaque(false);
								pnl.add(lbl);
								dayPane.setVisible(true);
								testPk++;
							}
						}
					}
				}
			}
		}
//		int testPk2 = testPk;
//		if(20 > testPk) {
//			int i = 20 - testPk;
//			for(i = 20; i < 0; i--) {
//				dayPane.setVisible(false);
//				JLabel lbl = new JLabel();
//				lbl.setIcon(iconManager.getImageIcon("calendarDot_mini_null"));
//				lbl.setBounds(0, 0, 15, 15);
//				lbl.setOpaque(false);
//				pnl.add(lbl);
//				dayPane.setVisible(true);
//				testPk++;
//			}
//		}
	}

	public JPanel getCalPnlByDate(LocalDate searchDate) {
		LocalDate dateValue;
		JPanel thisPnl = null;
		for (JPanel pnl : datePnlList) {
			dateValue = cd.strToLocalDate(pnl.getName());
			if (searchDate.equals(dateValue)) {
				thisPnl = pnl;
				return thisPnl;
			}
		}
		return thisPnl;
	}

	// 투두리스트 출력 패널 개수 구하기 (출력 패널 세로 사이즈 정하는 데 사용)
	public int getTodoPnlCount(List<PrintPlanner> list, LocalDate date) {
		int height = 0;
		for (PrintPlanner p : list) {
			if (cd.checkLocalDateRange(date, p.getUp(), p.getDead()) || p.getUp().equals(date)) {
				height++;
			}
		}
		return height;
	}

	// 출력할 리스트만 반환받기
	public List<PrintPlanner> getPrintTodoList(List<PrintPlanner> list, LocalDate date) {
		List<PrintPlanner> thisList = new ArrayList<>();
		for (PrintPlanner p : printCurrentList) {
			if (cd.checkLocalDateRange(date, p.getUp(), p.getDead())) {
				thisList.add(p);
			}
		}
		return thisList;
	}

	// 투두리스트 출력 메소드
	public void getTodoList(List<PrintPlanner> list) {
		List<PrintPlanner> thisList = getPrintTodoList(list, selDate);
		int count = 0;
		int pkNum = 0;
		if (thisList.size() == 0) {
			JPanel pnl = new JPanel();
			pnl.setBounds(0, 0, 700, 90);
			pnl.setLayout(null);
			pnl.setOpaque(false);
			JLabel color = new JLabel(iconManager.getImageIcon("calendarDot_9"));
			color.setBounds(0, 5, 20, 20);
			color.setLayout(null);
			color.setOpaque(false);
			JButton clickBox = new JButton(iconManager.getImageIcon("t_null"));
			clickBox.setBounds(0, 0, 700, 90);
			clickBox.setLayout(null);
			clickBox.setOpaque(false);
			clickBox.setBorderPainted(false);
			clickBox.setContentAreaFilled(false);
			clickBox.setFocusPainted(false);
			JLabel title = new JLabel();
			title.setText("일정이 없습니다");
			title.setFont(fontManager.nanumFontBold(23));
			title.setBounds(60, 0, 417, 30);
			title.setOpaque(false);

			pnl.add(clickBox);
			pnl.add(color);
			pnl.add(title);
			listPnl.add(pnl);
		} else {
			for (PrintPlanner p : thisList) {
				if (pkNum != p.getPk()) {
					pkNum = p.getPk();
					JPanel pnl = new JPanel();
					pnl.setBounds(0, 0, 700, 90);
					pnl.setLayout(null);
					pnl.setOpaque(false);
					String imgsrc = "calendarDot_" + p.getPk();
					JLabel color = new JLabel(iconManager.getImageIcon(imgsrc));
					color.setBounds(0, 5, 20, 20);
					color.setLayout(null);
					color.setOpaque(false);
					todoTaskBtn clickBox = new todoTaskBtn(p.getTaskPk(), mainFrame);
					clickBox.setName(String.valueOf(count + 1));
					todoBtnList.add(clickBox);
					getTodoBtn(clickBox);
					JLabel title = new JLabel();
					title.setName(String.valueOf(count + 1));
					title.setText(p.getTaskName());
					title.setFont(fontManager.nanumFontBold(23));
					title.setBounds(60, 0, 417, 30);
					title.setOpaque(false);
					JLabel dateText = new JLabel();
					dateText.setName(String.valueOf(count + 1));
					dateText.setText(p.getUp().toString() + " ~ " + p.getDead().toString());
					dateText.setFont(fontManager.nanumFontBold(18));
					dateText.setBounds(60, 35, 417, 30);
					dateText.setOpaque(false);
					todoDateList.add(dateText);
					pnl.add(clickBox);
					pnl.add(color);
					pnl.add(title);
					pnl.add(dateText);
					listPnl.add(pnl);
					count++;
				}
			}
		}
	}

	// 투두버튼 이미지 + 투명하게 설정 메소드
	public JButton getTodoBtn(JButton btn) {
		btn.setIcon(iconManager.getImageIcon("t_null"));
		btn.setRolloverIcon(iconManager.getImageIcon("t_c"));
		btn.setBounds(0, 0, 700, 90);
		btn.setLayout(null);
		btn.setOpaque(false);
		btn.setBorderPainted(false);
		btn.setContentAreaFilled(false);
		btn.setFocusPainted(false);
		btn.addActionListener(new TodoBtn());

		return btn;
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
		setPrintDayOfWeek(selDate);
		listPnl.setVisible(false);
		listPnl.removeAll();
		listPnl.setLayout(new GridLayout(getTodoPnlCount(printCurrentList, selDate), 1, 0, 0));
		listPnl.setBounds(35, 90, 700, getTodoPnlCount(printCurrentList, selDate) * 90);
		getTodoList(printCurrentList);
		dayPane.setVisible(false);
		dayPane.removeAll();
		if (flag) {
			setDay(loginMemberNo, settingView);
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
			button.getName();
		}
	}

	private class CalBtn implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton button = (JButton) e.getSource();
			String str = button.getName();
			button.setIcon(iconManager.getImageIcon("_c"));
			for (JButton btn : dateBtnList) {
				if (!(button.getName().equals(btn.getName()))) {
					btn.setIcon(iconManager.getImageIcon("calBtn_null"));
					canSelectDate = true;
				}
			}
			day = Integer.parseInt(str);
			listPnl.setVisible(false);
			listPnl.removeAll();
			selDate = LocalDate.of(year, month, day);
			setPrintDayOfWeek(selDate);
			getTodoList(printCurrentList);
			listPnl.setVisible(true);
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
			setDay(loginMemberNo, settingView);
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
			listPnl.setVisible(false);
			listPnl.removeAll();
			year--;
			month = 12;
			selDate = LocalDate.of(year, month, 1);
			setPrintDayOfWeek(selDate);
			getTodoList(printCurrentList);
			listPnl.setLayout(new GridLayout(getTodoPnlCount(printCurrentList, selDate), 1, 0, 0));
			listPnl.setBounds(35, 90, 700, getTodoPnlCount(printCurrentList, selDate) * 90);
			listPnl.setVisible(true);
		} else {
			listPnl.setVisible(false);
			listPnl.removeAll();
			month--;
			selDate = LocalDate.of(year, month, 1);
			setPrintDayOfWeek(selDate);
			getTodoList(printCurrentList);
			listPnl.setLayout(new GridLayout(getTodoPnlCount(printCurrentList, selDate), 1, 0, 0));
			listPnl.setBounds(35, 90, 700, getTodoPnlCount(printCurrentList, selDate) * 90);
			listPnl.setVisible(true);
		}
	}

	public void nextMonth() {
		if (month == 12) {
			listPnl.setVisible(false);
			listPnl.removeAll();
			year++;
			month = 1;
			selDate = LocalDate.of(year, month, 1);
			setPrintDayOfWeek(selDate);
			getTodoList(printCurrentList);
			listPnl.setLayout(new GridLayout(getTodoPnlCount(printCurrentList, selDate), 1, 0, 0));
			listPnl.setBounds(35, 90, 700, getTodoPnlCount(printCurrentList, selDate) * 90);
			listPnl.setVisible(true);
		} else {
			listPnl.setVisible(false);
			listPnl.removeAll();
			month++;
			selDate = LocalDate.of(year, month, 1);
			setPrintDayOfWeek(selDate);
			getTodoList(printCurrentList);
			listPnl.setLayout(new GridLayout(getTodoPnlCount(printCurrentList, selDate), 1, 0, 0));
			listPnl.setBounds(35, 90, 700, getTodoPnlCount(printCurrentList, selDate) * 90);
			listPnl.setVisible(true);
		}
	}
}