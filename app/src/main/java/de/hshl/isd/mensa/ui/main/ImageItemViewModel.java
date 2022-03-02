package de.hshl.isd.mensa.ui.main;

class ImageItemViewModel extends ItemViewModel {

    private String image;
    private String detail;

    public ImageItemViewModel(String text) {
        super(text);
    }

    public ImageItemViewModel(String text, String image, String detail) {
        super(text);
        this.image = image;
        this.detail = detail;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
