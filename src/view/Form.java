package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import infrastructure.ImageGallery;
import presenter.Actions;

/**
 * La clase Form representa un formulario de inicio para la aplicación.
 * Permite al usuario ingresar la configuración inicial, como el número de
 * puerto,
 * la dirección IP y el nombre del cliente.
 * Esta clase extiende JFrame y se utiliza para mostrar la interfaz gráfica.
 */
public class Form extends JFrame {

    private JTextField portTextField; // Campo de texto para ingresar el número de puerto
    private JTextField IPTextField; // Campo de texto para ingresar la dirección IP
    private JTextField clientNameTextField; // Campo de texto para ingresar el nombre del cliente
    private JPanel southPanel, centerPanel, northPanel; // Paneles para organizar los componentes
    private GalleryApp galleryApp; // Instancia de la aplicación de la galería

    /**
     * Constructor de la clase Form.
     * Crea y configura la interfaz gráfica del formulario de inicio.
     * 
     * @param actionListener ActionListener para manejar eventos de los componentes.
     */
    public Form(ActionListener actionListener) {
        super("Formulario de inicio");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 300);
        setLocation(500, 100);
        galleryApp = new GalleryApp(actionListener); // Instancia de la aplicación de la galería

        // Configuración del diseño de la ventana
        setLayout(new BorderLayout());
        this.setBackground(Color.WHITE);

        // Configuración del panel norte
        northPanel = new JPanel();
        northPanel.setBackground(Color.BLACK);
        northPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 5));
        JLabel tittleApp = new JLabel("BIENVENIDO!");
        tittleApp.setForeground(Color.WHITE);
        northPanel.add(tittleApp);
        this.add(northPanel, BorderLayout.NORTH);

        // Configuración del panel centro
        GridLayout gridLayout = new GridLayout(3, 2);
        gridLayout.setVgap(50);
        gridLayout.setHgap(0);
        centerPanel = new JPanel();
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setLayout(gridLayout);

        // Creación de componentes del formulario
        JLabel portLabel = new JLabel("Número del puerto:");
        portTextField = new JTextField(10);
        portTextField.setMinimumSize(new Dimension(100, 50));

        JLabel ipLabel = new JLabel("Dirección IP:");
        IPTextField = new JTextField(15);
        IPTextField.setMinimumSize(new Dimension(100, 50));

        JLabel clientNameLabel = new JLabel("Nombre del Cliente:");
        clientNameTextField = new JTextField(20);
        clientNameTextField.setMinimumSize(new Dimension(100, 50));

        // Configuración del panel sur
        southPanel = new JPanel();
        southPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 5));
        southPanel.setBackground(Color.BLACK);
        JButton okButton = new JButton("Aceptar");
        okButton.setPreferredSize(new Dimension(100, 20));
        okButton.setBackground(Color.decode("#A3FED9"));
        southPanel.add(okButton);
        this.add(southPanel, BorderLayout.SOUTH);

        // Agregar componentes al panel centro
        centerPanel.add(portLabel, BorderLayout.NORTH);
        centerPanel.add(portTextField, BorderLayout.CENTER);
        centerPanel.add(ipLabel, BorderLayout.SOUTH);
        centerPanel.add(IPTextField, BorderLayout.CENTER);
        centerPanel.add(clientNameLabel, BorderLayout.SOUTH);
        centerPanel.add(clientNameTextField, BorderLayout.CENTER);

        // Agregar panel centro a la ventana
        this.add(centerPanel, BorderLayout.CENTER);

        // Configurar la acción del botón "Aceptar"
        okButton.addActionListener(actionListener);
        okButton.setActionCommand(Actions.OK_BUTTON.toString());

        setVisible(true);
    }

    /**
     * Método para obtener los datos ingresados en el formulario.
     * 
     * @return Un array de String con los datos del cliente: número de puerto,
     *         dirección IP y nombre.
     */
    public String[] rescueData() {
        String[] dataClient = new String[3];
        String port = portTextField.getText();
        String IP = IPTextField.getText();
        String clientName = clientNameTextField.getText();

        // Guardar los datos en el array
        dataClient[0] = port;
        dataClient[1] = IP;
        dataClient[2] = clientName;

        return dataClient;
    }

    /**
     * Método para establecer la visibilidad de la aplicación de la galería.
     * 
     * @param visible Un booleano que indica si la aplicación de la galería debe ser
     *                visible o no.
     */
    public void setGalleryAppVisibility(boolean visible) {
        galleryApp.setFrameVisible(visible);
    }

    /**
     * Método para establecer la visibilidad del formulario.
     * 
     * @param visible Un booleano que indica si el formulario debe ser visible o no.
     */
    public void setFormVisible(boolean visible) {
        setVisible(visible);
    }

    /**
     * Método para mostrar el cuadro de diálogo para seleccionar archivos en la
     * aplicación de la galería.
     */
    public void showFileChooser() {
        galleryApp.showFileChooser();
    }

    public void setFrameVisiblePanelCenter(boolean visible) {
        galleryApp.setFrameVisiblePanelCenter(visible);
    }

    public void displayImages(List<ImageGallery> imageList) {
        galleryApp.displayImages(imageList);
    }

}
