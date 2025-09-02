package app.core.utils;

public class Pixel {
    public int x, y;

    public Pixel(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Pixel add(Pixel p) {
        return new Pixel(x + p.x, y + p.y);
    }
    public Pixel sub(Pixel p) {
        return new Pixel(x - p.x, y - p.y);
    }
    public Pixel mul(Pixel p) {
        return new Pixel(x * p.x, y * p.y);
    }
    public Pixel div(Pixel p) {
        return new Pixel(x / p.x, y / p.y);
    }
}
