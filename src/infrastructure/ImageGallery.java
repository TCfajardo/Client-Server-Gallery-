package infrastructure;

import java.io.Serializable;

public class ImageGallery implements Serializable {

    private byte[] imageBytes;

    public byte[] getImageBytes() {
        return imageBytes;
    }

    public void setImageBytes(byte[] imageBytes) {
        this.imageBytes = imageBytes;
    }
}
