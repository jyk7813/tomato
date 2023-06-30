package pnl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.security.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
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
import pnl.commonpnl.TopMainPnl;
import tomatoPj.Column;
import tomatoPj.Member;
import tomatoPj.MemberRepository;
import tomatoPj.Member_Tag_Package_Repository;
import tomatoPj.Project;
import tomatoPj.ProjectRepository;
import tomatoPj.Task;
import tomatoPj.TaskRepository;
import utility.CalendarData;
import utility.FontData;
import utility.IconData;
import utility.PrintPlanner;
import utility.Utility;

public class TestTodoPnl extends JPanel{
	private Image image;
	
	private final static IconData IC = new IconData();
	private final static FontData FT = new FontData();
	private final static Utility UT = new Utility();
	private final static CalendarData CD = new CalendarData();
	private PrintPlanner pp;
	private Project selectPj;
	private boolean toggleSwitch = true;
	
	
	// 달력 출력 패널 클래스 ------------------------------------
	CalendarSwing printCal = new CalendarSwing();
	
	public TestTodoPnl(MainFrame mainFrame) {
		addComponentListener(new ComponentListener() {
			@Override
			public void componentShown(ComponentEvent e) {
				setView(toggleSwitch);
			}

			@Override
			public void componentResized(ComponentEvent e) {
			}

			@Override
			public void componentMoved(ComponentEvent e) {
			}

			@Override
			public void componentHidden(ComponentEvent e) {
			}
		});
		
//		// 상단 배경 패널 ------------------------------------
//		JPanel topBgPnl = new JPanel() {
//			public void paintComponent(Graphics g) {
//				super.paintComponent(g);
//				g.drawImage(IC.getImageIcon("topLine").getImage(), 0, 0, null);
//				setOpaque(false); // 이미지 불투명도 설정 : false = 불투명(이미지 표시) / true = 투명
//			}
//		};
		
		

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
		TopMainPnl topPnl = new TopMainPnl(mainFrame);
		topPnl.showCard("SelectedTodo");
		topPnl.setBounds(0, 0, 1920, 135);
		topPnl.setOpaque(false);


		// 달력 패널 -----------------------------------------
		JPanel calPnl = new JPanel();
		calPnl.setBounds(164, 300, 857, 870);
		calPnl.setLayout(null);
		calPnl.setOpaque(false);

		// 투두 리스트 패널 ------------------------------------
		JPanel todoListPnl = new JPanel();
		todoListPnl.setBounds(1026, 175, 857, 870);
		todoListPnl.setLayout(null);
		todoListPnl.setOpaque(false);
		
		// 달력 출력 패널 --------------------------------------
		printCal.setBounds(200, 135, 1718, 870);
		printCal.setLayout(null);
		printCal.setOpaque(false);
		
		
		// 막대바 출력 패널 -------------------------------------
		JPanel barPnl = new JPanel();
		barPnl.setBounds(200, 135, 768, 730);
		barPnl.setLayout(null);
		barPnl.setOpaque(false);
		JPanel barBox = new JPanel();
		barBox.setBounds(0, 0, 90, 110);
		barBox.setLayout(null);
		barBox.setOpaque(false);
//		List<Project> pjListOfUser = null; // 로그인한 유저의 모든 프로젝트
//		List<Task> tkListOfUser = mainFrame.loginMember.getTakeTaskList(); // 로그인한 유저의 모든 프로젝트의 모든 태스크
		String barName = "";
		String barColor = "";
		ImageIcon barDraw;
//		if(mainFrame.loginMember.getPjList() != null) {
//			for(Project pj : pjListOfUser) {
//				System.out.println(pj.getTitle());
//				try {
//					List<Task> tkOfPj = tr.taskListBypjNo(pj.getProject_no());
//					for(Task tk : tkOfPj) {
//						System.out.println("투두에서확인" + tk.getTitle());
//					}
//				} catch (SQLException e1) {
//					e1.printStackTrace();
//				}
//			}
//		}
		
		barPnl.add(barBox);
		
		
	
		
		// 투두리스트 출력 패널 ----------------------------------
		
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

//		// 현재 날짜 출력 라벨
//		JLabel currentDate = new JLabel();
//		String todoDate = CD.getCurrentDate();
//		currentDate.setText(todoDate);
//		currentDate.setFont(FT.nanumFontBold(18));
//		currentDate.setForeground(Color.DARK_GRAY);
//		currentDate.setBounds(90, 140, 240, 30);
//		currentDate.setOpaque(false);
//		todoListPnl.add(currentDate);

		calBgPnl.setBounds(164, 160, 1718, 870);
		calBgPnl.setLayout(null);
		calBgPnl.setOpaque(false);

		// 배경 패널에 각 패널 붙이기 ------------------------------
		bgPnl.add(topPnl); // 상단 패널
//		bgPnl.add(topBgPnl); // 상단 배경 패널
		bgPnl.add(todoListPnl); // 투두 리스트 패널
		bgPnl.add(calPnl); // 달력 패널
		bgPnl.add(barPnl); // 막대바 출력 패널
		add(printCal); // 달력 패널
		bgPnl.add(calBgPnl); // 메인 영역 배경 패널
		// -----------------------------------------------
		bgPnl.setBounds(0, 0, 1920, 1080);
		bgPnl.setLayout(null);
		bgPnl.setOpaque(false);
		setLayout(null);
		setOpaque(false);
		add(bgPnl);
//		getContentPane().add(bgPnl);
//		// -----------------------------------------------
//		setExtendedState(JFrame.MAXIMIZED_BOTH);
//		setUndecorated(true);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		getContentPane().setLayout(null);
		setVisible(true);
		// -----------------------------------------------
	}
	
