package pnl;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.List;

import javax.swing.JPanel;

import frame.MainFrame;
import pnl.boradPnl.BoardselectPnl;
import pnl.commonpnl.ProjectMemberPnl;
import pnl.commonpnl.TopMainPnl;
import tomatoPj.Column;

public class BoradPnl extends JPanel{
	private Image image;
	public BoardselectPnl panel_2;
	private String title;
	private MainFrame mainFrame;
	public ProjectMemberPnl projectMemberPnl;
	
	public BoradPnl(Image image, MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		this.image = image;
		projectMemberPnl = new ProjectMemberPnl(mainFrame);
		panel_2 = new BoardselectPnl(mainFrame);
		setLayout(new BorderLayout(0, 0));
		addPnl();
	
		
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				
				projectMemberPnl.myInfoPnl.settingMyInfopnl();
				int size=0;
				try {
					size = mainFrame.pjInfo.getCol().size();
					
				} catch (NullPointerException e1) {
					size=0;
				}
				if(size>0 && mainFrame.pjInfo.getColumnCnt() != mainFrame.pjInfo.getCol().size()) {
					panel_2.columnSelectPnl.columnSetting();
				}
				mainFrame.pjInfo.setColumnCnt(0);
			}
		});
		
		
		
	
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, this);
	}

	public void addPnl() {
		TopMainPnl topPnl = new TopMainPnl(mainFrame);
		add(topPnl, BorderLayout.NORTH);
		
		
		add(projectMemberPnl, BorderLayout.WEST);
		projectMemberPnl.setOpaque(false);
		
		panel_2.setOpaque(false);
		add(panel_2, BorderLayout.CENTER);
	}
	
}
