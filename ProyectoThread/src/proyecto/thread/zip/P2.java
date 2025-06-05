package proyecto.thread.zip;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class P2 {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Compresión de Archivos");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        JPanel panel = new JPanel(new BorderLayout());
        JButton botonComprimir = new JButton("Comprimir Archivos");
        JPanel panelProgreso = new JPanel();
        panelProgreso.setLayout(new BoxLayout(panelProgreso, BoxLayout.Y_AXIS));

        panel.add(botonComprimir, BorderLayout.NORTH);
        panel.add(panelProgreso, BorderLayout.CENTER);

        botonComprimir.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setMultiSelectionEnabled(true);
            int opcion = fileChooser.showOpenDialog(frame);

            if (opcion == JFileChooser.APPROVE_OPTION) {
                File[] archivosSeleccionados = fileChooser.getSelectedFiles();

                for (File archivo : archivosSeleccionados) {
                    JProgressBar barraProgreso = new JProgressBar(0, 100);
                    barraProgreso.setStringPainted(true);
                    panelProgreso.add(barraProgreso);

                    panelProgreso.revalidate();
                    panelProgreso.repaint();

                    Thread hiloCompresion = new Thread(() -> {
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
                    });

                    hiloCompresion.start();
                }
            }
        });

        frame.add(panel);
        frame.setVisible(true);
    }
}