package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;

public class GalleryApp extends JFrame {
    private JButton cargarButton;
    private JButton verButton;
    private JPanel galleryPanel;

    public GalleryApp() {
        super("Galeria de Imagenes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 800);

        // Crear los botones
        cargarButton = new JButton("Cargar imagen");
        verButton = new JButton("Ver galeria");

        // Crear el panel de la galería
        galleryPanel = new JPanel();
        galleryPanel.setBackground(Color.WHITE);

        // Configurar el layout del frame
        setLayout(new BorderLayout());

        // Agregar los botones al panel superior
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(cargarButton);
        buttonPanel.add(verButton);
        add(buttonPanel, BorderLayout.NORTH);

        // Agregar el panel de la galería al centro del frame
        add(galleryPanel, BorderLayout.CENTER);

        // Configurar la acción del botón "Cargar"
        cargarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cargarImagen();
            }
        });

        // Configurar la acción del botón "Ver"
        verButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                verGaleria();
            }
        });

        setVisible(true);
    }

    private void cargarImagen() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Seleccionar Imagen");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setFileFilter(new FileNameExtensionFilter("Archivos de Imagen", "jpg"));

        int returnValue = fileChooser.showOpenDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            JOptionPane.showMessageDialog(this, "Archivo seleccionado: " + selectedFile.getAbsolutePath());
            // Aquí puedes implementar la lógica para cargar la imagen seleccionada
        }
    }

    public void verGaleria() {
        // Aquí puedes implementar la lógica para mostrar las imágenes cargadas en la
        // galería
        JOptionPane.showMessageDialog(this, "Mostrando galería...");
    }

    public File mostrarFileChooser() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Seleccionar Imagen");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setFileFilter(new FileNameExtensionFilter("Archivos de Imagen", "jpg"));

        int returnValue = fileChooser.showOpenDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            JOptionPane.showMessageDialog(this, "Archivo seleccionado: " + selectedFile.getAbsolutePath());
            // Aquí puedes implementar la lógica para cargar la imagen seleccionada
            return selectedFile;
        }
        return null;
    }

    public void setCargarButtonListener(ActionListener listener) {
        cargarButton.addActionListener(listener);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GalleryApp();
            }
        });
    }
}