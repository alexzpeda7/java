package software.pdi.ui;

import software.pdi.modelo.Imagen;
import software.pdi.operaciones.*;
import software.pdi.util.ArchivoUtils;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Stack;

public class VentanaPrincipal extends JFrame {
    
    private Imagen imagenActual;
    private JLabel visorImagen;
    private Stack<Imagen> pilaUndo = new Stack<>();
    private Stack<Imagen> pilaRedo = new Stack<>();
    
    // Operaciones
    private AjustesImagen ajustes;
    private FiltrosSuavizado suavizado;
    private FiltrosRealce realce;
    private FiltrosMorfologicos morfologicos;
    private FiltrosFrecuencia frecuencia;

    public VentanaPrincipal() {
        inicializarComponentes();
        inicializarOperaciones();
        configurarMenu();
        configurarVentana();
    }

    private void inicializarComponentes() {
        visorImagen = new JLabel("", JLabel.CENTER);
        add(new JScrollPane(visorImagen), BorderLayout.CENTER);
    }

    private void inicializarOperaciones() {
        ajustes = new AjustesImagen(this);
        suavizado = new FiltrosSuavizado(this);
        realce = new FiltrosRealce(this);
        morfologicos = new FiltrosMorfologicos(this);
        frecuencia = new FiltrosFrecuencia(this);
    }

