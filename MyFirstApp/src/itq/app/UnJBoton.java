package itq.app;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class UnJBoton {

    public static void main(String args[]) {

        Frame f = new Frame();

        JButton b = new JButton("Pulsame");
        f.add(b);

        f.pack();
        f.setVisible(true);

        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("HOLA");
            }
        });
    }
}
