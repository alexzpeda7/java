package proyecto.thread.zip;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class CompresionArchivos {

    public static void main(String[] args) {
        // Crear ventana principal
        JFrame frame = new JFrame("Compresión de Archivos");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);

        // Crear panel y elementos de la interfaz
        JPanel panel = new JPanel(new BorderLayout());
        JButton botonComprimir = new JButton("Comprimir Archivo");
        JProgressBar barraProgreso = new JProgressBar(0, 100);
        barraProgreso.setStringPainted(true); // Muestra el texto del porcentaje

        // Añadir elementos al panel
        panel.add(botonComprimir, BorderLayout.NORTH);
        panel.add(barraProgreso, BorderLayout.CENTER);

        // Acción del botón
        botonComprimir.addActionListener(e -> {
            Thread hiloCompresion = new Thread(() -> {
                final String NOMBRE_ARCHIVO = "C:\\Users\\zeped\\OneDrive\\Escritorio\\lenguajes.txt";
                final String NOMBRE_ARCHIVO_ZIP = "C:\\Users\\zeped\\OneDrive\\Escritorio\\arcivo.zip";

                try {
                    File archivo = new File(NOMBRE_ARCHIVO);
                    if (!archivo.exists()) {
                        JOptionPane.showMessageDialog(frame, "El archivo no existe.");
                        return;
                    }

                    long tamanoTotal = archivo.length();
                    long tamanoProcesado = 0;

                    FileOutputStream fos = new FileOutputStream(NOMBRE_ARCHIVO_ZIP);
                    ZipOutputStream zos = new ZipOutputStream(fos);
                    FileInputStream fis = new FileInputStream(archivo);

                    ZipEntry ze = new ZipEntry(archivo.getName());
                    zos.putNextEntry(ze);

                    byte[] bytes = new byte[1024];
                    int longitud;

                    while ((longitud = fis.read(bytes)) >= 0) {
                        zos.write(bytes, 0, longitud);
                        tamanoProcesado += longitud;

                        // Calcula el porcentaje de progreso y actualiza la barra
                        int progreso = (int) ((tamanoProcesado * 100) / tamanoTotal);
                        SwingUtilities.invokeLater(() -> barraProgreso.setValue(progreso));
                    }

                    zos.close();
                    fis.close();
                    fos.close();

                    SwingUtilities.invokeLater(() -> {
                        barraProgreso.setValue(100); // Asegúrate de que la barra esté completa
                        JOptionPane.showMessageDialog(frame, "Compresión finalizada.");
                    });

                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage());
                }
            });

            hiloCompresion.start();
        });

        // Configuración final de la ventana
        frame.add(panel);
        frame.setVisible(true);
    }
}