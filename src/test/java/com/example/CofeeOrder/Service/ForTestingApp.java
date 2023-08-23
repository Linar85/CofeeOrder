//package com.example.CofeeOrder.Service;
//
//import com.example.CofeeOrder.Dao.EventStoreDao;
//import com.example.CofeeOrder.model.Event.*;
//import com.example.CofeeOrder.model.EventType;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//@SpringBootTest
//class ForTestingApp {
//
//    @Autowired
//    EventServiceImpl eventService;
//
//    @Autowired
//    EventStoreDao eventStoreDao;
//
//    OrderRegisteredEvent registeredEvent = new OrderRegisteredEvent();
//    OrderCancelledEvent cancelledEvent = new OrderCancelledEvent();
//    OrderProcessedEvent processedEvent = new OrderProcessedEvent();
//    OrderIsReadyEvent isReadyEvent = new OrderIsReadyEvent();
//    OrderDeliveredEvent deliveredEvent = new OrderDeliveredEvent();
//
//    @BeforeEach
//    void init() {
//
//        registeredEvent.setOrderId(3L);
//        registeredEvent.setCreatedDate(LocalDateTime.now().withNano(0));
//        registeredEvent.setEmployeeId(2L);
//        registeredEvent.setClientId(3L);
//        registeredEvent.setProductId(5L);
//        registeredEvent.setExpectedDeliveryTime(LocalDateTime.now().withNano(0).plusDays(1L));
//        registeredEvent.setProductPrice(500d);
//        registeredEvent.setEventType(EventType.Values.REGISTERED);
//
//        processedEvent.setOrderId(3L);
//        processedEvent.setCreatedDate(LocalDateTime.now());
//        processedEvent.setEmployeeId(5L);
//
//        isReadyEvent.setOrderId(3L);
//        isReadyEvent.setCreatedDate(LocalDateTime.now());
//        isReadyEvent.setEmployeeId(5L);
//
//        deliveredEvent.setOrderId(3L);
//        deliveredEvent.setCreatedDate(LocalDateTime.now());
//        deliveredEvent.setEmployeeId(5L);
//
//        cancelledEvent.setOrderId(3L);
//        cancelledEvent.setCreatedDate(LocalDateTime.now());
//        cancelledEvent.setRejectionReason("very big cost");
//        cancelledEvent.setEmployeeId(5L);
//
//    }
//
//    @Test
//    void recordEvent() {
//        eventService.recordEvent(registeredEvent);
//        eventService.recordEvent(processedEvent);
//        eventService.recordEvent(isReadyEvent);
//        eventService.recordEvent(deliveredEvent);
//
//    }
//
//    @Test
//    void isReadyEvent() {
//        eventService.recordEvent(isReadyEvent);
//    }
//
//    @Test
//    void deliveredEvent() {
//        eventService.recordEvent(deliveredEvent);
//    }
//
//    @Test
//    void cancelEvent() {
//        eventService.recordEvent(cancelledEvent);
//    }
//
//    @Test
//    void registerEvent() {
//        eventService.recordEvent(registeredEvent);
//    }
//
//    @Test
//    void processEvent() {
//        eventService.recordEvent(processedEvent);
//    }
//
//    @Test
//    void getEvents() {
//        List<Event> events = eventStoreDao.findEventByOrderIdOrderById(1L);
//    }
//
//    @Test
//    void getLastEventById() {
//        var event = eventStoreDao.findFirstByOrderIdOrderByIdDesc(1);
//    }
//
//
//    @Test
//    void getOrder(){
//
//    }
//}