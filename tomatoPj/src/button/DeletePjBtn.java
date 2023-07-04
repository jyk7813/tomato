package button;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import frame.MainFrame;
import tomatoPj.ProjectRepository;
import utility.IconData;
import utility.Utility;

public class DeletePjBtn extends JButton{
	private IconData iconData;
	private Utility utility;
	private ImageIcon deleteIcon;
	private ProjectRepository projectRepository;
	
	public DeletePjBtn(MainFrame mainFrame, int project_no) {
		projectRepository = new ProjectRepository();
		iconData = new IconData();
		utility = new Utility();
		deleteIcon = iconData.getImageIcon("delete_btn");
		utility.setButtonProperties(this);
		addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				try {
					projectRepository.deleteProject(project_no);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			
		});
		this.setIcon(deleteIcon);
	}
}
