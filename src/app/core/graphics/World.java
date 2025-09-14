package app.core.graphics;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import app.core.utils.ExceptionHandler;
import app.core.utils.Generators;
import app.core.utils.Pos;
import app.core.utils.Utility;

public class World {
    private int[][] tiles;
    private Chunk[] chunks;
    private ArrayList<BufferedImage> images;
    private int sizeX, sizeY, chunksX, chunksY;
    private int worldScale;
    private String seed;

    public World(String mapSeed, int gridSizeX, int gridSizeY, int chunksX, int chunksY) {
        sizeX = gridSizeX;
        sizeY = gridSizeY;
        this.chunksX = chunksX;
        this.chunksY = chunksY;
        seed = mapSeed;
        tiles = new int[sizeY][sizeX];
        images = new ArrayList<>();
        worldScale = 1;
        chunks = new Chunk[chunksY * chunksX];
    }

    public void loadMapSeed() {

    }
    public void loadMapSeed(String mapSeed) {
        seed = mapSeed;
        loadMapSeed();
    }

    public void loadChunks(int chunkSizeX, int chunkSizeY) {
        for (int y = 0; y < chunksY; y++) {
            for (int x = 0; x < chunksX; x++) {
                chunks[y * chunksX + x] = new Chunk(chunkSizeX, chunkSizeY, x * chunkSizeX, y * chunkSizeY);
            }
        }
    }

    public void loadChunksAroundPlayer(Pos playerPos, int chunkSizeX, int chunkSizeY) {
        int scale = getWorldScale();
        int tileSizeX = images.get(0).getWidth() * scale, tileSizeY = images.get(0).getHeight() * scale;
        int offx = (int)(playerPos.x / (tileSizeX * chunkSizeX)) - chunksX / 2;
        int offy = (int)(playerPos.y / (tileSizeY * chunkSizeY)) - chunksY / 2;
        for (int y = 0; y < chunksY; y++) {
            for (int x = 0; x < chunksX; x++) {
                if (x + offx < 0 || y + offy < 0) {
                    chunks[y * chunksX + x] = null;
                    continue;
                }
                chunks[y * chunksX + x] = new Chunk(chunkSizeX, chunkSizeY, (x + offx) * chunkSizeX, (y + offy) * chunkSizeY);
            }
        }
    }

    public void addImage(String imagePath) {
        ExceptionHandler.tryCatch(() -> {
            images.add(ImageIO.read(new File(imagePath)));
        });
    }
    public void createMap(long seed, double scale) {
        for (int y = 0; y < sizeY; y++) {
            for (int x = 0; x < sizeX; x++) {
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
                // idx = 4; // Debugging
                tiles[y][x] = idx;
                double v = Generators.perlin(x / scale, y / scale);
                if (v < 0.0) {
                    idx = 1;
                }
                else if (v >= 0.0) {
                    idx = 0;
                }
                if (v > -0.05 && v < 0.05) {
                    idx = 3;
                }
                tiles[y][x] = idx;
            }
        }
        Generators.generatePerlinNoise(sizeX, sizeY, scale).save("perlin.bmp");
    }
    public void setWorldScale(int scale) {
        if (scale < 1) {
            return;
        }
        worldScale = scale;
    }

    public int[][] getTiles() {
        return tiles;
    }
    public Chunk[] getChunks() {
        return chunks;
    }
    public int getChunkCountX() {
        return chunksX;
    }
    public int getChunkCountY() {
        return chunksY;
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
    public int getGridSizeX() {
        return sizeX;
    }
    public int getGridSizeY() {
        return sizeY;
    }
}
