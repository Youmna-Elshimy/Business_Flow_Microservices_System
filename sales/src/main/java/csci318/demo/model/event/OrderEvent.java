package csci318.demo.model.event;

public class OrderEvent {
    private long productId;
    private String supplier;
    private int quantity;
    private long orderId;
    private String status;
    private long customerId;

    public OrderEvent() {
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
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
        return "OrderEvent{" +
                ", customer id=" + customerId + '\'' +
                ", status='" + status + '\'' +
                ", supplier='" + supplier + '\'' +
                ", product id=" + productId + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}