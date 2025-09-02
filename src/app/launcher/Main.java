package app.launcher;

import app.core.graphics.Sprite;
import app.core.graphics.Window;

public class Main {
    public static void main(String[] args) {
        Window window = new Window("Andromeda", 800, 600);
        Sprite s = new Sprite();
        s.loadImageFromFile("resources/sprite.png");
        window.setFPSCap(120);
        window.capFPS();
        while (true) {
            if (window.shouldClose()) {
                System.exit(0);
            }
            if (window.keyPressed('w')) {
                s.moveY(-1);
            }
            if (window.keyPressed('s')) {
                s.moveY(1);
            }
            if (window.keyPressed('a')) {
                s.moveX(-1);
            }
            if (window.keyPressed('d')) {
                s.moveX(1);
            }
            if (window.keyPressed('f') && !window.isFullscreen()) {
                window.fullscreen();
            }
            if (window.keyPressed('r') && window.isFullscreen()) {
                window.windowed();
            }
            window.clear();
            window.drawSprite(s);
            window.drawFPS();
            window.refresh();
        }
    }
}
