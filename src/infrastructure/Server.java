package infrastructure;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Server {

    private List<ImageGallery> imageList;

    public Server(int port) {
        imageList = new ArrayList<>();
        System.out.println("Server started at " + LocalDateTime.now());
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
                Thread clientThread = new ClientHandler(client);
                clientThread.start();
            }
        } catch (IOException e) {
            System.out.println("Error " + e.getMessage() + " in Server at " + LocalDateTime.now());
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingresa el numero del puerto: ");
        int port = scanner.nextInt();
        new Server(port);
    }

    public class ClientHandler extends Thread {
        private Socket clientSocket;

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        @Override
        public void run() {
            try {
                // Crear flujo de entrada para recibir datos del cliente
                ObjectInputStream inputStream = new ObjectInputStream(clientSocket.getInputStream());

                processImage(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void processImage(ObjectInputStream inputStream) {
            // Recibir la imagen desde el cliente
            ImageGallery image;
            try {
                image = (ImageGallery) inputStream.readObject();

                // Obtener los bytes de la imagen
                byte[] imageBytes = image.getImageBytes();

                // Generar un nombre único para la imagen usando un timestamp
                String fileName = "imagen_" + System.currentTimeMillis() + ".jpg";

                // Guardar los bytes de la imagen en un archivo en el servidor
                saveImageToFile(imageBytes, fileName);

                // Agregar la imagen a la lista del servidor
                synchronized (imageList) {
                    imageList.add(image);
                }

                System.out.println("Imagen guardada como: " + fileName);
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

        private void sendImageListToClient(ObjectOutputStream outputStream) {
            try {
                outputStream.writeObject(imageList);
                System.out.println("Lista de imágenes enviada al cliente.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
