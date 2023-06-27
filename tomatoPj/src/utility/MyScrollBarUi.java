package utility;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class MyScrollBarUi extends BasicScrollBarUI {
    private Image thumb;
    private Image track;

    public MyScrollBarUi(Image thumb, Image track) {
        this.thumb = thumb;
        this.track = track;
    }

    @Override
    protected void configureScrollBarColors() {
        this.thumbColor = new Color(0,0,0,0);
        this.trackColor = new Color(0,0,0,0);
    }

    @Override
    protected JButton createDecreaseButton(int orientation) {
        return createZeroButton();
    }

    @Override
    protected JButton createIncreaseButton(int orientation) {
        return createZeroButton();
    }

    private JButton createZeroButton() {
        JButton jbutton = new JButton();
        jbutton.setPreferredSize(new Dimension(0, 0));
        jbutton.setMinimumSize(new Dimension(0, 0));
        jbutton.setMaximumSize(new Dimension(0, 0));
        return jbutton;
    }

    @Override
    protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
        g.drawImage(track, trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height, null);
    }

    @Override
    protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
        g.drawImage(thumb, thumbBounds.x, thumbBounds.y, thumbBounds.width, thumbBounds.height, null);
    }
}
