package pnl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import frame.MainFrame;
import tomatoPj.Member;
import tomatoPj.Member_Tag_Package_Repository;
import tomatoPj.Task;
import utility.CalendarData;
import utility.FontData;
import utility.IconData;
import utility.Utility;

public class TestTodoPnl extends JPanel{
	private Image image;
	
	private final static IconData IC = new IconData();
	private final static FontData FT = new FontData();
	private final static Utility UT = new Utility();
	private final static CalendarData CD = new CalendarData();

	private boolean toggleSwitch = true;

	// 달력 출력 패널 클래스 ------------------------------------
	CalendarSwing printCal = new CalendarSwing();

	public TestTodoPnl(MainFrame mainFrame) {

		// 상단 배경 패널 ------------------------------------
		JPanel topBgPnl = new JPanel() {
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(IC.getImageIcon("topLine").getImage(), 0, 0, null);
				setOpaque(false); // 이미지 불투명도 설정 : false = 불투명(이미지 표시) / true = 투명
			}
		};

		// 메인 영역 배경 패널 ------------------------------------
		JPanel calBgPnl = new JPanel() {
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(IC.getImageIcon("calendarWeek").getImage(), 0, 0, null);
				setOpaque(false);
			}
		};

		// 배경 패널 -----------------------------------------
		JPanel bgPnl = new JPanel() {
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(IC.getImageIcon("Background").getImage(), 0, 0, null);
				setOpaque(false);
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
		calPnl.setBounds(164, 300, 857, 870);
		calPnl.setLayout(null);
		calPnl.setOpaque(false);

		// 달력 출력 패널 --------------------------------------
//		printCal.setBounds(0, 0, 857, 870);
//		printCal.setLayout(null);
//		printCal.setOpaque(false);
//		printCal.add(printCal);

//		JLABEL PRINTCURRENTMONTH = NEW JLABEL();
//		STRING CURRENTMONTH = CD.GETCURRENTSELDATE("MONTHOFVALUE") + " 월";
//		PRINTCURRENTMONTH.SETTEXT(CURRENTMONTH);
//		PRINTCURRENTMONTH.SETFONT(FT.NANUMFONTBOLD(25));
//		PRINTCURRENTMONTH.SETFOREGROUND(COLOR.DARK_GRAY);
//		PRINTCURRENTMONTH.SETBOUNDS(120, 30, 50, 34);

//		calPnl.add(printCurrentMonth);
//		calPnl.add(printCal);

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
		bgPnl.setOpaque(false);
		setOpaque(false);
		add(bgPnl);
//		getContentPane().add(bgPnl);
//		// -----------------------------------------------
//		setExtendedState(JFrame.MAXIMIZED_BOTH);
//		setUndecorated(true);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		getContentPane().setLayout(null);
//		setVisible(true);
		// -----------------------------------------------

	}

	private JPanel CalendarMain() {
		// TODO Auto-generated method stub
		return null;
	}

//	public static void main(String[] args) {
//		new TestTodoPnl();
//	}

}

