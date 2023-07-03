package pnl.boradPnl;

import java.awt.Graphics;
import java.awt.Image;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import button.deleteColBtn;
import frame.MainFrame;
import tomatoPj.Column;
import utility.IconData;

public class ColumnTitlePnl extends JPanel {
	private IconData iconData;
	private Image image;
	public JLabel titleLbl;
	public String colTitle;
	private deleteColBtn deletecolBtn;
	/**
	 * Create the panel.
	 */
	public ColumnTitlePnl(MainFrame mainFrame, String colTitle, int column_no) {
		
		iconData = new IconData();

		this.image = iconData.getImageIcon("boardTop_opaque").getImage();
		setLayout(null);
		setOpaque(false);
		
		titleLbl = new JLabel(colTitle, SwingConstants.CENTER);
		titleLbl.setBounds(0, 0, 350, 60);
		add(titleLbl);

		
	}


	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, null);
	}
}
