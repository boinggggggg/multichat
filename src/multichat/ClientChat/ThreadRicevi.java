
package multichat.ClientChat;
import java.io.IOException;
import java.io.BufferedReader;
import java.net.Socket;
import java.io.InputStreamReader;

public class ThreadRicevi implements Runnable {
    private Socket socket;
    BufferedReader in;
     // Costruttore che inizializza il socket e il BufferedReader
    public ThreadRicevi(Socket socket) throws IOException{
        this.socket = socket;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }
    // Metodo che viene eseguito nel thread per ricevere i messaggi dal server
    @Override
    public void run() {
    String messaggio;
    try {
        messaggio = in.readLine();
        while (messaggio != null) {
            System.out.println(messaggio);
            messaggio = in.readLine();
        }
        System.out.println("Server Chiuso");
    } catch (IOException e) {
        System.out.println("Errore di connessione");
    } finally {
        try {
            in.close(); // Chiude il BufferedReader
            socket.close(); // Chiude il socket
        } catch (IOException e) {
            System.out.println("Errore nel chiudere le risorse");
        }
    }
}
}
