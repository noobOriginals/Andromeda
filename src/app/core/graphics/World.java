package app.core.graphics;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import app.core.utils.ExceptionHandler;
import app.core.utils.PerlinUtils;
import app.core.utils.Pos;
import app.core.utils.SeedPerlin;

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
    public void createMap(double scale) {
        SeedPerlin gen = new SeedPerlin(seed.hashCode());
        for (int y = 0; y < sizeY; y++) {
            for (int x = 0; x < sizeX; x++) {
                double v = gen.perlin(x / scale, y / scale, PerlinUtils::fade);
                int idx = 0;
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
        genColorPerlinImage(gen, "perlin.png", sizeX, sizeY, scale);
    }
    private static void genSeedPerlinImage(SeedPerlin gen, String name, int width, int height, double scale) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int p = Math.clamp((int)((gen.perlin(x / scale, y / scale, PerlinUtils::fade) + 1) * 0.5 * 255), 0, 255);
                image.setRGB(x, y, (p << 16) | (p << 8) | p);
            }
        }
        File output = new File(name);
        ExceptionHandler.tryCatch(() -> {
            ImageIO.write(image, "png", output);
        });
        System.out.println("Saved " + name);
    }
    private static void genColorPerlinImage(SeedPerlin gen, String name, int width, int height, double scale) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
            	double value = gen.perlin(x / scale, y / scale, PerlinUtils::fade);
                int thrsh = 252;
                double r = 1.0, g = 1.0, b = 1.0;
                g -= Math.abs(value);
                r -= Math.clamp(value, 0.0, 1.0);
                b += Math.clamp(value, -1.0, 0.0);
                int rf, gf, bf;
                rf = (int)(r * 255);
                gf = (int)(g * 255);
                bf = (int)(b * 255);
                int rgb = (rf << 16) | (gf << 8) | bf;
                if (rf >= thrsh && gf >= thrsh && bf >= thrsh) {
                    rgb = (0 << 16) | (255 << 8) | 0;
                }
                if (x % (int)scale == 0 || y % (int)scale == 0) {
                    rgb = 0;
                }
                image.setRGB(x, y, rgb);
            }
        }
        File output = new File(name);
        ExceptionHandler.tryCatch(() -> {
            ImageIO.write(image, "png", output);
        });
        System.out.println("Saved " + name);
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
