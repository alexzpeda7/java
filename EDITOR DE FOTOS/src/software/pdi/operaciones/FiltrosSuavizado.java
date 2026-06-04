package software.pdi.operaciones;

import software.pdi.modelo.Imagen;
import software.pdi.ui.VentanaPrincipal;

import javax.swing.*;

public class FiltrosSuavizado {
    private VentanaPrincipal ventana;

    public FiltrosSuavizado(VentanaPrincipal ventana) {
        this.ventana = ventana;
    }

    public void filtroMedia() {
        Imagen img = ventana.getImagenActual();
        if (img == null) {
            JOptionPane.showMessageDialog(ventana, "No hay imagen cargada.");
            return;
        }

        ventana.respaldarEstado();
        Imagen resultado = new Imagen(img.clonar().getBufferedImage());
        int[][] kernel = {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
        KernelUtils.aplicarKernel(img, resultado, kernel, 9);
        ventana.setImagenActual(resultado);
        ventana.actualizarVista();
    }

    public void filtroMediana() {
        Imagen img = ventana.getImagenActual();
        if (img == null) {
            JOptionPane.showMessageDialog(ventana, "No hay imagen cargada.");
            return;
        }

        ventana.respaldarEstado();
        Imagen resultado = new Imagen(img.clonar().getBufferedImage());
        KernelUtils.filtroMediana(img, resultado);
        ventana.setImagenActual(resultado);
        ventana.actualizarVista();
    }

    public void filtroGaussiano() {
        Imagen img = ventana.getImagenActual();
        if (img == null) {
            JOptionPane.showMessageDialog(ventana, "No hay imagen cargada.");
            return;
        }

        ventana.respaldarEstado();
        Imagen resultado = new Imagen(img.clonar().getBufferedImage());
        int[][] kernel = {{1, 2, 1}, {2, 4, 2}, {1, 2, 1}};
        KernelUtils.aplicarKernel(img, resultado, kernel, 16);
        ventana.setImagenActual(resultado);
        ventana.actualizarVista();
    }
}