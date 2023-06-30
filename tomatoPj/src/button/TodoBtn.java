package button;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import frame.MainFrame;
import pnl.commonpnl.TopMainPnl;
import utility.IconData;

public class TodoBtn extends JButton {
	private IconData iconData;
	private ImageIcon todoBrightIcon;
	private ImageIcon todoIcon;

	public TodoBtn(MainFrame mainFrame, TopMainPnl topMainPnl) {
		iconData = new IconData();

		todoIcon = iconData.getImageIcon("navi_todo");
		todoBrightIcon = iconData.getImageIcon("navi_todo_white");

		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				TodoBtn.this.setIcon(todoBrightIcon); // LogoutBtn 객체를 참조
			}

			@Override
			public void mouseExited(MouseEvent e) {
				TodoBtn.this.setIcon(todoIcon); // LogoutBtn 객체를 참조
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				mainFrame.showCard("todo");

			}

		});

		// JButton에 아이콘을 설정합니다.
		this.setIcon(todoIcon);
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(150, 70);
	}
}
