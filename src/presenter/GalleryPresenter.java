package presenter;

import java.io.File;

import infrastructure.Client;
import infrastructure.ImageGallery;
import view.GalleryApp;

public class GalleryPresenter {

    private GalleryApp view;
    private Client client;

    public GalleryPresenter(GalleryApp view, Client client) {
        this.view = view;
        this.client = client;

        // Configurar la acción del botón "Cargar" en la vista
        view.setCargarButtonListener(e -> cargarImagen());
    }

    private void cargarImagen() {
        // Llamar al método de la vista para mostrar el JFileChooser
        File selectedFile = view.mostrarFileChooser();

        if (selectedFile != null) {
            // Llamar al método del cliente para enviar la imagen
            ImageGallery image = client.loadImage(selectedFile);
            // Aquí puedes implementar la lógica para mostrar la imagen en la galería
            view.verGaleria();
        }
    }

    public static void main(String[] args) {
        // Crear instancia del cliente y la vista
        Client client = new Client();
        GalleryApp view = new GalleryApp();

        // Crear instancia del controlador y pasar la vista y el cliente
        GalleryPresenter presenter = new GalleryPresenter(view, client);
    }
}
