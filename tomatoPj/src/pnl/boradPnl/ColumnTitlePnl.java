package pnl.boradPnl;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;
import utility.IconData;

public class ColumnTitlePnl extends JPanel {
	private IconData iconData;
	private Image image;

	/**
	 * Create the panel.
	 */
	public ColumnTitlePnl() {
		iconData = new IconData();

		this.image = iconData.getImageIcon("boardTop").getImage();
		setLayout(null);
		setOpaque(false);

	}


	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, null);
	}
}
