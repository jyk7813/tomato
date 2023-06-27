package pnl;

import javax.swing.JPanel;

import pnl.commonpnl.ProjectMemberPnl;

import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import java.awt.Dimension;

public class ProjectSelectWestPnl extends JPanel {

	/**
	 * Create the panel.
	 */
	public ProjectSelectWestPnl() {
		setLayout(new BorderLayout(0, 0));

		ProjectMemberPnl projectMemberPnl = new ProjectMemberPnl() {
			@Override
			public Dimension getPreferredSize() {
				setOpaque(false);
				return new Dimension(510, 905);
			}
		};

		JPanel panel_1 = new JPanel();
		setOpaque(false);
		add(panel_1, BorderLayout.CENTER);
		add(projectMemberPnl, BorderLayout.WEST);

	}

}
