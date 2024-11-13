/**
 * Author : Sahana Baggaon Gajendra
 * email : sahanabaggaongajendra@gmail.com
 */



package ds.edu.makeupsearchapp;

/**
 * Represents a product in the makeup search application.
 * Each product includes details such as its name, price,
 * image link, and product link.
 */

public class Product {
    private String name;
    private String price;
    private String image_link;
    private String product_link;

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getImage_link() {
        return image_link;
    }

    public String getProduct_link() {
        return product_link;
    }
}
