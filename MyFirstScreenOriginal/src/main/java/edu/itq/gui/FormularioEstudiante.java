package edu.itq.gui;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;

public class FormularioEstudiante {
    public static void main(String[] args) {
        // Crear el marco principal
        JFrame frame = new JFrame("Formulario de Estudiante");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);

        // Crear panel
        JPanel panel = new JPanel();
        
        // Crear las etiquetas y campos de texto
        JLabel lblNumeroControl = new JLabel("Número de Control:");
        JTextField txtNumeroControl = new JTextField(20);
        
        JLabel lblNombre = new JLabel("Nombre:");
        JTextField txtNombre = new JTextField(20);
        
        JLabel lblApellido = new JLabel("Apellido:");
        JTextField txtApellido = new JTextField(20);
        
        // Crear un GroupLayout para el panel
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);

        // Configurar el GroupLayout
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        // Definir los componentes horizontales y verticales
        layout.setHorizontalGroup(
            layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                    .addComponent(lblNumeroControl)
                    .addComponent(lblNombre)
                    .addComponent(lblApellido))
                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                    .addComponent(txtNumeroControl)
                    .addComponent(txtNombre)
                    .addComponent(txtApellido))
        );

        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                    .addComponent(lblNumeroControl)
                    .addComponent(txtNumeroControl))
                .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                    .addComponent(lblNombre)
                    .addComponent(txtNombre))
                .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                    .addComponent(lblApellido)
                    .addComponent(txtApellido))
        );

        // Agregar el panel al marco
        frame.add(panel);

        // Hacer visible el marco
        frame.setVisible(true);
    }
}
