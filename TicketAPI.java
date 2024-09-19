import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

public class TicketAPI {

    private static final String SERVER_URL = "http://localhost:3000";

    public static void nextTicket(int boxNumber) {
        try {
            // Usamos URI para construir la URL
            URI uri = new URI(SERVER_URL + "/next");
            URL url = uri.toURL(); // Convertimos URI a URL

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST"); // Cambiado a POST para esta ruta

            // Enviar el número de box como parte del cuerpo
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/json");
            String jsonInputString = "{\"box\": " + boxNumber + "}";
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String responseLine;
            while ((responseLine = in.readLine()) != null) {
                System.out.println(responseLine);
            }
            in.close();
            conn.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setTicket(int ticketNumber, int boxNumber) {
        try {
            // Usamos URI para construir la URL
            URI uri = new URI(SERVER_URL + "/set-ticket");
            URL url = uri.toURL(); // Convertimos URI a URL

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST"); // Cambiado a POST para esta ruta

            // Enviar el ticket y el número de box como parte del cuerpo
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/json");
            String jsonInputString = "{\"ticket\": " + ticketNumber + ", \"box\": " + boxNumber + "}";
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String responseLine;
            while ((responseLine = in.readLine()) != null) {
                System.out.println(responseLine);
            }
            in.close();
            conn.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getCurrentTicket() {
        StringBuilder response = new StringBuilder();
        try {
            // Usamos URI para construir la URL
            URI uri = new URI(SERVER_URL + "/ticket");
            URL url = uri.toURL(); // Convertimos URI a URL

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String responseLine;
            while ((responseLine = in.readLine()) != null) {
                response.append(responseLine);
            }
            in.close();
            conn.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return response.toString();
    }
}
