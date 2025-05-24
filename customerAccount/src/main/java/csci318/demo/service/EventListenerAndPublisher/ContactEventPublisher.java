package csci318.demo.service.EventListenerAndPublisher;

import csci318.demo.model.CustomerEvent.ContactEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class ContactEventPublisher {
   @Autowired
   @Lazy
   private ContactEventPublisher ContactEventPublisher;

   public void publishContactEvent(final String message){
       System.out.println("Contact Event Published!");
       ContactEvent ContactEvent = new ContactEvent(this, message);
       ContactEventPublisher.publishContactEvent("Contact Event Published!");
       System.out.println("Contact Event Publisher");
   }
}
