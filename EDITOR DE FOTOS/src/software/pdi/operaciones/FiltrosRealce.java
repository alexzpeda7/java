package software.pdi.operaciones;

import software.pdi.modelo.Imagen;
import software.pdi.ui.VentanaPrincipal;

import javax.swing.*;

public class FiltrosRealce {
    private VentanaPrincipal ventana;

    public FiltrosRealce(VentanaPrincipal ventana) {
        this.ventana = ventana;
    }

    // Laplaciano - Detección de bordes mediante segunda derivada
    public void filtroLaplaciano() {
        Imagen img = ventana.getImagenActual();
        if (img == null) {
            JOptionPane.showMessageDialog(ventana, "No hay imagen cargada.");
            return;
        }

        ventana.respaldarEstado();
        Imagen resultado = new Imagen(img.clonar().getBufferedImage());
        int[][] kernel = {{0, -1, 0}, {-1, 4, -1}, {0, -1, 0}};
        KernelUtils.aplicarKernel(img, resultado, kernel, 1);
        ventana.setImagenActual(resultado);
        ventana.actualizarVista();
    }

    // Prewitt - Detección de bordes horizontales y verticales
    public void filtroPrewitt() {
        Imagen img = ventana.getImagenActual();
        if (img == null) {
            JOptionPane.showMessageDialog(ventana, "No hay imagen cargada.");
            return;
        }

        ventana.respaldarEstado();
        // Convertir a grises primero
        new AjustesImagen(ventana).convertirAGris();
        
        Imagen resultado = new Imagen(img.clonar().getBufferedImage());
        int[][] kx = {{-1, 0, 1}, {-1, 0, 1}, {-1, 0, 1}}; // Kernel horizontal
        int[][] ky = {{-1, -1, -1}, {0, 0, 0}, {1, 1, 1}}; // Kernel vertical
        KernelUtils.convolucionDoble(img, resultado, kx, ky);
        ventana.setImagenActual(resultado);
        ventana.actualizarVista();
    }

    // Roberts - Detección de bordes con kernel 2x2 (rápido pero sensible al ruido)
    public void filtroRoberts() {
        Imagen img = ventana.getImagenActual();
        if (img == null) {
            JOptionPane.showMessageDialog(ventana, "No hay imagen cargada.");
            return;
        }

        ventana.respaldarEstado();
        // Convertir a grises primero
        new AjustesImagen(ventana).convertirAGris();
        
        Imagen resultado = new Imagen(img.clonar().getBufferedImage());
        int[][] kx = {{1, 0}, {0, -1}};  // Kernel Roberts para diagonal 1
        int[][] ky = {{0, 1}, {-1, 0}};  // Kernel Roberts para diagonal 2
        KernelUtils.convolucionDoble(img, resultado, kx, ky);
        ventana.setImagenActual(resultado);
        ventana.actualizarVista();
    }

    // Sobel - Detección de bordes con suavizado (menos sensible al ruido)
    public void filtroSobel() {
        Imagen img = ventana.getImagenActual();
        if (img == null) {
            JOptionPane.showMessageDialog(ventana, "No hay imagen cargada.");
            return;
        }

        ventana.respaldarEstado();
        // Convertir a grises primero
        new AjustesImagen(ventana).convertirAGris();
        
        Imagen resultado = new Imagen(img.clonar().getBufferedImage());
        int[][] kx = {{-1, 0, 1}, {-2, 0, 2}, {-1, 0, 1}}; // Kernel Sobel horizontal
        int[][] ky = {{-1, -2, -1}, {0, 0, 0}, {1, 2, 1}}; // Kernel Sobel vertical
        KernelUtils.convolucionDoble(img, resultado, kx, ky);
        ventana.setImagenActual(resultado);
        ventana.actualizarVista();
    }

    // Canny - Detector de bordes avanzado (múltiples etapas)
    public void filtroCanny() {
        Imagen img = ventana.getImagenActual();
        if (img == null) {
            JOptionPane.showMessageDialog(ventana, "No hay imagen cargada.");
            return;
        }

        ventana.respaldarEstado();
        // Convertir a grises
        new AjustesImagen(ventana).convertirAGris();
        
        // Aplicar filtro Gaussiano para suavizar (paso 1 de Canny)
        new FiltrosSuavizado(ventana).filtroGaussiano();
        
        // Aplicar Sobel para detección de gradientes (paso 2 de Canny)
        Imagen resultado = new Imagen(img.clonar().getBufferedImage());
        int[][] kx = {{-1, 0, 1}, {-2, 0, 2}, {-1, 0, 1}};
        int[][] ky = {{-1, -2, -1}, {0, 0, 0}, {1, 2, 1}};
        KernelUtils.convolucionDoble(img, resultado, kx, ky);
        ventana.setImagenActual(resultado);
        ventana.actualizarVista();
        
    }
}