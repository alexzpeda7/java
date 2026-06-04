package software.pdi.operaciones;

import software.pdi.modelo.Imagen;
import software.pdi.ui.VentanaPrincipal;

import javax.swing.*;

public class FiltrosFrecuencia {
    private VentanaPrincipal ventana;

    public FiltrosFrecuencia(VentanaPrincipal ventana) {
        this.ventana = ventana;
    }

    public void filtroPasaBajo() {
        Imagen img = ventana.getImagenActual();
        if (img == null) {
            JOptionPane.showMessageDialog(ventana, "No hay imagen cargada.");
            return;
        }

        ventana.respaldarEstado();
        Imagen resultado = new Imagen(img.clonar().getBufferedImage());
        int[][] kernel = {{1, 1, 1}, {1, 2, 1}, {1, 1, 1}};
        KernelUtils.aplicarKernel(img, resultado, kernel, 10);
        ventana.setImagenActual(resultado);
        ventana.actualizarVista();
    }

    public void filtroPasaAlto() {
        Imagen img = ventana.getImagenActual();
        if (img == null) {
            JOptionPane.showMessageDialog(ventana, "No hay imagen cargada.");
            return;
        }

        ventana.respaldarEstado();
        Imagen resultado = new Imagen(img.clonar().getBufferedImage());
        int[][] kernel = {{0, -1, 0}, {-1, 5, -1}, {0, -1, 0}};
        KernelUtils.aplicarKernel(img, resultado, kernel, 1);
        ventana.setImagenActual(resultado);
        ventana.actualizarVista();
    }

    public void filtroPasoBanda() {
        Imagen img = ventana.getImagenActual();
        if (img == null) {
            JOptionPane.showMessageDialog(ventana, "No hay imagen cargada.");
            return;
        }

        ventana.respaldarEstado();
        Imagen resultado = new Imagen(img.clonar().getBufferedImage());
        int[][] kernel = {{-1, -1, -1}, {-1, 8, -1}, {-1, -1, -1}};
        KernelUtils.aplicarKernel(img, resultado, kernel, 1);
        ventana.setImagenActual(resultado);
        ventana.actualizarVista();
    }
}