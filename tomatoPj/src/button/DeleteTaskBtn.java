package button;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import frame.MainFrame;
import tomatoPj.TaskRepository;
import utility.IconData;
import utility.Utility;

public class DeleteTaskBtn extends JButton {
	private IconData iconData;
	private Utility utility;
	private ImageIcon deleteIcon;
	private TaskRepository taskRepository;

	public DeleteTaskBtn(MainFrame mainFrame, int task_no) {
		taskRepository = new TaskRepository();
		iconData = new IconData();
		utility = new Utility();
		deleteIcon = iconData.getImageIcon("delete_btn");
		utility.setButtonProperties(this);
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getButton()== MouseEvent.BUTTON1) {
					taskRepository.deleteTask(task_no);
				}
			}

		});
		this.setIcon(deleteIcon);
	}
}
