package software.pdi.operaciones;

import software.pdi.modelo.Imagen;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;

public class KernelUtils {

    public static void aplicarKernel(Imagen origen, Imagen destino, int[][] kernel, int divisor) {
        BufferedImage src = origen.getBufferedImage();
        BufferedImage dst = destino.getBufferedImage();
        int tam = kernel.length;
        int offset = tam / 2;

        for (int y = 0; y < src.getHeight(); y++) {
            for (int x = 0; x < src.getWidth(); x++) {
                int sr = 0, sg = 0, sb = 0;
                
                for (int ky = 0; ky < tam; ky++) {
                    for (int kx = 0; kx < tam; kx++) {
                        int px = Math.min(Math.max(x + kx - offset, 0), src.getWidth() - 1);
                        int py = Math.min(Math.max(y + ky - offset, 0), src.getHeight() - 1);
                        Color p = new Color(src.getRGB(px, py));
                        int peso = kernel[ky][kx];
                        sr += p.getRed() * peso;
                        sg += p.getGreen() * peso;
                        sb += p.getBlue() * peso;
                    }
                }

                int r = Math.max(0, Math.min(255, sr / divisor));
                int g = Math.max(0, Math.min(255, sg / divisor));
                int b = Math.max(0, Math.min(255, sb / divisor));
                dst.setRGB(x, y, new Color(r, g, b).getRGB());
            }
        }
    }

    public static void filtroMediana(Imagen origen, Imagen destino) {
        BufferedImage src = origen.getBufferedImage();
        BufferedImage dst = destino.getBufferedImage();
        int tam = 3, offset = tam / 2;

        for (int y = 0; y < src.getHeight(); y++) {
            for (int x = 0; x < src.getWidth(); x++) {
                ArrayList<Integer> r = new ArrayList<>(), g = new ArrayList<>(), b = new ArrayList<>();
                
                for (int ky = 0; ky < tam; ky++) {
                    for (int kx = 0; kx < tam; kx++) {
                        int px = Math.min(Math.max(x + kx - offset, 0), src.getWidth() - 1);
                        int py = Math.min(Math.max(y + ky - offset, 0), src.getHeight() - 1);
                        Color c = new Color(src.getRGB(px, py));
                        r.add(c.getRed());
                        g.add(c.getGreen());
                        b.add(c.getBlue());
                    }
                }
                
                Collections.sort(r);
                Collections.sort(g);
                Collections.sort(b);
                int m = r.size() / 2;
                dst.setRGB(x, y, new Color(r.get(m), g.get(m), b.get(m)).getRGB());
            }
        }
    }

    public static void convolucionDoble(Imagen origen, Imagen destino, int[][] kx, int[][] ky) {
        BufferedImage src = origen.getBufferedImage();
        BufferedImage dst = destino.getBufferedImage();
        int tam = kx.length, offset = tam / 2;

        for (int y = 0; y < src.getHeight(); y++) {
            for (int x = 0; x < src.getWidth(); x++) {
                int sx = 0, sy = 0;
                
                for (int ky_ = 0; ky_ < tam; ky_++) {
                    for (int kx_ = 0; kx_ < tam; kx_++) {
                        int px = Math.min(Math.max(x + kx_ - offset, 0), src.getWidth() - 1);
                        int py = Math.min(Math.max(y + ky_ - offset, 0), src.getHeight() - 1);
                        int gris = new Color(src.getRGB(px, py)).getRed();
                        sx += gris * kx[ky_][kx_];
                        sy += gris * ky[ky_][kx_];
                    }
                }
                
                int mag = Math.min(255, (int) Math.sqrt(sx * sx + sy * sy));
                dst.setRGB(x, y, new Color(mag, mag, mag).getRGB());
            }
        }
    }

    public static void aplicarMorfologia(Imagen origen, Imagen destino, int[][] elemento, boolean erosion) {
        BufferedImage src = origen.getBufferedImage();
        BufferedImage dst = destino.getBufferedImage();
        int tam = elemento.length, offset = tam / 2;

        for (int y = 0; y < src.getHeight(); y++) {
            for (int x = 0; x < src.getWidth(); x++) {
                boolean cumple = erosion;
                
                for (int ky = 0; ky < tam; ky++) {
                    for (int kx = 0; kx < tam; kx++) {
                        if (elemento[ky][kx] == 1) {
                            int px = x + kx - offset, py = y + ky - offset;
                            if (px >= 0 && px < src.getWidth() && py >= 0 && py < src.getHeight()) {
                                Color c = new Color(src.getRGB(px, py));
                                boolean blanco = c.getRed() > 128;
                                if (erosion && !blanco) cumple = false;
                                if (!erosion && blanco) cumple = true;
                            }
                        }
                    }
                }
                dst.setRGB(x, y, cumple ? Color.WHITE.getRGB() : Color.BLACK.getRGB());
            }
        }
    }
}