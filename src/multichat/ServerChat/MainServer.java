
package multichat.ServerChat;

import java.io.IOException;
import java.net.Socket;
import java.net.ServerSocket;
import java.util.ArrayList;

public class MainServer {
    public static void main(String[] args){
        final int PORT = 5500;
        try{
            ServerSocket serverSocket = new ServerSocket(PORT);// Crea un ServerSocket per ascoltare le connessioni sulla porta specificata
            ArrayList<Thread> listaThreadConnessioni = new ArrayList<Thread>();// Lista per tenere traccia dei thread di connessione
            ListaClient listaClient = new ListaClient();
            System.out.println("Server Aperto");
            System.out.println("In attesa di connessioni..");
            // Ciclo infinito che accetta connessioni dai client
            while(true){
                Socket nuovoClient = serverSocket.accept();// Accetta una connessione in ingresso
                listaClient.addClient(nuovoClient);// Aggiunge il nuovo client alla lista dei client connessi
                // Crea un nuovo thread per gestire la connessione del nuovo client
                Thread connessioneThread = new Thread((Runnable) new ThreadConnessione(nuovoClient,listaClient));
                listaThreadConnessioni.add(connessioniThread);// Aggiunge il thread alla lista e lo avvia
                listaThreadConnessioni.get(listaThreadConnessioni.size()-1).start();
            }
        } catch (IOException e){
            // Gestione delle eccezioni in caso di errore di connessione
            System.out.println("Errore di connessione");
        }
    }
}
