package pnl.commonpnl;

import java.awt.Dimension;

import javax.swing.JPanel;

public class topPnl extends JPanel {

	public topPnl() {
		setOpaque(false);
	}

	@Override
	public Dimension getPreferredSize() {
		
		return new Dimension(1920,135);
	}

}
