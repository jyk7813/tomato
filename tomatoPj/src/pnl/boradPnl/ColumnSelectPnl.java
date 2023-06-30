package pnl.boradPnl;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import frame.MainFrame;
import tomatoPj.Column;
import tomatoPj.ColumnRepository;
import tomatoPj.Task;
import tomatoPj.TaskRepository;
import utility.IconData;
import utility.Utility;

public class ColumnSelectPnl extends JPanel {
	private int columnCount = 0;
	private IconData iconData;
	private Utility utility;
	public JButton addBtn;
	public Column thisCol;
	// public String colTitle;
	private TaskRepository taskRepo;
	private ColumnRepository colRepo;

	/**
	 * Create the panel.
	 */
	public ColumnSelectPnl(MainFrame mainFrame) {
		colRepo = new ColumnRepository();
		taskRepo = new TaskRepository();
		// this.colTitle = colTitle;
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

				// System.out.println("pj_no " + mainFrame.pjInfo.getProject_no());
				List<Column> col = mainFrame.pjInfo.getCol();
				// System.out.println("크기" + col.size());

				if (col.size() > 0) {
					Collections.sort(col, (a, b) -> a.getColumn_index() - b.getColumn_index());
					for (Column column : col) {
						columnCount++;

						ColumnPnl columnPnl = new ColumnPnl(mainFrame, column.getTitle(), column);
						add(columnPnl);
						columnPnl.setBounds(addColumnPnl.getX() + 31, 0, 350, 940);
						addColumnPnl.setBounds(addColumnPnl.getX() + columnPnl.getWidth() + 20, addColumnPnl.getY(),
								350, 60);
						add(addColumnPnl);
						revalidate();
						repaint();
					//	columnPnl.addcardBtn.doClick();

					}
				} else {
					Collections.sort(col, (a, b) -> a.getColumn_index() - b.getColumn_index());
					Column column = colRepo.addColumn(mainFrame.pjInfo.getProject_no());
					//System.out.println(column);
					ColumnPnl columnPnl = new ColumnPnl(mainFrame, column.getTitle(), column);
					add(columnPnl);
					columnPnl.setBounds(addColumnPnl.getX() + 31, 0, 350, 940);
					addColumnPnl.setBounds(addColumnPnl.getX() + columnPnl.getWidth() + 20, addColumnPnl.getY(), 350,
							60);
					add(addColumnPnl);
					revalidate();
					repaint();

				}

			}
		});
		/*
		 * List<Column> col = mainFrame.pjInfo.getCol(); if(col.size() > 0) {
		 * Collections.sort(col, (a, b) -> a.getColumn_index() - b.getColumn_index());
		 * for (Column column : col) { columnCount++; List<Task> taskList = new
		 * ArrayList<>(); try { taskList =
		 * taskRepo.taskListByColNo(column.getColumn_no()); } catch (SQLException e1) {
		 * e1.printStackTrace(); }
		 * 
		 * ColumnPnl columnPnl = new ColumnPnl(mainFrame, column.getTitle(), column,
		 * taskList); add(columnPnl); columnPnl.setBounds(addColumnPnl.getX()+31, 0,
		 * 350, 940); addColumnPnl.setBounds(addColumnPnl.getX() + columnPnl.getWidth()
		 * + 20, addColumnPnl.getY(), 350, 60); add(addColumnPnl); revalidate();
		 * repaint();
		 * 
		 * }
		 */
	}

	@Override
	public Dimension getPreferredSize() {
		int width = (350 + 20) * columnCount + 350; // 각 컬럼의 너비 (350) + 간격 (20) + 최초의 350
		return new Dimension(width, 945);
	}

}
