package pnl;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;

import dbutil.DBUtil;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.BorderFactory;
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
	public TaskPnl() {
		IconData IC = new IconData();
		FontData FD = new FontData();
		Utility util = new Utility();
		
		Imoportance = 0;
		// task �г�
		JPanel TaskPnlMain=TaskMain(IC);
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
		
		// task detail
//		JPanel TaskUnderPnlDetail = TaskUnderPnlDetail(TaskUnderPanel);
		//스크롤페인
		
		JScrollPane scrollPane =scrollPane(IC,TaskUnderPanel);
		

		

		JLabel Background =new JLabel(IC.getImageIcon("selectTask(BG)"));
		TaskPnlMainLbl.add(StarAndDate);
		TaskPnlMain.add(TaskPnlMainLbl);
		TaskPnlMainLbl.add(scrollPane);
		Background.add(TaskPnlMain);
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
		TaskUnderPanel.setPreferredSize(new Dimension(625,593));
		TaskUnderPanel.setOpaque(false);
		return TaskUnderPanel;
	}
	
	public JPanel TaskUnderPnlDetail(JPanel TaskUnderPnl) {
		JPanel TaskUnderPnlDetail = new JPanel();
		TaskUnderPnlDetail.setSize(631,552);
		TaskUnderPnlDetail.setBackground(new Color(255,55,44));
		return TaskUnderPnlDetail;
	}
	
	public JScrollPane scrollPane(IconData IC, JPanel TaskUnderPnlDetail) {
		JScrollPane scrollPane = new JScrollPane(TaskUnderPnlDetail, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
//		Image track = IC.getImageIcon("dragBarLength_b").getImage();
//		Image thumb = IC.getImageIcon("dragBarLength_f").getImage();
//		scrollPane.getVerticalScrollBar().setUI(new MyScrollBarUi(thumb,track));
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		return scrollPane;
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
