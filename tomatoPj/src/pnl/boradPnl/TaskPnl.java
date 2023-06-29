package pnl.boradPnl;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import frame.MainFrame;
import utility.IconData;
import utility.Utility;

public class TaskPnl extends JPanel {
	private IconData iconData;
	private Image image;
	private Utility utility;

	/**
	 * Create the panel.
	 */
	public TaskPnl(MainFrame mainFrame) {
		iconData = new IconData();
		utility = new Utility();

		this.image = iconData.getImageIcon("boardMiddle_opaque").getImage();
		setLayout(null);
		setOpaque(false);
		JButton jButton = new JButton();
		jButton.setBounds(0, 0, 360, 80);
		add(jButton);
		utility.setButtonProperties(jButton);
		
		jButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.showCard("task");
			}
		});

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, null);
	}
}
