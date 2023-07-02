package pnl.boradPnl;

import java.awt.Graphics;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import dbutil.SelectProjectInfo;
import frame.MainFrame;
import tomatoPj.Column;
import tomatoPj.Feedback;
import pnl.Taskrefrom;
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

	// 테스크 클릭 버튼
	/**
	 * Create the panel.
	 */
	public TaskPnl(MainFrame mainFrame, Column column, Task task) {
		
		this.mainFrame = mainFrame;
		iconData = new IconData();
		utility = new Utility();
		this.pjInfo = mainFrame.pjInfo;
		
		
		// 넌이제부터 태스크야 알았어?
		this.image = iconData.getImageIcon("boardMiddle_opaque").getImage();
		setLayout(null);
		setOpaque(false);
		String tasktitle;
		
			if(task==null) { 
			tasktitle = "title";
		} else {
			tasktitle = task.getTitle();
		}
			
		
		
		jButton2 = new JButton(tasktitle);
		jButton2.setBounds(0, 0, 360, 80);
		add(jButton2);
		utility.setButtonProperties(jButton2);
		
		jButton2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Feedback feedback = new Feedback(6, 27, 1, "대본수정");
				
				if(task !=null) {
					System.out.println("주는 칼럼 제목1");
					System.out.println(column.getTitle());
				mainFrame.setTask(task, column, feedback);
				mainFrame.showCard("task");
				}
				if (task == null){
					System.out.println("주는 칼럼 제목2");
					System.out.println(column.getTitle());
					mainFrame.setTask(task, column, feedback);
					mainFrame.showCard("task");
				}
			
				revalidate();
				repaint();
			}
		});

	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, null);
	}

}
