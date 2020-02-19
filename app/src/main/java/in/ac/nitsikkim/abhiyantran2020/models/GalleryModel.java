package in.ac.nitsikkim.abhiyantran2020.models;

public class GalleryModel {

    private String galleryImage;
    private String galleryInfo;

    public GalleryModel(String galleryImage, String galleryInfo) {
        this.galleryImage = galleryImage;
        this.galleryInfo = galleryInfo;
    }

    public String getGalleryImage() {
        return galleryImage;
    }

    public String getGalleryInfo() {
        return galleryInfo;
    }
}
