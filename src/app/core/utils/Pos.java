package app.core.utils;

public class Pos {
    public int x, y;

    public Pos(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Pos add(Pos p) {
        return new Pos(x + p.x, y + p.y);
    }
    public Pos sub(Pos p) {
        return new Pos(x - p.x, y - p.y);
    }
    public Pos mul(Pos p) {
        return new Pos(x * p.x, y * p.y);
    }
    public Pos div(Pos p) {
        return new Pos(x / p.x, y / p.y);
    }
}
