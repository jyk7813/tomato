 package pnl.boradPnl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import frame.MainFrame;

import javax.swing.BoxLayout;
import javax.swing.JButton;

public class BoardselectPnl extends JPanel {
	
    public ColumnSelectPnl columnSelectPnl;

	/**
     * Create the panel.
     */
	// 보드판넬 메인 부분
    public BoardselectPnl(MainFrame mainFrame) {
       
        setLayout(new BorderLayout());
        setOpaque(false);
        
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        add(scrollPane, BorderLayout.CENTER);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(null);
        
        columnSelectPnl = new ColumnSelectPnl(mainFrame);

//        add(columnSelectPnl);
        scrollPane.setViewportView(columnSelectPnl);
        
        
        
        
//    	JButton btnNewButton = new JButton("New button");
//		add(btnNewButton);
//		
//		btnNewButton.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				mainFrame.showCard("task");
//			}
//		});
//        
//        ColumnPnl panel_1 = new ColumnPnl(mainFrame);
//        scrollPane.setViewportView(panel_1);
//        panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
//        
        
    }

    
}
