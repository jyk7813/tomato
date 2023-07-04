package pnl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import frame.MainFrame;
import tomatoPj.Column;
import tomatoPj.ColumnRepository;
import tomatoPj.Task;
import tomatoPj.TaskRepository;
import frame.MainFrame;
import tomatoPj.Task;


public class todoTaskBtn extends JButton {
	private int task_no;
	private ColumnRepository colRepo;
	private TaskRepository taskRepo;
	private Task task;
	private Column column;
	public todoTaskBtn(int task_no, MainFrame mainFrame) {
		colRepo = new ColumnRepository();
		taskRepo = new TaskRepository();
		this.task_no = task_no;
		task = taskRepo.searchTaskBy_no(task_no);
		column = colRepo.searchCol_task_no(task_no);
		
		addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.setTask(task, column);
				mainFrame.showCard("task");
			}
		});
	}

	public int getTask_no() {
		return task_no;
	}

	public void setTask_no(int task_no) {
		this.task_no = task_no;
	}

}
