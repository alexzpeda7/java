package edu.itq.gui;
import javax.swing.*;
import java.awt.*;

public class FormularioEstudianteGrid {
    public static void main(String[] args) {
        // Crear el marco principal
        JFrame frame = new JFrame("Formulario de Estudiante");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 250);

        // Panel principal con un Layout
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 10, 10)); // GridLayout con 4 filas y 2 columnas

        // Crear etiquetas y campos de texto
        JLabel lblNumeroControl = new JLabel("Número de Control:");
        JTextField txtNumeroControl = new JTextField();
        
        JLabel lblNombre = new JLabel("Nombre:");
        JTextField txtNombre = new JTextField();
        
        JLabel lblApellido = new JLabel("Apellido:");
        JTextField txtApellido = new JTextField();
        
        // Agregar los componentes al panel
        panel.add(lblNumeroControl);
        panel.add(txtNumeroControl);
        
        panel.add(lblNombre);
        panel.add(txtNombre);
        
        panel.add(lblApellido);
        panel.add(txtApellido);
        
        // Agregar el panel al marco
        frame.add(panel);
        
        // Hacer visible el marco
        frame.setVisible(true);
    }
}
