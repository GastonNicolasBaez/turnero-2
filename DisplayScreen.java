import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;
import org.json.JSONObject;
import org.json.JSONException;

public class DisplayScreen extends JFrame {
    private JLabel ticketLabel;
    private JLabel boxLabel;

    public DisplayScreen() {
        setTitle("Pantalla de Visualización");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        ticketLabel = new JLabel("Ticket: ");
        ticketLabel.setBounds(50, 50, 300, 30);
        add(ticketLabel);

        boxLabel = new JLabel("Box: ");
        boxLabel.setBounds(50, 100, 300, 30);
        add(boxLabel);

        // Actualiza cada 3 segundos
        Timer timer = new Timer();
        // En el constructor de DisplayScreen
        timer.schedule(new TimerTask() {
           @Override
              public void run() {
                String response = TicketAPI.getCurrentTicket();
               try {
            // Parsear el JSON y extraer los valores de ticket y box
            JSONObject json = new JSONObject(response);
            int ticketNumber = json.getInt("ticket");
            int boxNumber = json.getInt("box");

            // Actualizar las etiquetas con el ticket y box
            ticketLabel.setText("Ticket Actual: " + ticketNumber);
            boxLabel.setText("Box: " + boxNumber);
        } catch (JSONException e) {
            e.printStackTrace();
            ticketLabel.setText("Error al obtener el ticket.");
        }
    }
}, 0, 3000);


        setVisible(true);
    }
}
