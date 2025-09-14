package app.launcher;

import app.core.graphics.Sprite;
import app.core.graphics.Window;
import app.core.graphics.World;
import app.core.utils.Pos;

public class Main {
    public static void main(String[] args) {
        Window window = new Window("Andromeda", 800, 600);
        Sprite s = new Sprite();
        s.loadImageFromFile("resources/sprite.png");
        window.setFPSCap(120);
        window.capFPS();
        World world = new World(null, 500, 300, 15, 10);
        world.addImage("resources/tile0.png");
        world.addImage("resources/tile1.png");
        world.addImage("resources/tile2.png");
        world.addImage("resources/tile3.png");
        world.addImage("resources/tile4.png");
        world.createMap("noob".hashCode(), 50.0);
        world.setWorldScale(2);
        while (true) {
            if (window.shouldClose()) {
                System.exit(0);
            }
            if (window.keyPressed('w')) {
                s.moveY(-3);
            }
            if (window.keyPressed('s')) {
                s.moveY(3);
            }
            if (window.keyPressed('a')) {
                s.moveX(-3);
            }
            if (window.keyPressed('d')) {
                s.moveX(3);
            }
            if (window.keyPressed('f') && !window.isFullscreen()) {
                window.fullscreen();
            }
            if (window.keyPressed('r') && window.isFullscreen()) {
                window.windowed();
            }
            world.loadChunksAroundPlayer(new Pos(s.getPos().x + window.getWidth() / 2, s.getPos().y + window.getHeight() / 2), 3, 3);
            window.clear();
            window.drawWorldChunks(world, new Pos(-s.getPos().x, -s.getPos().y));
            window.drawSprite(s);
            window.drawFPS();
            window.refresh();
        }
    }
}
