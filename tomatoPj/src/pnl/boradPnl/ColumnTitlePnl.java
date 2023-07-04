package pnl.boradPnl;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
	public deleteColBtn deletecolBtn;
	/**
	 * Create the panel.
	 */
	public ColumnTitlePnl(MainFrame mainFrame, String colTitle, int column_no, ColumnSelectPnl columnSelectPnl) {
		this.colTitle = colTitle;
		iconData = new IconData();

		this.image = iconData.getImageIcon("boardtoptranslucent").getImage();
		setLayout(null);
		setOpaque(false);
		
		titleLbl = new JLabel(colTitle, SwingConstants.CENTER);
		titleLbl.setBounds(0, 0, 350, 60);
		add(titleLbl);
		deletecolBtn = new deleteColBtn(mainFrame,column_no);
		deletecolBtn.setBounds(30, 30, 30, 30);
		add(deletecolBtn);
		deletecolBtn.setVisible(false);
		
	}
	
	public void setimage() {
		if (!isEnabled()) {
			this.image = iconData.getImageIcon("boardtoptranslucent").getImage();
			deletecolBtn.setVisible(false);
		} else {
			this.image = iconData.getImageIcon("boardtop_opaque").getImage();
			
		}
	}


	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, null);
	}
}
