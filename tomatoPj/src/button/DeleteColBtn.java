package button;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import frame.MainFrame;
import pnl.commonpnl.MemberListPnl;
import pnl.commonpnl.MemberPnl;
import tomatoPj.ColumnRepository;
import tomatoPj.Member;
import utility.IconData;
import utility.Utility;

public class DeleteColBtn extends JButton {
	private IconData iconData;
	private Utility utility;
	private ImageIcon deleteIcon;
	private ColumnRepository columnRepository;

	public DeleteColBtn(MainFrame mainFrame, int column_no) {
		columnRepository = new ColumnRepository();
		iconData = new IconData();
		utility = new Utility();
		deleteIcon = iconData.getImageIcon("delete_btn");
		utility.setButtonProperties(this);
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				columnRepository.deleteColumn(column_no);
				
				//mainFrame.showCard("projectSelect");
				
				mainFrame.boradPnl.panel_2.columnSelectPnl.updatePnl();
				
			}

		});
		this.setIcon(deleteIcon);
	}
}
