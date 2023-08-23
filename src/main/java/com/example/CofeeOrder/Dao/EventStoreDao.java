package com.example.CofeeOrder.Dao;

import com.example.CofeeOrder.model.Event.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventStoreDao extends JpaRepository<Event, Long> {


    //    Order findOrder(int id);
    List<Event> findEventByOrderIdOrderById(long orderId);

    Event findFirstByOrderIdOrderByIdDesc(long orderId);
}
