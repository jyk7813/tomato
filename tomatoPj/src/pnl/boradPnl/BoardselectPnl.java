 package pnl.boradPnl;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import frame.MainFrame;
import tomatoPj.Column;

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

        scrollPane.setViewportView(columnSelectPnl);
   
    }
}
