package software.pdi.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class HistogramaFrame extends JFrame {
    
    public HistogramaFrame(BufferedImage imagenHistograma, JFrame padre) {
        setTitle("Histograma de color");
        setLayout(new BorderLayout());
        
        JLabel visorHistograma = new JLabel(new ImageIcon(imagenHistograma));
        add(new JScrollPane(visorHistograma), BorderLayout.CENTER);
        
        // Panel de información
        JPanel panelInfo = new JPanel();
        panelInfo.setLayout(new FlowLayout());
        
        // Leyenda de colores
        panelInfo.add(crearLeyenda("Rojo", Color.RED));
        panelInfo.add(crearLeyenda("Verde", Color.GREEN));
        panelInfo.add(crearLeyenda("Azul", Color.BLUE));
        
        add(panelInfo, BorderLayout.SOUTH);
        
        setSize(imagenHistograma.getWidth() + 40, imagenHistograma.getHeight() + 100);
        setLocationRelativeTo(padre);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    
    private JPanel crearLeyenda(String texto, Color color) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        
        JLabel colorLabel = new JLabel("   ");
        colorLabel.setOpaque(true);
        colorLabel.setBackground(color);
        colorLabel.setPreferredSize(new Dimension(20, 20));
        
        JLabel textoLabel = new JLabel(texto);
        
        panel.add(colorLabel);
        panel.add(textoLabel);
        
        return panel;
    }
}