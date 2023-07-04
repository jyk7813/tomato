package button;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import frame.MainFrame;
import utility.IconData;
import utility.Utility;

public class deleteColBtn extends JButton{
	private IconData iconData;
	private Utility utility;
	private ImageIcon deleteIcon;
	
	public deleteColBtn(MainFrame mainFrame, int column_no) {
		iconData = new IconData();
		utility = new Utility();
		deleteIcon = iconData.getImageIcon("delete_btn");
		utility.setButtonProperties(this);
		addMouseListener(new MouseAdapter() {
			@Override
 			public void mousePressed(MouseEvent e) {
				mainFrame.showCard("columnSelect");
				revalidate();
				repaint();
 			}
			
		});
		this.setIcon(deleteIcon);
	}
}
