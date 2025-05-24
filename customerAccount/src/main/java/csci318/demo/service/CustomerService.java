package csci318.demo.service;

import csci318.demo.model.Contact;
import csci318.demo.model.Customer;
import csci318.demo.repository.CustomerRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private final CustomerRepository cusRepo;

    public CustomerService(CustomerRepository cusRepo) {
        this.cusRepo = cusRepo;
    }

    //finding customer
    public Customer findCus(long id) {
        Optional<Customer> customer = cusRepo.findById(id);
        if (customer.isPresent()) {
            return customer.get();
        }
        return null;
    }

    //saving customer
    public void save(Customer cus) {
        cusRepo.save(cus);
    }

    //creating customer
    public long createCustomer(String companyName, String address, String country) {

        Customer cus = new Customer(companyName, address, country);

        cusRepo.save(cus);

        return cus.getId();
    }

    //updating customer
    public void updateCustomer(long id, String companyName, String address, String country) {
        Customer cus = cusRepo.findById(id).get();
        cus.setAddress(address);
        cus.setCompanyName(companyName);
        cus.setCountry(country);
        cusRepo.save(cus);
    }

    //finding contact
    public Contact findContact(long id) {
        if (cusRepo.existsById(id)) {
            return cusRepo.findById(id).get().getContact();
        } else {
            return null;
        }
    }

    //creating contact
    public long createContact(long id, String name, String phone, String email, String position) {

        Contact contact = new Contact(id, name, phone, email, position);

        Customer cus = findCus(id);

        cus.setContact(contact);

        cusRepo.save(cus);

        return cus.getId();
    }

    //updating contact
    public void updateContact(Long contactId, String name, String phone, String email, String position) {
        Optional<Customer> customerOptional = cusRepo.findById(contactId);
        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            Contact contact = customerOptional.get().getContact();

            contact.getContactValueObject().setName(name);
            contact.getContactValueObject().setPhone(phone);
            contact.getContactValueObject().setEmail(email);
            contact.getContactValueObject().setPosition(position);
            cusRepo.save(customer);
        }
    }

    public List<Customer> getAllCustomers() {
        return cusRepo.findAll();
    }

}