	// 페이지 전환 메소드 (전체 프로젝트 / 프로젝트 별) ---------------
			public void setView (Boolean toggleSwitch) {
				TaskRepository tr = new TaskRepository();
				MemberRepository mr = new MemberRepository();
				ProjectRepository pr = new ProjectRepository();
				MainFrame mainFrame = new MainFrame();

				List<Project> pjListOfUser = mainFrame.loginMember.getPjList(); // 로그인한 유저의 모든 프로젝트
				List<Task> tkListOfUser = mainFrame.loginMember.getTakeTaskList(); // 로그인한 유저의 모든 프로젝트의 모든 태스크
				List<Task> tkAll;
				List<Member> mbAll;
				List<PrintPlanner> printList = null;
				Collections.sort(pjListOfUser, (a, b) -> a.getProject_no() - b.getProject_no());
				
				Project firstPj = pjListOfUser.get(0);
				String strUpdate = "";
				String strDeadLine = "";
				if (toggleSwitch) {
					printList = new ArrayList<>();
					try {
						for(Project pjList : pjListOfUser) {
							tkAll = tr.taskListBypjNo(pjList.getProject_no());
							for(Task tkList : tkAll) {
								strUpdate = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(tkList.getUpdateDate());
								strDeadLine = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(tkList.getDeadLine());
								PrintPlanner p = new PrintPlanner(pjList.getProject_no(), pjList.getTitle(), tkList.getTitle(), strUpdate, strDeadLine);
								printList.add(p);
								System.out.println(p);
							}
						}
					} catch (SQLException e1) {
						System.out.println("투두오류");
						e1.printStackTrace();
					}
				} else {
					try {
						List<Member> memsOfPj = mr.getMemberBypj_no(firstPj.getProject_no());
						List<Task> taskOfPj = tr.taskListBypjNo(firstPj.getProject_no());
						List<Member> memOfTask; 
						for(Member m : memsOfPj) {
							for(Task t : taskOfPj) {
								memOfTask = t.getList();
								for(Member mOft : memOfTask) {
									if(m.getMember_no() == mOft.getMember_no()) {
										String strUpdate1 = "";
										String strDeadLine1 = "";
										strUpdate = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(t.getUpdateDate());
										strDeadLine = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(t.getDeadLine());
										PrintPlanner p = new PrintPlanner(m.getMember_no(), m.getName(), t.getTitle(), strUpdate, strDeadLine);
										printList.add(p);
										System.out.println(p);
									}
								}
							}
						}
						
					} catch (SQLException e1) {
						System.out.println("투두오류");
						e1.printStackTrace();
					}
				}	
			}
}

