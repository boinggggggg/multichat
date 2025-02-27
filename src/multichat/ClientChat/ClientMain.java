package multichat.ClientChat;
import java.io.IOException;
import java.net.Socket;

public class ClientMain {
    public static void main(String[] args){
        Socket clientSocket;// Dichiarazione del socket per la connessione al server
        try{
            // Creazione di un socket e connessione al server locale sulla porta 5500
            clientSocket = new Socket("127.0.0.1",5500);
            // Creazione e avvio del thread per inviare i messaggi
            Thread invioThread = new Thread(new ThreadInvio(clientSocket));
            // Creazione e avvio del thread per ricevere i messaggi
            Thread riceviThread = new Thread(new ThreadRicevi(clientSocket));
            invioThread.start();
            riceviThread.start();
        } catch (IOException e) {
            // Gestione delle eccezioni se la connessione al server fallisce
            System.out.println("Impossibile connettersi con il server");
        }
    }
}
