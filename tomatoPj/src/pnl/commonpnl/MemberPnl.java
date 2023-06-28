package pnl.commonpnl;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.JPanel;

import utility.IconData;

public class MemberPnl extends JPanel {
	private IconData iconData;
	private Image image;
	/**
	 * Create the panel.
	 */
	public MemberPnl() {
		iconData = new IconData();
		this.image = iconData.getImageIcon("user1").getImage();
		
		setOpaque(false);
	}
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null); 
	}
}