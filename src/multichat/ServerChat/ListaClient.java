package multichat.ServerChat;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ListaClient {
    private ArrayList<Socket> listaSockets;// Lista per mantenere traccia di tutti i client connessi al server
    // Costruttore che inizializza la lista dei socket
    public ListaClient(){
        listaSockets = new ArrayList<Socket>();
    }
    // Aggiunge un client alla lista dei client connessi
    public synchronized void addClient(Socket c) throws IOException {
        listaSockets.add(c);// Aggiunge il socket del client alla lista
    }
    // Rimuove un client dalla lista dei client connessi
    public synchronized void removeClient(int i) throws IOException {
        listaSockets.get(i).close();// Chiude la connessione del client
        listaSockets.remove(i);// Rimuove il client dalla lista
    }
    // Invia un messaggio a tutti i client connessi, tranne al client che ha inviato il messaggio
    public synchronized void sendAll(String message,Socket client) throws IOException {
        for(Socket socket: listaSockets) {
            if(socket!=client) {
                // Non inviare il messaggio al client che lo ha inviato
                PrintWriter out = new PrintWriter(socket.getOutputStream());// Ottiene il flusso di output per il socket
                out.println(message);// Scrive il messaggio nel flusso di output
                out.flush();// Assicura che il messaggio venga effettivamente inviato
            }
        }
    }
}
