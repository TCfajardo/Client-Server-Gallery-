package infrastructure;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public Server() {
        final int PUERTO = 5000;

        try {
            // Crear un ServerSocket que escuche en el puerto especificado
            ServerSocket servidor = new ServerSocket(PUERTO);
            System.out.println("Servidor iniciado. Esperando conexiones...");
            boolean wait = true;

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
                // Crear flujo de entrada para recibir datos del cliente
                ObjectInputStream inputStream = new ObjectInputStream(clienteSocket.getInputStream());

                // Recibir la imagen desde el cliente
                Image image = (Image) inputStream.readObject();

                // Obtener los bytes de la imagen
                byte[] imageBytes = image.getImageBytes();

                // Guardar los bytes de la imagen en un archivo en el servidor
                saveImageToFile(imageBytes, "nombre_del_archivo.jpg"); // Puedes cambiar el nombre y la extensión del
                                                                       // archivo según tus necesidades

                // Cerrar la conexión con el cliente
                clienteSocket.close();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        private void saveImageToFile(byte[] imageBytes, String fileName) throws IOException {
            try (FileOutputStream fileOutputStream = new FileOutputStream(fileName)) {
                fileOutputStream.write(imageBytes);
                System.out.println("Imagen guardada como: " + fileName);
            }
        }
    }
}