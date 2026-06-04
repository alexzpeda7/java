package software.pdi.operaciones;

import software.pdi.modelo.Imagen;
import software.pdi.ui.VentanaPrincipal;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class FiltrosMorfologicos {
    private VentanaPrincipal ventana;

    public FiltrosMorfologicos(VentanaPrincipal ventana) {
        this.ventana = ventana;
    }

    public void aplicarErosion() {
        Imagen img = ventana.getImagenActual();
        if (img == null) {
            JOptionPane.showMessageDialog(ventana, "Cargue una imagen binaria.");
            return;
        }
        if (!img.esBinaria()) {
            JOptionPane.showMessageDialog(ventana, "Solo válido para imágenes binarias.");
            return;
        }

        ventana.respaldarEstado();
        Imagen resultado = new Imagen(img.clonar().getBufferedImage());
        int[][] elemento = {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
        KernelUtils.aplicarMorfologia(img, resultado, elemento, true);
        ventana.setImagenActual(resultado);
        ventana.actualizarVista();
    }

    public void aplicarDilatacion() {
        Imagen img = ventana.getImagenActual();
        if (img == null) {
            JOptionPane.showMessageDialog(ventana, "Cargue una imagen binaria.");
            return;
        }
        if (!img.esBinaria()) {
            JOptionPane.showMessageDialog(ventana, "Solo válido para imágenes binarias.");
            return;
        }

        ventana.respaldarEstado();
        Imagen resultado = new Imagen(img.clonar().getBufferedImage());
        int[][] elemento = {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
        KernelUtils.aplicarMorfologia(img, resultado, elemento, false);
        ventana.setImagenActual(resultado);
        ventana.actualizarVista();
    }

    public void realizarApertura() {
        aplicarErosion();
        aplicarDilatacion();
    }

    public void realizarCierre() {
        aplicarDilatacion();
        aplicarErosion();
    }

    public void ejecutarEsqueletonizacion() {
        Imagen img = ventana.getImagenActual();
        if (img == null) {
            JOptionPane.showMessageDialog(ventana, "Cargue una imagen binaria.");
            return;
        }
        if (!img.esBinaria()) {
            JOptionPane.showMessageDialog(ventana, "Solo válido para binarios.");
            return;
        }

        ventana.respaldarEstado();
        BufferedImage out = new BufferedImage(img.getAncho(), img.getAlto(), img.getTipo());
        BufferedImage src = img.getBufferedImage();
        
        for (int y = 0; y < src.getHeight(); y++) {
            for (int x = 0; x < src.getWidth(); x++) {
                out.setRGB(x, y, src.getRGB(x, y));
            }
        }

        boolean cambio;
        do {
            cambio = pasoEsqueleto(out, 1) | pasoEsqueleto(out, 2);
        } while (cambio);

        ventana.setImagenActual(new Imagen(out));
        ventana.actualizarVista();
    }

    private boolean pasoEsqueleto(BufferedImage img, int paso) {
        boolean cambio = false;
        ArrayList<Point> borrar = new ArrayList<>();
        
        for (int y = 1; y < img.getHeight() - 1; y++) {
            for (int x = 1; x < img.getWidth() - 1; x++) {
                Color p = new Color(img.getRGB(x, y));
                if (p.getRed() == 0) continue;
                
                int[] v = new int[8];
                v[0] = new Color(img.getRGB(x, y - 1)).getRed() > 128 ? 1 : 0;
                v[1] = new Color(img.getRGB(x + 1, y - 1)).getRed() > 128 ? 1 : 0;
                v[2] = new Color(img.getRGB(x + 1, y)).getRed() > 128 ? 1 : 0;
                v[3] = new Color(img.getRGB(x + 1, y + 1)).getRed() > 128 ? 1 : 0;
                v[4] = new Color(img.getRGB(x, y + 1)).getRed() > 128 ? 1 : 0;
                v[5] = new Color(img.getRGB(x - 1, y + 1)).getRed() > 128 ? 1 : 0;
                v[6] = new Color(img.getRGB(x - 1, y)).getRed() > 128 ? 1 : 0;
                v[7] = new Color(img.getRGB(x - 1, y - 1)).getRed() > 128 ? 1 : 0;
                
                int trans = 0, count = 0;
                for (int i = 0; i < 8; i++) {
                    if (v[i] == 0 && v[(i + 1) % 8] == 1) trans++;
                    count += v[i];
                }
                
                boolean c1 = (count >= 2 && count <= 6);
                boolean c2 = (trans == 1);
                boolean c3 = (paso == 1) ? (v[0] * v[2] * v[4] == 0 && v[2] * v[4] * v[6] == 0) 
                                         : (v[0] * v[2] * v[6] == 0 && v[0] * v[4] * v[6] == 0);
                
                if (c1 && c2 && c3) {
                    borrar.add(new Point(x, y));
                    cambio = true;
                }
            }
        }
        
        for (Point p : borrar) {
            img.setRGB(p.x, p.y, Color.BLACK.getRGB());
        }
        return cambio;
    }
}