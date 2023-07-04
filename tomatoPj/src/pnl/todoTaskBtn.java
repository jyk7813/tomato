package pnl;

import javax.swing.JButton;

import tomatoPj.Task;

public class todoTaskBtn extends JButton {
	private int task_no;
	
	public todoTaskBtn(int task_no) {
		this.task_no = task_no;
		
	}

	public int getTask_no() {
		return task_no;
	}

	public void setTask_no(int task_no) {
		this.task_no = task_no;
	}

}
