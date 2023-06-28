package pnl.projectpnl;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import frame.MainFrame;
import utility.IconData;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;

public class ProjectPnl extends JPanel {
    private IconData iconData = new IconData();
    private Image image;

    public ProjectPnl(MainFrame mainFrame) {
        this.image = iconData.getImageIcon("projectIcon").getImage(); // Set imagePath to your image path
        setOpaque(false);
        setLayout(new BorderLayout(0, 0));
        
        JButton btnNewButton = new JButton("New button");
        btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.showCard("columSelect");
			}
		});
        add(btnNewButton, BorderLayout.CENTER);
        
        

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null); // draws the image onto the JPanel
    }
}
