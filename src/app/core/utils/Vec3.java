package app.core.utils;

public class Vec3 {
    public double x, y, z;
    public Vec3() {
        x = y = z = 0.0;
    }
    public Vec3(double d) {
        x = y = z = d;
    }
    public Vec3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public Vec3 neg() {
        return new Vec3(-x, -y, -z);
    }
    public Vec3 add(Vec3 v) {
        return new Vec3(x + v.x, y + v.y, z + v.z);
    }
    public Vec3 sub(Vec3 v) {
        return new Vec3(x - v.x, y - v.y, z - v.z);
    }
    public Vec3 mul(Vec3 v) {
        return new Vec3(x * v.x, y * v.y, z * v.z);
    }
    public Vec3 div(Vec3 v) {
        return new Vec3(x / v.x, y / v.y, z / v.z);
    }
    public Vec3 add(double d) {
        return new Vec3(x + d, y + d, z + d);
    }
    public Vec3 sub(double d) {
        return new Vec3(x - d, y - d, z - d);
    }
    public Vec3 mul(double d) {
        return new Vec3(x * d, y * d, z * d);
    }
    public Vec3 div(double d) {
        return new Vec3(x / d, y / d, z / d);
    }
    public double dot(Vec3 v) {
        return x * v.x + y * v.y + z * v.z;
    }
    public Vec3 cross(Vec3 v) {
        return new Vec3(y * v.z - z * v.y, z * v.x - x * v.z, x * v.y - y * v.x);
    }
    public double len() {
        return Math.sqrt(x * x + y * y + z * z);
    }
    public double lenSq() {
        return x * x + y * y + z * z;
    }
    public Vec3 normalize() {
        double len = this.len();
        if (len == 0.0) {
            return new Vec3(0.0, 0.0, 0.0);
        }
        return new Vec3(x / len, y / len, z / len);
    }
    public void copy(Vec3 other) {
        if (other == null) {
            throw new RuntimeException("copy() method argument \"other\" cannot be a null reference.");
        }
        this.x = other.x;
        this.y = other.y;
        this.z = other.z;
    }
    @Override
    public String toString() {
        return String.format("%d\n%d\n%d", x, y, z);
    }
}
