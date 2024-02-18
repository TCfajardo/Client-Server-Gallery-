package infrastructure;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Server {

    private List<ImageGallery> imageList;

    public Server() {
        final int PUERTO = 5000;
        imageList = new ArrayList<>();
		System.out.println("Server started at " + LocalDateTime.now());
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
			System.out.println("Error " + e.getMessage() + " in Server at " + LocalDateTime.now());
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

				processImage(inputStream);
                
                // Cerrar la conexión con el cliente
                clienteSocket.close();
				System.out.println("Client disconnected...");
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

			// Obtener el nombre del archivo enviado por el cliente
			String fileName = image.getFileName();

			// Guardar los bytes de la imagen en un archivo en el servidor con el nombre del
			// archivo seleccionado
			for (int i = 0; i < imageList.size() + 1; i++) {

				saveImageToFile(imageBytes, "archivo" + i + ".jpg");
			}
			// Agregar la imagen a la lista del servidor
			synchronized (imageList) {
				imageList.add(image);
			}
			} catch (ClassNotFoundException | IOException e) {
			System.out.println("Error, no found file"+ e.getMessage());
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
