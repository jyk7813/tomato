package pnl;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;

import org.springframework.security.access.method.P;

import dbutil.DBUtil;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import frame.MainFrame;
import tomatoPj.Task;
import utility.FontData;
import utility.IconData;
import utility.MyScrollBarUi;
import utility.Utility;

public class TaskPnl extends JPanel{
	private int Imoportance;
	//멤버의 이미지 아이콘
	List<ImageIcon> list = new ArrayList<>();
	int Count = 0;
	
	public TaskPnl() {
		
		IconData IC = new IconData();
		FontData FD = new FontData();
		Utility util = new Utility();
		list.add(IC.getImageIcon("user1"));
		list.add(IC.getImageIcon("user2"));
		list.add(IC.getImageIcon("user3"));
		list.add(IC.getImageIcon("user4"));
		list.add(IC.getImageIcon("user5"));
		
		Imoportance = 0;
		// task �г�
		JPanel TaskMain=TaskMain(IC);
		JLabel TaskPnlMainLbl = TaskMainLbl(IC);
		// 날짜와 별
		JPanel StarAndDate = StarAndDate();
			
		//별 세팅
		StarSet(IC,StarAndDate,util);
		
		//Update date 라벨
		String date ="2023.06.23";
		JLabel StartDate=SetUpdateLbl(FD,date);
		JLabel StartDateAdd= UpdateMentLbl(FD);
		
		//DeadLineDate 까지
		String deadDate = "2023.07.3";
		JLabel DeadLineDate = DeadLineDate(FD,deadDate) ;
		JLabel DeadLineDateAdd = DeadLineDateAdd(FD);
		
		//기간 바
		JLabel TimeMangementBar = TimeMangementBar(IC);
		
		//기간 화살표
		JLabel TimeManagementNavi = TimeMangerMentNavi(IC);
		
		//전부 더하기
		StarAndDateADD(StarAndDate,DeadLineDate,DeadLineDateAdd,StartDate,StartDateAdd,TimeMangementBar,TimeManagementNavi);
		
		
		//task 테스크 패널 
		JPanel TaskUnderPanel = TaskUnderPnl();
		
		//스크롤페인
		
		JScrollPane scrollPane =scrollPane(IC,TaskUnderPanel);
		
		//프로젝트 타이틀과 멤버 아이콘이 들어갈 패널
		JPanel ProTitleAndMember = ProTitleAndMember(TaskUnderPanel);
		
		String title= "프로젝트 제목";
		JLabel projectTitle = ProjectTitle(FD,ProTitleAndMember,title);
		
		JPanel MemberPnl =MemberPnl(ProTitleAndMember);
		
		// 멤버용 패널 
//		seletMember(IC, MemberPnl);
//		MemberPnl.add(seletMember(IC, MemberPnl));
		ProTitleAndMember.add(projectTitle);
		scrollPane.add(ProTitleAndMember);
		
		JLabel Background =new JLabel(IC.getImageIcon("selectTask(BG)"));
		// 추가버튼
		selectMember(IC,MemberPnl);
		
		TaskMain.add(StarAndDate);
//		TaskMain.add(TaskPnlMainLbl);
		TaskMain.add(scrollPane);
		Background.add(TaskMain);
		add(Background);


	}
	/**
	 * @author 이호재
	 * @param IconDate IC
	 * @return 메인페널
	 */
	public JPanel TaskMain(IconData IC) {
		// task �г�
		JPanel TaskPnlMain = new JPanel();
		TaskPnlMain.setSize(631,725);
		TaskPnlMain.setLocation(645,296);
		TaskPnlMain.setOpaque(false);
		// 라벨 이미지 추가
		return TaskPnlMain;
	}
	/**
	 * @author 이호재
	 * @param IconDate IC
	 * @return 메인페널의 라벨 이미지
	 */
	public JLabel TaskMainLbl(IconData IC) {
		JLabel TaskPnlMainLbl = new JLabel(IC.getImageIcon("contentPanel"));
		TaskPnlMainLbl.setSize(631,725);
		TaskPnlMainLbl.setLocation(645,296);
		
		TaskPnlMainLbl.setLayout(new BoxLayout(TaskPnlMainLbl, BoxLayout.Y_AXIS));
		TaskPnlMainLbl.setOpaque(false);
		return TaskPnlMainLbl;
	}
	/**
	 * @param 이호재
	 * @return 별과 기간 패널 반환
	 */
	public JPanel StarAndDate() {
		JPanel StarAndDate = new JPanel();
		StarAndDate.setPreferredSize(new Dimension(631,134));
		StarAndDate.setOpaque(false);
		StarAndDate.setLayout(null);
		return StarAndDate;
	}
	
