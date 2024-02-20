package view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;


public class GalleryClient extends JPanel {

	public GalleryClient() {
        setBackground(Color.decode("#5BF8C6"));
        
        // Establecer el layout como GridBagLayout
        setLayout(new GridBagLayout());
        
        // Crear el JLabel
        JLabel jLabel = new JLabel("Galería descargada en la carpeta /Descargas.");
        
        // Crear un GridBagConstraints para centrar el JLabel
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0; // Estirar en el eje X
        gbc.weighty = 1.0; // Estirar en el eje Y
        gbc.anchor = GridBagConstraints.CENTER; // Centrar en el contenedor
        add(jLabel, gbc);
    }


    public void setFrameVisible(boolean visible) {
        this.setVisible(visible);
    }

}