package pnl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import utility.CalendarData;
import utility.FontData;
import utility.IconData;
import utility.Utility;

public class TestTodoPnl extends JFrame {
	private final static IconData IC = new IconData();
	private final static FontData FT = new FontData();
	private final static Utility UT = new Utility();
	private final static CalendarData CD = new CalendarData();

	private boolean toggleSwitch = true;

	// 달력 출력 패널 클래스 ------------------------------------
	class CalendarMain extends JPanel implements ActionListener {
		// ------외형 구현------------
		private  IconData icon = new IconData();
		private  FontData font = new FontData();
		private Utility util = new Utility();

		Calendar cal;
		int year, month, date;
		JPanel pane = new JPanel();

		// 월선택 좌우버튼
		JButton selMonthbeforeBtn = UT.getBtn(50, 28, "before_btn");
		JButton selMonthnextBtn = UT.getBtn(200, 28, "next_btn");

		// 위에 라벨추가
		JLabel yearlb = new JLabel("년");
		JLabel monthlb = new JLabel("월");

		//년월 추가
		JComboBox<Integer> yearCombo = new JComboBox<Integer>();
		DefaultComboBoxModel<Integer> yearModel = new DefaultComboBoxModel<Integer>();
		JComboBox<Integer> monthCombo = new JComboBox<Integer>();
		DefaultComboBoxModel<Integer> monthModel = new DefaultComboBoxModel<Integer>();

		// 패널추가
		JPanel pane2 = new JPanel(new BorderLayout());
		JPanel title = new JPanel(new GridLayout(1, 7));
		String titleStr[] = { "일", "월", "화", "수", "목", "금", "토" };
		JPanel datePane = new JPanel(new GridLayout(0, 7));

		// 화면디자인
		public CalendarMain() {
			// ------년도 월 구하기------------
			cal = Calendar.getInstance(); // 현재날짜
			year = cal.get(Calendar.YEAR);
			month = cal.get(Calendar.MONTH) + 1;
			date = cal.get(Calendar.DATE);

			// 년
			for (int i = year - 100; i <= year + 50; i++) {
				yearModel.addElement(i);
			}

			yearCombo.setModel(yearModel);
			yearCombo.setSelectedItem(year);

			// 월
			for (int i = 1; i <= 12; i++) {
				monthModel.addElement(i);
			}
			monthCombo.setModel(monthModel);
			monthCombo.setSelectedItem(month);

			// 월화수목금토일
			for (int i = 0; i < titleStr.length; i++) {
				JLabel lbl = new JLabel(titleStr[i], JLabel.CENTER);
				if (i == 0) {
					lbl.setForeground(Color.red);
				} else if (i == 6) {
					lbl.setForeground(Color.blue);
				}
				title.add(lbl);
			}
			// 날짜 출력
			day(year, month);

			// ----------------------------
			pane.add(selMonthbeforeBtn);
			pane.add(yearCombo);
			pane.add(yearlb);
			pane.add(monthCombo);
			pane.add(monthlb);
			pane.add(selMonthnextBtn);
			pane.setBackground(Color.CYAN);
			add(BorderLayout.NORTH, pane);
			pane2.add(title, "North");
			pane2.add(datePane);
			add(BorderLayout.CENTER, pane2);

			// 각종 명령어
			setVisible(true);
			setSize(400, 300);
			setResizable(false);
			setDefaultCloseOperation(EXIT_ON_CLOSE);

			// ----------기능구현----------
			selMonthbeforeBtn.addActionListener(this);
			selMonthnextBtn.addActionListener(this);
			yearCombo.addActionListener(this);
			monthCombo.addActionListener(this);
		}

		// 기능구현
		public void actionPerformed(ActionEvent e) {
			Object eventObj = e.getSource();
			if (eventObj instanceof JComboBox) {
				datePane.setVisible(false); // 보여지는 패널을 숨킨다.
				datePane.removeAll(); // 라벨 지우기
				day((Integer) yearCombo.getSelectedItem(), (Integer) monthCombo.getSelectedItem());
				datePane.setVisible(true); // 패널 재출력
			} else if (eventObj instanceof JButton) {
				JButton eventBtn = (JButton) eventObj;
				int yy = (Integer) yearCombo.getSelectedItem();
				int mm = (Integer) monthCombo.getSelectedItem();
				if (eventBtn.equals(selMonthbeforeBtn)) { // 전달
					if (mm == 1) {
						yy--;
						mm = 12;
					} else {
						mm--;
					}
				} else if (eventBtn.equals(selMonthnextBtn)) { // 다음달
					if (mm == 12) {
						yy++;
						mm = 1;
					} else {
						mm++;
					}
				}
				yearCombo.setSelectedItem(yy);
				monthCombo.setSelectedItem(mm);
			}
		}

