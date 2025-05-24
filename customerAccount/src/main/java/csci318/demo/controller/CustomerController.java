package csci318.demo.controller;

import csci318.demo.controller.dto.ContactDTO;
import csci318.demo.controller.dto.CustomerDTO;
import csci318.demo.model.Contact;
import csci318.demo.model.Customer;
import csci318.demo.model.CustomerEvent.ContactEvent;
import csci318.demo.service.CustomerService;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerController {
    private CustomerService cusService;

    @Autowired
    public CustomerController(CustomerService cusService) {
        this.cusService = cusService;
    }

    @PostMapping("/customer")
    ResponseEntity<String> createCustomer(@RequestBody CustomerDTO customerDTO) {
        String companyName = customerDTO.getCompanyName();
        String address = customerDTO.getAddress();
        String country = customerDTO.getCountry();
        cusService.createCustomer(companyName, address, country);
        return new ResponseEntity<>("new customer created -->  company Name:" + companyName + ",address: " + address + ",country: " + country, HttpStatus.OK);
    }

    @PutMapping("/customer/{id}")
    ResponseEntity<String> updateCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
        Customer customer = cusService.findCus(id);
        if (customer != null) {
            String oldName = customer.getCompanyName();
            String companyName = customerDTO.getCompanyName();
            String address = customerDTO.getAddress();
            String country = customerDTO.getCountry();
            cusService.updateCustomer(id, companyName, address, country);
            return new ResponseEntity<>("updated customer:" + oldName + " --> " + companyName + ",address: " + address + ",country: " + country, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/customer/{id}")
    ResponseEntity getCustomer(@PathVariable Long id) {
        Customer customer = cusService.findCus(id);
        if (customer != null) {
            return new ResponseEntity(customer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/customer/{id}/contact")
    ResponseEntity<String> createContactDetail(@PathVariable Long id, @RequestBody ContactDTO contactDTO) {
        Customer customer = cusService.findCus(id);
        if (customer.getContact() != null) {
            return new ResponseEntity<>("Customer " + customer.getContact().getContactValueObject().getName() + "(" + customer.getId() + ")" + " already has created details", HttpStatus.BAD_REQUEST);
        } else {
            Contact con = new Contact(id, contactDTO.getName(), contactDTO.getPhone(), contactDTO.getEmail(), contactDTO.getPosition());
            customer.setContact(con);
            cusService.save(customer);
            return new ResponseEntity<>("Contact created for " + con.getContactValueObject().getName() + "(" + customer.getId() + ") --> phone: " + contactDTO.getPhone() + ",email: " + contactDTO.getEmail() + ",position: " + contactDTO.getPosition(), HttpStatus.OK);
        }
    }

    @PutMapping("/customer/{id}/contact")
    ResponseEntity<String> updateContactDetail(@PathVariable Long id, @RequestBody ContactDTO contactDTO) {
        Customer customer = cusService.findCus(id);
        Contact contact = customer.getContact();
        if (contact != null) {
            cusService.updateContact(id, contactDTO.getName(), contactDTO.getPhone(), contactDTO.getEmail(), contactDTO.getPosition());
            cusService.save(customer);
            return new ResponseEntity<>("Contact updated -->" + "updated name:" + contactDTO.getName() + ",phone: " + contactDTO.getPhone() + ",email: " + contactDTO.getEmail() + ",position: " + contactDTO.getPosition(), HttpStatus.OK);

        } else {
            return new ResponseEntity<>("Customer does not have details to update", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/customer/{id}/contact")
    ResponseEntity<Customer> getContactDetail(@PathVariable Long id) {
        Customer customer = cusService.findCus(id);
        if (customer.getContact() != null) {
            return new ResponseEntity<Customer>(customer, HttpStatus.OK);
        } else {
            return new ResponseEntity<Customer>(customer, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/customers")
    List<Customer> allCustomers() {
        return cusService.getAllCustomers()
                .stream()
                .map(customer -> {
                    Customer cus1 = new Customer();
                    cus1.setAddress(customer.getAddress());
                    cus1.setCompanyName(customer.getCompanyName());
                    cus1.setCountry(customer.getCountry());
                    cus1.setId(customer.getId());
                    cus1.setContact(customer.getContact());
                    return cus1;
                }).collect(Collectors.toList());
    }

    @GetMapping("/customers/sort")
    List<Customer> sortByCountryEVENT() {
        ContactEvent contactEvent = new ContactEvent(this, "woogoo");
        List<Customer> cus = allCustomers();
        return ContactEvent.eventSortByCountryAsc(cus);
    }
}
