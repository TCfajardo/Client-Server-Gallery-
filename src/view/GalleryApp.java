package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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
                cargarImagenes();
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

    private void cargarImagenes() {
        // Aquí puedes implementar la lógica para cargar las imágenes desde algún directorio o recurso
        JOptionPane.showMessageDialog(this, "Cargando imagenes...");
    }

    private void verGaleria() {
        // Aquí puedes implementar la lógica para mostrar las imágenes cargadas en la galería
        JOptionPane.showMessageDialog(this, "Mostrando galeria...");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GalleryApp();
            }
        });
    }
}
