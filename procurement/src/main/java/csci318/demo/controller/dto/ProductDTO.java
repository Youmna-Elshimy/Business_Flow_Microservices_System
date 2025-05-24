package csci318.demo.controller.dto;

import csci318.demo.model.ProductDetail;

import javax.persistence.PrimaryKeyJoinColumn;

public class ProductDTO {
    private String productCategory;
    private String name;
    private double price;
    private int stock;
    private long productId;
    @PrimaryKeyJoinColumn
    private ProductDetail productDetails;

    @Override
    public String toString() {
        return "ProductForm{" +
                "productCategory='" + productCategory + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    public long getId() {
        return productId;
    }

    public void setId(long productId) {
        this.productId = productId;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public ProductDetail getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(ProductDetail productDetails) {
        this.productDetails = productDetails;
    }
}
