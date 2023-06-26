package utility;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class MyScrollBarUi extends BasicScrollBarUI {
    private Image imageThumb, imageTrack;

    MyScrollBarUi(Image imageThumb, Image imageTrack) {
        this.imageThumb = imageThumb;
        this.imageTrack = imageTrack;
    }

    @Override
    protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {        
        g.drawImage(imageThumb, thumbBounds.x, thumbBounds.y, thumbBounds.width, thumbBounds.height, null);
    }

    @Override
    protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
        g.drawImage(imageTrack, trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height, null);
    }
}