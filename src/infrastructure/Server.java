package infrastructure;
import java.io.*;
import java.net.*;

public class Server {

    public Server (){
        final int PUERTO = 5000;
        
        try {
            // Crear un ServerSocket que escuche en el puerto especificado
            ServerSocket servidor = new ServerSocket(PUERTO);
            System.out.println("Servidor iniciado. Esperando conexiones...");
            boolean wait =true;
            
            while (wait) {
                // Esperar a que un cliente se conecte y aceptar la conexión
                Socket cliente = servidor.accept();
                System.out.println("Cliente conectado desde: " + cliente.getInetAddress());

                // Crear un hilo para manejar la conexión con el cliente
                Thread clienteThread = new ClienteHandler(cliente);
                clienteThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Server();
    }

    public class ClienteHandler extends Thread {
        private Socket clienteSocket;
    
        public ClienteHandler(Socket socket) {
            this.clienteSocket = socket;
        }
    
        @Override
        public void run() {
            try {
                // Aquí puedes implementar la lógica para recibir/enviar datos
                // Por ejemplo, recibir una imagen del cliente y guardarla en el servidor
    
                // Cerrar la conexión con el cliente
                clienteSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}