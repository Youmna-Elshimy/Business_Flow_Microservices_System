package csci318.demo.model.CustomerEvent;

import org.springframework.context.ApplicationEvent;

import csci318.demo.model.Customer;
import csci318.demo.repository.CustomerRepository;

import java.util.*;

import static java.util.Arrays.stream;

public class ContactEvent extends ApplicationEvent {
    private CustomerRepository customerRepository;
    private String eventMessage;

    public ContactEvent(Object source, String eventMessage) {
        super(source);
        this.eventMessage = eventMessage;
        System.out.println("Contact Event");
    }

    public String getMessage() {
        return eventMessage;
    }

    public static List<Customer> eventSortByCountryAsc(List<Customer> unsortedCustomerList) {
        unsortedCustomerList.sort(Comparator.comparing(Customer::getCountry));
        List<Customer> sortedList = new ArrayList<Customer>();
        for (Customer cus : unsortedCustomerList) {
            sortedList.add(cus);
        }
        return sortedList;
    }
}
