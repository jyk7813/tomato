package pnl.projectpnl;

import javax.swing.JPanel;

import frame.MainFrame;
import pnl.commonpnl.ProjectMemberPnl;

import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import java.awt.Dimension;

public class ProjectSelectWestPnl extends JPanel {
	public ProjectMemberPnl projectMemberPnl;
	/**
	 * Create the panel.
	 */
	public ProjectSelectWestPnl(MainFrame mainFrame) {
		setLayout(new BorderLayout(0, 0));

		projectMemberPnl = new ProjectMemberPnl(mainFrame) {
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
