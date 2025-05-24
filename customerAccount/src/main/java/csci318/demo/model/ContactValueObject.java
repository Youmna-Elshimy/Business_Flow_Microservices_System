package csci318.demo.model;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class ContactValueObject {
    private String name; //value objects
    private String phone; //value objects
    private String email; //value objects
    private String position; //value objects

    public ContactValueObject() {
    }
    public ContactValueObject(String name, String phone, String email, String position) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactValueObject that = (ContactValueObject) o;

        if (!Objects.equals(name, that.name)) return false;
        if (!Objects.equals(phone, that.phone)) return false;
        if (!Objects.equals(email, that.email)) return false;
        return Objects.equals(position, that.position);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (position != null ? position.hashCode() : 0);
        return result;
    }
}
