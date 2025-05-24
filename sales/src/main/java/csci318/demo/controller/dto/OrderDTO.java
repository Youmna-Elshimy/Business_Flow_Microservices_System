package csci318.demo.controller.dto;

public class OrderDTO {
    private long id;
    private long productId;
    private String supplier;
    private int quantity;
    private String status;
    private long customerId;

    public void setId(Long id) {
        this.id = id;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "status='" + status + '\'' +
                ", customer id=" + customerId + '\'' +
                ", supplier='" + supplier + '\'' +
                ", product id=" + productId + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}