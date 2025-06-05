package edu.itq.gui;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JOptionPane;

import edu.itq.dao.EstudianteDao;
import edu.itq.dto.Estudiante;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;

public class PantallaEstudiantes {

    private JFrame frame;
    private JTextField textNombre;
    private JTextField textApellido;
    private JTextField textNoControl;
    private JTable table;
    private DefaultTableModel model;

    private EstudianteDao dao = new EstudianteDao();

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                PantallaEstudiantes window = new PantallaEstudiantes();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public PantallaEstudiantes() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JLabel lblNoControl = new JLabel("Número de control:");
        
        textNoControl = new JTextField();
        textNoControl.setColumns(10);

        JLabel lblNombre = new JLabel("Nombre:");
        
        textNombre = new JTextField();
        textNombre.setColumns(10);
        
        JLabel lblApellido = new JLabel("Apellidos:");
        
        textApellido = new JTextField();
        textApellido.setColumns(20);

        JScrollPane scrollPane = new JScrollPane();
        
        table = new JTable();
        model = new DefaultTableModel(
            new Object[][] {},
            new String[] {"No. Control", "Nombre", "Apellidos"}
        );
        table.setModel(model);
        scrollPane.setViewportView(table);

        JButton btnAlta = new JButton("Alta");
        btnAlta.addActionListener(e -> altaEstudiante());
        
        JButton btnBaja = new JButton("Baja");
        btnBaja.addActionListener(e -> bajaEstudiante());
        
        JButton btnCambio = new JButton("Cambio");
        btnCambio.addActionListener(e -> cambioEstudiante());

        JButton btnConsultar = new JButton("Consultar");
        btnConsultar.addActionListener(e -> consultaEstudiantes());

        GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
        groupLayout.setHorizontalGroup(
            groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                    .addGap(30)
                    .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                        .addComponent(lblNoControl)
                        .addComponent(lblNombre)
                        .addComponent(lblApellido))
                    .addGap(18)
                    .addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
                        .addComponent(textNoControl)
                        .addComponent(textNombre)
                        .addComponent(textApellido))
                    .addGap(18)
                    .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                        .addComponent(btnAlta)
                        .addComponent(btnBaja)
                        .addComponent(btnCambio)
                        .addComponent(btnConsultar))
                    .addContainerGap(25, Short.MAX_VALUE))
                .addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE)
        );
        groupLayout.setVerticalGroup(
            groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                    .addGap(30)
                    .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblNoControl)
                        .addComponent(textNoControl, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnAlta))
                    .addGap(18)
                    .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblNombre)
                        .addComponent(textNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnBaja))
                    .addGap(18)
                    .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblApellido)
                        .addComponent(textApellido, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnCambio))
                    .addGap(18)
                    .addComponent(btnConsultar)
                    .addPreferredGap(ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                    .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))
        );
        frame.getContentPane().setLayout(groupLayout);

        consultaEstudiantes();
    }

    private void altaEstudiante() {
        String noControl = textNoControl.getText();
        String nombre = textNombre.getText();
        String apellidos = textApellido.getText();
        Estudiante estudiante = new Estudiante(noControl, nombre, apellidos);
        dao.insertarEstudiante(estudiante);
        consultaEstudiantes();
        JOptionPane.showMessageDialog(frame, "Estudiante dado de alta.");
    }

    private void bajaEstudiante() {
        int row = table.getSelectedRow();
        if (row != -1) {
            String noControl = (String) model.getValueAt(row, 0);
            dao.eliminarEstudiante(noControl);
            consultaEstudiantes();
            JOptionPane.showMessageDialog(frame, "Estudiante dado de baja.");
        } else {
            JOptionPane.showMessageDialog(frame, "Selecciona un estudiante para dar de baja.");
        }
    }

    private void cambioEstudiante() {
        int row = table.getSelectedRow();
        if (row != -1) {
            String noControl = (String) model.getValueAt(row, 0);
            String nombre = textNombre.getText();
            String apellidos = textApellido.getText();
            Estudiante estudiante = new Estudiante(noControl, nombre, apellidos);
            dao.actualizarEstudiante(estudiante);
            consultaEstudiantes();
            JOptionPane.showMessageDialog(frame, "Estudiante actualizado.");
        } else {
            JOptionPane.showMessageDialog(frame, "Selecciona un estudiante para actualizar.");
        }
    }

    private void consultaEstudiantes() {
        model.setRowCount(0);
        List<Estudiante> estudiantes = dao.consultarEstudiantes();
        for (Estudiante estudiante : estudiantes) {
            model.addRow(new Object[] { estudiante.getNoControl(), estudiante.getNombre(), estudiante.getApellidos() });
        }
    }
}
