 package pnl.boradPnl;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import frame.MainFrame;

import javax.swing.BoxLayout;
import javax.swing.JButton;

public class boardselectPnl extends JPanel {
    /**
     * Create the panel.
     */
    public boardselectPnl(MainFrame mainFrame) {
        setLayout(new BorderLayout());
        setOpaque(false);
        
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setOpaque(false);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        panel.add(scrollPane, BorderLayout.CENTER);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(null);
    	JButton btnNewButton = new JButton("New button");
		add(btnNewButton);
		
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.showCard("task");
			}
		});
        
        ColumnPnl panel_1 = new ColumnPnl(mainFrame);
        scrollPane.setViewportView(panel_1);
        panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
        
        
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(1790, 945);
    }
}
