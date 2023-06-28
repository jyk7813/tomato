package pnl.projectpnl;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JPanel;

import frame.MainFrame;
import utility.IconData;
import java.awt.BorderLayout;
import javax.swing.JTextField;

public class ProjectPnl extends JPanel {
    private IconData iconData = new IconData();
    private Image image;
    private JTextField textField;

    public ProjectPnl(MainFrame mainFrame, int project_no, String title) {
        this.image = iconData.getImageIcon("projectIcon").getImage(); // Set imagePath to your image path
        setOpaque(false);
        setLayout(new BorderLayout(0, 0));
        
        JButton btnNewButton = new JButton(title);
        btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.showCard("columnSelect");
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
