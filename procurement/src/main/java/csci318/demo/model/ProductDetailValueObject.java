package csci318.demo.model;

import javax.persistence.Embeddable;
@Embeddable
public class ProductDetailValueObject {
    private String comment;
    private String description;

    public ProductDetailValueObject() {
    }

    public ProductDetailValueObject(String comment, String description) {
        this.comment = comment;
        this.description = description;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getComment() {
        return comment;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductDetailValueObject that = (ProductDetailValueObject) o;
        if (that.comment.equals(comment) && that.description.equals(description)) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = comment != null ? comment.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }


}
