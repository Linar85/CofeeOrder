package com.example.CofeeOrder.Service;

import com.example.CofeeOrder.Dao.EventStoreDao;
import com.example.CofeeOrder.exceptions.OrderIsCancelledOrDeliveredException;
import com.example.CofeeOrder.exceptions.RegisteredOrderIsNotFoundException;
import com.example.CofeeOrder.model.Event.*;
import com.example.CofeeOrder.model.EventType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
class EventServiceImplTest {

    @InjectMocks
    private EventServiceImpl eventService;

    @Mock
    private EventStoreDao eventStore;

    OrderRegisteredEvent registeredEvent = new OrderRegisteredEvent();
    OrderCancelledEvent cancelledEvent = new OrderCancelledEvent();
    OrderProcessedEvent processedEvent = new OrderProcessedEvent();
    OrderIsReadyEvent isReadyEvent = new OrderIsReadyEvent();
    OrderDeliveredEvent deliveredEvent = new OrderDeliveredEvent();

    @BeforeEach
    void init() {
        registeredEvent.setOrderId(1L);
        registeredEvent.setCreatedDate(LocalDateTime.now().withNano(0));
        registeredEvent.setEmployeeId(2L);
        registeredEvent.setClientId(3L);
        registeredEvent.setProductId(5L);
        registeredEvent.setExpectedDeliveryTime(LocalDateTime.now().withNano(0).plusDays(1L));
        registeredEvent.setProductPrice(500d);
        registeredEvent.setEventType(EventType.Values.REGISTERED);

        cancelledEvent.setOrderId(1L);
        cancelledEvent.setCreatedDate(LocalDateTime.now());
        cancelledEvent.setRejectionReason("very big cost");
        cancelledEvent.setEmployeeId(5L);
        cancelledEvent.setEventType(EventType.Values.CANCELED);

        processedEvent.setOrderId(1L);
        processedEvent.setCreatedDate(LocalDateTime.now());
        processedEvent.setEmployeeId(5L);

        isReadyEvent.setOrderId(1L);
        isReadyEvent.setCreatedDate(LocalDateTime.now());
        isReadyEvent.setEmployeeId(5L);

        deliveredEvent.setOrderId(1L);
        deliveredEvent.setCreatedDate(LocalDateTime.now());
        deliveredEvent.setEmployeeId(5L);


    }

    @Test
    void registerNewEvent() {

        lenient().doReturn(null).when(eventStore).findFirstByOrderIdOrderByIdDesc(1L);
        lenient().doReturn(new ArrayList<>()).when(eventStore).findEventByOrderIdOrderById(1L);
        eventService.recordEvent(registeredEvent);

        Mockito.verify(eventStore).save(any());
    }

    @Test
    void eventAfterCanceled() {

        List<Event> actualEventsList = Arrays.asList(cancelledEvent, registeredEvent);
        lenient().doReturn(cancelledEvent).when(eventStore).findFirstByOrderIdOrderByIdDesc(1L);
        lenient().doReturn(actualEventsList).when(eventStore).findEventByOrderIdOrderById(1L);

        Assertions.assertThrows(OrderIsCancelledOrDeliveredException.class, () -> eventService.recordEvent(cancelledEvent));
    }

    @Test
    void eventAfterDelivered() {

        List<Event> actualEventsList = Arrays.asList(deliveredEvent, registeredEvent);
        lenient().doReturn(deliveredEvent).when(eventStore).findFirstByOrderIdOrderByIdDesc(1L);
        lenient().doReturn(actualEventsList).when(eventStore).findEventByOrderIdOrderById(1L);

        Assertions.assertThrows(OrderIsCancelledOrDeliveredException.class, () -> eventService.recordEvent(isReadyEvent));
    }

    @Test
    void eventWithoutRegister() {

        lenient().doReturn(processedEvent).when(eventStore).findFirstByOrderIdOrderByIdDesc(1L);
        lenient().doReturn(new ArrayList<>()).when(eventStore).findEventByOrderIdOrderById(1L);

        Assertions.assertThrows(RegisteredOrderIsNotFoundException.class, () -> eventService.recordEvent(processedEvent));
    }

    @Test
    void addEventSuccess() {

        List<Event> actualEventsList = Arrays.asList(registeredEvent);
        lenient().doReturn(registeredEvent).when(eventStore).findFirstByOrderIdOrderByIdDesc(1L);
        lenient().doReturn(actualEventsList).when(eventStore).findEventByOrderIdOrderById(1L);
        eventService.recordEvent(isReadyEvent);

        Mockito.verify(eventStore).save(any());
    }
}