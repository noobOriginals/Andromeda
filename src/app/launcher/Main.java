package app.launcher;

import app.core.graphics.Sprite;
import app.core.graphics.Window;
import app.core.graphics.World;

public class Main {
    public static void main(String[] args) {
        Window window = new Window("Andromeda", 800, 600);
        Sprite s = new Sprite();
        s.loadImageFromFile("resources/sprite.png");
        window.setFPSCap(120);
        window.capFPS();
        World world = new World(null, 50, 30, 5, 3);
        world.addImage("resources/tile0.png");
        world.addImage("resources/tile1.png");
        world.addImage("resources/tile2.png");
        world.addImage("resources/tile3.png");
        world.addImage("resources/tile4.png");
        world.createMap();
        world.setWorldScale(2);
        world.loadChunks(10, 10);
        while (true) {
            if (window.shouldClose()) {
                System.exit(0);
            }
            if (window.keyPressed('w')) {
                s.moveY(-2);
            }
            if (window.keyPressed('s')) {
                s.moveY(2);
            }
            if (window.keyPressed('a')) {
                s.moveX(-2);
            }
            if (window.keyPressed('d')) {
                s.moveX(2);
            }
            if (window.keyPressed('f') && !window.isFullscreen()) {
                window.fullscreen();
            }
            if (window.keyPressed('r') && window.isFullscreen()) {
                window.windowed();
            }
            window.clear();
            window.drawWorldChunks(world);
            // window.drawWorld(world);
            window.drawSprite(s);
            window.drawFPS();
            window.refresh();
        }
    }
}
