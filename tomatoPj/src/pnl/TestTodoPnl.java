package pnl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import frame.MainFrame;
import tomatoPj.Project;
import tomatoPj.Task;
import tomatoPj.TaskRepository;
import utility.CalendarData;
import utility.FontData;
import utility.IconData;
import utility.Utility;

public class TestTodoPnl extends JFrame{
	private Image image;
	private MainFrame mainFrame = new MainFrame();
	
	private final static IconData IC = new IconData();
	private final static FontData FT = new FontData();
	private final static Utility UT = new Utility();
	private final static CalendarData CD = new CalendarData();
	
	private boolean toggleSwitch = true;
	
	
	// 달력 출력 패널 클래스 ------------------------------------
	CalendarSwing printCal = new CalendarSwing();
	
//	public TestTodoPnl(MainFrame mainFrame) {
	public TestTodoPnl() {
		// DB 데이터 받아오기 -------------------------------------
		LocalDate update;
		LocalDate deadLine;
		
		// 전체 프로젝트
		List<Project> pjListOfUser = mainFrame.loginMember.getPjList(); // 로그인한 멤버가 참여중인 모든 프로젝트
		List<Task> taskListOfPj = mainFrame.loginMember.getTakeTaskList(); // 로그인한 멤버가 참여중인 프로젝트들의 모든 태스크
		
		// 프로젝트 별
		
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
				g.drawImage(IC.getImageIcon("calendarWeekBg").getImage(), 0, 0, null);
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
		JButton kanbanMenuBtn = UT.getBtnRoll(820, 60, "navi_board2");
		topPnl.add(kanbanMenuBtn);
		JButton todoMenuBtn = UT.getBtnRoll(1020, 60, "navi_planner2");
		topPnl.add(todoMenuBtn);
		JButton logoutBtn = UT.getBtnRoll(1649, 40, "logout_btn");
		topPnl.add(logoutBtn);
		topPnl.setBounds(0, 0, 1920, 135);
		topPnl.setLayout(null);
		topPnl.setOpaque(false);

		topBgPnl.setBounds(0, 0, 1920, 135);
		topBgPnl.setLayout(null);
		topBgPnl.setOpaque(false);

		// 달력 출력 패널 --------------------------------------
		printCal.setBounds(200, 135, 1718, 870);
		printCal.setLayout(null);
		printCal.setOpaque(false);
		
		System.out.println(printCal.year + "" + printCal.month);
		
		// -----------------------------------------------
		// 태스크 막대바 출력 패널 --------------------------------
		String barName;
		String barColor = "";
		ImageIcon bar = IC.getImageIcon("calendarBar_" + barColor);
		JPanel barDrawBox = new JPanel();
		barDrawBox.setBounds(0, 0, 100, 116);
		barDrawBox.setLayout(null);
		barDrawBox.setOpaque(false);
		if(toggleSwitch) { // true = 전체 프로젝트
			JLabel nameLbl = new JLabel();
			JLabel barLbl = new JLabel();
			TaskRepository tr = new TaskRepository();
			int i = 0;
			int max = 0;
			for(Project Pjlist : pjListOfUser) {
				List<Task> tkList = tr.taskListBypjNo(Pjlist.getProject_no());
				for(Task task : tkList) {
//					String startStamp = String.valueOf(task.getDeadLine());
//					String finishStamp = String.valueOf(task.getUpdateDate());
//					Date s = new Date(Long.parseLong(startStamp));
//					Date f = new Date(Long.parseLong(finishStamp));
					int days = (int)(task.getDeadLine().getTime() - task.getUpdateDate().getTime()) / 100000000;
					if (days > 24) {
						
					}
					
				}
				if(taskListOfPj)
				barName = Pjlist.getTitle();
				barColor = i;
				nameLbl.setText(barName);
				nameLbl.setFont(FT.nanumFontBold(11));
				nameLbl.setBounds(0, 0, 100, 16);
				barLbl.setIcon(bar);
				i++;
			}
		}
		
		
		
		// 투두 리스트 출력 패널 ---------------------------------
		// -----------------------------------------------
		
		

		// 뷰 설정 토글 버튼
		JButton toggleBtn = UT.getBtn(1380, 175, "prijectAll_toggle");
		add(toggleBtn);
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

		calBgPnl.setBounds(164, 160, 1718, 870);
		calBgPnl.setLayout(null);
		calBgPnl.setOpaque(false);

		// 배경 패널에 각 패널 붙이기 ------------------------------
		bgPnl.add(topPnl); // 상단 패널
		bgPnl.add(topBgPnl); // 상단 배경 패널
		add(printCal); // 달력 패널
		bgPnl.add(calBgPnl); // 메인 영역 배경 패널
		// -----------------------------------------------
		bgPnl.setBounds(0, 0, 1920, 1080);
		bgPnl.setLayout(null);
		bgPnl.setOpaque(false);
		setLayout(null);
//		setOpaque(false);
		add(bgPnl);
//		getContentPane().add(bgPnl);
//		// -----------------------------------------------
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

