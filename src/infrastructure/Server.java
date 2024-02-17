package infrastructure;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Server {

    private List<ImageGallery> imageList;

    public Server(int port) {
        imageList = new ArrayList<>();

        try {
            // Crear un ServerSocket que escuche en el puerto especificado
            ServerSocket server = new ServerSocket(port);
            System.out.println("Servidor iniciado. Esperando conexiones...");
            boolean wait = true;

            while (wait) {
                // Esperar a que un cliente se conecte y aceptar la conexión
                Socket client = server.accept();
                System.out.println("Cliente conectado desde: " + client.getInetAddress());

                // Crear un hilo para manejar la conexión con el cliente
                Thread clientThread = new ClienteHandler(client);
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingresa el numero del puerto: ");
        int port = scanner.nextInt();
        new Server(port);
    }

    public class ClienteHandler extends Thread {
        private Socket clientSocket;

        public ClienteHandler(Socket socket) {
            this.clientSocket = socket;
        }

        @Override
        public void run() {
            try {
                // Crear flujo de entrada para recibir datos del cliente
                ObjectInputStream inputStream = new ObjectInputStream(clientSocket.getInputStream());

                // Recibir la imagen desde el cliente
                ImageGallery image = (ImageGallery) inputStream.readObject();

                // Obtener los bytes de la imagen
                byte[] imageBytes = image.getImageBytes();

                // Guardar los bytes de la imagen en un archivo en el servidor con el nombre del
                // archivo seleccionado
                for (int i = 0; i < imageList.size() + 1; i++) {

                    saveImageToFile(imageBytes, "file" + i + ".jpg");
                }

                // Agregar la imagen a la lista del servidor
                synchronized (imageList) {
                    imageList.add(image);
                }

                // Cerrar la conexión con el cliente
                clientSocket.close();
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
