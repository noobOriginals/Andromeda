package app.core.graphics;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import app.core.utils.ExceptionHandler;
import app.core.utils.Pixel;

public class Sprite {
    private BufferedImage image = null;
    private Pixel position = new Pixel(0, 0);

    public void loadImageFromFile(String filepath) {
        ExceptionHandler.tryCatch(() -> {
            image = ImageIO.read(new File(filepath));
        });
    }

    public void setPos(Pixel pos) {
        position = pos;
    }
    public void moveBy(Pixel addPos) {
        position = position.add(addPos);
    }
    public void moveX(int x) {
        position.x += x;
    }
    public void moveY(int y) {
        position.y += y;
    }

    public BufferedImage getImage() {
        return image;
    }
    public Pixel getPos() {
        return position;
    }
}
