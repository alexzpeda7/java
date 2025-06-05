package proyecto.thread.zip.gui;
import java.awt.BorderLayout;
import java.io.File;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class CompresorGUI extends CompresorDeArchivos{
    public static void main(String[] args) {
        JFrame frame = new JFrame("Compresor de Archivos");
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

                    CompresorDeArchivos compresor = new CompresorDeArchivos();
                    compresor.comprimirArchivo(archivo, barraProgreso, frame);
                }
            }
        });

        frame.add(panel);
        frame.setVisible(true);
    }
}