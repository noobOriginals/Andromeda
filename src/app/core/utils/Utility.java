package app.core.utils;

import java.util.Random;

public class Utility {
    private static final ThreadLocal<Random> threadLocalRandom = ThreadLocal.withInitial(Random::new);

    public static double degreesToRadians(double degrees) {
        return degrees * Math.PI / 180.0;
    }
    public static double radiansToDegrees(double radians) {
        return radians * 180.0 / Math.PI;
    }
    public static double clamp(double value, double min, double max) {
        if (value < min) {
            return min;
        }
        if (value > max) {
            return max;
        }
        return value;
    }
    public static Vec2 clamp(Vec2 v, double min, double max) {
        double x, y;
        x = v.x;
        y = v.y;
        if (v.x < min) {
            x = min;
        }
        if (v.x > max) {
            x = max;
        }
        if (v.y < min) {
            y = min;
        }
        if (v.y > max) {
            y = max;
        }
        return new Vec2(x, y);
    }
    public static Vec3 clamp(Vec3 v, double min, double max) {
        double x, y, z;
        x = v.x;
        y = v.y;
        z = v.z;
        if (v.x < min) {
            x = min;
        }
        if (v.x > max) {
            x = max;
        }
        if (v.y < min) {
            y = min;
        }
        if (v.y > max) {
            y = max;
        }
        if (v.z < min) {
            z = min;
        }
        if (v.z > max) {
            z = max;
        }
        return new Vec3(x, y, z);
    }
    public static double randomDouble() {
        return threadLocalRandom.get().nextDouble();
    }
    public static double randomDouble(double min, double max) {
        return min + threadLocalRandom.get().nextDouble() * (max - min);
    }
    public static double randomGaussian() {
        return threadLocalRandom.get().nextGaussian();
    }
    public static double randomGaussian(double mean, double stddev) {
        return mean + stddev * threadLocalRandom.get().nextGaussian();
    }
    public static Vec2 randomVec2() {
        return new Vec2(randomDouble(), randomDouble());
    }
    public static Vec2 randomVec2(double min, double max) {
        return new Vec2(randomDouble(min, max), randomDouble(min, max));
    }
    public static Vec2 randomUnitVec2() {
        return new Vec2(randomGaussian(), randomGaussian()).normalize();
    }
    public static Vec3 randomVec3() {
        return new Vec3(randomDouble(), randomDouble(), randomDouble());
    }
    public static Vec3 randomVec3(double min, double max) {
        return new Vec3(randomDouble(min, max), randomDouble(min, max), randomDouble(min, max));
    }
    public static Vec3 randomUnitVec3() {
        return new Vec3(randomGaussian(), randomGaussian(), randomGaussian()).normalize();
    }
    public static Vec3 gammaCorrect(Vec3 color) {
        return new Vec3(Math.sqrt(color.x), Math.sqrt(color.y), Math.sqrt(color.z));
    }
    public static boolean isNearZero(double d) {
        return Math.abs(d) < 1e-8;
    }
    public static boolean isNearZero(Vec2 v) {
        return isNearZero(v.x) && isNearZero(v.y);
    }
    public static boolean isNearZero(Vec3 v) {
        return isNearZero(v.x) && isNearZero(v.y) && isNearZero(v.z);
    }
    public static Vec3 reflect(Vec3 v, Vec3 normal) {
        return v.sub(normal.mul(2.0 * v.dot(normal)));
    }
    public static Vec3 refract(Vec3 uv, Vec3 normal, double ratio) {
        double cos = Math.min(uv.neg().dot(normal), 1.0);
        Vec3 perp = normal.mul(cos).add(uv).mul(ratio);
        Vec3 parl = normal.mul(-Math.sqrt(Math.abs(1.0 - perp.lenSq())));
        return perp.add(parl);
    }
    public static double reflectance(double cosine, double refIdx) {
        double r0 = (1.0 - refIdx) / (1.0 + refIdx);
        r0 = r0 * r0;
        return r0 + (1.0 - r0) * Math.pow(1.0 - cosine, 5.0);
    }
}
