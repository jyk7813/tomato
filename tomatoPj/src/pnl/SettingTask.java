package pnl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import frame.MainFrame;
import tomatoPj.Feedback;
import tomatoPj.Task;
import utility.IconData;

public class SettingTask {
	Task task;
	Taskrefrom ts;
	IconData IC;
	Feedback feedback;
	public SettingTask(Task task,Taskrefrom ts,Feedback feedback) {
		IC = new IconData();
		this.task = task;
		this.ts = ts;
		this.feedback = feedback;
	}
	
	public String setUpdataDate() {
		String updateDate = "";
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
		Timestamp tt = new Timestamp(System.currentTimeMillis());
		
		updateDate = dateFormat.format(tt);
		
		if(task != null) {
		
		updateDate = dateFormat.format(task.getUpdateDate());
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
		return updateDate;
		}
		return updateDate;
	}
	public void SetStar() {
		for(int i = 0; i<task.getImportance(); i++) {
			System.out.println(200);
			ts.returnImoportance= task.getImportance();
			ts.stars[i].setIcon(IC.getImageIcon("starRed"));
		}
	}
	public String setContent() {
		String content = "내용을 입력해주세요!";
		if(task != null) {
		content = task.getContent();
		}
		return content;
	}
	public String setFeedback() {
		String feedback = "피드백을 입력해주세요!";
		if(feedback != null) {
			feedback = this.feedback.getComment();
		}
		return feedback;
	}

}
