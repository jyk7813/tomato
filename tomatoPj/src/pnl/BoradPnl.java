package pnl;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

import frame.MainFrame;
import pnl.boradPnl.boardselectPnl;
import pnl.commonpnl.ProjectMemberPnl;
import pnl.commonpnl.topPnl;

import java.awt.BorderLayout;

public class BoradPnl extends JPanel{
	private Image image;
	
	public BoradPnl(Image image, MainFrame mainFrame) {
		this.image = image;
		setLayout(new BorderLayout(0, 0));
		
		topPnl topPnl = new topPnl();
		add(topPnl, BorderLayout.NORTH);
		
		ProjectMemberPnl projectMemberPnl = new ProjectMemberPnl();
		
		add(projectMemberPnl, BorderLayout.WEST);
		projectMemberPnl.setOpaque(false);
		
		boardselectPnl panel_2 = new boardselectPnl();
		panel_2.setOpaque(false);
		add(panel_2, BorderLayout.CENTER);
		
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, this);
	}
}
