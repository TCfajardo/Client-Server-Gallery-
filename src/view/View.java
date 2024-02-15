package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class View extends JFrame {

    private JButton uploadButton;
    private JButton downloadButton;
    private JPanel principalPanel;

    public View() {
        this.setTitle("Image Uploader/Downloader");
        this.setSize(500, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBackground(Color.BLUE);

        this.principalPanel = new JPanel();
        this.uploadButton = new JButton();
        this.downloadButton = new JButton();

        this.initComponents();
        this.setVisible(true);
    }

    public void initComponents() {
        principalPanel.setLayout(new BorderLayout());
        uploadButton.setText("Upload image");
        uploadButton.setPreferredSize(new Dimension(100, 50));
        downloadButton.setText("Download images");
        downloadButton.setPreferredSize(new Dimension(100, 50));
        principalPanel.add(uploadButton, BorderLayout.SOUTH);
        principalPanel.add(downloadButton, BorderLayout.SOUTH);

        principalPanel.setVisible(true);
        this.add(principalPanel);

    }

    public static void main(String[] args) {
        new View();
    }

}
