
package multichat.ServerChat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ThreadConnessione implements Runnable{
    private Socket client;
    private BufferedReader in;
    private ListaClient listaClient;
    private String nomeClient;
    // Costruttore che inizializza la connessione con il client e la lista di client
    public ThreadConnessione(Socket client, ListaClient listaClient) throws IOException {
        this.client = client;// Assegna il socket del client
        this.listaClient = listaClient;// Assegna la lista di client
        in = new BufferedReader(new InputStreamReader(client.getInputStream()));// Inizializza il BufferedReader per leggere i dati dal client
        nomeClient = "errore";// Valore iniziale per il nome del client
    }
    
    // Metodo che gestisce la lettura dei messaggi inviati dal client e la loro distribuzione
    public void run() {
    String messaggio;
    boolean primo = true;
    try {
         // Ciclo che continua finché il thread non viene interrotto
        while (!Thread.interrupted()) {
            messaggio = in.readLine();// Legge il messaggio inviato dal client
            if (primo) {
                // Il primo messaggio è il nome del client
                nomeClient = messaggio;
                System.out.println(nomeClient + " connesso.");
                primo = false;// Imposta 'primo' a false per non ripetere questa parte
            } else {
                // Invia il messaggio ricevuto da questo client a tutti gli altri client
                listaClient.sendAll(nomeClient + ": " + messaggio, client);
            }
        }
    } catch (IOException e) {
        // Gestione delle eccezioni in caso di errore nella lettura o connessione interrotta
        System.out.println("Connessione interrotta con " + nomeClient);
    } finally {
        try {
            in.close(); // Chiude il BufferedReader
            client.close(); // Chiude il socket
        } catch (IOException e) {
            System.out.println("Errore nel chiudere le risorse per " + nomeClient);
        }
    }
}
}