    private void configurarVentana() {
        setTitle("Software PDI - Editor de Imágenes");
        setSize(950, 720);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void configurarMenu() {
        JMenuBar barra = new JMenuBar();
        
        barra.add(crearMenuArchivo());
        barra.add(crearMenuEdicion());
        barra.add(crearMenuImagen());
        barra.add(crearMenuFiltros());
        barra.add(crearMenuSalir());
        
        setJMenuBar(barra);
    }

    private JMenu crearMenuArchivo() {
        JMenu menu = new JMenu("Archivo");
        
        JMenuItem abrir = new JMenuItem("Abrir imagen");
        JMenuItem guardar = new JMenuItem("Guardar");
        JMenuItem guardarComo = new JMenuItem("Guardar como...");
        JMenu exportar = new JMenu("Exportar");
        JMenuItem cerrar = new JMenuItem("Cerrar");

        // Submenú exportar
        exportar.add(new JMenuItem("PNG"));
        exportar.add(new JMenuItem("JPG"));
        exportar.add(new JMenuItem("GIF"));
        exportar.add(new JMenuItem("TIFF"));

        menu.add(abrir);
        menu.add(guardar);
        menu.add(guardarComo);
        menu.addSeparator();
        menu.add(exportar);
        menu.addSeparator();
        menu.add(cerrar);

        // Acciones
        abrir.addActionListener(e -> cargarImagen());
        guardar.addActionListener(e -> almacenarImagen(false));
        guardarComo.addActionListener(e -> almacenarImagen(true));
        cerrar.addActionListener(e -> limpiarImagen());

        for (int i = 0; i < exportar.getItemCount(); i++) {
            JMenuItem item = (JMenuItem) exportar.getItem(i);
            String formato = item.getText().toLowerCase();
            item.addActionListener(e -> exportarImagen(formato));
        }

        return menu;
    }

    private JMenu crearMenuEdicion() {
        JMenu menu = new JMenu("Edición");
        
        JMenuItem deshacer = new JMenuItem("Deshacer");
        JMenuItem rehacer = new JMenuItem("Rehacer");
        JMenuItem escalar = new JMenuItem("Cambiar tamaño");
        JMenuItem girar = new JMenuItem("Rotar 90°");

        menu.add(deshacer);
        menu.add(rehacer);
        menu.addSeparator();
        menu.add(escalar);
        menu.add(girar);

        deshacer.addActionListener(e -> deshacerAccion());
        rehacer.addActionListener(e -> rehacerAccion());
        escalar.addActionListener(e -> redimensionar());
        girar.addActionListener(e -> rotarImagen());

        return menu;
    }


 private JMenu crearMenuImagen() {
     JMenu menu = new JMenu("Imagen");

     JMenuItem brillo = new JMenuItem("Ajustar brillo");
     JMenuItem contraste = new JMenuItem("Ajustar contraste");
     JMenuItem gris = new JMenuItem("Escala de grises");
     JMenuItem binario = new JMenuItem("Imagen binaria (umbralización)");
     JMenuItem negativo = new JMenuItem("Negativo de la imagen");
     JMenuItem histograma = new JMenuItem("Histograma (general y por canal)");

     JMenu conversionesMenu = new JMenu("Conversión de espacios color");
     
     JMenu rgbTo = new JMenu("RGB a...");
     rgbTo.add(new JMenuItem("HSI"));
     rgbTo.add(new JMenuItem("HSL"));
     rgbTo.add(new JMenuItem("HSV"));
     rgbTo.add(new JMenuItem("CIELab"));
     
     JMenu toRgb = new JMenu("... a RGB");
     toRgb.add(new JMenuItem("HSI a RGB"));
     toRgb.add(new JMenuItem("HSL a RGB"));
     toRgb.add(new JMenuItem("HSV a RGB"));
     toRgb.add(new JMenuItem("CIELab a RGB"));
     
     conversionesMenu.add(rgbTo);
     conversionesMenu.add(toRgb);
     // ====================================================

     menu.add(brillo);
     menu.add(contraste);
     menu.add(gris);
     menu.add(binario);
     menu.add(negativo);
     menu.add(histograma);
     menu.addSeparator();
     menu.add(conversionesMenu); // NUEVO: Agregar el submenú

     // Acciones existentes
     brillo.addActionListener(e -> ajustes.modificarBrillo());
     contraste.addActionListener(e -> ajustes.modificarContraste());
     gris.addActionListener(e -> ajustes.convertirAGris());
     binario.addActionListener(e -> ajustes.convertirBinario());
     negativo.addActionListener(e -> ajustes.aplicarNegativo());
     histograma.addActionListener(e -> ajustes.mostrarHistograma());
     
     // ===== NUEVO: Acciones para conversiones RGB a... =====
     for (int i = 0; i < rgbTo.getItemCount(); i++) {
         JMenuItem item = (JMenuItem) rgbTo.getItem(i);
         String texto = item.getText();
         item.addActionListener(e -> {
             if (texto.equals("HSI")) ajustes.convertirRGBaHSI();
             else if (texto.equals("HSL")) ajustes.convertirRGBaHSL();
             else if (texto.equals("HSV")) ajustes.convertirRGBaHSV();
             else if (texto.equals("CIELab")) ajustes.convertirRGBaLab();
         });
     }
     
     // ===== NUEVO: Acciones para conversiones ... a RGB =====
     for (int i = 0; i < toRgb.getItemCount(); i++) {
         JMenuItem item = (JMenuItem) toRgb.getItem(i);
         String texto = item.getText();
         item.addActionListener(e -> {
             if (texto.equals("HSI a RGB")) ajustes.convertirHSIaRGB();
             else if (texto.equals("HSL a RGB")) ajustes.convertirHSLaRGB();
             else if (texto.equals("HSV a RGB")) ajustes.convertirHSVaRGB();
             else if (texto.equals("CIELab a RGB")) ajustes.convertirLabaRGB();
         });
     }

     return menu;
 }

    private JMenu crearMenuFiltros() {
        JMenu menu = new JMenu("Filtros");

        // Suavizado
        JMenu suavizadoMenu = new JMenu("Suavizado");
        suavizadoMenu.add(new JMenuItem("Filtro de media"));
        suavizadoMenu.add(new JMenuItem("Filtro de mediana"));
        suavizadoMenu.add(new JMenuItem("Gaussiano"));

        // Realce
        JMenu realceMenu = new JMenu("Realce");
        realceMenu.add(new JMenuItem("Laplaciano"));
        realceMenu.add(new JMenuItem("Prewitt"));
        realceMenu.add(new JMenuItem("Roberts"));
        realceMenu.add(new JMenuItem("Sobel"));
        realceMenu.add(new JMenuItem("Canny"));

        // Morfología
        JMenu morfMenu = new JMenu("Morfología binaria");
        morfMenu.add(new JMenuItem("Erosión"));
        morfMenu.add(new JMenuItem("Dilatación"));
        morfMenu.add(new JMenuItem("Apertura"));
        morfMenu.add(new JMenuItem("Cierre"));
        morfMenu.add(new JMenuItem("Esqueletonización"));

        // Frecuencia
        JMenu freqMenu = new JMenu("Frecuencia");
        freqMenu.add(new JMenuItem("Pasa bajo"));
        freqMenu.add(new JMenuItem("Pasa alto"));
        freqMenu.add(new JMenuItem("Paso banda"));

        menu.add(suavizadoMenu);
        menu.add(realceMenu);
        menu.add(morfMenu);
        menu.add(freqMenu);

        // Acciones suavizado
        for (int i = 0; i < suavizadoMenu.getItemCount(); i++) {
            JMenuItem item = (JMenuItem) suavizadoMenu.getItem(i);
            String texto = item.getText();
            item.addActionListener(e -> {
                if (texto.equals("Filtro de media")) suavizado.filtroMedia();
                else if (texto.equals("Filtro de mediana")) suavizado.filtroMediana();
                else if (texto.equals("Gaussiano")) suavizado.filtroGaussiano();
            });
        }

        // Acciones realce
        for (int i = 0; i < realceMenu.getItemCount(); i++) {
            JMenuItem item = (JMenuItem) realceMenu.getItem(i);
            String texto = item.getText();
            item.addActionListener(e -> {
                if (texto.equals("Laplaciano")) realce.filtroLaplaciano();
                else if (texto.equals("Prewitt")) realce.filtroPrewitt();
                else if (texto.equals("Roberts")) realce.filtroRoberts();
                else if (texto.equals("Sobel")) realce.filtroSobel();
                else if (texto.equals("Canny")) realce.filtroCanny();
            });
        }

        // Acciones morfología
        for (int i = 0; i < morfMenu.getItemCount(); i++) {
            JMenuItem item = (JMenuItem) morfMenu.getItem(i);
            String texto = item.getText();
            item.addActionListener(e -> {
                if (texto.equals("Erosión")) morfologicos.aplicarErosion();
                else if (texto.equals("Dilatación")) morfologicos.aplicarDilatacion();
                else if (texto.equals("Apertura")) morfologicos.realizarApertura();
                else if (texto.equals("Cierre")) morfologicos.realizarCierre();
                else if (texto.equals("Esqueletonización")) morfologicos.ejecutarEsqueletonizacion();
            });
        }

        // Acciones frecuencia
        for (int i = 0; i < freqMenu.getItemCount(); i++) {
            JMenuItem item = (JMenuItem) freqMenu.getItem(i);
            String texto = item.getText();
            item.addActionListener(e -> {
                if (texto.equals("Pasa bajo")) frecuencia.filtroPasaBajo();
                else if (texto.equals("Pasa alto")) frecuencia.filtroPasaAlto();
                else if (texto.equals("Paso banda")) frecuencia.filtroPasoBanda();
            });
        }

        return menu;
    }

    private JMenu crearMenuSalir() {
        JMenu menu = new JMenu("Salir");
        JMenuItem salir = new JMenuItem("Salir del programa");
        menu.add(salir);
        salir.addActionListener(e -> System.exit(0));
        return menu;
    }

    // Métodos de gestión de imagen
    public void cargarImagen() {
        File archivo = ArchivoUtils.seleccionarArchivo(this, "Abrir imagen", 
            new String[]{"jpg", "jpeg", "png", "gif", "tiff"});
        
        if (archivo != null) {
            try {
                imagenActual = new Imagen(archivo);
                if (imagenActual.getBufferedImage() != null) {
                    visorImagen.setIcon(new ImageIcon(imagenActual.getBufferedImage()));
                    setTitle("Software PDI - " + archivo.getName());
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error al intentar abrir la imagen.");
            }
        }
    }

    public void almacenarImagen(boolean guardarComo) {
        if (imagenActual == null) {
            JOptionPane.showMessageDialog(this, "No hay ninguna imagen cargada.");
            return;
        }

        File archivo = imagenActual.getArchivo();
        if (archivo == null || guardarComo) {
            archivo = ArchivoUtils.seleccionarArchivoGuardar(this, "Guardar imagen", "png");
        }

        if (archivo != null) {
            if (ArchivoUtils.guardarImagen(imagenActual.getBufferedImage(), archivo, "png")) {
                imagenActual.setArchivo(archivo);
                JOptionPane.showMessageDialog(this, "Archivo guardado con éxito");
            }
        }
    }

    public void exportarImagen(String formato) {
        if (imagenActual == null) {
            JOptionPane.showMessageDialog(this, "Debe cargar una imagen primero.");
            return;
        }

        File archivo = ArchivoUtils.seleccionarArchivoGuardar(this, 
            "Exportar como " + formato.toUpperCase(), formato);
        
        if (archivo != null) {
            if (ArchivoUtils.guardarImagen(imagenActual.getBufferedImage(), archivo, formato)) {
                JOptionPane.showMessageDialog(this, 
                    "Imagen exportada correctamente como " + formato.toUpperCase());
            }
        }
    }

    public void limpiarImagen() {
        imagenActual = null;
        visorImagen.setIcon(null);
        setTitle("Software PDI - Editor de Imágenes");
        JOptionPane.showMessageDialog(this, "La imagen se ha cerrado correctamente.");
    }

    // Métodos de gestión de estado
    public void respaldarEstado() {
        if (imagenActual != null) {
            pilaUndo.push(imagenActual.clonar());
            pilaRedo.clear();
        }
    }

    public void deshacerAccion() {
        if (!pilaUndo.isEmpty()) {
            pilaRedo.push(imagenActual);
            imagenActual = pilaUndo.pop();
            actualizarVista();
        } else {
            JOptionPane.showMessageDialog(this, "No hay más acciones para deshacer.");
        }
    }

    public void rehacerAccion() {
        if (!pilaRedo.isEmpty()) {
            pilaUndo.push(imagenActual);
            imagenActual = pilaRedo.pop();
            actualizarVista();
        } else {
            JOptionPane.showMessageDialog(this, "No hay cambios para rehacer.");
        }
    }

    public void redimensionar() {
        if (imagenActual == null) {
            JOptionPane.showMessageDialog(this, "Debe abrir una imagen primero.");
            return;
        }

        try {
            String anchoStr = JOptionPane.showInputDialog(this, "Nuevo ancho:", 
                imagenActual.getAncho());
            String altoStr = JOptionPane.showInputDialog(this, "Nuevo alto:", 
                imagenActual.getAlto());
            
            if (anchoStr == null || altoStr == null) return;

            int ancho = Integer.parseInt(anchoStr);
            int alto = Integer.parseInt(altoStr);

            respaldarEstado();
            imagenActual.redimensionar(ancho, alto);
            actualizarVista();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Los valores ingresados no son válidos.");
        }
    }

    public void rotarImagen() {
        if (imagenActual == null) {
            JOptionPane.showMessageDialog(this, "Debe abrir una imagen primero.");
            return;
        }
        respaldarEstado();
        imagenActual.rotar();
        actualizarVista();
    }

    public void actualizarVista() {
        if (imagenActual != null) {
            visorImagen.setIcon(new ImageIcon(imagenActual.getBufferedImage()));
        }
    }

    // Getters
    public Imagen getImagenActual() { return imagenActual; }
    public void setImagenActual(Imagen imagen) { this.imagenActual = imagen; }
}