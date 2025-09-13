package app.core.graphics;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import app.core.utils.ExceptionHandler;
import app.core.utils.Pos;

public class Sprite {
    private BufferedImage image = null;
    private Pos position = new Pos(0, 0);

    public void loadImageFromFile(String filepath) {
        ExceptionHandler.tryCatch(() -> {
            image = ImageIO.read(new File(filepath));
        });
    }

    public void setPos(Pos pos) {
        position = pos;
    }
    public void moveBy(Pos addPos) {
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
    public Pos getPos() {
        return position;
    }
}
