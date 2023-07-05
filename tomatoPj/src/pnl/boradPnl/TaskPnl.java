package pnl.boradPnl;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import button.DeleteTaskBtn;
import dbutil.SelectProjectInfo;
import frame.MainFrame;
import tomatoPj.Column;
import tomatoPj.Task;
import utility.IconData;
import utility.Utility;

interface ButtonClickListener {
	void onButtonClick();
}

public class TaskPnl extends JPanel {
	private IconData iconData;
	private Image image;
	private Utility utility;
	private JButton jButton;
	SelectProjectInfo pjInfo;
	MainFrame mainFrame;
	// 자기참조용
	public JButton jButton2;
	public DeleteTaskBtn deleteTaskBtn;

	// 테스크 클릭 버튼
	/**
	 * Create the panel.
	 */
	public TaskPnl(MainFrame mainFrame, Column column, Task task) {

		this.mainFrame = mainFrame;
		iconData = new IconData();
		utility = new Utility();
		this.pjInfo = mainFrame.pjInfo;
		deleteTaskBtn = new DeleteTaskBtn(mainFrame, task,mainFrame.pjInfo.getProject_no());
		deleteTaskBtn.setBounds(300, 0, 30, 30);
		add(deleteTaskBtn);
		deleteTaskBtn.setVisible(false);

		// 넌이제부터 태스크야 알았어?
		this.image = iconData.getImageIcon("boardMiddletranslucent").getImage();
		setLayout(null);
		setOpaque(false);
		String tasktitle;

		if (task == null) {
			tasktitle = "title";
		} else {
			tasktitle = task.getTitle();
		}
		
		JLabel taskTitleLbl = new JLabel(tasktitle,SwingConstants.CENTER);
		taskTitleLbl.setBounds(0,25, 360, 30);
		add(taskTitleLbl);
		taskTitleLbl.setOpaque(false);
		
//
//		jButton2 = new JButton(tasktitle);
//		jButton2.setBounds(0, 0, 360, 80);
//		add(jButton2);
//		utility.setButtonProperties(jButton2);
		
		addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				if (!isEnabled()) {
					return;
				}

				if (task != null && e.getButton() == MouseEvent.BUTTON1) {					
					
					mainFrame.setTask(task, column);
					mainFrame.showCard("task");
				}
				if (task == null && e.getButton() == MouseEvent.BUTTON1) {
					
					mainFrame.setTask(task, column);
					mainFrame.showCard("task");
				}

				revalidate();
				repaint();
			}
			
		});

//		jButton2.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				if (!isEnabled()) {
//					return;
//				}
//
//				if (task != null) {					
//					mainFrame.setTask(task, column);
//					mainFrame.showCard("task");
//				}
//				if (task == null) {
//					mainFrame.setTask(task, column);
//					mainFrame.showCard("task");
//				}
//
//				revalidate();
//				repaint();
//			}
//		});
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, null);
	}

	public void setimage() {
		if (!isEnabled()) {
			this.image = iconData.getImageIcon("boardMiddletranslucent").getImage();
			revalidate();
			repaint();
		} else {
			this.image = iconData.getImageIcon("boardMiddle_opaque").getImage();
			revalidate();
			repaint();
		}
	}

}
