package pnl.boradPnl;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import frame.MainFrame;
import tomatoPj.Column;
import pnl.Taskrefrom;
import tomatoPj.Task;
import utility.IconData;
import utility.Utility;

public class TaskPnl extends JPanel {
	private IconData iconData;
	private Image image;
	private Utility utility;
	private JButton jButton;
	Taskrefrom tr;
	/**
	 * Create the panel.
	 */
	public TaskPnl(MainFrame mainFrame, Column column, Task task) {
		iconData = new IconData();
		utility = new Utility();

		// 넌이제부터 태스크야 알았어?
		this.image = iconData.getImageIcon("boardMiddle_opaque").getImage();
		setLayout(null);
		setOpaque(false);
		String tasktitle;
		//System.out.println("야!!!!!!" + task.equals(null));
		if(task!=null) { 
			tasktitle = "title";
		} else {
			tasktitle = task.getTitle();
		}
		
		
		
		JButton jButton = new JButton(tasktitle);
		jButton.setBounds(0, 0, 360, 80);
		add(jButton);
		utility.setButtonProperties(jButton);
		
		jButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
//				tr.settingNewTask();
			
				mainFrame.showCard("task");
				
			}
		});

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, null);
	}
}
