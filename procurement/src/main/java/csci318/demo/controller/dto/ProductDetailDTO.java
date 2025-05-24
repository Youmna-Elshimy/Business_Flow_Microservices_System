package csci318.demo.controller.dto;

public class ProductDetailDTO {
    private String description;
    private String comment;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "FormBackingProductDetail{" +
                "description='" + description + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
