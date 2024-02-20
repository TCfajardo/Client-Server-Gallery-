package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import infrastructure.ImageGallery;
import presenter.Actions;

public class GalleryApp extends JFrame {
    private JButton loadButton;
    private JButton showButton;
    private JPanel galleryPanel, southJPanel, northPanel;
    private GalleryClient galleryClient;

    public GalleryApp(ActionListener actionListener) {
        super("Galeria de Imagenes");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Evitar que se cierre directamente
        setSize(500, 200);
        setLocation(500, 100);
        setLayout(new BorderLayout());

        this.galleryClient = new GalleryClient();

        northPanel = new JPanel();
        northPanel.setBackground(Color.BLACK);
        northPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 5));
        JLabel tittleApp = new JLabel("¡BIENVENIDO!");
        tittleApp.setForeground(Color.WHITE);
        northPanel.add(tittleApp);
        this.add(northPanel, BorderLayout.NORTH);

        // Crear los botones
        loadButton = new JButton("Cargar imagen");
        loadButton.setBackground(Color.decode("#A4EDFE"));
        loadButton.setActionCommand(Actions.LOAD_BUTTON.toString());
        loadButton.addActionListener(actionListener);
        showButton = new JButton("Descargar galeria");
        showButton.setBackground(Color.decode("#A4EDFE"));
        showButton.setActionCommand(Actions.VIEW_GALLERY.toString());
        showButton.addActionListener(actionListener);

        // Crear el panel de la galería
        galleryPanel = new JPanel();
        galleryPanel.setBackground(Color.WHITE);
        galleryPanel.setLayout(new BorderLayout());
        JLabel message = new JLabel("¿Quieres cargar una imagen o descargar la galeria?");
        //galleryPanel.add(message, BorderLayout.NORTH);
        galleryPanel.add(galleryClient, BorderLayout.CENTER);

        galleryPanel.setVisible(true);
        this.add(galleryPanel, BorderLayout.CENTER);

        southJPanel = new JPanel();
        southJPanel.setBackground(Color.BLACK);
        // Agregar los botones al panel superior
        southJPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        southJPanel.add(loadButton);
        southJPanel.add(showButton);
        this.add(southJPanel, BorderLayout.SOUTH);

        // Configurar la acción del botón "Cargar"
        loadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // mostrarFileChooser();
            }
        });

        // Configurar la acción del botón "Ver"
        showButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showGallery();
            }
        });

        // Agregar un WindowListener para detectar el cierre del frame
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                closeApplication();
            }
        });
    }

    public void showGallery() {
        // mostrar to do
        JOptionPane.showMessageDialog(this, "Descargar galería...");
        downloadClientImagesFolder();
    }

    private void closeApplication() {
        int option = JOptionPane.showConfirmDialog(this, "¿Estás seguro que quieres salir?", "Confirmar salida",
                JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            System.exit(0);

        }
    }

    public File showFileChooser() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Seleccionar Imagen");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setFileFilter(new FileNameExtensionFilter("Archivos de Imagen", "jpg"));

        int returnValue = fileChooser.showOpenDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            JOptionPane.showMessageDialog(this, "Archivo seleccionado: " + selectedFile.getAbsolutePath());
            return selectedFile;
        }
        return null;
    }
    
    public void downloadClientImagesFolder() {
        // Ruta de la carpeta clientImgs en el directorio del proyecto
        String clientImgsPath = "src/clientImgs";

        // Ruta de la carpeta Descargas del sistema operativo del cliente
        String downloadsPath = System.getProperty("user.home") + File.separator + "Downloads";

        try {
            // Copiar la carpeta clientImgs en la carpeta Descargas
            copyFolder(new File(clientImgsPath), new File(downloadsPath));
            System.out.println("Carpeta clientImgs descargada en la carpeta Descargas.");
        } catch (IOException e) {
            System.err.println("Error al descargar la carpeta clientImgs: " + e.getMessage());
        }
    }

    private void copyFolder(File source, File destination) throws IOException {
        // Verificar si la carpeta de destino existe, si no, crearla
        if (!destination.exists()) {
            destination.mkdirs();
        }

        // Obtener la lista de archivos y subdirectorios en la carpeta de origen
        File[] files = source.listFiles();
        if (files != null) {
            for (File file : files) {
                File destinationFile = new File(destination, file.getName());
                if (file.isDirectory()) {
                    // Si es un directorio, llamar recursivamente a copyFolder
                    copyFolder(file, destinationFile);
                } else {
                    // Si es un archivo, copiarlo al destino
                    Files.copy(file.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                }
            }
        }
    }

    public void setFrameVisible(boolean visible) {
        setVisible(visible);
    }

    public void setFrameVisiblePanelCenter(boolean visible) {
        galleryClient.setFrameVisible(visible);
    }


}
