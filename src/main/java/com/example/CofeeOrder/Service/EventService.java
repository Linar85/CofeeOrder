package com.example.CofeeOrder.Service;


import com.example.CofeeOrder.model.Event.Event;
import com.example.CofeeOrder.model.Order.Order;

public interface EventService {

    void recordEvent(Event event);

    Order getOrder(int id);
}
