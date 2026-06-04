package software.pdi.util;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ArchivoUtils {
    
    public static File seleccionarArchivo(JFrame padre, String titulo, String[] extensiones) {
        JFileChooser selector = new JFileChooser();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter(
            "Imágenes (" + String.join(", ", extensiones) + ")", extensiones);
        selector.setFileFilter(filtro);
        selector.setDialogTitle(titulo);
        selector.setCurrentDirectory(new File(System.getProperty("user.home"), "Documents"));
        
        if (selector.showOpenDialog(padre) == JFileChooser.APPROVE_OPTION) {
            return selector.getSelectedFile();
        }
        return null;
    }
    
    public static File seleccionarArchivoGuardar(JFrame padre, String titulo, String extension) {
        JFileChooser selector = new JFileChooser();
        selector.setDialogTitle(titulo);
        selector.setCurrentDirectory(new File(System.getProperty("user.home"), "Documents"));
        selector.setSelectedFile(new File("imagen." + extension));
        
        if (selector.showSaveDialog(padre) == JFileChooser.APPROVE_OPTION) {
            File archivo = selector.getSelectedFile();
            // Asegurar la extensión
            String ruta = archivo.getAbsolutePath();
            if (!ruta.toLowerCase().endsWith("." + extension.toLowerCase())) {
                archivo = new File(ruta + "." + extension);
            }
            return archivo;
        }
        return null;
    }
    
    public static boolean guardarImagen(BufferedImage imagen, File archivo, String formato) {
        try {
            return ImageIO.write(imagen, formato, archivo);
        } catch (IOException e) {
            return false;
        }
    }
    
    public static BufferedImage cargarImagen(File archivo) {
        try {
            return ImageIO.read(archivo);
        } catch (IOException e) {
            return null;
        }
    }
}