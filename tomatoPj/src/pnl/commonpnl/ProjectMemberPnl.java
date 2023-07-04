package pnl.commonpnl;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.UIManager;

import frame.MainFrame;
import utility.IconData;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class ProjectMemberPnl extends JPanel {
	private IconData iconData;
	private ProjectTitlePnl projectTitlePnl;
	public MyInfoPnl myInfoPnl;
	public MemberListPnl memberPnl;
	/**
	 * Create the panel.
	 */
	public ProjectMemberPnl(MainFrame mainFrame) {
		iconData = new IconData();
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 130, 853);
		panel.setOpaque(false);
		add(panel);
		panel.setLayout(null);
		
		
		projectTitlePnl = new ProjectTitlePnl(mainFrame);
		projectTitlePnl.setBackground(new Color(0,0,0,0));
		projectTitlePnl.setBounds(0, 40, 130, 140);
		panel.add(projectTitlePnl);
		projectTitlePnl.setLayout(new GridLayout(0, 1, 0, 0));
		
		myInfoPnl = new MyInfoPnl(mainFrame);
		myInfoPnl.setBackground(new Color(0,0,0,0));
		myInfoPnl.setBounds(0, 200, 130, 140);
		panel.add(myInfoPnl);
		myInfoPnl.setLayout(new GridLayout(1, 0, 0, 0));
		
		memberPnl = new MemberListPnl(mainFrame);
		memberPnl.setBackground(new Color(0,0,0,0));
		memberPnl.setOpaque(false);
		memberPnl.setBounds(0, 360, 130, 553);
		panel.add(memberPnl);
		panel.setBackground(new Color(0,0,0,0));
		
		
	}
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(130, 905);
	}
}
