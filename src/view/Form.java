package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import presenter.Actions;

public class Form extends JFrame {
    private JTextField portTextField;
    private JTextField IPTextField;
    private JTextField clientNameTextField;
    private JPanel southPanel, centerPanel, northPanel;
    private GalleryApp galleryApp;

    public Form(ActionListener actionListener) {
        super("Formulario de inicio");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 300);
        setLocation(500, 100);
        galleryApp = new GalleryApp(actionListener);
        setLayout(new BorderLayout());
        this.setBackground(Color.WHITE);

        northPanel = new JPanel();
        northPanel.setBackground(Color.BLACK);
        northPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 5));
        JLabel tittleApp = new JLabel("BIENVENIDO!");
        tittleApp.setForeground(Color.WHITE);
        northPanel.add(tittleApp);
        this.add(northPanel, BorderLayout.NORTH);

        GridLayout gridLayout = new GridLayout(3, 2);
        gridLayout.setVgap(50);
        gridLayout.setHgap(0);

        centerPanel = new JPanel();
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setLayout(gridLayout);

        // Crear componentes del formulario
        JLabel portLabel = new JLabel("Número del puerto:");
        portTextField = new JTextField(10);
        portTextField.setMinimumSize(new Dimension(100, 50));

        JLabel ipLabel = new JLabel("Dirección IP:");
        IPTextField = new JTextField(15);
        IPTextField.setMinimumSize(new Dimension(100, 50));

        JLabel clientNameLabel = new JLabel("Nombre del Cliente:");
        clientNameTextField = new JTextField(20);
        clientNameTextField.setMinimumSize(new Dimension(100, 50));

        southPanel = new JPanel();
        southPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 5));
        southPanel.setBackground(Color.BLACK);
        JButton okButton = new JButton("Aceptar");
        okButton.setPreferredSize(new Dimension(100, 20));
        okButton.setBackground(Color.decode("#A3FED9"));
        southPanel.add(okButton);
        this.add(southPanel, BorderLayout.SOUTH);

        centerPanel.add(portLabel, BorderLayout.NORTH);
        centerPanel.add(portTextField, BorderLayout.CENTER);
        centerPanel.add(ipLabel, BorderLayout.SOUTH);
        centerPanel.add(IPTextField, BorderLayout.CENTER);
        centerPanel.add(clientNameLabel, BorderLayout.SOUTH);
        centerPanel.add(clientNameTextField, BorderLayout.CENTER);

        this.add(centerPanel, BorderLayout.CENTER);
        // Configurar la acción del botón "Aceptar"
        okButton.addActionListener(actionListener);
        okButton.setActionCommand(Actions.OK_BUTTON.toString());
        setVisible(true);
    }

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

    public void setGalleryAppVisibility(boolean visible) {
        galleryApp.setFrameVisible(visible);
    }

    public void setFormVisible(boolean visible) {
        setVisible(visible);
    }

    public void showFileChooser() {
        galleryApp.showFileChooser();
    }

}
