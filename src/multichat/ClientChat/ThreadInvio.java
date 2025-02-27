package multichat.ClientChat;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ThreadInvio implements Runnable {
    private Scanner sc;// Scanner per leggere l'input dell'utente
    private PrintWriter out;// PrintWriter per inviare i messaggi al server
    // Costruttore che riceve un socket, che verrà utilizzato per inviare messaggi al server
    public ThreadInvio(Socket socket){
        // Inizializzazione dello scanner per leggere dall'input standard (console)
        sc = new Scanner(System.in);
        // Inizializzazione di PrintWriter per inviare dati attraverso il socket
        out = new PrintWriter(socket.getOutputStream());
    }
    // Metodo che viene eseguito nel thread
    @Override
    public void run(){
        String message;
        boolean primo = true;// Variabile per gestire il flusso di messaggi (richiesta nome utente all'inizio)
        // Ciclo che continua finché il thread non viene interrotto
        while(!Thread.interrupted()){
            if(primo){
                System.out.println("Dammi il nome utente");// Chiede all'utente di inserire il nome
            }
            message = sc.nextLine();
            out.println(message);
            out.flush();// Assicura che il messaggio venga effettivamente inviato immediatamente
            if(primo) {
                System.out.println("Utente acquisito, scrivi messaggio");// Mostra un messaggio quando l'utente è stato acquisito
                primo=false;// Cambia stato per evitare di richiedere nuovamente il nome utente
            }
        }
    }
}
