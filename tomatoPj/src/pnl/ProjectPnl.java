package pnl;
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

        JLayeredPane layeredPane = new JLayeredPane();

        // Create components
        JButton button1 = new JButton("Button 1");
        JButton button2 = new JButton("Button 2");

        // Set location and size
        button1.setBounds(30, 30, 100, 100);
        button2.setBounds(60, 60, 100, 100);

        // Add components to the layered pane, on different layers
        layeredPane.add(button1, new Integer(1));  // button1 on layer 1
        layeredPane.add(button2, new Integer(2));  // button2 on layer 2

        // Button2 will be above Button1 because it has a higher layer number
        setOpaque(false);
        // Add the layered pane to the JPanel
        add(layeredPane);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null); // draws the image onto the JPanel
    }
}
