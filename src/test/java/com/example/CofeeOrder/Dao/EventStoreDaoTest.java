package com.example.CofeeOrder.Dao;

import com.example.CofeeOrder.model.Event.*;
import com.example.CofeeOrder.model.EventType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@DataJpaTest
@ExtendWith(SpringExtension.class)
class EventStoreDaoTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private EventStoreDao eventStore;

    OrderRegisteredEvent registeredEvent = new OrderRegisteredEvent();
    OrderCancelledEvent cancelledEvent = new OrderCancelledEvent();
    OrderProcessedEvent processedEvent = new OrderProcessedEvent();
    OrderIsReadyEvent isReadyEvent = new OrderIsReadyEvent();
    OrderDeliveredEvent deliveredEvent = new OrderDeliveredEvent();


    @Test
    public void findAllEventsByOrder() {

        cancelledEvent.setOrderId(2L);
        cancelledEvent.setCreatedDate(LocalDateTime.now());
        cancelledEvent.setRejectionReason("very big cost");
        cancelledEvent.setEmployeeId(5L);
        entityManager.persist(cancelledEvent);
        entityManager.flush();

        Event foundEvent = eventStore.findFirstByOrderIdOrderByIdDesc(cancelledEvent.getOrderId());

        Assertions.assertEquals(foundEvent.getOrderId(), cancelledEvent.getOrderId());
    }

    @Test
    public void findFirstByOrderId() {

        registeredEvent.setOrderId(3L);
        registeredEvent.setCreatedDate(LocalDateTime.now().withNano(0));
        registeredEvent.setEmployeeId(2L);
        registeredEvent.setClientId(3L);
        registeredEvent.setProductId(5L);
        registeredEvent.setExpectedDeliveryTime(LocalDateTime.now().withNano(0).plusDays(1L));
        registeredEvent.setProductPrice(500d);
        registeredEvent.setEventType(EventType.Values.REGISTERED);

        processedEvent.setOrderId(3L);
        processedEvent.setCreatedDate(LocalDateTime.now());
        processedEvent.setEmployeeId(5L);

        isReadyEvent.setOrderId(3L);
        isReadyEvent.setCreatedDate(LocalDateTime.now());
        isReadyEvent.setEmployeeId(5L);

        deliveredEvent.setOrderId(3L);
        deliveredEvent.setCreatedDate(LocalDateTime.now());
        deliveredEvent.setEmployeeId(5L);

        cancelledEvent.setOrderId(3L);
        cancelledEvent.setCreatedDate(LocalDateTime.now());
        cancelledEvent.setRejectionReason("very big cost");
        cancelledEvent.setEmployeeId(5L);

        entityManager.persist(registeredEvent);
        entityManager.persist(processedEvent);
        entityManager.persist(isReadyEvent);
        entityManager.persist(deliveredEvent);
        entityManager.persist(cancelledEvent);
        entityManager.flush();

        List<Event> actualEventsList =
                Arrays.asList(registeredEvent, processedEvent, isReadyEvent, deliveredEvent, cancelledEvent);


        var expectedEventsList = eventStore.findEventByOrderIdOrderById(3L);

        Assertions.assertEquals(expectedEventsList, actualEventsList);


    }


}