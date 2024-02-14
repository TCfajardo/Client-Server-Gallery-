package infrastructure;

import java.net.Socket;

public class Client {
    
    public Client() {
        final String host = "127.0.0.1";
        final int port = 5000;

        try {
            Socket sc = new Socket(host, port);
            System.out.println("Cliente conectado...");

            sc.close();
            
            System.out.println("Cliente desconectado");
            
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public static void main(String[] args) {
        new Client();
    }
}
