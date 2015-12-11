package music.business;

import java.io.File;
import java.text.NumberFormat;
import java.io.Serializable;

public class Product implements Serializable {

    private String code;
    private String description;
    private double price;
//    private String imageURL;

    public Product() {
        code = "";
        description = "";
        price = 0;
//        imageURL = "";
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getArtistName() {
        String artistName = description.substring(0, description.indexOf(" - "));
        return artistName;
    }

    public String getAlbumName() {
        String albumName = description.substring(description.indexOf(" - ") + 3);
        return albumName;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public String getPriceCurrencyFormat() {
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        return currency.format(price);
    }

    public String getImageURL() {
        String imageURL = "/yuliana/images/" + code + "_cover.jpg";
            return imageURL;


    }

    public String getProductType() {
        return "Audio CD";
    }
}
