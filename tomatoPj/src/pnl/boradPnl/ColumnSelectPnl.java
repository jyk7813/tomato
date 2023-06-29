package pnl.boradPnl;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.Collections;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import frame.MainFrame;
import tomatoPj.Column;
import utility.IconData;
import utility.Utility;

public class ColumnSelectPnl extends JPanel {
	 private int columnCount = 0;
	 private IconData iconData;
	 private Utility utility;
	 public JButton addBtn;
	 public Column thisCol;
	// public String colTitle;
	 
	/**
	 * Create the panel.
	 */
	public ColumnSelectPnl(MainFrame mainFrame) {
		//this.colTitle = colTitle;
		iconData = new IconData();
		utility = new Utility();
		setLayout(null);
		JPanel columnTopPanel = new JPanel();
		columnTopPanel.setLayout(null);
		columnTopPanel.setBounds(31, 0, 350, 101);
		columnTopPanel.setOpaque(false);

//		ColumnPnl columnPnl = new ColumnPnl();
//		add(columnPnl);
//		columnPnl.setBounds(31, 0, 350, 940);
		setOpaque(false);
		
		AddColumnPnl addColumnPnl = new AddColumnPnl();
		addColumnPnl.setBounds(0, 41, 350, 60);
		addColumnPnl.setOpaque(false); // for testing
		columnTopPanel.add(addColumnPnl);
		add(columnTopPanel);
		
		addBtn = new JButton();
		addBtn.setBounds(0, 0, 350, 60);
		addColumnPnl.add(addBtn);
		utility.setButtonProperties(addBtn);
		
		// 칼럼추가버튼 액션리스너
		addBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				List<Column> col = mainFrame.pjInfo.getCol();
				Collections.sort(col, (a, b) -> a.getColumn_index() - b.getColumn_index());
				for (Column column : col) {
					columnCount++;
					ColumnPnl columnPnl = new ColumnPnl(mainFrame, column.getTitle());
					
					add(columnPnl);
					columnPnl.setBounds(addColumnPnl.getX()+31, 0, 350, 940);
					addColumnPnl.setBounds(addColumnPnl.getX() + columnPnl.getWidth() + 20, addColumnPnl.getY(), 350, 60);
					add(addColumnPnl);
					revalidate();
					repaint();
					// 태스크 생성
					//columnPnl.addcardBtn.doClick();
				}
			}
		});	
	}
	  @Override
	    public Dimension getPreferredSize() {
	        int width = (350 + 20) * columnCount + 350; // 각 컬럼의 너비 (350) + 간격 (20) + 최초의 350
	        return new Dimension(width, 945);
	    }

	
	

}
