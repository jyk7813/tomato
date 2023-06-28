package pnl;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JPanel;

import frame.MainFrame;
import pnl.boradPnl.boardselectPnl;
import pnl.commonpnl.ProjectMemberPnl;
import pnl.commonpnl.topPnl;
import tomatoPj.Member;
import tomatoPj.Task;

import java.awt.BorderLayout;

public class TaskBackgroundPnl extends JPanel{
	private Image image;
	private Task task;
	
	public TaskBackgroundPnl(Image image, MainFrame mainFrame) {
		
		
		this.image = image;
		setLayout(null);
		Taskrefrom taskrefrom = new Taskrefrom();
		add(taskrefrom);
		
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, this);
	}
}
