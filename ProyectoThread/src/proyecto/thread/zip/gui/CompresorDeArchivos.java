package proyecto.thread.zip.gui;

import javax.swing.*;
import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class CompresorDeArchivos {

    public void comprimirArchivo(File archivo, JProgressBar barraProgreso, JFrame frame) {
        new Thread(() -> {
            String nombreArchivoZip = archivo.getParent() + File.separator + archivo.getName() + ".zip";

            try {
                if (!archivo.exists()) {
                    JOptionPane.showMessageDialog(frame, "El archivo no existe.");
                    return;
                }

                long tamanoTotal = archivo.length();
                long tamanoProcesado = 0;

                FileOutputStream fos = new FileOutputStream(nombreArchivoZip);
                ZipOutputStream zos = new ZipOutputStream(fos);
                FileInputStream fis = new FileInputStream(archivo);

                ZipEntry ze = new ZipEntry(archivo.getName());
                zos.putNextEntry(ze);

                byte[] bytes = new byte[1024];
                int longitud;

                while ((longitud = fis.read(bytes)) >= 0) {
                    zos.write(bytes, 0, longitud);
                    tamanoProcesado += longitud;

                    int progreso = (int) ((tamanoProcesado * 100) / tamanoTotal);
                    SwingUtilities.invokeLater(() -> barraProgreso.setValue(progreso));
                }

                zos.close();
                fis.close();
                fos.close();

                SwingUtilities.invokeLater(() -> {
                    barraProgreso.setValue(100);
                    JOptionPane.showMessageDialog(frame, "Compresión del archivo " + archivo.getName() + " finalizada.");
                });

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(frame, "Error al comprimir el archivo " + archivo.getName() + ": " + ex.getMessage());
            }
        }).start();
    }
}