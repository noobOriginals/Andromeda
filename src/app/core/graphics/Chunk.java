package app.core.graphics;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Chunk {
    private BufferedImage chunk;
    private int sizeX, sizeY;

    public Chunk(World world, int offsetx, int offsety, int tileCountX, int tileCountY) {
        sizeX = tileCountX;
        sizeY = tileCountY;
        int[][] tiles = world.getTiles();
        ArrayList<BufferedImage> images = world.getImages();
        int tileSizeX = images.get(0).getWidth(), tileSizeY = images.get(0).getHeight();
        chunk = new BufferedImage(tileSizeX * tileCountX, tileSizeY * tileCountY, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = chunk.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
        for (int y = offsety; y < tileCountY + offsety; y++) {
            for (int x = offsetx; x < tileCountX + offsetx; x++) {
                g2d.drawImage(images.get(tiles[y][x]), (x - offsetx) * tileSizeX, (y - offsety) * tileSizeY, tileSizeX, tileSizeY, null);
            }
        }
        g2d.dispose();
    }

    public int getTileCountX() {
        return sizeX;
    }
    public int getTileCountY() {
        return sizeY;
    }
    public BufferedImage getImage() {
        return chunk;
    }
}
