package csci318.demo.model.productEvent;

import csci318.demo.model.ProductDetail;

import javax.persistence.PrimaryKeyJoinColumn;

public class ProductEvent {
    private String productCategory;
    private String name;
    private double price;
    private int stock;
    private long productId;
    @PrimaryKeyJoinColumn
    private ProductDetail productDetails;

    public ProductEvent() {
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
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

    public ProductDetail getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(ProductDetail productDetails) {
        this.productDetails = productDetails;
    }
    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
