package examen.itq;

import javax.swing.*;
import java.awt.event.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class EXAMEN {
    public static void main(String[] args) {

        JFrame frame = new JFrame("Interfaz Gráfica");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 800);  
        JLabel nombreLabel = new JLabel("Nombre:");
        JComboBox<String> nombreComboBox = new JComboBox<>(new String[] {"Opción 1", "Opción 2", "Opción 3"});
        nombreComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"ZEPEDA QUINTANAR"}));
        JLabel apellidoLabel = new JLabel("Apellido:");
        JTextField apellidoTextField = new JTextField(20);
        JTable table = new JTable(5, 2);
        
        GroupLayout layout = new GroupLayout(frame.getContentPane());
        layout.setHorizontalGroup(
            layout.createParallelGroup(Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(Alignment.LEADING)
                                .addComponent(nombreLabel)
                                .addComponent(apellidoLabel))
                            .addGroup(layout.createParallelGroup(Alignment.LEADING)
                                .addComponent(nombreComboBox, 0, 186, Short.MAX_VALUE)
                                .addGroup(layout.createSequentialGroup()
                                    .addPreferredGap(ComponentPlacement.RELATED)
                                    .addComponent(apellidoTextField, GroupLayout.PREFERRED_SIZE, 196, GroupLayout.PREFERRED_SIZE))))
                        .addComponent(table, GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE))
                    .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(nombreLabel)
                        .addComponent(apellidoTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(apellidoLabel)
                        .addComponent(nombreComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(table, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
        );
        frame.getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
        nombreComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                apellidoTextField.setText("BRIAN ALEJANDRO ZEPEDA QUINTANAR");

     
                int selectedIndex = nombreComboBox.getSelectedIndex();
                for (int i = 0; i < table.getRowCount(); i++) {
                    table.setValueAt("ZEPEDA", i, 0);
                    table.setValueAt("QUINTANAR", i, 1);
                }

                System.out.println("Seleccionado: " + (String) nombreComboBox.getSelectedItem());
            }
        });
 
        frame.pack();
        frame.setVisible(true);
    }
}

