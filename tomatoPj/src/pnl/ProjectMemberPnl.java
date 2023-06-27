package pnl;

import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JLabel;

public class ProjectMemberPnl extends JPanel {

	/**
	 * Create the panel.
	 */
	public ProjectMemberPnl() {
		setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		add(lblNewLabel_3);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("New label");
		add(lblNewLabel);

	}

}
