package button;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import frame.MainFrame;
import pnl.commonpnl.TopMainPnl;
import utility.IconData;
import utility.Utility;

public class CanBanBtn extends JButton {
	private IconData iconData;
	private ImageIcon canBanBrightIcon;
	private ImageIcon canbanIcon;
	private Utility utility;

	public CanBanBtn(MainFrame mainFrame, TopMainPnl topMainPnl) {
		iconData = new IconData();
		utility = new Utility();
		canbanIcon = iconData.getImageIcon("canbangray");
		canBanBrightIcon = iconData.getImageIcon("canbanwhite");
		utility.setButtonProperties(this);
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
			public void mousePressed(MouseEvent arg0) {
				mainFrame.todoBtnActive = false;
				mainFrame.pjInfo=mainFrame.tempInfo;
				

				mainFrame.getContentPane().removeAll();
				mainFrame.addPnl();
				//mainFrame.showCard("projectSelect");
				mainFrame.showCard("columnSelect");
				mainFrame.columnActive = true;
				mainFrame.boradPnl.panel_2.columnSelectPnl.columnSetting();

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
