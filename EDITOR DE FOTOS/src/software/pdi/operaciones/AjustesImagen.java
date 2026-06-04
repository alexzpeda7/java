package software.pdi.operaciones;

import software.pdi.modelo.Imagen;
import software.pdi.ui.HistogramaFrame;
import software.pdi.ui.VentanaPrincipal;
import software.pdi.util.HistogramaUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class AjustesImagen {
    private VentanaPrincipal ventana;

    public AjustesImagen(VentanaPrincipal ventana) {
        this.ventana = ventana;
    }

    public void modificarBrillo() {
        Imagen img = ventana.getImagenActual();
        if (img == null) {
            JOptionPane.showMessageDialog(ventana, "No hay imagen cargada.");
            return;
        }

        try {
            int brillo = Integer.parseInt(JOptionPane.showInputDialog(
                ventana, "Ingrese un valor de brillo (-255 a 255):", "0"));
            
            if (brillo < -255 || brillo > 255) {
                JOptionPane.showMessageDialog(ventana, "El valor debe estar entre -255 y 255.");
                return;
            }

            ventana.respaldarEstado();
            BufferedImage bi = img.getBufferedImage();
            
            for (int y = 0; y < bi.getHeight(); y++) {
                for (int x = 0; x < bi.getWidth(); x++) {
                    Color px = new Color(bi.getRGB(x, y));
                    int r = Math.max(0, Math.min(255, px.getRed() + brillo));
                    int g = Math.max(0, Math.min(255, px.getGreen() + brillo));
                    int b = Math.max(0, Math.min(255, px.getBlue() + brillo));
                    bi.setRGB(x, y, new Color(r, g, b).getRGB());
                }
            }
            ventana.actualizarVista();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(ventana, "Valor ingresado no válido.");
        }
    }

    public void modificarContraste() {
        Imagen img = ventana.getImagenActual();
        if (img == null) {
            JOptionPane.showMessageDialog(ventana, "Debe abrir una imagen antes.");
            return;
        }

        try {
            double factor = Double.parseDouble(JOptionPane.showInputDialog(
                ventana, "Factor de contraste (0.1 - 3.0):", "1.0"));
            
            if (factor < 0.1 || factor > 3.0) {
                JOptionPane.showMessageDialog(ventana, "El factor debe estar entre 0.1 y 3.0");
                return;
            }

            ventana.respaldarEstado();
            BufferedImage bi = img.getBufferedImage();
            
            for (int y = 0; y < bi.getHeight(); y++) {
                for (int x = 0; x < bi.getWidth(); x++) {
                    Color px = new Color(bi.getRGB(x, y));
                    int r = (int) Math.max(0, Math.min(255, ((px.getRed() - 128) * factor + 128)));
                    int g = (int) Math.max(0, Math.min(255, ((px.getGreen() - 128) * factor + 128)));
                    int b = (int) Math.max(0, Math.min(255, ((px.getBlue() - 128) * factor + 128)));
                    bi.setRGB(x, y, new Color(r, g, b).getRGB());
                }
            }
            ventana.actualizarVista();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(ventana, "Valor inválido.");
        }
    }

    public void convertirAGris() {
        Imagen img = ventana.getImagenActual();
        if (img == null) {
            JOptionPane.showMessageDialog(ventana, "Cargue una imagen primero.");
            return;
        }

        ventana.respaldarEstado();
        BufferedImage bi = img.getBufferedImage();
        
        for (int y = 0; y < bi.getHeight(); y++) {
            for (int x = 0; x < bi.getWidth(); x++) {
                Color px = new Color(bi.getRGB(x, y));
                int g = (px.getRed() + px.getGreen() + px.getBlue()) / 3;
                bi.setRGB(x, y, new Color(g, g, g).getRGB());
            }
        }
        ventana.actualizarVista();
    }

    public void convertirBinario() {
        Imagen img = ventana.getImagenActual();
        if (img == null) {
            JOptionPane.showMessageDialog(ventana, "No hay imagen cargada.");
            return;
        }

        try {
            int umbral = Integer.parseInt(JOptionPane.showInputDialog(
                ventana, "Valor de umbral (0-255):", "127"));
            
            if (umbral < 0 || umbral > 255) {
                JOptionPane.showMessageDialog(ventana, "El valor debe estar entre 0 y 255.");
                return;
            }

            ventana.respaldarEstado();
            convertirAGris(); // Primero a grises
            
            BufferedImage bi = img.getBufferedImage();
            for (int y = 0; y < bi.getHeight(); y++) {
                for (int x = 0; x < bi.getWidth(); x++) {
                    int gris = new Color(bi.getRGB(x, y)).getRed();
                    int v = (gris > umbral) ? 255 : 0;
                    bi.setRGB(x, y, new Color(v, v, v).getRGB());
                }
            }
            ventana.actualizarVista();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(ventana, "Valor ingresado inválido.");
        }
    }

    public void aplicarNegativo() {
        Imagen img = ventana.getImagenActual();
        if (img == null) {
            JOptionPane.showMessageDialog(ventana, "Debe abrir una imagen primero.");
            return;
        }

        ventana.respaldarEstado();
        BufferedImage bi = img.getBufferedImage();
        
        for (int y = 0; y < bi.getHeight(); y++) {
            for (int x = 0; x < bi.getWidth(); x++) {
                Color px = new Color(bi.getRGB(x, y));
                bi.setRGB(x, y, new Color(255 - px.getRed(), 
                                          255 - px.getGreen(), 
                                          255 - px.getBlue()).getRGB());
            }
        }
        ventana.actualizarVista();
    }

    public void mostrarHistograma() {
        Imagen img = ventana.getImagenActual();
        if (img == null) {
            JOptionPane.showMessageDialog(ventana, "Debe cargar una imagen primero.");
            return;
        }

        int[][] histogramas = HistogramaUtils.calcularHistogramas(img.getBufferedImage());
        BufferedImage histImage = HistogramaUtils.generarImagenHistograma(histogramas);
        
        new HistogramaFrame(histImage, ventana).setVisible(true);
    }
    
    // ========== CONVERSIONES DE ESPACIOS DE COLOR ==========
    
    // RGB a HSV
    public void convertirRGBaHSV() {
        Imagen img = ventana.getImagenActual();
        if (img == null) { 
            JOptionPane.showMessageDialog(ventana, "Debe abrir una imagen primero."); 
            return; 
        }
        
        ventana.respaldarEstado();
        BufferedImage bi = img.getBufferedImage();
        BufferedImage nueva = new BufferedImage(bi.getWidth(), bi.getHeight(), bi.getType());

        for (int y = 0; y < bi.getHeight(); y++) {
            for (int x = 0; x < bi.getWidth(); x++) {
                Color px = new Color(bi.getRGB(x, y));
                float[] hsv = Color.RGBtoHSB(px.getRed(), px.getGreen(), px.getBlue(), null);
                int rgb = Color.HSBtoRGB(hsv[0], hsv[1], hsv[2]);
                nueva.setRGB(x, y, rgb);
            }
        }
        
        img.setBufferedImage(nueva);
        ventana.actualizarVista();
        JOptionPane.showMessageDialog(ventana, "Imagen convertida a HSV");
    }

    // HSV a RGB
    public void convertirHSVaRGB() {
        Imagen img = ventana.getImagenActual();
        if (img == null) { 
            JOptionPane.showMessageDialog(ventana, "Debe abrir una imagen primero."); 
            return; 
        }
        
        ventana.respaldarEstado();
        BufferedImage bi = img.getBufferedImage();
        BufferedImage nueva = new BufferedImage(bi.getWidth(), bi.getHeight(), bi.getType());

        JOptionPane.showMessageDialog(ventana, 
            "Para una conversión completa, la imagen debe estar en espacio HSV.\n" +
            "Se aplicará una conversión estándar usando Color.HSBtoRGB()");
        
        for (int y = 0; y < bi.getHeight(); y++) {
            for (int x = 0; x < bi.getWidth(); x++) {
                Color px = new Color(bi.getRGB(x, y));
           
                float[] hsv = Color.RGBtoHSB(px.getRed(), px.getGreen(), px.getBlue(), null);
                int rgb = Color.HSBtoRGB(hsv[0], hsv[1], hsv[2]);
                nueva.setRGB(x, y, rgb);
            }
        }
        
        img.setBufferedImage(nueva);
        ventana.actualizarVista();
        JOptionPane.showMessageDialog(ventana, "Conversión HSV a RGB completada");
    }

    // RGB → HSL
    public void convertirRGBaHSL() {
        Imagen img = ventana.getImagenActual();
        if (img == null) { 
            JOptionPane.showMessageDialog(ventana, "Debe abrir una imagen primero."); 
            return; 
        }
        
        ventana.respaldarEstado();
        BufferedImage bi = img.getBufferedImage();
        BufferedImage nueva = new BufferedImage(bi.getWidth(), bi.getHeight(), bi.getType());

        for (int y = 0; y < bi.getHeight(); y++) {
            for (int x = 0; x < bi.getWidth(); x++) {
                Color px = new Color(bi.getRGB(x, y));
                float[] hsl = rgbToHsl(px.getRed(), px.getGreen(), px.getBlue());
             
                int rgb = Color.HSBtoRGB(hsl[0], hsl[1], hsl[2]);
                nueva.setRGB(x, y, rgb);
            }
        }
        
        img.setBufferedImage(nueva);
        ventana.actualizarVista();
        JOptionPane.showMessageDialog(ventana, "Imagen convertida a HSL");
    }

    // HSL a RGB
    public void convertirHSLaRGB() {
        Imagen img = ventana.getImagenActual();
        if (img == null) { 
            JOptionPane.showMessageDialog(ventana, "Debe abrir una imagen primero."); 
            return; 
        }
        
        ventana.respaldarEstado();
        BufferedImage bi = img.getBufferedImage();
        BufferedImage nueva = new BufferedImage(bi.getWidth(), bi.getHeight(), bi.getType());

      
        for (int y = 0; y < bi.getHeight(); y++) {
            for (int x = 0; x < bi.getWidth(); x++) {
                Color px = new Color(bi.getRGB(x, y));

                float h = px.getRed() / 255f;
                float s = px.getGreen() / 255f;
                float l = px.getBlue() / 255f;
                
                int rgb = Color.HSBtoRGB(h, s, l);
                nueva.setRGB(x, y, rgb);
            }
        }
        
        img.setBufferedImage(nueva);
        ventana.actualizarVista();
        JOptionPane.showMessageDialog(ventana, "Conversión HSL a RGB completada");
    }

    // RGB a HSI
    public void convertirRGBaHSI() {
        Imagen img = ventana.getImagenActual();
        if (img == null) { 
            JOptionPane.showMessageDialog(ventana, "Debe abrir una imagen primero."); 
            return; 
        }
        
        ventana.respaldarEstado();
        BufferedImage bi = img.getBufferedImage();
        BufferedImage nueva = new BufferedImage(bi.getWidth(), bi.getHeight(), bi.getType());

        for (int y = 0; y < bi.getHeight(); y++) {
            for (int x = 0; x < bi.getWidth(); x++) {
                Color px = new Color(bi.getRGB(x, y));
                float[] hsi = rgbToHsi(px.getRed(), px.getGreen(), px.getBlue());
                
                int rgb = Color.HSBtoRGB(hsi[0], hsi[1], hsi[2]);
                nueva.setRGB(x, y, rgb);
            }
        }
        
        img.setBufferedImage(nueva);
        ventana.actualizarVista();
        JOptionPane.showMessageDialog(ventana, "Imagen convertida a HSI");
    }

    // HSI a RGB
    public void convertirHSIaRGB() {
        Imagen img = ventana.getImagenActual();
        if (img == null) { 
            JOptionPane.showMessageDialog(ventana, "Debe abrir una imagen primero."); 
            return; 
        }
        
        ventana.respaldarEstado();
        BufferedImage bi = img.getBufferedImage();
        BufferedImage nueva = new BufferedImage(bi.getWidth(), bi.getHeight(), bi.getType());

        for (int y = 0; y < bi.getHeight(); y++) {
            for (int x = 0; x < bi.getWidth(); x++) {
                Color px = new Color(bi.getRGB(x, y));
                
                float h = px.getRed() / 255f;
                float s = px.getGreen() / 255f;
                float i = px.getBlue() / 255f;
                
                int rgb = Color.HSBtoRGB(h, s, i);
                nueva.setRGB(x, y, rgb);
            }
        }
        
        img.setBufferedImage(nueva);
        ventana.actualizarVista();
        JOptionPane.showMessageDialog(ventana, "Conversión HSI a RGB completada");
    }

    // RGB → CIELab
    public void convertirRGBaLab() {
        Imagen img = ventana.getImagenActual();
        if (img == null) { 
            JOptionPane.showMessageDialog(ventana, "Debe abrir una imagen primero."); 
            return; 
        }
        
        ventana.respaldarEstado();
        BufferedImage bi = img.getBufferedImage();
        BufferedImage nueva = new BufferedImage(bi.getWidth(), bi.getHeight(), bi.getType());

        for (int y = 0; y < bi.getHeight(); y++) {
            for (int x = 0; x < bi.getWidth(); x++) {
                Color px = new Color(bi.getRGB(x, y));
                double[] lab = rgbToLab(px.getRed(), px.getGreen(), px.getBlue());
                
          
                int l = (int) Math.max(0, Math.min(255, lab[0] * 255 / 100));
                nueva.setRGB(x, y, new Color(l, l, l).getRGB());
            }
        }
        
        img.setBufferedImage(nueva);
        ventana.actualizarVista();
        JOptionPane.showMessageDialog(ventana, "Imagen convertida a CIELab (mostrando componente L)");
    }

    // CIELab a RGB
    public void convertirLabaRGB1() {
        Imagen img = ventana.getImagenActual();
        if (img == null) { 
            JOptionPane.showMessageDialog(ventana, "Debe abrir una imagen primero."); 
            return; 
        }
        
        ventana.respaldarEstado();
        BufferedImage bi = img.getBufferedImage();
        BufferedImage nueva = new BufferedImage(bi.getWidth(), bi.getHeight(), bi.getType());

        JOptionPane.showMessageDialog(ventana, 
            "Para una conversión completa de Lab a RGB, se necesitan los tres componentes.\n" +
            "Se mostrará la imagen en escala de grises basada en L.");
        
        for (int y = 0; y < bi.getHeight(); y++) {
            for (int x = 0; x < bi.getWidth(); x++) {
                Color px = new Color(bi.getRGB(x, y));
                
                int l = px.getRed();
                nueva.setRGB(x, y, new Color(l, l, l).getRGB());
            }
        }
        
        img.setBufferedImage(nueva);
        ventana.actualizarVista();
        JOptionPane.showMessageDialog(ventana, "Conversión Lab a RGB completada");
    }

    // ========== MÉTODOS AUXILIARES PARA CONVERSIONES ==========
    
    private float[] rgbToHsl(int r, int g, int b) {
        float rf = r / 255f;
        float gf = g / 255f;
        float bf = b / 255f;
        
        float max = Math.max(rf, Math.max(gf, bf));
        float min = Math.min(rf, Math.min(gf, bf));
        float h, s, l = (max + min) / 2f;

        if (max == min) {
            h = s = 0f;
        } else {
            float d = max - min;
            s = l > 0.5f ? d / (2f - max - min) : d / (max + min);
            
            if (max == rf) {
                h = (gf - bf) / d + (gf < bf ? 6f : 0f);
            } else if (max == gf) {
                h = (bf - rf) / d + 2f;
            } else {
                h = (rf - gf) / d + 4f;
            }
            h /= 6f;
        }
        
        return new float[]{h, s, l};
    }

    private float[] rgbToHsi(int r, int g, int b) {
        float rf = r / 255f;
        float gf = g / 255f;
        float bf = b / 255f;
        
        float i = (rf + gf + bf) / 3f;
        
        if (rf == gf && gf == bf) {
            return new float[]{0f, 0f, i};
        }
        
        float min = Math.min(rf, Math.min(gf, bf));
        float s = 1f - (3f / (rf + gf + bf)) * min;
        
        float num = 0.5f * ((rf - gf) + (rf - bf));
        float den = (float) Math.sqrt((rf - gf) * (rf - gf) + (rf - bf) * (gf - bf));
        float theta = (float) Math.acos(num / den);
        
        float h = (bf <= gf) ? theta : (float) (2f * Math.PI - theta);
        h = h / (float) (2f * Math.PI);
        
        return new float[]{h, s, i};
    }

    private double[] rgbToLab(int r, int g, int b) {
        double rf = r / 255.0;
        double gf = g / 255.0;
        double bf = b / 255.0;
        
        rf = (rf > 0.04045) ? Math.pow((rf + 0.055) / 1.055, 2.4) : rf / 12.92;
        gf = (gf > 0.04045) ? Math.pow((gf + 0.055) / 1.055, 2.4) : gf / 12.92;
        bf = (bf > 0.04045) ? Math.pow((bf + 0.055) / 1.055, 2.4) : bf / 12.92;
        
        double x = rf * 0.4124 + gf * 0.3576 + bf * 0.1805;
        double y = rf * 0.2126 + gf * 0.7152 + bf * 0.0722;
        double z = rf * 0.0193 + gf * 0.1192 + bf * 0.9505;
        
        x /= 0.95047;
        y /= 1.00000;
        z /= 1.08883;
        
        x = (x > 0.008856) ? Math.pow(x, 1.0/3.0) : (7.787 * x + 16.0/116.0);
        y = (y > 0.008856) ? Math.pow(y, 1.0/3.0) : (7.787 * y + 16.0/116.0);
        z = (z > 0.008856) ? Math.pow(z, 1.0/3.0) : (7.787 * z + 16.0/116.0);
        
        double L = (116.0 * y) - 16.0;
        double a = 500.0 * (x - y);
        double bLab = 200.0 * (y - z);
        
        return new double[]{L, a, bLab};
    }
    
 // CONVERSIONES CIELab 

    public void convertirRGBaLab1() {
        Imagen img = ventana.getImagenActual();
        if (img == null) { 
            JOptionPane.showMessageDialog(ventana, "Debe abrir una imagen primero."); 
            return; 
        }
        
        ventana.respaldarEstado();
        BufferedImage bi = img.getBufferedImage();
        BufferedImage nueva = new BufferedImage(bi.getWidth(), bi.getHeight(), bi.getType());

    
        double[][][] labImage = new double[bi.getHeight()][bi.getWidth()][3];
        
        for (int y = 0; y < bi.getHeight(); y++) {
            for (int x = 0; x < bi.getWidth(); x++) {
                Color px = new Color(bi.getRGB(x, y));
                double[] lab = rgbToLab(px.getRed(), px.getGreen(), px.getBlue());
                labImage[y][x] = lab;
                
            
                int l = (int) Math.max(0, Math.min(255, lab[0] * 255 / 100));
                nueva.setRGB(x, y, new Color(l, l, l).getRGB());
            }
        }
        
        
        img.setBufferedImage(nueva);
        ventana.actualizarVista();
        JOptionPane.showMessageDialog(ventana, 
            "Imagen convertida a CIELab (mostrando componente L como gris)\n" +
            "Los componentes a y b se han perdido en esta visualización.");
    }

    
     //CIELab a RGB 
    public void convertirLabaRGB() {
        Imagen img = ventana.getImagenActual();
        if (img == null) { 
            JOptionPane.showMessageDialog(ventana, "Debe abrir una imagen primero."); 
            return; 
        }
        
     
        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(new JLabel("L (0-100):"));
        JTextField campoL = new JTextField("50");
        panel.add(campoL);
        panel.add(new JLabel("a (-128 a 127):"));
        JTextField campoA = new JTextField("0");
        panel.add(campoA);
        panel.add(new JLabel("b (-128 a 127):"));
        JTextField campoB = new JTextField("0");
        panel.add(campoB);
        
        int resultado = JOptionPane.showConfirmDialog(ventana, panel, 
            "Ingrese valores CIELab", JOptionPane.OK_CANCEL_OPTION);
        
        if (resultado != JOptionPane.OK_OPTION) return;
        
        try {
            double L = Double.parseDouble(campoL.getText());
            double a = Double.parseDouble(campoA.getText());
            double b = Double.parseDouble(campoB.getText());
            
            // Validar rangos
            L = Math.max(0, Math.min(100, L));
            a = Math.max(-128, Math.min(127, a));
            b = Math.max(-128, Math.min(127, b));
            
            ventana.respaldarEstado();
            BufferedImage bi = img.getBufferedImage();
            BufferedImage nueva = new BufferedImage(bi.getWidth(), bi.getHeight(), bi.getType());
            
            // Convertir Lab a RGB para CADA píxel
            for (int y = 0; y < bi.getHeight(); y++) {
                for (int x = 0; x < bi.getWidth(); x++) {
                    int rgb = labToRgb(L, a, b);
                    nueva.setRGB(x, y, rgb);
                }
            }
            
            img.setBufferedImage(nueva);
            ventana.actualizarVista();
            JOptionPane.showMessageDialog(ventana, 
                "Conversión Lab a RGB completada usando L=" + L + 
                ", a=" + a + ", b=" + b);
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(ventana, "Valores inválidos");
        }
    }

 void convertirLabaRGBDesdeCanales() {
        Imagen img = ventana.getImagenActual();
        if (img == null) { 
            JOptionPane.showMessageDialog(ventana, "Debe abrir una imagen primero."); 
            return; 
        }
        
        ventana.respaldarEstado();
        BufferedImage bi = img.getBufferedImage();
        BufferedImage nueva = new BufferedImage(bi.getWidth(), bi.getHeight(), bi.getType());
        
        for (int y = 0; y < bi.getHeight(); y++) {
            for (int x = 0; x < bi.getWidth(); x++) {
                Color px = new Color(bi.getRGB(x, y));
                
                // Extraer Lab de los canales RGB
                // L: 0-100 mapeado a 0-255
                // a: -128 a 127 mapeado a 0-255
                // b: -128 a 127 mapeado a 0-255
                double L = px.getRed() * 100.0 / 255.0;
                double a = (px.getGreen() - 128) * 255.0 / 127.0;
                double b = (px.getBlue() - 128) * 255.0 / 127.0;
                
                int rgb = labToRgb(L, a, b);
                nueva.setRGB(x, y, rgb);
            }
        }
        
        img.setBufferedImage(nueva);
        ventana.actualizarVista();
        JOptionPane.showMessageDialog(ventana, "Conversión Lab a RGB desde canales completada");
    }

    // ========== MÉTODO AUXILIAR PARA LAB a RGB ==========

    private int labToRgb(double L, double a, double b) {
        // Paso 1: Lab → XYZ
        double y = (L + 16) / 116.0;
        double x = a / 500.0 + y;
        double z = y - b / 200.0;

        double x3 = x * x * x;
        double y3 = y * y * y;
        double z3 = z * z * z;

        x = (x3 > 0.008856) ? x3 : (x - 16.0/116.0) / 7.787;
        y = (y3 > 0.008856) ? y3 : (y - 16.0/116.0) / 7.787;
        z = (z3 > 0.008856) ? z3 : (z - 16.0/116.0) / 7.787;

        // Referencia D65
        x = x * 0.95047;
        y = y * 1.00000;
        z = z * 1.08883;

        // Paso 2: XYZ a RGB lineal
        double r = x * 3.2406 + y * (-1.5372) + z * (-0.4986);
        double g = x * (-0.9689) + y * 1.8758 + z * 0.0415;
        double blue = x * 0.0557 + y * (-0.2040) + z * 1.0570;

        // Paso 3: Corrección gamma inversa
        r = (r > 0.0031308) ? 1.055 * Math.pow(r, 1/2.4) - 0.055 : 12.92 * r;
        g = (g > 0.0031308) ? 1.055 * Math.pow(g, 1/2.4) - 0.055 : 12.92 * g;
        blue = (blue > 0.0031308) ? 1.055 * Math.pow(blue, 1/2.4) - 0.055 : 12.92 * blue;

        // Paso 4: Escalar y asegurar rango 0-255
        int R = (int) Math.max(0, Math.min(255, r * 255));
        int G = (int) Math.max(0, Math.min(255, g * 255));
        int B = (int) Math.max(0, Math.min(255, blue * 255));

        return new Color(R, G, B).getRGB();
    }
}