package pnl.commonpnl;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

import utility.FontData;
import utility.IconData;
import javax.swing.JLabel;
import java.awt.GridLayout;

public class ProjectTitlePnl extends JPanel {
	private IconData iconData;
	private FontData fontData;
	private Image image;
	/**
	 * Create the panel.
	 */
	public ProjectTitlePnl() {
		iconData = new IconData();
		fontData = new FontData();
		this.image = iconData.getImageIcon("mini_bar").getImage();
		
		
		setOpaque(false);
		setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel projectTiltleLbl = new JLabel("Project1");
		projectTiltleLbl.setFont(fontData.nanumFont(20));
		add(projectTiltleLbl);
		
		
	}
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null); 
	}

}
