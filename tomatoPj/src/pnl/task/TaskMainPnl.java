package pnl.task;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import utility.FontData;
import utility.IconData;
import utility.Utility;

public class TaskMainPnl extends JPanel{
	private IconData IC;
	private FontData FD;
	private Utility util;
	
	
	public TaskMainPnl(){
		IC = new IconData();
		FD = new FontData();
		util = new Utility();
				
		
		
		add(TaskMainLbl());
		setBounds(645, 293, 631, 725);
		setOpaque(false);
	}
	
	public JLabel TaskMainLbl() {
		JLabel TaskPnlMainLbl = new JLabel(IC.getImageIcon("contentPanel"));
		TaskPnlMainLbl.setSize(631, 725);
		TaskPnlMainLbl.setLocation(645, 290);
		TaskPnlMainLbl.setLayout(new BoxLayout(TaskPnlMainLbl, BoxLayout.Y_AXIS));
		TaskPnlMainLbl.setOpaque(false);
		return TaskPnlMainLbl;
	}
	public JPanel StarAndDate() {
		JPanel StarAndDate = new JPanel();
		StarAndDate.setPreferredSize(new Dimension(631, 140));
		StarAndDate.setOpaque(false);
		StarAndDate.setLayout(null);
		return StarAndDate;
	}

}
