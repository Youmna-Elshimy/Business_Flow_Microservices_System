package csci318.demo.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ContactID implements Serializable {
    @Column(name = "contactId")
    private long contactId;

    public ContactID() {
    }

    public ContactID(long contactId) {
        this.contactId = contactId;
    }

    public long getContactIdId() {
        return contactId;
    }

    public void setContactId(long contactId) {
        this.contactId = contactId;
    }
}