		// 날짜출력
		public void day(int year, int month) {
			Calendar date = Calendar.getInstance();// 오늘날짜 + 시간
			date.set(year, month - 1, 1);
			int week = date.get(Calendar.DAY_OF_WEEK);
			int lastDay = date.getActualMaximum(Calendar.DAY_OF_MONTH);

			// 공백출력
			for (int space = 1; space < week; space++) {
				datePane.add(new JLabel("\t"));
			}

			// 날짜 출력
			for (int day = 1; day <= lastDay; day++) {
				JLabel lbl = new JLabel(String.valueOf(day), JLabel.CENTER);
				cal.set(year, month - 1, day);
				int Week = cal.get(Calendar.DAY_OF_WEEK);
				if (Week == 1) {
					lbl.setForeground(Color.red);
				} else if (Week == 7) {
					lbl.setForeground(Color.BLUE);
				}
				datePane.add(lbl);
			}
		}
	}

	public TestTodoPnl() {

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
		JButton logoBtn = UT.getBtnRoll(100, 45, "topLogo");
		topPnl.add(logoBtn);
		JButton kanbanMenuBtn = UT.getBtnRoll(753, 60, "navi_board2");
		topPnl.add(kanbanMenuBtn);
		JButton todoMenuBtn = UT.getBtnRoll(915, 60, "navi_todo2");
		topPnl.add(todoMenuBtn);
		JButton projectMenuBtn = UT.getBtnRoll(1064, 60, "navi_planner2");
		topPnl.add(projectMenuBtn);
		JButton logoutBtn = UT.getBtnRoll(1649, 40, "logout_btn");
		topPnl.add(logoutBtn);
		topPnl.setBounds(0, 0, 1920, 135);
		topPnl.setLayout(null);
		topPnl.setOpaque(false);

		topBgPnl.setBounds(0, 0, 1920, 135);
		topBgPnl.setLayout(null);
		topBgPnl.setOpaque(false);

		// 달력 패널 -----------------------------------------
		JPanel calPnl = new JPanel();
		calPnl.setBounds(164, 175, 857, 870);
		calPnl.setLayout(null);
		calPnl.setOpaque(false);

		JLabel printCurrentMonth = new JLabel();
		String currentMonth = CD.getCurrentSelDate("monthofvalue") + " 월";
		printCurrentMonth.setText(currentMonth);
		printCurrentMonth.setFont(FT.nanumFontBold(25));
		printCurrentMonth.setForeground(Color.DARK_GRAY);
		printCurrentMonth.setBounds(120, 30, 50, 34);

		calPnl.add(printCurrentMonth);

//		// 월선택 좌우버튼
//		JButton selMonthbeforeBtn = UT.getBtn(50, 28, "before_btn");
//		calPnl.add(selMonthbeforeBtn);
//		JButton selMonthnextBtn = UT.getBtn(200, 28, "next_btn");
//		calPnl.add(selMonthnextBtn);

		// 투두 리스트 패널 ------------------------------------
		JPanel todoListPnl = new JPanel();
		todoListPnl.setBounds(1026, 175, 857, 870);
		todoListPnl.setLayout(null);
		todoListPnl.setOpaque(false);

		// 뷰 설정 토글 버튼
		JButton toggleBtn = UT.getBtn(350, 0, "prijectAll_toggle");
		todoListPnl.add(toggleBtn);
		ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (toggleSwitch) {
					toggleBtn.setIcon(IC.getImageIcon("prijectEach_toggle"));
					toggleSwitch = false;
				} else {
					toggleBtn.setIcon(IC.getImageIcon("prijectAll_toggle"));
					toggleSwitch = true;
				}
			}
		};
		toggleBtn.addActionListener(listener);

		// 선택 버튼
		JButton selBtn = UT.getBtn(785, 15, "projectOpne");
		todoListPnl.add(selBtn);

		// 현재 날짜 출력 라벨
		JLabel currentDate = new JLabel();
		String todoDate = CD.getCurrentDate();
		currentDate.setText(todoDate);
		currentDate.setFont(FT.nanumFontBold(18));
		currentDate.setForeground(Color.DARK_GRAY);
		currentDate.setBounds(90, 140, 240, 30);

		todoListPnl.add(currentDate);

		calBgPnl.setBounds(164, 160, 1718, 870);
		calBgPnl.setLayout(null);
		calBgPnl.setOpaque(false);

		// 배경 패널에 각 패널 붙이기 ------------------------------
		bgPnl.add(topPnl); // 상단 패널
		bgPnl.add(topBgPnl); // 상단 배경 패널
		bgPnl.add(todoListPnl); // 투두 리스트 패널
		bgPnl.add(calPnl); // 달력 패널
		bgPnl.add(calBgPnl); // 메인 영역 배경 패널
		// -----------------------------------------------
		bgPnl.setBounds(0, 0, 1920, 1080);
		bgPnl.setLayout(null);
		getContentPane().add(bgPnl);
		// -----------------------------------------------
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setVisible(true);
		// -----------------------------------------------

	}

	public static void main(String[] args) {
		new TestTodoPnl();
	}

}
