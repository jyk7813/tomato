package pnl.boradPnl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import frame.MainFrame;

public class ColumnSelectPnl extends JPanel {
	 private int columnCount = 0;
	/**
	 * Create the panel.
	 */
	public ColumnSelectPnl(MainFrame mainFrame) {
		setLayout(null);
		JPanel columnTitle = new JPanel();
		columnTitle.setLayout(null);
		columnTitle.setBounds(31, 0, 350, 101);
		columnTitle.setOpaque(false);
		
//		ColumnPnl columnPnl = new ColumnPnl();
//		add(columnPnl);
//		columnPnl.setBounds(31, 0, 350, 940);
		setOpaque(false);
		
		AddColumnPnl addColumnPnl = new AddColumnPnl();
		addColumnPnl.setBounds(0, 41, 350, 60);
		addColumnPnl.setOpaque(false); // for testing
		columnTitle.add(addColumnPnl);
		add(columnTitle);
		
		JButton addBtn = new JButton();
		addBtn.setBounds(0, 0, 350, 60);
		addColumnPnl.add(addBtn);
		addBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				columnCount++;
				ColumnPnl columnPnl = new ColumnPnl(mainFrame);
				add(columnPnl);
				columnPnl.setBounds(addColumnPnl.getX()+31, 0, 350, 940);
				addColumnPnl.setBounds(addColumnPnl.getX()+31 + columnPnl.getWidth(), addColumnPnl.getY(), 350, 60);
				add(addColumnPnl);
				revalidate();
				repaint();
			}
		});

		
	}
	  @Override
	    public Dimension getPreferredSize() {
	        int width = (350 + 20) * columnCount + 350; // 각 컬럼의 너비 (350) + 간격 (20) + 최초의 350
	        return new Dimension(width, 945);
	    }

	
	

}
