package presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import infrastructure.Client;
import infrastructure.ImageGallery;
import view.Form;
import view.GalleryApp;

public class GalleryPresenter implements ActionListener {

    private GalleryApp view;
    private Form formView;
    private Client client;

    public GalleryPresenter() {
        this.formView = new Form(this);
        this.view = new GalleryApp(this);
        this.client = new Client(0, null, null);
    }

    private void loadImage() {
        // Llamar al método de la vista para mostrar el JFileChooser
        File selectedFile = view.showFileChooser();

        if (selectedFile != null) {
            // Llamar al método del cliente para enviar la imagen
            ImageGallery image = client.loadImage(selectedFile);
        }
    }

    public static void main(String[] args) {

        GalleryPresenter presenter = new GalleryPresenter();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Actions action = Actions.valueOf(e.getActionCommand());
        switch (action) {
            case OK_BUTTON:
                System.out.println("ok button ");
                String[] data = formView.rescueData();
                this.client = new Client(Integer.parseInt(data[0]), data[1], data[2]);
                System.out.println("datos entregados");
                formView.setGalleryAppVisibility(true);
                formView.setFormVisible(false);

                break;
            case LOAD_BUTTON:
                System.out.println("carga----------------");
                this.loadImage();
                break;
            case VIEW_GALLERY:
                System.out.println("Ver--------------");

                break;
        }

    }
}
