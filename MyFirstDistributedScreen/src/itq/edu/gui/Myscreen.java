package itq.edu.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Myscreen {

    private JFrame frame;
    private JTextField textField;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Myscreen window = new Myscreen();
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
    public Myscreen() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout(0, 0));
        
        JLabel lblNewLabel = new JLabel("New label");
        frame.getContentPane().add(lblNewLabel, BorderLayout.WEST);
        
        textField = new JTextField();
        frame.getContentPane().add(textField, BorderLayout.CENTER);
        textField.setColumns(10);
        
        JButton btnNewButton = new JButton("New button");
        frame.getContentPane().add(btnNewButton, BorderLayout.SOUTH);
        
        JLabel lblNewLabel_1 = new JLabel("New label");
        frame.getContentPane().add(lblNewLabel_1, BorderLayout.NORTH);
    }

}
