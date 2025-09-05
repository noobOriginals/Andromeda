package app.core.graphics;

import app.core.utils.Pixel;

public class Tile {
    private int imageIdx;
    private Pixel gridPos;

    public Tile(int x, int y, int imageIdx) {
        this(new Pixel(x, y), imageIdx);
    }
    public Tile(Pixel gridPos, int imageIdx) {
        this.gridPos = gridPos;
        this.imageIdx = imageIdx;
    }

    public Pixel getGridPos() {
        return gridPos;
    }
    public int getImageIdx() {
        return imageIdx;
    }
}
