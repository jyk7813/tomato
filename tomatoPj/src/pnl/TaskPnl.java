package pnl;

import javax.swing.JPanel;
import javax.swing.JToggleButton;

import dbutil.DBUtil;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import frame.MainFrame;
import tomatoPj.Task;
import utility.FontData;
import utility.IconData;
import utility.Utility;

public class TaskPnl extends JPanel{
	private int Imoportance;
	public TaskPnl() {
		IconData IC = new IconData();
		FontData FD = new FontData();
		Utility util = new Utility();
		Imoportance = 0;
		// task �г�
		JPanel TaskPnlMain = new JPanel();
		TaskPnlMain.setSize(631,725);
		TaskPnlMain.setLocation(645,296);
		TaskPnlMain.setLayout(new BoxLayout(TaskPnlMain, BoxLayout.Y_AXIS));
		TaskPnlMain.setOpaque(false);
		
		
		// 날짜와 별
		JPanel StarAndDate = new JPanel();
		StarAndDate.setPreferredSize(new Dimension(631,134));
		StarAndDate.setOpaque(false);
		StarAndDate.setLayout(null);
		
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
			Connection conn = null;
			PreparedStatement stmt = null;
			try {
				conn = DBUtil.getConnection();
				String sql = "update task\r\n" + 
						"set importance = ?\r\n" + 
						"where task_no = 1;";
				
						
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, Imoportance+1);
				stmt.executeUpdate();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}finally {
				DBUtil.close(conn);
				DBUtil.close(stmt);
			}
			
			
			util.setButtonProperties(Star);
			StarAndDate.add(Stars[i]);
		}
		//Update date 라벨
		JLabel StartDate = new JLabel("2023.06.23");
		StartDate.setSize(98,16);
		StartDate.setLocation(65,68);
		StartDate.setForeground(new Color(36,161,138));
		StartDate.setFont(FD.nanumFont(16));
		JLabel StartDateAdd = new JLabel("부터");
		StartDateAdd.setSize(31,16);
		StartDateAdd.setLocation(165,68);
		StartDateAdd.setFont(FD.nanumFont(16));
		
		//DeadLineDate 까지
		JLabel DeadLineDate = new JLabel("2023.06.23");
		DeadLineDate.setSize(98,16);
		DeadLineDate.setLocation(435,68);
		DeadLineDate.setForeground(new Color(235,105,97));
		DeadLineDate.setFont(FD.nanumFont(16));
		JLabel DeadLineDateAdd = new JLabel("까지");
		DeadLineDateAdd.setSize(31,16);
		DeadLineDateAdd.setLocation(535,68);
		DeadLineDateAdd.setFont(FD.nanumFont(16));
		
		//기간 바
		JLabel TimeMangementBar = new JLabel(IC.getImageIcon("timeManagementBar"));
		TimeMangementBar.setSize(504, 22);
		TimeMangementBar.setLocation(63, 88);
		
		//기간 화살표
		JLabel TimeManagementNavi = new JLabel(IC.getImageIcon("timeManagementNavi"));
		TimeManagementNavi.setSize(16,14);
		TimeManagementNavi.setLocation(63,109);
		
		
		StarAndDate.add(DeadLineDate);
		StarAndDate.add(DeadLineDateAdd);
		StarAndDate.add(StartDate);
		StarAndDate.add(StartDateAdd);
		StarAndDate.add(TimeManagementNavi);		
		StarAndDate.add(TimeMangementBar);
		
		TaskPnlMain.add(StarAndDate);
		
		//task 테스크 패널 
		JPanel TaskUnderPanel = new JPanel();
		TaskUnderPanel.setPreferredSize(new Dimension(625,591));
		TaskUnderPanel.setOpaque(false);
		TaskPnlMain.add(TaskUnderPanel);
		
		
		


		

		JLabel Background =new JLabel(IC.getImageIcon("selectTask(BG)"));

		Background.add(TaskPnlMain);
		add(Background);


		
		setSize(1920, 1080);
		setVisible(true);
	}


	public static class MyFrame extends JFrame {
	    public MyFrame() {
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setTitle("My Frame");
	        setSize(1920, 1080);
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
