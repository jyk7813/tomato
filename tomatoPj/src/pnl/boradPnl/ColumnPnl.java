package pnl.boradPnl;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import frame.MainFrame;

public class ColumnPnl extends JPanel {

	/**
	 * Create the panel.
	 */
	public ColumnPnl(MainFrame mainFrame) {
		
		setLayout(new BorderLayout(0, 0));
		
		JPanel columnTitle = new JPanel();
		add(columnTitle, BorderLayout.NORTH);
		columnTitle.setLayout(null);
		columnTitle.setPreferredSize(new Dimension(350,101));
		columnTitle.setOpaque(false);
		
//		JPanel columnTitleSet = new JPanel();
//		columnTitleSet.setLayout(null);
//		columnTitleSet.setBounds(0, 0, 350, 101);
//		columnTitleSet.setOpaque(false);
//		columnTitle.add(columnTitleSet);
//		
//		AddColumnPnl addColumnPnl = new AddColumnPnl();
//		addColumnPnl.setBounds(0, 41, 350, 60);
//		addColumnPnl.setOpaque(false); // for testing
//		columnTitle.add(addColumnPnl);

		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		add(scrollPane, BorderLayout.CENTER);
		setOpaque(false);
		
		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(null);
		panel.setOpaque(false);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setBorder(null);
		scrollPane.setViewportView(new TaskPnl(mainFrame));
//		AddColumnPnl taskPnl = new AddColumnPnl();
//		taskPnl.setBounds(0,0,350,60);
//		panel.add(taskPnl);
//		
	}

}
