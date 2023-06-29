package pnl;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.List;

import javax.swing.JPanel;

import dbutil.LoginMember;
import dbutil.SelectProjectInfo;
import frame.MainFrame;
import pnl.boradPnl.BoardselectPnl;
import pnl.commonpnl.ProjectMemberPnl;
import pnl.commonpnl.topPnl;
import tomatoPj.Column;
import tomatoPj.Member;
import utility.Utility;

import java.awt.BorderLayout;

public class BoradPnl extends JPanel{
	private Image image;
	private BoardselectPnl panel_2;
	private String title;
	
	public BoradPnl(Image image, MainFrame mainFrame) {
		
		
		this.image = image;
		setLayout(new BorderLayout(0, 0));
		
		topPnl topPnl = new topPnl(mainFrame);
		add(topPnl, BorderLayout.NORTH);
		
		ProjectMemberPnl projectMemberPnl = new ProjectMemberPnl();
		
		add(projectMemberPnl, BorderLayout.WEST);
		projectMemberPnl.setOpaque(false);
		//////////
		
		panel_2 = new BoardselectPnl(mainFrame, title);
		panel_2.setOpaque(false);
		add(panel_2, BorderLayout.CENTER);
		title = "title";
	
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				List<Column> col = mainFrame.pjInfo.getCol();
				panel_2.columnSelectPnl.thisCol = col.get(0);
				title = col.get(0).getTitle();
				System.out.println("제목이없어? " + col.get(0).getTitle());
				
				panel_2.columnSelectPnl.addBtn.doClick();
			}
		});
		
	
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, this);
	}
}
