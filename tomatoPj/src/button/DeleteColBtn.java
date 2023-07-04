package button;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import frame.MainFrame;
import tomatoPj.Column;
import tomatoPj.ColumnRepository;
import utility.IconData;
import utility.Utility;

public class DeleteColBtn extends JButton {
	private IconData iconData;
	private Utility utility;
	private ImageIcon deleteIcon;
	private ColumnRepository columnRepository;

	public DeleteColBtn(MainFrame mainFrame, int column_no, Column column) {
		columnRepository = new ColumnRepository();
		iconData = new IconData();
		utility = new Utility();
		deleteIcon = iconData.getImageIcon("delete_btn");
		utility.setButtonProperties(this);
		addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				columnRepository.deleteColumn(column_no);

				mainFrame.boradPnl.panel_2.columnSelectPnl.deleteColumn(column, mainFrame.pjInfo.getProject_no());
			}

		});

		this.setIcon(deleteIcon);
	}
}
