package view;

import java.awt.Color;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import infrastructure.ImageGallery;

public class GalleryClient extends JPanel {

    public GalleryClient() {
        setBackground(Color.RED);
        setVisible(false);
    }

    public void displayImages(List<ImageGallery> imageList) {
        removeAll(); // Elimina cualquier imagen anteriormente mostrada

        for (ImageGallery image : imageList) {
            try {
                // Convierte los bytes de la imagen a un ImageIcon
                ImageIcon imageIcon = new ImageIcon(image.getImageBytes());

                // Crea un JLabel para mostrar la imagen
                JLabel imageLabel = new JLabel(imageIcon);

                // Agrega el JLabel al panel GalleryClient
                add(imageLabel);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        revalidate(); // Actualiza el panel
        repaint(); // Vuelve a pintar el panel
    }

    public void setFrameVisible(boolean visible) {
        this.setVisible(visible);
    }

}