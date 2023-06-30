package pnl;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

import frame.MainFrame;
import tomatoPj.Task;

public class TaskBackgroundPnl extends JPanel{
	private Image image;
	private Task task;
	public Taskrefrom taskrefrom;
	public TaskBackgroundPnl(Image image, MainFrame mainFrame) {
		
		
		this.image = image;
		setLayout(null);

		taskrefrom = new Taskrefrom(mainFrame);

		add(taskrefrom);
		
	}
	public Taskrefrom TaskBackgroundPnl() {
		
		return taskrefrom;
	
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, this);
	}
}
