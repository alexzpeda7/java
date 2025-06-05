package app.itq.estudainte;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Optional;

public class EstudianteApp extends JFrame {
    private EstudianteServicio servicio = new EstudianteServicio();
    private JTextField nombreField, domicilioField, telefonoField, idField;
    private DefaultTableModel tableModel;

    public EstudianteApp() {
        setTitle("Gestión de Estudiantes");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(5, 2));
        panel.add(new JLabel("ID:"));
        idField = new JTextField();
        panel.add(idField);

        panel.add(new JLabel("Nombre:"));
        nombreField = new JTextField();
        panel.add(nombreField);

        panel.add(new JLabel("Domicilio:"));
        domicilioField = new JTextField();
        panel.add(domicilioField);

        panel.add(new JLabel("Teléfono:"));
        telefonoField = new JTextField();
        panel.add(telefonoField);

        JButton addButton = new JButton("Agregar");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarEstudiante();
            }
        });
        panel.add(addButton);

        JButton updateButton = new JButton("Actualizar");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarEstudiante();
            }
        });
        panel.add(updateButton);

        JButton deleteButton = new JButton("Eliminar");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarEstudiante();
            }
        });
        panel.add(deleteButton);

        JButton findButton = new JButton("Buscar");
        findButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarEstudiante();
            }
        });
        panel.add(findButton);

        getContentPane().add(panel, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(new Object[]{"ID", "Nombre", "Domicilio", "Teléfono"}, 0);
        JTable table = new JTable(tableModel);
        getContentPane().add(new JScrollPane(table), BorderLayout.CENTER);

        actualizarTabla();
    }

    private void agregarEstudiante() {
        String nombre = nombreField.getText();
        String domicilio = domicilioField.getText();
        String telefono = telefonoField.getText();
        servicio.agregarEstudiante(new Estudiante(0, nombre, domicilio, telefono));
        actualizarTabla();
        limpiarCampos();
    }

    private void actualizarEstudiante() {
        int id = Integer.parseInt(idField.getText());
        String nombre = nombreField.getText();
        String domicilio = domicilioField.getText();
        String telefono = telefonoField.getText();
        servicio.actualizarEstudiante(new Estudiante(id, nombre, domicilio, telefono));
        actualizarTabla();
        limpiarCampos();
    }

    private void eliminarEstudiante() {
        int id = Integer.parseInt(idField.getText());
        servicio.eliminarEstudiante(id);
        actualizarTabla();
        limpiarCampos();
    }

    private void buscarEstudiante() {
        int id = Integer.parseInt(idField.getText());
        Optional<Estudiante> estudiante = servicio.buscarEstudiante(id);
        if (estudiante.isPresent()) {
            nombreField.setText(estudiante.get().getNombre());
            domicilioField.setText(estudiante.get().getDomicilio());
            telefonoField.setText(estudiante.get().getTelefono());
        } else {
            JOptionPane.showMessageDialog(this, "Estudiante no encontrado");
        }
    }

    private void actualizarTabla() {
        tableModel.setRowCount(0);
        List<Estudiante> estudiantes = servicio.obtenerTodos();
        for (Estudiante estudiante : estudiantes) {
            tableModel.addRow(new Object[]{estudiante.getId(), estudiante.getNombre(), estudiante.getDomicilio(), estudiante.getTelefono()});
        }
    }

    private void limpiarCampos() {
        idField.setText("");
        nombreField.setText("");
        domicilioField.setText("");
        telefonoField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new EstudianteApp().setVisible(true);
            }
        });
    }
}
