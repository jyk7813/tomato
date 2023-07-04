package pnl;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import dbutil.DBUtil;
import frame.MainFrame;
import tomatoPj.Column;
import tomatoPj.Feedback;
import tomatoPj.Function_Tag;
import tomatoPj.Member_task;
import tomatoPj.Task;
import tomatoPj.TaskRepository;
import utility.IconData;

public class SettingTask {
	DBUtil dbutil;
	Task task;
	Taskrefrom ts;
	IconData IC;
	Feedback feedback;
	Column Takecolumn;
	MainFrame MF;
	TaskRepository TR;
	TagAddButton TAB;
	public SettingTask(Taskrefrom ts,Task task,Column column,Feedback feedback) {
		dbutil = new DBUtil();
		IC = new IconData();
		this.task = task;
		ts.TakeTask = task;
		this.ts = ts;
		this.feedback = feedback;
		this.Takecolumn = column;
		TR = new TaskRepository();

	}
	public void reset() {
		task =null;
		ts.TakeTask = null;
		this.feedback = null;
		ts.useingMemberNum = 0;
		ts.task_Pk = 0;
		ts.Active = 0;
		

		String updateDate = "";
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
		Timestamp tt = new Timestamp(System.currentTimeMillis());
		updateDate = dateFormat.format(tt);
		ts.StartDate.setText(updateDate);
		
		String updateDate2 = "";
		
		Timestamp tt2 = new Timestamp(System.currentTimeMillis());
		updateDate2 = dateFormat.format(tt2);
		ts.deadLineDate.setText(updateDate2);
		
		for(int i = 0; i<5; i++) {
			ts.returnImoportance=0;
			ts.stars[i].setIcon(IC.getImageIcon("starGray"));
		}

		ts.taskTitle.setText("제목을 입력해주세요");
		ts.contentText.setText("내용을 입력해주세요");
		ts.feedBackText.setText("피드백을 입력해주세요");
		ts.memberList = new ArrayList<>();
		ts.Function_Tag_List = new ArrayList<>();
		ts.tagPnl.removeAll();
		ts.TagSet();
		ts.tagPnl.add(ts.plusTag);
		
	}
	
	public void settingMember_TaskList(){
		ts.member_taskList = new ArrayList<>();
		if(task != null) {
		
		}
		}

	public void setMemberlist() {
		if(task != null) {
		try {
			ts.memberList = TR.searchMemberByTask_no(task.getTask_no());
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}else {
			ts.memberList = new ArrayList<>();
		}
	}
	
	public void setTaglist() {
		if(task != null) {
			try {
				System.out.println(TR.searchFunction_tagByTask_no(task.getTask_no()));
				ts.Function_Tag_List=TR.searchFunction_tagByTask_no(task.getTask_no());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			ts.Function_Tag_List = new ArrayList<>();
		}
	}
	public void setUsingMemberNum() {

		ts.useingMemberNum = MF.loginMember.getMember().getMember_no();
	}
	public void settingPKAndAc() {
		if(task != null) {
		ts.task_Pk = task.getTask_no();
		ts.Active = task.getActive();
		}		
	}
	public void TagAddButton(List<Function_Tag>list) {
		
		ts.tagPnl.add(ts.plusTag);
		IconData IC =new IconData();
		
		
		ts.return_Function_Tag_List = list;

		
		for(int i = 0; i<list.size();i++) {
			JLabel box = new JLabel();
//			box.setSize();
			
			JLabel tag = new JLabel(IC.getImageIcon("tag"));
			JLabel tagText = new JLabel();		
			tag.setLayout(null);
			tagText.setForeground(Color.BLACK);
			
			tagText.setSize(80,30);
			tagText.setLocation(20,0);
			System.out.println(list.get(i).getText());
		    ts.tagTexts.add(list.get(i).getText());
		    tagText.setText(list.get(i).getText());
		    tag.add(tagText);
		    tagText.setVisible(true);
		    ts.CountTag =list.size();
		    tag.addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
						ts.CountTag--;
						ts.tagPnl.remove(tag);
	        	        // 아이콘들 왼쪽 정렬
	        	        for(int i = 0;i< ts.tagTexts.size();i++) {
	        	        	if(ts.tagTexts.get(i).equals(tagText.getText()))
	        	        	ts.tagTexts.remove(i);
	        	        	
	        	        }
	        	       for(int i = 0; i<list.size(); i++) {
	        	    	   if(list.get(i).getText().equals(tagText.getText())) {
	        	    		   list.remove(i);
	        	    	   }
	        	       }
	        	        ts.plusTag.setVisible(true);
	        	        ts.tagPnl.revalidate();
	        	        ts.tagPnl.repaint();
					}
				
			    });		
		    ts.tagPnl.add(tag);
		    ts.tagPnl.revalidate();
		    ts.tagPnl.repaint();
		    System.out.println("카운팅 확인");
		    System.out.println(ts.CountTag);
		    
		}	
		if(ts.CountTag>4) {
			ts.plusTag.setVisible(false);
		}
	}	
	
	public String setUpdataDate() {
		String updateDate = "";
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
		Timestamp tt = new Timestamp(System.currentTimeMillis());
		
		updateDate = dateFormat.format(tt);
		
		if(task != null) {
		
		updateDate = dateFormat.format(task.getUpdateDate());
		ts.StartDate.setText(updateDate);
		return updateDate;
		}
		return updateDate;
	}
	
	public String setDeadDate() {
		String updateDate = "";
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
		Timestamp tt = new Timestamp(System.currentTimeMillis());
		
		updateDate = dateFormat.format(tt);
		
		if(task != null) {
		
		updateDate = dateFormat.format(task.getDeadLine());
		ts.deadLineDate.setText(updateDate);
		return updateDate;
		}
		return updateDate;
	}
	
	public void SetStar() {
		if(task != null) {
		for(int i = 0; i<task.getImportance(); i++) {
			ts.returnImoportance= task.getImportance();
			ts.stars[i].setIcon(IC.getImageIcon("starRed"));
		}
		}
	}
	public void setTitle() {
		
		if(task != null) {
			ts.taskTitle.setText(task.getTitle());
		}else if(task == null) {
			ts.taskTitle.setText("제목을 입력해주세요");
		}
	}
	public String setContent() {

		String content = "내용을 입력해주세요!";
		if(task != null) {
		content = task.getContent();
		ts.contentText.setText(content);
		}
		return content;
	}
	public void setColTitle() {
		String calomnTitle = Takecolumn.getTitle();
		ts.calomnTitle.setText(calomnTitle);

		}
	
	public void setFeedback() {
		ts.feedBackText.setText("피드백을 입력해주세요");

		if(feedback != null && task != null) {
		ts.TakeFeedBack = feedback;
		ts.returnFeedBack_PK = feedback.getFeedback_no();
		ts.returnFeedBack_Task_no = task.getTask_no();

		ts.feedBackText.setText(feedback.getComment());
		}
}
}
