package com.example.CofeeOrder.Service;

import com.example.CofeeOrder.Dao.EventStoreDao;
import com.example.CofeeOrder.exceptions.OrderIsCancelledOrDeliveredException;
import com.example.CofeeOrder.exceptions.RegisteredOrderIsNotFoundException;
import com.example.CofeeOrder.model.Event.Event;
import com.example.CofeeOrder.model.Event.OrderRegisteredEvent;
import com.example.CofeeOrder.model.EventType;
import com.example.CofeeOrder.model.Order.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventStoreDao eventStore;

    public EventServiceImpl(EventStoreDao eventStore) {
        this.eventStore = eventStore;
    }

    @Override
    public void recordEvent(Event event) {

        var eventsList = eventStore.findEventByOrderIdOrderById(event.getOrderId());
        var currentEvent = eventStore.findFirstByOrderIdOrderByIdDesc(event.getOrderId());

        if (currentEvent != null &&
                (currentEvent.getEventType().equals(EventType.Values.CANCELED) ||
                        currentEvent.getEventType().equals(EventType.Values.DELIVERED))) {
            throw new OrderIsCancelledOrDeliveredException("Your order has been delivered or canceled");
        }

        if (eventsList.isEmpty() && event.getEventType().equals(EventType.Values.REGISTERED)) {
            eventStore.save(event);
            return;
        }

        if (!eventsList.isEmpty()) {
            eventStore.save(event);
        } else throw new RegisteredOrderIsNotFoundException("Register an order first");
    }

    @Override
    public Order getOrder(int orderId) {

        var currentEvent = eventStore.findFirstByOrderIdOrderByIdDesc(orderId);
        var eventsList = eventStore.findEventByOrderIdOrderById(orderId);
        Order order = new Order();
        order.setCurrentEvent(currentEvent);
        order.setEventList(eventsList);
        return order;
    }
}
