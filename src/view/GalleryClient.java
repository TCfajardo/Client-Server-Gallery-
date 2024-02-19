package view;

import java.awt.Color;

import javax.swing.JPanel;

public class GalleryClient extends JPanel {

    public GalleryClient() {
        setBackground(Color.RED);
    }

    public void setFrameVisible(boolean visible) {
        this.setVisible(visible);
    }

}