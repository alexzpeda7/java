package software.pdi.util;

import java.awt.*;
import java.awt.image.BufferedImage;

public class HistogramaUtils {
    
    public static int[][] calcularHistogramas(BufferedImage imagen) {
        int[] hR = new int[256];
        int[] hG = new int[256];
        int[] hB = new int[256];
        int[] hGray = new int[256];

        for (int y = 0; y < imagen.getHeight(); y++) {
            for (int x = 0; x < imagen.getWidth(); x++) {
                Color p = new Color(imagen.getRGB(x, y));
                hR[p.getRed()]++;
                hG[p.getGreen()]++;
                hB[p.getBlue()]++;
                int g = (p.getRed() + p.getGreen() + p.getBlue()) / 3;
                hGray[g]++;
            }
        }
        
        return new int[][]{hR, hG, hB, hGray};
    }
    
    public static BufferedImage generarImagenHistograma(int[][] histogramas) {
        int[] hR = histogramas[0];
        int[] hG = histogramas[1];
        int[] hB = histogramas[2];
        int[] hGray = histogramas[3];
        
        int w = 512, h = 300;
        BufferedImage histImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = histImage.createGraphics();
        
        // Fondo blanco
        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, w, h);
        
        // Encontrar el valor máximo para escalar
        int max = 0;
        for (int i = 0; i < 256; i++) {
            max = Math.max(max, Math.max(Math.max(hR[i], hG[i]), 
                           Math.max(hB[i], hGray[i])));
        }
        
        // Dibujar líneas del histograma
        g2.setStroke(new BasicStroke(1.5f));
        
        for (int i = 0; i < 255; i++) {
            int x1 = 50 + (i * (w - 100) / 255);
            int x2 = 50 + ((i + 1) * (w - 100) / 255);
            
            // Rojo
            g2.setColor(new Color(255, 0, 0, 150));
            g2.drawLine(x1, h - 50 - hR[i] * (h - 100) / max, 
                       x2, h - 50 - hR[i + 1] * (h - 100) / max);
            
            // Verde
            g2.setColor(new Color(0, 255, 0, 150));
            g2.drawLine(x1, h - 50 - hG[i] * (h - 100) / max, 
                       x2, h - 50 - hG[i + 1] * (h - 100) / max);
            
            // Azul
            g2.setColor(new Color(0, 0, 255, 150));
            g2.drawLine(x1, h - 50 - hB[i] * (h - 100) / max, 
                       x2, h - 50 - hB[i + 1] * (h - 100) / max);
        }
        
        // Dibujar ejes
        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(2f));
        g2.drawLine(40, h - 40, w - 40, h - 40); // Eje X
        g2.drawLine(40, 40, 40, h - 40); // Eje Y
        
        // Etiquetas
        g2.setFont(new Font("Arial", Font.PLAIN, 10));
        g2.drawString("0", 35, h - 25);
        g2.drawString("255", w - 55, h - 25);
        g2.drawString("Intensidad", w / 2 - 30, h - 5);
        
        g2.dispose();
        
        return histImage;
    }
}