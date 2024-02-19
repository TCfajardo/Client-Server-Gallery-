package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import presenter.Actions;

public class GalleryApp extends JFrame {
    private JButton loadButton;
    private JButton showButton;
    private JPanel galleryPanel, southJPanel, northPanel;

    public GalleryApp(ActionListener actionListener) {
        super("Galeria de Imagenes");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Evitar que se cierre directamente
        setSize(600, 700);
        setLocation(500, 100);
        setLayout(new BorderLayout());

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
        showButton = new JButton("Ver galeria");
        showButton.setBackground(Color.decode("#A4EDFE"));
        showButton.setActionCommand(Actions.VIEW_GALLERY.toString());
        showButton.addActionListener(actionListener);

        // Crear el panel de la galería
        galleryPanel = new JPanel();
        galleryPanel.setBackground(Color.WHITE);
        galleryPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 5));
        JLabel message = new JLabel("¿Quieres cargar una imagen o ver la galeria?");
        galleryPanel.add(message);
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
        JOptionPane.showMessageDialog(this, "Mostrando galería...");
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

    public void setFrameVisible(boolean visible) {
        setVisible(visible);
    }

}
