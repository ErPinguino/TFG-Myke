package com.tfg.tfg.Map;

public class PerlinNoise {
    private final int[] perm = new int[512];

    public PerlinNoise(long seed) {
        int[] p = new int[256];
        for (int i = 0; i < 256; i++) p[i] = i;
        for (int i = 255; i > 0; i--) {
            int j = (int) ((seed + i) % (i + 1));
            int tmp = p[i]; p[i] = p[j]; p[j] = tmp;
        }
        for (int i = 0; i < 512; i++) perm[i] = p[i & 255];
    }

    private double fade(double t) {
        return t * t * t * (t * (t * 6 - 15) + 10);
    }

    private double lerp(double t, double a, double b) {
        return a + t * (b - a);
    }

    private double grad(int hash, double x, double y) {
        switch (hash & 0x3) {
            case 0: return  x + y;
            case 1: return -x + y;
            case 2: return  x - y;
            case 3: return -x - y;
        }
        return 0;
    }

    public double noise(double x, double y) {
        int X = (int)Math.floor(x) & 255;
        int Y = (int)Math.floor(y) & 255;
        double xf = x - Math.floor(x), yf = y - Math.floor(y);
        double u = fade(xf), v = fade(yf);
        int aa = perm[X    + perm[Y   ]];
        int ab = perm[X    + perm[Y+1]];
        int ba = perm[X+1  + perm[Y   ]];
        int bb = perm[X+1  + perm[Y+1]];
        double x1 = lerp(u, grad(aa, xf,   yf  ), grad(ba, xf-1, yf  ));
        double x2 = lerp(u, grad(ab, xf,   yf-1), grad(bb, xf-1, yf-1));
        return lerp(v, x1, x2);
    }

    public double octavePerlin(double x, double y, int octaves) {
        double total = 0, frequency = 1, amplitude = 1, maxAmp = 0;
        for (int i = 0; i < octaves; i++) {
            total += noise(x * frequency, y * frequency) * amplitude;
            maxAmp += amplitude;
            amplitude *= 0.5;
            frequency *= 2;
        }
        return total / maxAmp;
    }
}
