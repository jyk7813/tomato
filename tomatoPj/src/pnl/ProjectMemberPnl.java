package pnl;

import javax.swing.JPanel;
import javax.swing.JLabel;

public class ProjectMemberPnl extends JPanel {

	/**
	 * Create the panel.
	 */
	public ProjectMemberPnl() {
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 130, 945);
		add(panel);
		panel.setLayout(null);
		
		JPanel projectTitle = new JPanel();
		projectTitle.setBounds(0, 41, 130, 140);
		panel.add(projectTitle);
		
		JLabel lblNewLabel = new JLabel("Title");
		projectTitle.add(lblNewLabel);
		
		JPanel myInfo = new JPanel();
		myInfo.setBounds(0, 199, 130, 140);
		panel.add(myInfo);

	}
}
