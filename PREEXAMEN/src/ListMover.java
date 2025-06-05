import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListMover extends JFrame {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private DefaultListModel<String> listModel1;
    private DefaultListModel<String> listModel2;
    private DefaultListModel<String> listModel3;
    private JList<String> list1;
    private JList<String> list2;
    private JList<String> list3;
    private JButton moveToSecondListButton;
    private JButton moveToThirdListButton;

    public ListMover() {
        setTitle("List Mover");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(1, 3));

        listModel1 = new DefaultListModel<>();
        listModel1.addElement("ESPAÑA");
        listModel1.addElement("MEXICO");
        listModel1.addElement("USA");

        listModel2 = new DefaultListModel<>();
        listModel3 = new DefaultListModel<>();
        listModel3.addElement("CROACIA");

        list1 = new JList<>(listModel1);
        list2 = new JList<>(listModel2);
        list3 = new JList<>(listModel3);

        moveToSecondListButton = new JButton(">>");
        moveToSecondListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveSelectedItems(list1, listModel2);
            }
        });

        moveToThirdListButton = new JButton(">>");
        moveToThirdListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveSelectedItems(list2, listModel3);
            }
        });

        add(new JScrollPane(list1));
        add(moveToSecondListButton);
        add(new JScrollPane(list2));
        add(moveToThirdListButton);
        add(new JScrollPane(list3));
    }

    private void moveSelectedItems(JList<String> sourceList, DefaultListModel<String> targetListModel) {
        for (String selectedValue : sourceList.getSelectedValuesList()) {
            targetListModel.addElement(selectedValue);
        }
        for (String selectedValue : sourceList.getSelectedValuesList()) {
            ((DefaultListModel<String>) sourceList.getModel()).removeElement(selectedValue);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ListMover().setVisible(true);
            }
        });
    }
}
