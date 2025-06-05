package com.company.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import com.company.dao.ProductoDao;
import com.company.dto.Producto;

public class ProductosApp {

    private JFrame frame;
    private JTextField textId;
    private JTextField textNombre;
    private JTextField textPrecio;

    /** Capa de acceso a datos para productos. */
    private ProductoDao dao = new ProductoDao();
    private JTable table;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ProductosApp window = new ProductosApp();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public ProductosApp() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 400, 364);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        
        DefaultTableModel tableModel = new DefaultTableModel(
                new String[] {"ID", "Nombre", "Precio"}, 0);
        JScrollPane scrollPane_1 = new JScrollPane();
        
        JPanel panel_1 = new JPanel();
        GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
        groupLayout.setHorizontalGroup(
            groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                    .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                        .addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 252, GroupLayout.PREFERRED_SIZE)
                        .addGroup(groupLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(panel, GroupLayout.PREFERRED_SIZE, 294, GroupLayout.PREFERRED_SIZE))
                        .addGroup(groupLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 304, GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(692, Short.MAX_VALUE))
        );
        groupLayout.setVerticalGroup(
            groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                    .addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
                    .addGap(18)
                    .addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(18)
                    .addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(34, Short.MAX_VALUE))
        );
        
                JLabel lblNewLabel = new JLabel("id:");
        
                JLabel lblNewLabel_1 = new JLabel("nombre:");
        
                JLabel lblNewLabel_2 = new JLabel("precio:");
        
                textId = new JTextField();
                textId.setColumns(10);
        
                textNombre = new JTextField();
                textNombre.setColumns(10);
        
                textPrecio = new JTextField();
                textPrecio.setColumns(10);
        GroupLayout gl_panel_1 = new GroupLayout(panel_1);
        gl_panel_1.setHorizontalGroup(
            gl_panel_1.createParallelGroup(Alignment.TRAILING)
                .addGroup(gl_panel_1.createSequentialGroup()
                    .addGap(48)
                    .addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
                        .addComponent(lblNewLabel)
                        .addComponent(lblNewLabel_1)
                        .addComponent(lblNewLabel_2))
                    .addGap(18)
                    .addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
                        .addComponent(textId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
                            .addGroup(gl_panel_1.createSequentialGroup()
                                .addComponent(textNombre, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.RELATED))
                            .addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
                                .addComponent(textPrecio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(50))))
                    .addGap(10))
        );
        gl_panel_1.setVerticalGroup(
            gl_panel_1.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_1.createSequentialGroup()
                    .addGap(24)
                    .addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
                        .addComponent(textId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblNewLabel))
                    .addGap(18)
                    .addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
                        .addComponent(textNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblNewLabel_1))
                    .addGap(18)
                    .addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
                        .addComponent(textPrecio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblNewLabel_2))
                    .addContainerGap(13, Short.MAX_VALUE))
        );
        panel_1.setLayout(gl_panel_1);
        table = new JTable(tableModel);
        scrollPane_1.setViewportView(table);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = textId.getText();
                String nombre = textNombre.getText();
                Double precio = Double.parseDouble(textPrecio.getText());
                Producto producto = new Producto(id, nombre, precio);
                dao.create(producto);
                tableModel.addRow(new Object[] { producto.getId(), producto.getNombre(), producto.getPrecio() });
            } // WRAPPER => envolvente
            // double => Double
        });
        panel.add(btnGuardar);

        JButton btnBorrar = new JButton("Borrar");
        panel.add(btnBorrar);

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.addActionListener(e ->{
            String nombreABuscar = JOptionPane.showInputDialog("Nombre a buscar:");
            dao.read(nombreABuscar);
        });
        panel.add(btnBuscar);
        frame.getContentPane().setLayout(groupLayout);
    }
}
