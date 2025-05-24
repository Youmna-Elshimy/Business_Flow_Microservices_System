package csci318.demo.model;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
public class Contact extends org.springframework.data.domain.AbstractAggregateRoot<Customer> {

    private ContactID contactID; // Aggregate Identifier

    @Embedded
    private ContactValueObject contactValueObject;

    public Contact() {

    }

    public Contact(long id, String name, String phone, String email, String positoin) {
        this.contactID = new ContactID(id);
        this.contactValueObject = new ContactValueObject(name, phone, email, positoin);
    }


    public ContactID getContactID() {
        return contactID;
    }

    public void setContactID(ContactID contactID) {
        this.contactID = contactID;
    }

    public ContactValueObject getContactValueObject() {
        return contactValueObject;
    }

    public void setContactValueObject(ContactValueObject contactValueObject) {
        this.contactValueObject = contactValueObject;
    }

}
