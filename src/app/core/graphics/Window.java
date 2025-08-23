package app.core.graphics;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class Window {
    private final String TITLE;
    private final int WIDTH, HEIGHT;

    private final JFrame window;
    private final KeyInput keyInput;
    private final ImageIcon baseCanvas;

    private BufferedImage canvas;

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
    }

    public void clear() {
        SwingUtilities.invokeLater(() -> {
            canvas = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
            baseCanvas.setImage(canvas);
        });
    }
    public void refresh() {
        SwingUtilities.invokeLater(() -> {
            window.repaint();
        });
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

    public boolean shouldClose() {
        if (window.isDisplayable()) {
            return false;
        } else {
            return true;
        }
    }
    public boolean keyPressed(int keycode) {
        return keyInput.keyPressed(keycode);
    }
    public boolean keyPressed(char keychar) {
        return keyInput.keyPressed(keychar);
    }

    public void resetParams() {
        canvas = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
        baseCanvas.setImage(canvas);
        keyInput.resetParams();
        window.setTitle(TITLE);
        window.repaint();
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
