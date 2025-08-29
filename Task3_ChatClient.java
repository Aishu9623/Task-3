import java.io.*;
import java.net.*;

public class Task3_ChatClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 1234);
        new Thread(new ReadHandler(socket)).start();
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        // Subtask 2: Client to send & receive messages
        String input;
        while ((input = console.readLine()) != null) {
            out.println(input);
        }
    }

    private static class ReadHandler implements Runnable {
        private BufferedReader in;
        public ReadHandler(Socket socket) throws IOException {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }
        public void run() {
            try {
                String response;
                while ((response = in.readLine()) != null) {
                    System.out.println(response);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
