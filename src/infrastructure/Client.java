package infrastructure;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

import javax.imageio.ImageIO;

public class Client {

    private Socket sc;

    public Client(int port, String ip, String clientName) {

        try {
            sc = new Socket(ip, port);
            System.out.println("Cliente conectado..." + clientName);
        } catch (IOException e) {
            System.err.println("Error al conectar con el servidor: " + e.getMessage());
        }
    }

    public List<ImageGallery> receiveImageList() {
        try {
            ObjectInputStream inputStream = new ObjectInputStream(sc.getInputStream());
            return (List<ImageGallery>) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void closeConnection() {
        try {
            sc.close();
            System.out.println("Conexión cerrada con el servidor.");
        } catch (IOException e) {
            System.err.println("Error al cerrar la conexión con el servidor: " + e.getMessage());
        }
    }

    public ImageGallery loadImage(File selectedFile) {

        if (sc == null) {
            System.err.println("El socket no está inicializado correctamente.");
        }

        try {
            // Lee la imagen desde el archivo
            BufferedImage bufferedImage = ImageIO.read(selectedFile);

            // Convierte la BufferedImage a un arreglo de bytes
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "jpg", byteArrayOutputStream);
            byte[] imageBytes = byteArrayOutputStream.toByteArray();

            // Crea una instancia de la clase ImageGallery y establece los bytes de la
            // imagen
            ImageGallery image = new ImageGallery();
            image.setImageBytes(imageBytes);

            // Enviar la imagen al servidor
            try (ObjectOutputStream outputStream = new ObjectOutputStream(sc.getOutputStream())) {
                outputStream.writeObject(image);
                System.out.println("Imagen enviada al servidor.");
            }

            return image;
        } catch (IOException e) {
            System.err.println("Error al cargar la imagen: " + e.getMessage());
            return null;
        }
    }
}
