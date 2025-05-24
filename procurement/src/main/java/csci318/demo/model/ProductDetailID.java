package csci318.demo.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class ProductDetailID implements Serializable {
    @Column(name = "productDetailId")
    private long productDetailId;

    public ProductDetailID() {
    }


    public ProductDetailID(long productDetailId) {
        this.productDetailId = productDetailId;
    }

    public long getProductDetailId() {
        return productDetailId;
    }

    public void setProductDetailId(long productDetailId) {
        this.productDetailId = productDetailId;
    }
}
