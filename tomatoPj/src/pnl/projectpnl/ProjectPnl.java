package pnl.projectpnl;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import utility.IconData;

public class ProjectPnl extends JPanel {
    private IconData iconData = new IconData();
    private Image image;

    public ProjectPnl() {
        this.image = iconData.getImageIcon("projectIcon").getImage(); // Set imagePath to your image path
        setOpaque(false);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null); // draws the image onto the JPanel
    }
}
