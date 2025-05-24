package csci318.demo.model;

import csci318.demo.model.productEvent.ProductEvent;
import org.springframework.data.domain.AbstractAggregateRoot;

import javax.persistence.*;

@Entity(name = "productEntity")
public class Product extends AbstractAggregateRoot<Product> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String productCategory;
    private String name;
    private double price;
    private int stock;
    @PrimaryKeyJoinColumn
    private ProductDetail productDetails;

    public Product() {
    }

    public Product(String productCategory, String name, double price, int stock) {
        this.productCategory = productCategory;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public void stock(long productId) {
        ProductEvent productEvent = new ProductEvent();
        productEvent.setName(this.getName());
        productEvent.setProductId(productId);
        productEvent.setProductCategory(this.getProductCategory());
        productEvent.setProductDetails(this.getProductDetails());
        productEvent.setStock(this.getStock());
        productEvent.setPrice(this.getPrice());
        registerEvent(productEvent);
    }
}
