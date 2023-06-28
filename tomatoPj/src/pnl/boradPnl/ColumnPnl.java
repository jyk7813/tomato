package pnl.boradPnl;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import frame.MainFrame;

public class ColumnPnl extends JPanel {

	/**
	 * Create the panel.
	 */
	public ColumnPnl(MainFrame mainFrame) {
		setLayout(new BorderLayout(0, 0));

		
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(510, 905);
	}
}
