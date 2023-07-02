package pnl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.swing.JLabel;

import dbutil.DBUtil;
import frame.MainFrame;
import tomatoPj.Column;
import tomatoPj.Feedback;
import tomatoPj.Task;
import utility.IconData;

public class SettingTask {
	DBUtil dbutil;
	Task task;
	Taskrefrom ts;
	IconData IC;
	Feedback feedback;
	Column column;
	MainFrame MF;
	public SettingTask(Taskrefrom ts,Task task,Column column,Feedback feedback) {
		dbutil = new DBUtil();
		IC = new IconData();
		this.task = task;
		ts.TakeTask = task;
		this.ts = ts;
		this.feedback = feedback;
		this.column = column;
	}
	public void reset() {
		task =null;
		ts.TakeTask = null;
		this.feedback = null;
	}
	public void setUsingMemberNum() {
		ts.useingMemberNum = MF.loginMember.getMember().getMember_no();
	}
	public void settingPKAndAc() {
		ts.task_Pk = task.getTask_no();
		ts.Active = task.getActive();
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
		if(column != null) {
		String calomnTitle = column.getTitle();
		ts.calomnTitle.setText(calomnTitle);
		}
		}
	
	public void setFeedback() {
		ts.feedBackText.setText("피드백을 입력해주세요");

		if(feedback != null) {
		ts.TakeFeedBack = feedback;
		ts.returnFeedBack_PK = feedback.getFeedback_no();
		ts.returnFeedBack_Task_no = task.getTask_no();

		ts.feedBackText.setText(feedback.getComment());
		}
}
}
