package pnl.boradPnl;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import utility.IconData;
import javax.swing.JButton;

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
