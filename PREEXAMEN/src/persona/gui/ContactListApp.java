package persona.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContactListApp extends JFrame {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private JTextField nombreField;
    private JTextField domicilioField;
    private JTextField telefonoField;
    private DefaultListModel<String> listModel;
    private JList<String> contactList;
    private JButton addButton;

    public ContactListApp() {
        setTitle("Contact List App");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        nombreField = new JTextField(15);
        domicilioField = new JTextField(15);
        telefonoField = new JTextField(15);

        listModel = new DefaultListModel<>();
        contactList = new JList<>(listModel);

        addButton = new JButton("Agregar");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = nombreField.getText();
                String domicilio = domicilioField.getText();
                String telefono = telefonoField.getText();
                String contact = nombre + " - " + domicilio + " - " + telefono;
                listModel.addElement(contact);
                nombreField.setText("");
                domicilioField.setText("");
                telefonoField.setText("");
            }
        });

        // Crear layout usando GroupLayout
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(nombreField)
                                .addComponent(domicilioField)
                                .addComponent(telefonoField)
                                .addComponent(addButton))
                        .addComponent(new JScrollPane(contactList))
        );

        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(nombreField)
                                .addComponent(domicilioField)
                                .addComponent(telefonoField)
                                .addComponent(addButton))
                        .addComponent(new JScrollPane(contactList))
        );

        pack();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ContactListApp().setVisible(true);
            }
        });
    }
}