	/**
	 * @author 이호재
	 * @param IC
	 * @param StarAndDate
	 * @param util
	 *  
	 */
	public void StarSet(IconData IC,JPanel StarAndDate,Utility util) {
		//별
		JToggleButton [] Stars = new JToggleButton[5];
		for (int i = 0; i < Stars.length; i++) {
			JToggleButton Star = new JToggleButton(IC.getImageIcon("starGray"));
			Stars[i] = Star;
			Star.setSize(20,21);
			Star.setLocation(234+((i+1)*25), 28);
			MouseAdapter ma = new MouseAdapter() {
				 @Override
				    public void mousePressed(MouseEvent e) {
						for (int i = 0; i < Stars.length; i++) {
							Stars[i].setIcon(IC.getImageIcon("starGray"));
						}
						int index = 0;

						 for (JToggleButton Star : Stars) {
							 JToggleButton SelectedStar = (JToggleButton)e.getSource();
							 if(SelectedStar.equals(Star)) {
								 Imoportance = index;
							 }
							 index++;
						 }
						 System.out.println(Imoportance+1);
						 for(int i = 0;i<=Imoportance;i++)
							 Stars[i].setIcon(IC.getImageIcon("starRed"));
					   }
			
			};
			Star.addMouseListener(ma);
			util.setButtonProperties(Star);
			StarAndDate.add(Stars[i]);
		}
	}
	/**
	 * @author 이호재
	 * @param FD
	 * @param date JLabel :날짜 정보
	 * @return JLabel
	 */
	public JLabel SetUpdateLbl(FontData FD,String date) {
		//Update date 라벨
		JLabel StartDate = new JLabel(date);
		StartDate.setSize(98,16);
		StartDate.setLocation(65,68);
		StartDate.setForeground(new Color(36,161,138));
		StartDate.setFont(FD.nanumFont(16));
		return StartDate;
	}
	/**
	 * @author 이호재
	 * @param FD
	 * @return JLabel 
	*/
	public JLabel UpdateMentLbl(FontData FD) {
		JLabel StartDateAdd = new JLabel("부터");
		StartDateAdd.setSize(31,16);
		StartDateAdd.setLocation(165,68);
		StartDateAdd.setFont(FD.nanumFont(16));
		return StartDateAdd;
		
	}
	/**
	 * @author 이호재
	 * @param FD
	 * @param String 마감기한의 String 값
	 * @return JLabel 
	*/
	public JLabel DeadLineDate(FontData FD, String deadDate) {
		//DeadLineDate 까지
		JLabel DeadLineDate = new JLabel(deadDate);
		DeadLineDate.setSize(98,16);
		DeadLineDate.setLocation(435,68);
		DeadLineDate.setForeground(new Color(235,105,97));
		DeadLineDate.setFont(FD.nanumFont(16));
		return  DeadLineDate;
	}
	
