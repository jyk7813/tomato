package pnl.boradPnl;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import frame.MainFrame;
import tomatoPj.Column;
import tomatoPj.Task;
import tomatoPj.TaskRepository;
import utility.IconData;
import utility.Utility;

public class ColumnPnl extends JPanel {
	private IconData iconData;
	private Utility utility;
	private int taskCount = 0;
	public ColumnTitlePnl columnTitlePnl;
	public JButton addcardBtn;
	public Task task;
	private TaskRepository taskRepo;
	List<Task> taskList;
	private Column column;
	public TaskPnl taskPnl;
	public List<TaskPnl> taskPnls = new ArrayList<>();

	/**
	 * Create the panel.
	 */
	public ColumnPnl(MainFrame mainFrame, String colTitle, Column column, List<Task> taskList, ColumnSelectPnl columnSelectPnl) {
		this.column = column;
		try {
			if (column != null) {
				this.taskList = taskList;
				System.out.println(taskList.size());
			}
		} catch (NullPointerException e2) {
		}
		iconData = new IconData();
		utility = new Utility();

		setLayout(new BorderLayout(0, 0));
		 setEnabledRecursive(this, false);
		JPanel columnTop = new JPanel();
		add(columnTop, BorderLayout.NORTH);
		columnTop.setLayout(null);
		columnTop.setPreferredSize(new Dimension(350, 101));
		columnTop.setOpaque(false);

		String columntitle;
		if (colTitle == null) {
			columntitle = "title";
		} else {
			columntitle = colTitle;
		}

		columnTitlePnl = new ColumnTitlePnl(mainFrame, columntitle, column.getColumn_no(),columnSelectPnl);
		columnTitlePnl.setBounds(0, 41, 350, 80);
		columnTop.add(columnTitlePnl);


		addcardBtn = new JButton(iconData.getImageIcon("addtasktranslucent"));
		addcardBtn.setBounds(0, 0, 350, 79);
		utility.setButtonProperties(addcardBtn);

		JPanel panel = new JPanel() {
			@Override
			public Dimension getPreferredSize() {
				int height = 80 * taskCount + 80;
				return new Dimension(350, height);
			}
		};

		panel.setLayout(null);
		panel.setOpaque(false);
		panel.add(addcardBtn);

		// columnUnderPnl.add(addcardBtn);
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
				if (!isEnabled()) {
		            return; // Skip the action if the button is not enabled
		        }
				taskCount++;
				TaskPnl taskPnl = new TaskPnl(mainFrame, column, task);
                taskPnl.setBounds(0, 80 * (taskCount - 1), 350, 80);

                // Add the TaskPnl to the list
                taskPnls.add(taskPnl);

				panel.add(taskPnl);

				// Update the position of the addcardBtn
				addcardBtn.setLocation(addcardBtn.getX(), 80 * taskCount);

				panel.revalidate();
				panel.repaint();
			}
		});
		if (taskList.size() > 0) {
			for (Task task : taskList) {
				// addcardBtn.doClick();
				taskCount++;
				taskPnl = new TaskPnl(mainFrame, column, task);
                taskPnl.setBounds(0, 80 * (taskCount - 1), 350, 80);

                // Add the TaskPnl to the list
                taskPnls.add(taskPnl);
				panel.add(taskPnl);

				// Update the position of the addcardBtn
				addcardBtn.setLocation(addcardBtn.getX(), 80 * taskCount);

				panel.revalidate();
				panel.repaint();
			}
		}
		
	}
	public Column getColumn() {
		return column;
	}
	public void setColumn(Column column) {
		this.column = column;
	}
	private void setEnabledRecursive(Component component, boolean isEnabled) {
        component.setEnabled(isEnabled);
        if (component instanceof Container) {
            for (Component child : ((Container) component).getComponents()) {
                setEnabledRecursive(child, isEnabled);
            }
        }
    }
	public void setimage() {
		if (!isEnabled()) {
			addcardBtn.setIcon(iconData.getImageIcon("addtasktranslucent"));
			columnTitlePnl.setimage();
			revalidate();
			repaint();
		} else {
			addcardBtn.setIcon(iconData.getImageIcon("addcardicon"));
			columnTitlePnl.setimage();
			revalidate();
			repaint();
		}
	}
}
