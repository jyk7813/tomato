package pnl;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import frame.MainFrame;
import utility.IconData;
import utility.MyScrollBarUi;
import utility.Utility;

public class ProjectSelectPnl extends JPanel {

    private Image image;
    private IconData iconData;
    private Utility utility;
    private JLayeredPane centerPnl;
    private JButton jButton;
    private JScrollPane scrollPane;

    public ProjectSelectPnl(Image image, MainFrame mainFrame) {
        this.image = image;
        iconData = new IconData();
        utility = new Utility();
        setLayout(new BorderLayout(0, 0));

        centerPnl = new JLayeredPane();
        centerPnl.setOpaque(false);
        centerPnl.setLayout(null); // Necessary for JScrollPane to function correctly

        JPanel westPnl = new JPanel() {
            @Override
            public Dimension getPreferredSize() {
                setOpaque(false);
                return new Dimension(510, 945);
            }
        };
        JPanel eastPnl = new JPanel() {

            @Override
            public Dimension getPreferredSize() {
                setOpaque(false);
                return new Dimension(510, 945);
            }

        };
        JPanel northPanel = new JPanel() {
            @Override
            public Dimension getPreferredSize() {
                setOpaque(false);
                return new Dimension(1920, 135);
            }
        };
        jButton = new JButton(iconData.getImageIcon("blankAdd"));
        jButton.setBounds(0, 55, 900, 216);

        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addPanel();
            }
        });

        centerPnl.add(jButton, new Integer(3));  // Add jButton to a higher layer
        utility.setButtonProperties(jButton);

        // Create JScrollPane and add the centerPnl to it
        scrollPane = new JScrollPane(centerPnl, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane = new JScrollPane(centerPnl, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        Image thumb = iconData.getImageIcon("dragBarLength_f").getImage();
        Image track = iconData.getImageIcon("dragBarLength_b").getImage();
        scrollPane.getVerticalScrollBar().setUI(new MyScrollBarUi(thumb, track));
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);  // Add this line
        scrollPane.setBorder(null);

        add(scrollPane, BorderLayout.CENTER);  // Add the JScrollPane to the main panel
        add(northPanel, BorderLayout.NORTH);
        add(westPnl, BorderLayout.WEST);
        add(eastPnl, BorderLayout.EAST);
    }

    private void addPanel() {
        ProjectPnl projectPnl = new ProjectPnl();
        projectPnl.setBounds(0, jButton.getY(), 900, 216);  // Set the position to current jButton position
        centerPnl.add(projectPnl, new Integer(2));  // Add projectPnl to a lower layer
        jButton.setLocation(jButton.getX(), jButton.getY() + projectPnl.getHeight() + 10);  // Move jButton down

        // Update the preferred size of the centerPnl and validate the JScrollPane
        centerPnl.setPreferredSize(new Dimension(centerPnl.getWidth(), jButton.getY() + jButton.getHeight()));
        scrollPane.validate();
        centerPnl.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }
}
