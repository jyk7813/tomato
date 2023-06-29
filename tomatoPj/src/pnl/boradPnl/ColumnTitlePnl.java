package pnl.boradPnl;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import utility.IconData;

public class ColumnTitlePnl extends JPanel {
	private IconData iconData;
	private Image image;

	/**
	 * Create the panel.
	 */
	public ColumnTitlePnl() {
		iconData = new IconData();

		this.image = iconData.getImageIcon("boardTop_opaque").getImage();
		setLayout(null);
		setOpaque(false);
		
		JLabel titleLbl = new JLabel("title",SwingConstants.CENTER);
		titleLbl.setBounds(0, 0, 350, 60);
		add(titleLbl);

	}


	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, null);
	}
}
