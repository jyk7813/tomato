package pnl;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.GridLayout;

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
		projectTitle.setBackground(UIManager.getColor("menuText"));
		projectTitle.setBounds(0, 41, 130, 140);
		panel.add(projectTitle);
		projectTitle.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		projectTitle.add(lblNewLabel_1);
		
		JPanel myInfo = new JPanel();
		myInfo.setBackground(UIManager.getColor("textHighlight"));
		myInfo.setBounds(0, 199, 130, 140);
		panel.add(myInfo);
		myInfo.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.YELLOW);
		panel_1.setBounds(0, 351, 130, 552);
		panel.add(panel_1);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(0, 199, 130, 140);
		panel.add(lblNewLabel);

	}
}
