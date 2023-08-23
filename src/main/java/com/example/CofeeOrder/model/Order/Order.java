package com.example.CofeeOrder.model.Order;

import com.example.CofeeOrder.model.Event.Event;
import lombok.Data;

import java.util.List;

@Data
public class Order {

    private Event currentEvent;
    private List<Event> eventList;
}
