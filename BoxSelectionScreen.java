import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BoxSelectionScreen extends JFrame {
    private JTextField boxField;
    private JButton confirmButton;

    public BoxSelectionScreen() {
        setTitle("Selección de Box");
        setSize(300, 150);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel boxLabel = new JLabel("Ingrese su número de Box:");
        boxLabel.setBounds(50, 20, 200, 25);
        add(boxLabel);

        boxField = new JTextField();
        boxField.setBounds(50, 50, 200, 25);
        add(boxField);

        confirmButton = new JButton("Confirmar");
        confirmButton.setBounds(100, 80, 100, 25);
        add(confirmButton);

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int boxNumber = Integer.parseInt(boxField.getText());
                dispose(); // Cierra la ventana de selección de box
                new TurneroApp(boxNumber); // Pasa el número de box al TurneroApp
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new BoxSelectionScreen();
    }
}
