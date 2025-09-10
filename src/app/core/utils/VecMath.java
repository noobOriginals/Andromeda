package app.core.utils;

public class VecMath {
    public static Vec2 neg(Vec2 v) {
        return new Vec2(-v.x, -v.y);
    }
    public static Vec2 add(Vec2 v0, Vec2 v1) {
        return new Vec2(v0.x + v1.x, v0.y + v1.y);
    }
    public static Vec2 sub(Vec2 v0, Vec2 v1) {
        return new Vec2(v0.x - v1.x, v0.y - v1.y);
    }
    public static Vec2 mul(Vec2 v0, Vec2 v1) {
        return new Vec2(v0.x * v1.x, v0.y * v1.y);
    }
    public static Vec2 div(Vec2 v0, Vec2 v1) {
        return new Vec2(v0.x / v1.x, v0.y / v1.y);
    }
    public static Vec2 add(Vec2 v, double d) {
        return new Vec2(v.x + d, v.y + d);
    }
    public static Vec2 sub(Vec2 v, double d) {
        return new Vec2(v.x - d, v.y - d);
    }
    public static Vec2 sub(double d, Vec2 v) {
        return new Vec2(d - v.x, d - v.y);
    }
    public static Vec2 mul(Vec2 v, double d) {
        return new Vec2(v.x * d, v.y * d);
    }
    public static Vec2 div(Vec2 v, double d) {
        return new Vec2(v.x / d, v.y / d);
    }
    public static Vec2 div(double d, Vec2 v) {
        return new Vec2(d / v.x, d / v.y);
    }
    public static double dot(Vec2 v0, Vec2 v1) {
        return v0.x * v1.x + v0.y * v1.y;
    }
    public static double len(Vec2 v) {
        return Math.sqrt(v.x * v.x + v.y * v.y);
    }
    public static double lenSq(Vec2 v) {
        return v.x * v.x + v.y * v.y;
    }
    public static Vec2 normalize(Vec2 v) {
        double len = v.len();
        if (len == 0.0) {
            return new Vec2(0.0, 0.0);
        }
        return new Vec2(v.x / len, v.y / len);
    }
    public static String toString(Vec2 v) {
        return String.format("%d\n%d", v.x, v.y);
    }
    public static Vec3 neg(Vec3 v) {
        return new Vec3(-v.x, -v.y, -v.z);
    }
    public static Vec3 add(Vec3 v0, Vec3 v1) {
        return new Vec3(v0.x + v1.x, v0.y + v1.y, v0.z + v1.z);
    }
    public static Vec3 sub(Vec3 v0, Vec3 v1) {
        return new Vec3(v0.x - v1.x, v0.y - v1.y, v0.z - v1.z);
    }
    public static Vec3 mul(Vec3 v0, Vec3 v1) {
        return new Vec3(v0.x * v1.x, v0.y * v1.y, v0.z * v1.z);
    }
    public static Vec3 div(Vec3 v0, Vec3 v1) {
        return new Vec3(v0.x / v1.x, v0.y / v1.y, v0.z / v1.z);
    }
    public static Vec3 add(Vec3 v, double d) {
        return new Vec3(v.x + d, v.y + d, v.z + d);
    }
    public static Vec3 sub(Vec3 v, double d) {
        return new Vec3(v.x - d, v.y - d, v.z - d);
    }
    public static Vec3 sub(double d, Vec3 v) {
        return new Vec3(d - v.x, d - v.y, d - v.z);
    }
    public static Vec3 mul(Vec3 v, double d) {
        return new Vec3(v.x * d, v.y * d, v.z * d);
    }
    public static Vec3 div(Vec3 v, double d) {
        return new Vec3(v.x / d, v.y / d, v.z / d);
    }
    public static Vec3 div(double d, Vec3 v) {
        return new Vec3(d / v.x, d / v.y, d / v.z);
    }
    public static double dot(Vec3 v0, Vec3 v1) {
        return v0.x * v1.x + v0.y * v1.y + v0.z * v1.z;
    }
    public static Vec3 cross(Vec3 v0, Vec3 v1) {
        return new Vec3(v0.y * v1.z - v0.z * v1.y, v0.z * v1.x - v0.x * v1.z, v0.x * v1.y - v0.y * v1.x);
    }
    public static double len(Vec3 v) {
        return Math.sqrt(v.x * v.x + v.y * v.y + v.z * v.z);
    }
    public static double lenSq(Vec3 v) {
        return v.x * v.x + v.y * v.y + v.z * v.z;
    }
    public static Vec3 normalize(Vec3 v) {
        double len = v.len();
        if (len == 0.0) {
            return new Vec3(0.0, 0.0, 0.0);
        }
        return new Vec3(v.x / len, v.y / len, v.z / len);
    }
    public static String toString(Vec3 v) {
        return String.format("%d\n%d\n%d", v.x, v.y, v.z);
    }
}
