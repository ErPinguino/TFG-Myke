// src/main/java/com/tfg/tfg/Services/MapService.java
package com.tfg.tfg.Services;

import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.util.*;
import java.util.stream.IntStream;

@Service
public class MapService {

    /**
     * Genera un tile de mapa de tamaño `size`×`size`, usando Perlin Noise para altura.
     */
    public BufferedImage generateTile(long seed, int tileX, int tileZ, int size) {
        BufferedImage img = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
        PerlinNoise noise = new PerlinNoise(seed);

        double scale = 0.01; // controla el “zoom” del ruido
        for (int dx = 0; dx < size; dx++) {
            for (int dz = 0; dz < size; dz++) {
                double wx = tileX * size + dx;
                double wz = tileZ * size + dz;
                double h = noise.noise(wx * scale, wz * scale); // [-1…1]
                int gray = (int) ((h + 1) * 0.5 * 255);
                int rgb = (gray << 16) | (gray << 8) | gray;
                img.setRGB(dx, dz, rgb);
            }
        }
        return img;
    }

    /**
     * Implementación de Perlin Noise 2D (Ken Perlin, 2002).
     */
    static class PerlinNoise {
        private final int[] p = new int[512];

        public PerlinNoise(long seed) {
            List<Integer> perm = IntStream.range(0, 256).boxed().toList();
            Collections.shuffle(perm, new Random(seed));
            for (int i = 0; i < 512; i++) {
                p[i] = perm.get(i & 255);
            }
        }

        private static double fade(double t) {
            // 6t^5 - 15t^4 + 10t^3
            return t * t * t * (t * (t * 6 - 15) + 10);
        }

        private static double lerp(double t, double a, double b) {
            return a + t * (b - a);
        }

        private static double grad(int hash, double x, double y) {
            int h = hash & 3;
            double u = (h < 2) ? x : y;
            double v = (h < 2) ? y : x;
            return (((h & 1) == 0) ? u : -u) + (((h & 2) == 0) ? v : -v);
        }

        /**
         * @param x coordenada X (puede ser cualquier valor real)
         * @param y coordenada Y (idem)
         * @return ruido Perlin en [-1,1]
         */
        public double noise(double x, double y) {
            int X = (int) Math.floor(x) & 255;
            int Y = (int) Math.floor(y) & 255;
            x -= Math.floor(x);
            y -= Math.floor(y);
            double u = fade(x);
            double v = fade(y);

            int aa = p[p[X] + Y];
            int ab = p[p[X] + Y + 1];
            int ba = p[p[X + 1] + Y];
            int bb = p[p[X + 1] + Y + 1];

            double res = lerp(v,
                    lerp(u, grad(aa, x, y), grad(ba, x - 1, y)),
                    lerp(u, grad(ab, x, y - 1), grad(bb, x - 1, y - 1))
            );
            return res;
        }
    }
}
