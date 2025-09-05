package app.core.graphics;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import app.core.utils.ExceptionHandler;
import app.core.utils.Pixel;

public class World {
    private ArrayList<Tile> tileGrid;
    private ArrayList<BufferedImage> images;
    private int sizeX, sizeY;
    private int worldScale;
    private String seed;

    public World(String mapSeed, int gridSizeX, int gridSizeY) {
        sizeX = gridSizeX;
        sizeY = gridSizeY;
        seed = mapSeed;
        tileGrid = new ArrayList<>();
        images = new ArrayList<>();
        worldScale = 1;
    }

    public void loadMapSeed() {
        int[] indices = new int[tileGrid.size()];
        for (int i = 0; i < tileGrid.size(); i++) {
            indices[i] = tileGrid.get(i).getImageIdx();
        }
    }
    public void loadMapSeed(String mapSeed) {
        seed = mapSeed;
        loadMapSeed();
    }

    public void addImage(String imagePath) {
        ExceptionHandler.tryCatch(() -> {
            images.add(ImageIO.read(new File(imagePath)));
        });
    }
    public void createMap() {
        for (int y = 0; y < sizeX; y++) {
            for (int x = 0; x < sizeY; x++) {
                int idx = 0;
                int boundary = 10;
                if (y == 0) {
                    idx = 3;
                }
                else if (y == boundary) {
                    idx = 2;
                }
                else if (y > boundary) {
                    idx = 1;
                }
                tileGrid.add(new Tile(new Pixel(x, y), idx));
            }
        }
    }
    public void setWorldScale(int scale) {
        if (scale < 1) {
            return;
        }
        worldScale = scale;
    }

    public ArrayList<Tile> getTiles() {
        return tileGrid;
    }
    public ArrayList<BufferedImage> getImages() {
        if (images.isEmpty()) {
            return null;
        }
        return images;
    }
    public int getWorldScale() {
        return worldScale;
    }
}
