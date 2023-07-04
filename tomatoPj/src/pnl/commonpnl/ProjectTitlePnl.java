package pnl.commonpnl;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import frame.MainFrame;
import utility.FontData;
import utility.IconData;

public class ProjectTitlePnl extends JPanel {
	private IconData iconData;
	private FontData fontData;
	private Image image;
	public JLabel projectTiltleLbl;

	/**
	 * Create the panel.
	 */
	public ProjectTitlePnl(MainFrame mainFrame) {
		iconData = new IconData();
		fontData = new FontData();
		this.image = iconData.getImageIcon("mini_bar").getImage();

		setOpaque(false);
		setLayout(new GridLayout(1, 0, 0, 0));

		projectTiltleLbl = new JLabel("Project");
		projectTiltleLbl.setHorizontalAlignment(SwingConstants.CENTER);
		projectTiltleLbl.setFont(fontData.nanumFont(20));
		mainFrame.setSelectedProjectTitle(projectTiltleLbl.getText());
		add(projectTiltleLbl);
		actionTitle(mainFrame);

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, null);
	}
	public void actionTitle(MainFrame mainFrame) {
		mainFrame.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if ("selectedProjectTitle".equals(evt.getPropertyName())) {
					projectTiltleLbl.setText((String) evt.getNewValue());
				}
			}
		});
	}

}
