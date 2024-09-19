import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.json.JSONObject;
import org.json.JSONException;

public class TurneroApp extends JFrame {
    private JLabel ticketLabel;
    private JLabel boxLabel;
    private JButton nextButton;
    private JButton modifyButton;
    private JButton showDisplayButton;
    private JButton backButton;
    private JTextField ticketField;

    private int boxNumber; // Número de box asociado al operador

    public TurneroApp(int boxNumber) {
        this.boxNumber = boxNumber; // Asignar el valor del boxNumber

        setTitle("Turnero - Operador Box " + boxNumber);  // Mostrar el número de box en el título de la ventana
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Etiqueta para mostrar el número de ticket actual
        ticketLabel = new JLabel("Ticket Actual: ");
        ticketLabel.setBounds(50, 50, 300, 30);
        add(ticketLabel);

        // Etiqueta para mostrar el número de box
        boxLabel = new JLabel("Box: " + boxNumber);
        boxLabel.setBounds(50, 100, 300, 30);
        add(boxLabel);

        // Campo para modificar el número de ticket manualmente
        ticketField = new JTextField();
        ticketField.setBounds(50, 150, 200, 30);
        add(ticketField);

        // Botón para modificar el número de ticket
        modifyButton = new JButton("Modificar Ticket");
        modifyButton.setBounds(260, 150, 100, 30);
        add(modifyButton);

        modifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int ticket = Integer.parseInt(ticketField.getText());
                TicketAPI.setTicket(ticket, boxNumber);  // Usar boxNumber al modificar el ticket
                updateTicketDisplay();
            }
        });

        // Botón para avanzar al siguiente ticket
        nextButton = new JButton("Siguiente Paciente");
        nextButton.setBounds(100, 200, 150, 30);
        add(nextButton);

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TicketAPI.nextTicket(boxNumber);  // Usar boxNumber al avanzar el ticket
                updateTicketDisplay();
            }
        });

        // Botón para volver al menú anterior
        backButton = new JButton("Volver");
        backButton.setBounds(20, 250, 100, 30);
        add(backButton);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();  // Cierra la ventana actual
                new BoxSelectionScreen();  // Vuelve a la pantalla de selección de box
            }
        });

        // Botón para abrir la pantalla de visualización
        showDisplayButton = new JButton("Abrir Pantalla de Visualización");
        showDisplayButton.setBounds(150, 250, 200, 30);
        add(showDisplayButton);

        showDisplayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DisplayScreen();  // Abre la pantalla de visualización
            }
        });

        // Mostrar el ticket actual en la interfaz
        updateTicketDisplay();
        setVisible(true);
    }

    // Método para actualizar la etiqueta con el número de ticket actual
    private void updateTicketDisplay() {
        String response = TicketAPI.getCurrentTicket();  // Obtener el ticket actual
        try {
            // Parsear el JSON y extraer los valores de ticket y box
            JSONObject json = new JSONObject(response);
            int ticketNumber = json.getInt("ticket");
            int boxNumber = json.getInt("box");
    
            // Actualizar la etiqueta con el número de ticket y box
            ticketLabel.setText("Ticket Actual: " + ticketNumber);
            boxLabel.setText("Box: " + boxNumber);
        } catch (JSONException e) {
            e.printStackTrace();
            ticketLabel.setText("Error al obtener el ticket.");
        }
    }
    

    public static void main(String[] args) {
        new BoxSelectionScreen();
    }
}
