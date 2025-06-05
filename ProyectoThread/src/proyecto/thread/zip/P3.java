package proyecto.thread.zip;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class P3 {

    private JFrame frame;
    private List<JCheckBox> checkBoxes;
    private List<JProgressBar> progressBars;
    private JButton botonSeleccionar;
    private JButton botonComprimir;
    private File[] archivosSeleccionados;
    private ExecutorService executor;

    public void CompresionArchivos() {
        // Inicializar el executor con 4 hilos (uno por archivo)
        executor = Executors.newFixedThreadPool(4);
        
        // Configurar la ventana principal
        frame = new JFrame("Compresión de Archivos");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        
        // Inicializar listas para componentes
        checkBoxes = new ArrayList<>();
        progressBars = new ArrayList<>();
        archivosSeleccionados = new File[4];
        
        // Crear panel principal
        JPanel panel = new JPanel(new BorderLayout());
        
        // Panel para selección de archivos
        JPanel panelArchivos = new JPanel(new GridLayout(4, 3, 5, 5));
        
        // Crear componentes para cada archivo (4 archivos)
        for (int i = 0; i < 4; i++) {
            // Checkbox para seleccionar archivo
            JCheckBox checkBox = new JCheckBox("Archivo " + (i+1));
            checkBox.setEnabled(false);
            checkBoxes.add(checkBox);
            
            // Botón para seleccionar archivo
            JButton botonSeleccionarArchivo = new JButton("Seleccionar");
            final int index = i;
            botonSeleccionarArchivo.addActionListener(e -> seleccionarArchivo(index));
            
            // Barra de progreso
            JProgressBar progressBar = new JProgressBar(0, 100);
            progressBar.setStringPainted(true);
            progressBars.add(progressBar);
            
            // Añadir componentes al panel
            panelArchivos.add(checkBox);
            panelArchivos.add(botonSeleccionarArchivo);
            panelArchivos.add(progressBar);
        }
        
        // Panel para botones principales
        JPanel panelBotones = new JPanel(new FlowLayout());
        botonComprimir = new JButton("Comprimir Seleccionados");
        botonComprimir.setEnabled(false);
        botonComprimir.addActionListener(e -> comprimirArchivos());
        
        botonSeleccionar = new JButton("Seleccionar Todos");
        botonSeleccionar.addActionListener(e -> seleccionarTodos());
        
        panelBotones.add(botonSeleccionar);
        panelBotones.add(botonComprimir);
        
        // Añadir paneles al panel principal
        panel.add(panelArchivos, BorderLayout.CENTER);
        panel.add(panelBotones, BorderLayout.SOUTH);
        
        frame.add(panel);
        frame.setVisible(true);
    }
    
    private void seleccionarArchivo(int index) {
        JFileChooser fileChooser = new JFileChooser();
        int resultado = fileChooser.showOpenDialog(frame);
        
        if (resultado == JFileChooser.APPROVE_OPTION) {
            archivosSeleccionados[index] = fileChooser.getSelectedFile();
            checkBoxes.get(index).setText(archivosSeleccionados[index].getName());
            checkBoxes.get(index).setEnabled(true);
            checkBoxes.get(index).setSelected(true);
            verificarBotonComprimir();
        }
    }
    
    private void seleccionarTodos() {
        boolean todosSeleccionados = true;
        for (JCheckBox checkBox : checkBoxes) {
            if (checkBox.isEnabled() && !checkBox.isSelected()) {
                checkBox.setSelected(true);
            }
            if (!checkBox.isEnabled()) {
                todosSeleccionados = false;
            }
        }
        
        if (!todosSeleccionados) {
            JOptionPane.showMessageDialog(frame, "No todos los archivos han sido seleccionados aún.");
        }
    }
    
    private void verificarBotonComprimir() {
        boolean algunoSeleccionado = false;
        for (JCheckBox checkBox : checkBoxes) {
            if (checkBox.isEnabled() && checkBox.isSelected()) {
                algunoSeleccionado = true;
                break;
            }
        }
        botonComprimir.setEnabled(algunoSeleccionado);
    }
    
    private void comprimirArchivos() {
        for (int i = 0; i < 4; i++) {
            if (checkBoxes.get(i).isSelected() && checkBoxes.get(i).isEnabled()) {
                final int index = i;
                executor.execute(() -> {
                    try {
                        comprimirArchivo(index);
                    } catch (IOException ex) {
                        SwingUtilities.invokeLater(() -> {
                            JOptionPane.showMessageDialog(frame, 
                                "Error al comprimir " + archivosSeleccionados[index].getName() + 
                                ": " + ex.getMessage());
                        });
                    }
                });
            }
        }
    }
    
    private void comprimirArchivo(int index) throws IOException {
        File archivo = archivosSeleccionados[index];
        String nombreZip = archivo.getAbsolutePath().replaceFirst("[.][^.]+$", "") + ".zip";
        
        if (!archivo.exists()) {
            SwingUtilities.invokeLater(() -> {
                JOptionPane.showMessageDialog(frame, "El archivo " + archivo.getName() + " no existe.");
            });
            return;
        }
        
        long tamanoTotal = archivo.length();
        long tamanoProcesado = 0;
        
        FileOutputStream fos = new FileOutputStream(nombreZip);
        ZipOutputStream zos = new ZipOutputStream(fos);
        FileInputStream fis = new FileInputStream(archivo);
        
        ZipEntry ze = new ZipEntry(archivo.getName());
        zos.putNextEntry(ze);
        
        byte[] bytes = new byte[1024];
        int longitud;
        
        while ((longitud = fis.read(bytes)) >= 0) {
            zos.write(bytes, 0, longitud);
            tamanoProcesado += longitud;
            
            // Actualizar progreso
            final int progreso = (int) ((tamanoProcesado * 100) / tamanoTotal);
            SwingUtilities.invokeLater(() -> {
                progressBars.get(index).setValue(progreso);
            });
        }
        
        zos.close();
        fis.close();
        fos.close();
        
        SwingUtilities.invokeLater(() -> {
            progressBars.get(index).setValue(100);
            JOptionPane.showMessageDialog(frame, 
                "Compresión finalizada para " + archivo.getName() + 
                "\nGuardado como: " + nombreZip);
        });
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CompresionArchivos());
    }
}