	/**
	 * 
	 * @param FD
	 * @return "까지" 라벨
	 */
	public JLabel DeadLineDateAdd(FontData FD) {
		JLabel DeadLineDateAdd = new JLabel("까지");
		DeadLineDateAdd.setSize(31,16);
		DeadLineDateAdd.setLocation(535,68);
		DeadLineDateAdd.setFont(FD.nanumFont(16));
		return DeadLineDateAdd;
	}
	/**
	 * 
	 * @param IC
	 * @return 타임라인 선
	 */
	public JLabel TimeMangementBar(IconData IC) {
		JLabel TimeMangementBar = new JLabel(IC.getImageIcon("timeManagementBar"));
		TimeMangementBar.setSize(504, 22);
		TimeMangementBar.setLocation(63, 88);
		return TimeMangementBar;
	}
	/**
	 * @apiNote 시간 단위로 이동하는 기능 필요.
	 * @param IC
	 * @return 타임라인 네비
	 */
	public JLabel TimeMangerMentNavi(IconData IC) {
		JLabel TimeManagementNavi = new JLabel(IC.getImageIcon("timeManagementNavi"));
		TimeManagementNavi.setSize(16,14);
		TimeManagementNavi.setLocation(63,109);
		return TimeManagementNavi;
	}
	/*
	 * 별 기간 pnl 전부 더하는 행동
	 */
	public void StarAndDateADD(JPanel StarAndDate,JLabel DeadLineDate,JLabel DeadLineDateAdd,JLabel StartDate,JLabel StartDateAdd,JLabel TimeMangementBar,JLabel TimeManagementNavi) {
		StarAndDate.add(DeadLineDate);
		StarAndDate.add(DeadLineDateAdd);
		StarAndDate.add(StartDate);
		StarAndDate.add(StartDateAdd);
		StarAndDate.add(TimeManagementNavi);		
		StarAndDate.add(TimeMangementBar);
	}
	/*
	 * 언더페널
	 */
	public JPanel TaskUnderPnl() {
		JPanel TaskUnderPanel = new JPanel();
		TaskUnderPanel.setPreferredSize(new Dimension(625,591));
		TaskUnderPanel.setLayout(new BoxLayout(TaskUnderPanel, BoxLayout.Y_AXIS));
		TaskUnderPanel.setOpaque(false);
		return TaskUnderPanel;
	}
//사용 안하는중 혹시 모름	
//	public JPanel TaskUnderPnlDetail(JPanel TaskUnderPnl) {
//		JPanel TaskUnderPnlDetail = new JPanel();
//		TaskUnderPnlDetail.setSize(631,552);
//		TaskUnderPnlDetail.setBackground(new Color(255,55,44));
//		return TaskUnderPnlDetail;
//	}
	// 스크롤 페인
	public JScrollPane scrollPane(IconData IC, JPanel TaskUnderPnlDetail) {
		JScrollPane scrollPane = new JScrollPane(TaskUnderPnlDetail, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		return scrollPane;
	}
	
	public JPanel ProTitleAndMember(JPanel TaskUnderPanel) {
		//프로젝트 타이틀과 멤버 아이콘이 들어갈 패널
		JPanel ProTitleAndMember = new JPanel();
		ProTitleAndMember.setOpaque(false);
		ProTitleAndMember.setSize(631,164);
		ProTitleAndMember.setLayout(null);
		TaskUnderPanel.add(ProTitleAndMember);
		return ProTitleAndMember;
	}
	public JPanel MemberPnl(JPanel ProTitleAndMember) {
		JPanel MemberPnl = new JPanel();
//		MemberPnl.setOpaque(false);
		MemberPnl.setLayout(new BoxLayout(MemberPnl, BoxLayout.X_AXIS));
		MemberPnl.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		MemberPnl.add(Box.createRigidArea(new Dimension(10, 0)));
		MemberPnl.setSize(499,94);
		MemberPnl.setLocation(60,40);
		
		ProTitleAndMember.add(MemberPnl);
		return MemberPnl;
	}
	public JLabel ProjectTitle(FontData FD,JPanel ProTitleAndMember,String title) {
		JLabel projectTitle = new JLabel(title);
		projectTitle.setSize(208,53);
		projectTitle.setLocation(58,-5);
		projectTitle.setFont(FD.nanumFont(20));
		return projectTitle;
	}
	public void selectMember(IconData IC, JPanel MemberPnl) {
	    JLabel plus = new JLabel(IC.getImageIcon("plus"));
	    plus.setSize(80, 80);
	    plus.addMouseListener(new MouseAdapter() {
	        public void mousePressed(MouseEvent e) {
	            JLabel memberIcon = new JLabel(list.get(Count));

	            MemberPnl.add(memberIcon);

	            // 아이콘들을 수평으로 정렬하기 위해 BoxLayout 사용
	            MemberPnl.setLayout(new BoxLayout(MemberPnl, BoxLayout.X_AXIS));

	            // 아이콘들 간의 간격을 위한 빈 공간 컴포넌트 추가
	            MemberPnl.add(Box.createRigidArea(new Dimension(10, 0)));
	            
	            MemberPnl.revalidate();
	            MemberPnl.repaint();
	            Count++;
	            System.out.println(Count);
	        
	        }
	    });
	    MemberPnl.add(plus);
	}
	    
	public static class MyFrame extends JFrame {
	    public MyFrame() {
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setTitle("My Frame");
	        setSize(1920, 1080);
	        
	        setUndecorated(true);
	        setLocationRelativeTo(null); //중앙정렬
	    }
	}
	public static void main(String[] args) {
		MyFrame frame = new MyFrame();
		TaskPnl task = new TaskPnl();
		frame.add(task);
		frame.setVisible(true);
		
	}
	
}
