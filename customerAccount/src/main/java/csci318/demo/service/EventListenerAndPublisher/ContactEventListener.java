package csci318.demo.service.EventListenerAndPublisher;
import csci318.demo.model.CustomerEvent.ContactEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component("contactEventListener")
public class ContactEventListener implements ApplicationListener<ContactEvent> {
    @EventListener
    @Override
    public void onApplicationEvent(ContactEvent event) {
        System.out.println("Received spring custom event - " + event.getMessage());
        System.out.println("Contact Event Listener");
    }
}

