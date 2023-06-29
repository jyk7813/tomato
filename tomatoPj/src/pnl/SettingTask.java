package pnl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import frame.MainFrame;
import tomatoPj.Task;
import utility.IconData;

public class SettingTask {
	Task task;
	Taskrefrom ts;
	IconData IC;
	public SettingTask(Task task,Taskrefrom ts) {
		IC = new IconData();
		this.task = task;
		this.ts = ts;
		
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
			ts.stars[i].setIcon(IC.getImageIcon("starRed"));
		}
	}

}
