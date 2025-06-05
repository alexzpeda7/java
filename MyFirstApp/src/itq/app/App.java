package itq.app;

import java.awt.Color;
import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class App {

    public static void main(String[] args) {
       
        JPanel panelRojo = new JPanel();
        panelRojo.setBackground(Color.RED);
        panelRojo.setSize(300,300);
        
        JFrame ventana = new JFrame("Prueba en Rojo");
        ventana.setLocation(100,100);
        ventana.setSize(300,300);
        ventana.setVisible(true);
        
        Container contentPane=ventana.getContentPane();
        contentPane.add(panelRojo);
        
    }

}
