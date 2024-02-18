package infrastructure;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.imageio.ImageIO;

public class Client {

    private Socket sc;

    public Client(int port, String ip, String clientName) {
        final String host = ip;
        final int portConnection = port;

        try {
            sc = new Socket(host, portConnection);
            System.out.println("Cliente conectado..." + clientName);
        } catch (IOException e) {
            System.err.println("Error al conectar con el servidor: " + e.getMessage());
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
