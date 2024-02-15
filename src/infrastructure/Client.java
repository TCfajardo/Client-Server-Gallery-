package infrastructure;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.imageio.ImageIO;

public class Client {

    File pathString;
    Socket sc;

    public Client() {
        final String host = "127.0.0.1";
        final int port = 5000;

        try {
            sc = new Socket(host, port);
            System.out.println("Cliente conectado...");

            // Aquí deberías cargar la imagen desde algún lugar antes de enviarla
            ImageGallery image = loadImage(pathString);

            // Crear flujo de salida para enviar datos al servidor
            ObjectOutputStream outputStream = new ObjectOutputStream(sc.getOutputStream());

            // Enviar la imagen al servidor
            outputStream.writeObject(image);

            sc.close();

            System.out.println("Cliente desconectado");

        } catch (Exception e) {
            System.err.println(e);
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

            // Crea una instancia de la clase Image con los bytes de la imagen
            ImageGallery image = new ImageGallery();
            image.setImageBytes(imageBytes);

            // Enviar la imagen al servidor
            try (ObjectOutputStream outputStream = new ObjectOutputStream(sc.getOutputStream())) {
                outputStream.writeObject(image);
            }

            return image;
        } catch (IOException e) {
            e.printStackTrace();
            return null; // Manejo de errores: Devuelve null o implementa una lógica adecuada.
        }
    }

}
