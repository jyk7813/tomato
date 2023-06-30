package pnl.boradPnl;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

import utility.IconData;

public class AddColumnPnl extends JPanel {
	private IconData iconData;
	private Image image;

	/**
	 * Create the panel.
	 */
	public AddColumnPnl() {
		iconData = new IconData();

		this.image = iconData.getImageIcon("addColumn").getImage();
		setLayout(null);
		setOpaque(false);

	}


	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, null);
	}
}
