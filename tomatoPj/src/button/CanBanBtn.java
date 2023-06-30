package button;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import frame.MainFrame;
import utility.IconData;

public class CanBanBtn extends JButton {
	private IconData iconData;
	private ImageIcon canBanBrightIcon;
	private ImageIcon canbanIcon;

	public CanBanBtn(MainFrame mainFrame) {
		iconData = new IconData();

		canbanIcon = iconData.getImageIcon("navi_board");
		canBanBrightIcon = iconData.getImageIcon("navi_board");

		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				CanBanBtn.this.setIcon(canBanBrightIcon); // LogoutBtn 객체를 참조
			}

			@Override
			public void mouseExited(MouseEvent e) {
				CanBanBtn.this.setIcon(canbanIcon); // LogoutBtn 객체를 참조
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				mainFrame.removeAll();
				mainFrame.addPnl();
				mainFrame.showCard("columnSelect");

			}

		});

		// JButton에 아이콘을 설정합니다.
		this.setIcon(canbanIcon);
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(150, 70);
	}
}
