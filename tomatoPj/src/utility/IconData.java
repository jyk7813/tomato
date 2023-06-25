package utility;

import javax.swing.ImageIcon;

public class IconData {
	public ImageIcon mainIcon() {
		return new ImageIcon(getClass().getClassLoader().getResource("main(BG).png"));
	}
}
