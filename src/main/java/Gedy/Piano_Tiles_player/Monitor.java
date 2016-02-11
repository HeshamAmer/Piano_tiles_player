package Gedy.Piano_Tiles_player;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

public class Monitor {

/**
 * Returns a BufferedImage
 * @return
 */
public BufferedImage captureScreen() {
    Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
    BufferedImage capture = null;

    try {
        capture = new Robot().createScreenCapture(screenRect);
    } catch (AWTException e) {
        e.printStackTrace();
    }

    return capture;
}
}