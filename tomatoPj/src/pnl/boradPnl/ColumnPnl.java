package pnl.boradPnl;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import frame.MainFrame;
import utility.IconData;

public class ColumnPnl extends JPanel {
	private IconData iconData;
	/**
	 * Create the panel.
	 */
	public ColumnPnl(MainFrame mainFrame) {
		iconData = new IconData();
		
		setLayout(new BorderLayout(0, 0));
		
		JPanel columnTop = new JPanel();
		add(columnTop, BorderLayout.NORTH);
		columnTop.setLayout(null);
		columnTop.setPreferredSize(new Dimension(350,101));
		columnTop.setOpaque(false);
		
		ColumnTitlePnl columnTitlePnl = new ColumnTitlePnl();
		columnTitlePnl.setBounds(0, 41, 350, 80);
		columnTop.add(columnTitlePnl);
		
		
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
		JButton addcardBtn = new JButton();
		addcardBtn.setBounds(0, 0, 350, 80);
		
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
