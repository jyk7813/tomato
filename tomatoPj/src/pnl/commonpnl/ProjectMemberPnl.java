package pnl.commonpnl;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.UIManager;

import utility.IconData;

import java.awt.Color;
import java.awt.GridLayout;

public class ProjectMemberPnl extends JPanel {
	private IconData iconData;
	private ProjectTitlePnl projectTitlePnl;
	private MyInfoPnl myInfoPnl;
	private MemberListPnl memberPnl;
	/**
	 * Create the panel.
	 */
	public ProjectMemberPnl() {
		iconData = new IconData();
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 130, 853);
		panel.setOpaque(false);
		add(panel);
		panel.setLayout(null);
		
		
		projectTitlePnl = new ProjectTitlePnl();
		projectTitlePnl.setBackground(new Color(0,0,0,0));
		projectTitlePnl.setBounds(0, 40, 130, 140);
		panel.add(projectTitlePnl);
		projectTitlePnl.setLayout(new GridLayout(0, 1, 0, 0));
		
		myInfoPnl = new MyInfoPnl();
		myInfoPnl.setBackground(new Color(0,0,0,0));
		myInfoPnl.setBounds(0, 200, 130, 140);
		panel.add(myInfoPnl);
		myInfoPnl.setLayout(new GridLayout(1, 0, 0, 0));
		
		memberPnl = new MemberListPnl();
		memberPnl.setBackground(new Color(0,0,0,0));
		memberPnl.setOpaque(false);
		memberPnl.setBounds(0, 360, 130, 553);
		panel.add(memberPnl);
		panel.setBackground(new Color(0,0,0,0));
	}
}
