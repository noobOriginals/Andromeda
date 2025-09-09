package app.core.graphics;

public class Chunk {
    private int width, height, xoffset, yoffset;

    public Chunk(int width, int height, int xoffset, int yoffset) {
        this.width = width;
        this.height = height;
        this.xoffset = xoffset;
        this.yoffset = yoffset;
    }

    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    public int getXOffset() {
        return xoffset;
    }
    public int getYOffset() {
        return yoffset;
    }
}
