package pnl;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;

public class ProjectSelectEastPnl extends JPanel {
	
	/**
	 * Create the panel.
	 */
	public ProjectSelectEastPnl() {
		setLayout(new BorderLayout(0, 0));
		
		ProjectMemberPnl projectMemberPnl = new ProjectMemberPnl();
		add(projectMemberPnl, BorderLayout.WEST);
		
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.CENTER);

	}

}
