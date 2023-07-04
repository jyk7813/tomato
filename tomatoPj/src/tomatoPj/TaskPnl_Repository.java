package tomatoPj;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

import pnl.Taskrefrom;

public class TaskPnl_Repository {
	Map<Integer,Taskrefrom> Library;
	public TaskPnl_Repository() {
		Library = new HashMap<>();
	}
	public void input(int TaskNum,Taskrefrom Taskrefrom) {
		
		Library.put(TaskNum, Taskrefrom);
		
	}
	public Taskrefrom TaskPnlshow(int TaskNum) {
		return Library.get(TaskNum);
	}
}
