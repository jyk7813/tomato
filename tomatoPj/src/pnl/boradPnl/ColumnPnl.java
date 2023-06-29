package pnl.boradPnl;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import frame.MainFrame;
import utility.IconData;
import utility.Utility;

public class ColumnPnl extends JPanel {
	private IconData iconData;
	private Utility utility;
	private int taskCount = 0;
	
	/**
	 * Create the panel.
	 */
	public ColumnPnl(MainFrame mainFrame) {
		iconData = new IconData();
		utility = new Utility();
		
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
		
		JButton addcardBtn = new JButton(iconData.getImageIcon("addcardicon"));
		addcardBtn.setBounds(0, 0, 350, 79);
		utility.setButtonProperties(addcardBtn);

		JPanel panel = new JPanel() {
		    @Override
		    public Dimension getPreferredSize() {
		        int height = 80* taskCount + 80; 
		        return new Dimension(350, height);
		    }
		};

		panel.setLayout(null);
		panel.setOpaque(false);
		panel.add(addcardBtn);

		JScrollPane scrollPane = new JScrollPane(panel);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setBorder(null);

		add(scrollPane, BorderLayout.CENTER);
		setOpaque(false);

		addcardBtn.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        taskCount++;
		        TaskPnl taskPnl = new TaskPnl(mainFrame);
		        taskPnl.setBounds(0, 80 * (taskCount - 1), 350, 80); 
		        panel.add(taskPnl);
		        
		        // Update the position of the addcardBtn
		        addcardBtn.setLocation(addcardBtn.getX(), 80 * taskCount);
		        
		        panel.revalidate();
		        panel.repaint();
		    }
		});

//		AddColumnPnl taskPnl = new AddColumnPnl();
//		taskPnl.setBounds(0,0,350,60);
//		panel.add(taskPnl);
//		
	}
	

}
