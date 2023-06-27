package pnl.commonpnl;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.JPanel;

import utility.IconData;

public class MyInfoPnl extends JPanel {
	private IconData iconData;
	private Image image;
	/**
	 * Create the panel.
	 */
	public MyInfoPnl() {
		iconData = new IconData();
		this.image = iconData.getImageIcon("mini_bar").getImage();
		
		setOpaque(false);
		JLabel myInfoLbl = new JLabel(iconData.getImageIcon("user1"));
		myInfoLbl.setBounds(25, 30, 80, 80);
		add(myInfoLbl);
	}
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null); 
	}
}