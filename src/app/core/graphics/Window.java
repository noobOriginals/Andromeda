package app.core.graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import app.core.utils.ExceptionHandler;
import app.core.utils.Pixel;

public class Window {
    private final String TITLE;
    private final int WIDTH, HEIGHT;

    private final JFrame window;
    private final KeyInput keyInput;

    private ImageIcon baseCanvas;
    private BufferedImage canvas;

    private volatile boolean exit;
    private boolean fullscreen, capFPS;

    private long startTime, endTime, targetDelta;
    private int frames, FPS, FPSCap;
    private double deltaTime;

    public Window(String title, int width, int height) {
        // Constants for resseting the winow to default parameters
        TITLE = title;
        WIDTH = width;
        HEIGHT = height;

        // Initialize window parameters
        canvas = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
        baseCanvas = new ImageIcon(canvas);
        keyInput = new KeyInput();

        // Initialize the window itself
        window = new JFrame(TITLE);
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setResizable(false);
        window.add(new JLabel(baseCanvas));
        window.addKeyListener(keyInput);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        // Other utility stuff
        exit = false;
        fullscreen = false;
        capFPS = false;
        startTime = 0;
        endTime = 0;
        targetDelta = 0;
        frames = 0;
        FPS = 0;
        deltaTime = 0.0;
    }

    long st;
    public void drawSprite(Sprite sprite) {
        SwingUtilities.invokeLater(() -> {
            Graphics2D g2d = canvas.createGraphics();
            Pixel spritePos = sprite.getPos();
            g2d.drawImage(sprite.getImage(), null, spritePos.x, spritePos.y);
            g2d.dispose();
        });
    }
    public void drawWorld(World world) {
        SwingUtilities.invokeLater(() -> {
            Graphics2D g2d = canvas.createGraphics();
            int[][] tiles = world.getTiles();
            ArrayList<BufferedImage> images = world.getImages();
            int scale = world.getWorldScale();
            int tileSizeX = images.get(0).getWidth() * scale, tileSizeY = images.get(0).getHeight() * scale;
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
            for (int y = 0; y < world.getGridSizeY(); y++) {
                for (int x = 0; x < world.getGridSizeX(); x++) {
                    g2d.drawImage(images.get(tiles[y][x]), x * tileSizeX, y * tileSizeY, tileSizeX, tileSizeY, null);
                }
            }
            g2d.dispose();
        });
    }
    public void drawWorldChunks(World world) {
        SwingUtilities.invokeLater(() -> {
            Graphics2D g2d = canvas.createGraphics();
            Chunk[][] chunks = world.getChunks();
            int scale = world.getWorldScale();
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
            for (int y = 0; y < world.getChunkCountY(); y++) {
                for (int x = 0; x < world.getChunkCountX(); x++) {
                    BufferedImage image = chunks[y][x].getImage();
                    g2d.drawImage(image, x * image.getWidth() * scale, y * image.getHeight() * scale, image.getWidth() * scale, image.getHeight() * scale, null);
                }
            }
            g2d.dispose();
        });
    }
    public void drawFPS() {
        SwingUtilities.invokeLater(() -> {
            Graphics2D g2d = canvas.createGraphics();
            g2d.setColor(Color.BLACK);
            g2d.drawString("FPS: " + FPS, 10,20);
            g2d.dispose();
        });
    }
    public void clear() {
        SwingUtilities.invokeLater(() -> {
            st = System.nanoTime();
            canvas = new BufferedImage(window.getWidth(), window.getHeight(), BufferedImage.TYPE_INT_ARGB);
            baseCanvas.setImage(canvas);
        });
    }
    public void refresh() {
        endTime = System.nanoTime();
        SwingUtilities.invokeLater(() -> {
            long frameTime = System.nanoTime() - st;
            Graphics2D g2d = canvas.createGraphics();
            g2d.setColor(Color.BLACK);
            g2d.drawString("Frame Time: " + (frameTime / 1000000.0) + "ms", 10, 40);
            g2d.dispose();
            window.repaint();
        });
        frames++;
        deltaTime = (double)(endTime - startTime) / targetDelta;
        if (endTime - startTime >= 1000000000) {
            FPS = frames;
            frames = 0;
            startTime = endTime;
        }
        long sleepTime;
        if (capFPS) {
            double targetSleepTime = 1000000000.0 / FPSCap;
            sleepTime = (long)(targetSleepTime);
        }
        else {
            sleepTime = 1000000;
        }
        ExceptionHandler.tryCatch(() -> { Thread.sleep(sleepTime / 1000000); });
    }
    public void setResizable(boolean b) {
        window.setResizable(b);
    }
    public void show() {
        window.setVisible(true);
    }
    public void hide() {
        window.setVisible(false);
    }
    public void fullscreen() {
        fullscreen = true;
        SwingUtilities.invokeLater(() -> {
            window.dispose();
            window.setExtendedState(JFrame.MAXIMIZED_BOTH);
            window.setUndecorated(true);
            window.setVisible(true);
        });
    }
    public void windowed() {
        fullscreen = false;
        SwingUtilities.invokeLater(() -> {
            window.dispose();
            window.setExtendedState(0);
            window.setSize(new Dimension(WIDTH, HEIGHT));
            window.setUndecorated(false);
            window.setLocationRelativeTo(null);
            window.setVisible(true);
        });
    }
    public void setTargetFPS(int fps) {
        targetDelta = (long)(1000000000.0 / fps);
    }
    public void setFPSCap(int fps) {
        FPSCap = fps;
    }
    public void capFPS() {
        capFPS = true;
    }
    public void uncapFPS() {
        capFPS = false;
    }

    public boolean shouldClose() {
        SwingUtilities.invokeLater(() -> {
            exit = !window.isDisplayable();
        });
        return exit;
    }
    public boolean isFullscreen() {
        return fullscreen;
    }
    public boolean keyPressed(int keycode) {
        return keyInput.keyPressed(keycode);
    }
    public boolean keyPressed(char keychar) {
        return keyInput.keyPressed(keychar);
    }
    public double getDeltaTime() {
        return deltaTime;
    }

    public void resetParams() {
        SwingUtilities.invokeLater(() -> {
            canvas = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
            baseCanvas.setImage(canvas);
            keyInput.resetParams();
            window.setTitle(TITLE);
            window.repaint();
        });
    }

    private class KeyInput implements KeyListener {
        private HashMap<Character, Boolean> keysByChar = new HashMap<>();
        private HashMap<Integer, Boolean> keysByCode = new HashMap<>();

        @Override
        public void keyPressed(KeyEvent e) {
            keysByChar.put(Character.toLowerCase(e.getKeyChar()), true);
            keysByCode.put(e.getKeyCode(), true);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            keysByChar.put(Character.toLowerCase(e.getKeyChar()), false);
            keysByCode.put(e.getKeyCode(), false);
        }

        @Override
        public void keyTyped(KeyEvent e) {}

        public boolean keyPressed(char key) {
            if (!keysByChar.containsKey(Character.toLowerCase(key))) {
                return false;
            }
            return keysByChar.get(Character.toLowerCase(key));
        }
        public boolean keyPressed(int key) {
            if (!keysByCode.containsKey(key)) {
                return false;
            }
            return keysByCode.get(key);
        }

        public void resetParams() {
            keysByChar.clear();
            keysByCode.clear();
        }
    }
}
